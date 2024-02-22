package br.com.alura.fipefinder.model;

public enum TipoVeiculo {
    CARRO ("carros"),
    MOTO ("motos"),
    CAMINHAO ("caminhoes");

    private final String descricao;

    TipoVeiculo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
