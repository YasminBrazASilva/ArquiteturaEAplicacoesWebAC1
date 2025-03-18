package com.example.projetoescola.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.projetoescola.models.CategoriaProduto;
import com.example.projetoescola.models.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CategoriaProdutoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public CategoriaProduto inserir(CategoriaProduto categoriaProduto) {
        return entityManager.merge(categoriaProduto);
    }

    @Transactional
    public CategoriaProduto editar(CategoriaProduto categoriaProduto) {
        return entityManager.merge(categoriaProduto);
    }

    @Transactional
    public void excluir(CategoriaProduto categoriaProduto) {
        entityManager.remove(categoriaProduto);
    }

    @Transactional
    public void excluir(long id) {
        excluir(entityManager.find(CategoriaProduto.class, id));
    }
    
    public List<CategoriaProduto> selecionarTodos() {
        return entityManager.createQuery("FROM CategoriaProduto", CategoriaProduto.class).getResultList();
    }

    public List<Produto> selecionarPorId(Integer id) {
        String jpql = "select c from CategoriaProduto c where c.id_categoria like :id";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("id", "%" + id + "%");
        return query.getResultList();
    }
}
