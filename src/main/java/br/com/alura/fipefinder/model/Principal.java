package br.com.alura.fipefinder.model;

import br.com.alura.fipefinder.service.ConsumoAPI;
import br.com.alura.fipefinder.service.ConversorDados;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private TipoVeiculo tipoVeiculoEscolhido;
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConversorDados conversorDados = new ConversorDados();
    private final String enderecoBase;

    public Principal() {
        this.tipoVeiculoEscolhido = null;
        this.enderecoBase = "https://parallelum.com.br/fipe/api/v1/";
    }

    public void abrirMenu() {

        System.out.println("**** OPÇÕES ****");
        System.out.println("Carro \nMoto\nCaminhão");

        System.out.print("\nDigite uma das opções para consultar valores: ");
        var tipoVeiculoEscolhidoUsuario = leitura.nextLine();
        verificarTipoVeiculo(tipoVeiculoEscolhidoUsuario);

        consultarMarcaVeiculo();

        System.out.print("Informe o código da marca para consulta: ");
        var codigoMarca = leitura.nextLine();

        consultarModelosVeiculos(codigoMarca);
    }

    public void consultarMarcaVeiculo() {
        if (this.tipoVeiculoEscolhido == null) {
            return;
        }
        String json = consumoAPI.obterDados(this.enderecoBase +
                this.tipoVeiculoEscolhido.getDescricao()+"/marcas");

        List<Marca> marcas = conversorDados.obterLista(json, Marca.class);

        imprimirLista(marcas);
    }

    public void consultarModelosVeiculos(String codigoMarca) {
        if (this.tipoVeiculoEscolhido == null) {
            return;
        }

        String json = consumoAPI.obterDados(this.enderecoBase +
                this.tipoVeiculoEscolhido.getDescricao()+"/marcas/" + codigoMarca+"/modelos");

        Modelos modelos = conversorDados.obterDados(json, Modelos.class);

        imprimirLista(modelos.veiculos());
    }

    private <T> void imprimirLista(List<T> lista) {
        lista.forEach(System.out::println);
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
