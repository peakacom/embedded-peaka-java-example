package com.peaka.service;

import com.peaka.config.ConfigProperties;
import com.peaka.model.ConnectDTO;
import com.peaka.model.CreatePeakaProjectRequestDTO;
import com.peaka.model.PeakaAPIKeyDTO;
import com.peaka.model.PeakaProjectDTO;
import com.peaka.model.SupportedDriversDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class PeakaService {
    private static final String  PEAKA_PARTNER_API_BASE_URL= "https://partner.peaka.studio/api/v1";
    Logger logger = LoggerFactory.getLogger(PeakaService.class);

    @Autowired
    private ConfigProperties properties;

    public PeakaProjectDTO createPeakaProject() {
        String projectName = RandomStringUtils.randomAlphabetic(10);
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = new URI(PEAKA_PARTNER_API_BASE_URL +"/projects");

            HttpEntity<CreatePeakaProjectRequestDTO> request = new
                    HttpEntity<>(new CreatePeakaProjectRequestDTO(projectName),
                    getPartnerHttpHeaders());

            ResponseEntity<PeakaProjectDTO> result = restTemplate.postForEntity(uri, request, PeakaProjectDTO.class);
            return result.getBody();
        } catch (Exception e) {
            logger.error("Something went wrong creating project", e);
            return null;
        }
    }

    public PeakaAPIKeyDTO createPeakaAPIKey(String projectName, String projectId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = new URI(PEAKA_PARTNER_API_BASE_URL +"/projects/"+ projectId +"/apiKeys");

            HttpEntity<CreatePeakaProjectRequestDTO> request = new
                    HttpEntity<>(new CreatePeakaProjectRequestDTO(projectName),
                    getPartnerHttpHeaders());

            ResponseEntity<PeakaAPIKeyDTO> result = restTemplate.postForEntity(uri, request, PeakaAPIKeyDTO.class);
            return result.getBody();
        } catch (Exception e) {
            logger.error("Something went wrong creating api key", e);
            return null;
        }
    }

    public ConnectDTO connect(String projectAPIKey) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = new URI(PEAKA_PARTNER_API_BASE_URL +"/ui/initSession");

            HttpEntity<Void> request = new
                    HttpEntity<>(
                    getProjectHttpHeaders(projectAPIKey));

            ResponseEntity<ConnectDTO> result = restTemplate.exchange(uri, HttpMethod.GET, request, ConnectDTO.class);
            return result.getBody();
        } catch (Exception e) {
            logger.error("Something went wrong connecting", e);
            return null;
        }
    }

    public SupportedDriversDTO getJDBCURL(String projectAPIKey) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = new URI(PEAKA_PARTNER_API_BASE_URL +"/supportedDrivers/jdbc");

            HttpEntity<Void> request = new
                    HttpEntity<>(
                    getProjectHttpHeaders(projectAPIKey));

            ResponseEntity<SupportedDriversDTO> result =
                    restTemplate.exchange(uri, HttpMethod.GET, request, SupportedDriversDTO.class);
            return result.getBody();
        } catch (Exception e) {
            logger.error("Something went wrong connecting", e);
            return null;
        }
    }

    private HttpHeaders getProjectHttpHeaders(String projectAPIKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + projectAPIKey);
        return headers;
    }

    private HttpHeaders getPartnerHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + properties.getPartnerApiKey());
        return headers;
    }
}
