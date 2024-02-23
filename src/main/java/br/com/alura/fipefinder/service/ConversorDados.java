package br.com.alura.fipefinder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConversorDados implements IConverteDados{
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter json na classe " + e.getMessage());
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> type) {
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, type);
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter json na classe " + e.getMessage());
        }
    }
}
