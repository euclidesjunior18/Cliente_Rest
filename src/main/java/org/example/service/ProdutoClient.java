package org.example.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.example.model.Produto;

import java.util.List;

public interface ProdutoClient {

    @RequestLine("POST /produtos")
    @Headers("Content-Type: application/json")
    Produto criarProduto(Produto produto);

    @RequestLine("GET /produtos/{id}")
    Produto produtoComId(@Param("id")Long id);

    @RequestLine("PUT /produtos")
    @Headers("Content-Type: application/json")
    Produto atualizarProduto(Produto produto);

    @RequestLine("DELETE /produtos/{id}")
    void deleteProdutoComId(@Param("id") Long id);

    @RequestLine("GET /produtos")
    List<Produto> todosProdutos();

}
