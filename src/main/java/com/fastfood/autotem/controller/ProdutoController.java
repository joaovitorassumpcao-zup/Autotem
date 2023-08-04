package com.fastfood.autotem.controller;

import com.fastfood.autotem.dto.ProdutoDTO;
import com.fastfood.autotem.model.Produto;
import com.fastfood.autotem.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAllProdutos() {
        List<Produto> produtos = produtoService.listAll();
        List<ProdutoDTO> dtos = produtos.stream()
                .map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getTipoProduto()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.getProdutoById(id);
        return produto.map(p -> ResponseEntity.ok(new ProdutoDTO(p.getId(), p.getNome(), p.getPreco(), p.getTipoProduto())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setPreco(produtoDTO.preco());
        produto.setTipoProduto(produtoDTO.tipoProduto());
        Produto savedProduto = produtoService.createProduto(produto);
        ProdutoDTO savedDto = new ProdutoDTO(savedProduto.getId(), savedProduto.getNome(), savedProduto.getPreco(), savedProduto.getTipoProduto());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Optional<Produto> existingProduto = produtoService.getProdutoById(id);
        if (existingProduto.isPresent()) {
            Produto produto = existingProduto.get();
            produto.setNome(produtoDTO.nome());
            produto.setPreco(produtoDTO.preco());
            produto.setTipoProduto(produtoDTO.tipoProduto());
            Produto updatedProduto = produtoService.updateProduto(produto);
            ProdutoDTO updatedDto = new ProdutoDTO(updatedProduto.getId(), updatedProduto.getNome(), updatedProduto.getPreco(), updatedProduto.getTipoProduto());
            return ResponseEntity.ok(updatedDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdutoById(@PathVariable Long id) {
        produtoService.deleteProdutoById(id);
        return ResponseEntity.noContent().build();
    }
}
