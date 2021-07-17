package org.example;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.example.model.Produto;
import org.example.service.ProdutoClient;

import java.math.BigDecimal;
import java.util.List;
public class Main {

    public static void main(String[] args) {
        ProdutoClient produtoClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(ProdutoClient.class, "http://localhost:8080");

        Produto notebook = Produto.builder()
                .id(1l)
                .descricao("Vostro")
                .preco(BigDecimal.valueOf(4500))
                .marca("Dell")
                .build();

        Produto produtoCadastrado = produtoClient.criarProduto(notebook);
        System.out.println(String.format("org.example.model.Produto %s cadastrado com sucesso.", produtoCadastrado.getDescricao()));

        produtoCadastrado = produtoClient.produtoComId(1l);
        System.out.println(String.format("GET %s", produtoCadastrado.toString()));

        Produto notebookNovoPreco = notebook.withPreco(BigDecimal.valueOf(5500));
        produtoCadastrado = produtoClient.atualizarProduto(notebookNovoPreco);
        System.out.println(String.format("PUT %s", produtoCadastrado));

        List<Produto> produtos = produtoClient.todosProdutos();
        for (Produto produto : produtos) {
            System.out.println(produto);
        }

        produtoClient.deleteProdutoComId(1l);
    }
}
