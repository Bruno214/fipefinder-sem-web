package br.com.alura.fipefinder.model;

import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private TipoVeiculo tipoVeiculoEscolhido;

    public Principal() {
        this.tipoVeiculoEscolhido = null;
    }

    public void abrirMenu() {

        System.out.println("**** OPÇÕES ****");
        System.out.println("Carro \nMoto\nCaminhão");

        System.out.print("\nDigite uma das opções para consultar valores: ");
        var tipoVeiculoEscolhidoUsuario = leitura.nextLine();
        verificarTipoVeiculo(tipoVeiculoEscolhidoUsuario);

    }

    public void verificarTipoVeiculo(String tipoVeiculo) {
        switch (tipoVeiculo.toLowerCase()) {
            case "carro" -> this.tipoVeiculoEscolhido = TipoVeiculo.CARRO;
            case "moto" -> this.tipoVeiculoEscolhido = TipoVeiculo.MOTO;
            case "caminhao" -> this.tipoVeiculoEscolhido = TipoVeiculo.CAMINHAO;
            default -> System.out.println("Opção inválida");
        }
    }
}
