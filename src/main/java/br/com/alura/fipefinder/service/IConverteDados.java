package br.com.alura.fipefinder.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IConverteDados {

     <T> T obterDados(String json, Class<T> type);

     <T> List<T> obterLista(String json, Class<T> type);
}
