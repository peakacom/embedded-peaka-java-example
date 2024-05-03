package com.peaka.model;

public record GetDataRequestDTO(String apiKey, String catalogName, String schemaName, String tableName) {
}
