package com.peaka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties(value = "peaka")
@Primary
public class ConfigProperties {
    String partnerApiKey;

    public String getPartnerApiKey() {
        return partnerApiKey;
    }

    public void setPartnerApiKey(String partnerApiKey) {
        this.partnerApiKey = partnerApiKey;
    }
}




