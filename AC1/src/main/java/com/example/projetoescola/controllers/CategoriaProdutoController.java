package com.example.projetoescola.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoescola.models.CategoriaProduto;
import com.example.projetoescola.repositories.CategoriaProdutoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/categoriasprodutos")
public class CategoriaProdutoController {
    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    @GetMapping("")
    public List<CategoriaProduto> mostrarTodos() {
        return categoriaProdutoRepository.selecionarTodos();
    }

    @PostMapping("")
    public CategoriaProduto inserir(@RequestBody CategoriaProduto produto) {
        return categoriaProdutoRepository.inserir(produto);
    }
    
}