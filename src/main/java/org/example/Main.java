package org.example;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.example.model.Pessoa;
import org.example.model.Produto;
import org.example.service.PessoaClient;
import org.example.service.ProdutoClient;

import java.math.BigDecimal;
import java.util.List;
public class Main {

    public static void main(String[] args) {
        PessoaClient pessoaClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(PessoaClient.class, "http://localhost:8080");

        Pessoa pessoaExample = Pessoa.builder()
                .id(Long.valueOf(66))
                .nome("Mariazinha")
                .sexo('F')
                .rg(12345678)
                .build();

        Pessoa pessoaCadastrada = pessoaClient.criarPessoa(pessoaExample);
        System.out.println(String.format("org.example.model.Pessoa %s cadastrada com sucesso.", pessoaCadastrada.getNome()));

        pessoaCadastrada = pessoaClient.pessoaComId(Long.valueOf(66));
        System.out.println(String.format("GET %s", pessoaCadastrada.toString()));

        pessoaExample.setNome("Joaozinho");
        pessoaExample = pessoaClient.atualizarPessoa(pessoaExample);
        System.out.println(String.format("PUT %s", pessoaExample));

        List<Pessoa> pessoas = PessoaClient.todasPessoas();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }

        pessoaClient.deletePessoaComId(Long.valueOf(66));
    }
}
