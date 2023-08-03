package com.fastfood.autotem.dto;

import com.fastfood.autotem.model.TipoProduto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;


public record ProdutoDTO(Long id, String nome, BigDecimal preco, TipoProduto tipoProduto)
        implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;
}
