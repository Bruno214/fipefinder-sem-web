package br.com.alura.fipefinder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDados implements IConverteDados{
    private ObjectMapper mapper;


    @Override
    public <T> T obterDados(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter json na classe " + e.getMessage());
        }
    }
}
