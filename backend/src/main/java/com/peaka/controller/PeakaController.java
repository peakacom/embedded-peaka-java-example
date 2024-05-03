package com.peaka.controller;

import com.peaka.model.ConnectDTO;
import com.peaka.model.ConnectRequestDTO;
import com.peaka.model.CreatePeakaProjectDTO;
import com.peaka.model.GetDataRequestDTO;
import com.peaka.model.PeakaAPIKeyDTO;
import com.peaka.model.PeakaProjectDTO;
import com.peaka.model.SupportedDriversDTO;
import com.peaka.service.PeakaService;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/")
public class PeakaController {

    @Autowired
    private PeakaService service;

    @GetMapping("create-peaka-project")
    @CrossOrigin(origins = "http://localhost:5173")
    public CreatePeakaProjectDTO createPeakaProject() {
        // First create the project
        PeakaProjectDTO projectDTO = service.createPeakaProject();

        String projectName = projectDTO.getName();
        String projectId = projectDTO.getId();

        // Then generate api key for the project
        PeakaAPIKeyDTO apiKeyDTO = service.createPeakaAPIKey(projectName, projectId);

         return new CreatePeakaProjectDTO(
                 projectName, projectId, apiKeyDTO.getApiKey());
    }

    @PostMapping("connect")
    @CrossOrigin(origins = "http://localhost:5173")
    public ConnectDTO connect(@RequestBody ConnectRequestDTO requestDTO) {
        return service.connect(requestDTO.apiKey());
    }

    @PostMapping("get-data")
    @CrossOrigin(origins = "http://localhost:5173")
    public Map<String,Object> getData(@RequestBody GetDataRequestDTO requestDTO) {
        //Load the JDBC Driver for Peaka
        try {
            Class.forName("com.peaka.jdbc.PeakaDriver");
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

        Map<String, Object> result =  new HashMap<>();
        try {
            //Get the JDBC driver url from partner api
            SupportedDriversDTO driversDTO = service.getJDBCURL(requestDTO.apiKey());
            Properties properties = new Properties();
            properties.setProperty("SSL", "true");
            Connection connection = DriverManager.getConnection(driversDTO.JDBC(),properties);
            Statement stmt = connection.createStatement();

            Map<String, String> valuesMap = new HashMap<>();
            valuesMap.put("catalog", requestDTO.catalogName());
            valuesMap.put("schema", requestDTO.schemaName());
            valuesMap.put("table", requestDTO.tableName());
            String queryTemplate = "SELECT * FROM \"${catalog}\".\"${schema}\".\"${table}\" ";

            StringSubstitutor sub = new StringSubstitutor(valuesMap);
            String query = sub.replace(queryTemplate);

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // We are fetching first 10 columns
                for(int i=1; i<=10; i++) {
                    result.put(rs.getMetaData().getColumnName(i), rs.getObject(rs.getMetaData().getColumnName(i)));
                }
            }
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
