package com.example.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.biblioteca.models.Autor;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query(value= "SELECT a.* FROM Autor AS a WHERE a.nome LIKE CONCAT(:nome, '%')", nativeQuery= true)
    List<Autor> encontrarAutoresQueComecamCom(@Param("nome") String nome);

    @Query(value= "SELECT a FROM Autor a LEFT JOIN FETCH a.livros AS l WHERE a.id = :id")
    List<Autor> encontrarAutorPorId(@Param("id") Long id);
}
