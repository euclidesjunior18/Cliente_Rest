package org.example.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.example.model.Pessoa;

import java.util.List;

public interface PessoaClient {
    @RequestLine("POST /pessoas")
    @Headers("Content-Type: application/json")
    Pessoa criarPessoa(Pessoa pessoa);

    @RequestLine("GET /pessoas/{id}")
    Pessoa pessoaComId(@Param("id")Long id);

    @RequestLine("PUT /pessoas")
    @Headers("Content-Type: application/json")
    Pessoa atualizarPessoa(Pessoa pessoa);

    @RequestLine("DELETE /pessoas/{id}")
    void deletePessoaComId(@Param("id") Long id);

    @RequestLine("GET /pessoas")
    static List<Pessoa> todasPessoas() {
        return null;
    }
}
