package br.com.alura.fipefinder.model;

import br.com.alura.fipefinder.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private TipoVeiculo tipoVeiculoEscolhido;
    private final ConsumoAPI consumoAPI;
    private final String enderecoBase;

    public Principal() {
        this.tipoVeiculoEscolhido = null;
        this.enderecoBase = "https://parallelum.com.br/fipe/api/v1/";
        this.consumoAPI = new ConsumoAPI();
    }

    public void abrirMenu() {

        System.out.println("**** OPÇÕES ****");
        System.out.println("Carro \nMoto\nCaminhão");

        System.out.print("\nDigite uma das opções para consultar valores: ");
        var tipoVeiculoEscolhidoUsuario = leitura.nextLine();
        verificarTipoVeiculo(tipoVeiculoEscolhidoUsuario);

        consultarMarcaVeiculo();

    }

    public void consultarMarcaVeiculo() {
        String dados = this.consumoAPI.obterDados(this.enderecoBase +
                this.tipoVeiculoEscolhido.getDescricao()+"/marcas");

        System.out.println(dados);
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
