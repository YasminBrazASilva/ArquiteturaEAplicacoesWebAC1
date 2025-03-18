package com.example.projetoescola.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produto;
    @Column(length = 200, nullable = false)
    private String prod_nome;
    @Column(nullable = false)
    private Integer prod_qtd;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoriaProduto_id") 
    private CategoriaProduto categoriaProduto;

    public Produto(Integer id_produto, String prod_nome, Integer prod_qtd) {
        this.id_produto = id_produto;
        this.prod_nome = prod_nome;
        this.prod_qtd = prod_qtd;
    }

    public Produto() {
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public String getProd_nome() {
        return prod_nome;
    }

    public void setProd_nome(String prod_nome) {
        this.prod_nome = prod_nome;
    }

    public Integer getProd_qtd() {
        return prod_qtd;
    }

    public void setProd_qtd(Integer prod_qtd) {
        this.prod_qtd = prod_qtd;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    @Override
    public String toString() {
        return "Produto [id_produto=" + id_produto + ", prod_nome=" + prod_nome + ", prod_qtd=" + prod_qtd + "]";
    }

}
