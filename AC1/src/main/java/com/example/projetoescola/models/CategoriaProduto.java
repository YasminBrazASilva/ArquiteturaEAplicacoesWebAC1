package com.example.projetoescola.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_categorias")
public class CategoriaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categoria;
    @Column(length = 200, nullable = false)
    private String cat_nome;
    @Column(length = 200, nullable = false)
    private String cat_descricao;

    @OneToMany(mappedBy = "categoriaProduto", fetch = FetchType.EAGER)
    private List<Produto> produtos;

    public CategoriaProduto() {
    }

    public CategoriaProduto(Integer id_categoria, String cat_nome, String cat_descricao) {
        this.id_categoria = id_categoria;
        this.cat_nome = cat_nome;
        this.cat_descricao = cat_descricao;
    }

    public Integer getId_categoria() {
        return this.id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getCat_nome() {
        return this.cat_nome;
    }

    public void setCat_nome(String cat_nome) {
        this.cat_nome = cat_nome;
    }

    public String getCat_descricao() {
        return this.cat_descricao;
    }

    public void setCat_descricao(String cat_descricao) {
        this.cat_descricao = cat_descricao;
    }

    public List<Produto> getProdutos() {
        return this.produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    @Override
    public String toString() {
        return "CategoriaProduto [id_categoria=" + id_categoria + ", cat_nome=" + cat_nome + ", cat_descricao="
                + cat_descricao + ", produtos=" + produtos + "]";
    }
}
