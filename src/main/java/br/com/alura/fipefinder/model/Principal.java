package br.com.alura.fipefinder.model;

import br.com.alura.fipefinder.service.ConsumoAPI;
import br.com.alura.fipefinder.service.ConversorDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private TipoVeiculo tipoVeiculoEscolhido;
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConversorDados conversorDados = new ConversorDados();
    private final String enderecoBase;
    private List<Marca> veiculos = new ArrayList<>();

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

        System.out.print("\nInforme o código da marca para consulta: ");
        var codigoMarca = leitura.nextLine();

        consultarModelosVeiculos(codigoMarca);

        System.out.print("\nInforme o nome do carro para buscar: ");
        var carroBuscado = leitura.nextLine();

        consultarCarrosPeloNome(carroBuscado);

        System.out.print("\nDigite o código do modelo para consultar valores: ");
        var codigoVeiculo = leitura.nextLine();

        consultarValoresVeiculo(codigoMarca, codigoVeiculo);

    }

    public void consultarValoresVeiculo(String codigoMarca, String codigoVeiculo) {
        List<DadosVeiculo> veiculos = new ArrayList<>();

        String json = consumoAPI.obterDados(this.enderecoBase +
                this.tipoVeiculoEscolhido.getDescricao()+"/marcas/" +
                codigoMarca + "/modelos/" + codigoVeiculo+"/anos/");

        List<Marca> marcas = conversorDados.obterLista(json, Marca.class);


        for (Marca marca : marcas) {
            json = consumoAPI.obterDados(this.enderecoBase +
                    this.tipoVeiculoEscolhido.getDescricao()+"/marcas/" +
                    codigoMarca + "/modelos/" + codigoVeiculo+"/anos/" + marca.codigo());
            veiculos.add(conversorDados.obterDados(json, DadosVeiculo.class));
        }
        System.out.println("\nTodos os veiculos filtrados com avaliações por ano: ");
        imprimirLista(veiculos);
    }

    public void consultarCarrosPeloNome(String carroBuscado) {
        var lista = this.veiculos.stream()
                .filter(v -> v.nome().toLowerCase().contains(carroBuscado))
                .collect(Collectors.toList());

        imprimirLista(lista);

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
        this.veiculos = modelos.veiculos();

        imprimirLista(modelos.veiculos());
    }

    private <T> void imprimirLista(List<T> lista) {
        System.out.println("\n");
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
