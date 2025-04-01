package com.example.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.biblioteca.models.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long>{
    @Query(value= "SELECT l.* FROM Livro AS l WHERE l.preco > :valor", nativeQuery= true)
    List<Livro> encontrarLivrosComPrecoMaiorQue(@Param("valor") Double valor);

    @Query(value= "SELECT l.* FROM Livro AS l WHERE l.preco <= :valor", nativeQuery= true)
    List<Livro> encontrarLivrosComPrecoMenorOuIgualQue(@Param("valor") Double valor);

    @Query(value= "SELECT l.* FROM Livro AS l WHERE l.titulo LIKE CONCAT(:titulo, '%')", nativeQuery= true)
    List<Livro> encontrarLivrosQueComecamCom(@Param("titulo") String titulo);
}
