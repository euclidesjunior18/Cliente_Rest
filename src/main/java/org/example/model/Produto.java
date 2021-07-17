package org.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@With
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Produto {

    private Long id;

    private String descricao;

    private BigDecimal preco;

    private String marca;
}