package br.com.alura.fipefinder.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> type) throws JsonProcessingException;
}
