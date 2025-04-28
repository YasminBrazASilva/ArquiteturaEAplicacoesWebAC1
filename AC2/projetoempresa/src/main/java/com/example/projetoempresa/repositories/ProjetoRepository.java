package com.example.projetoempresa.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.projetoempresa.models.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    @Query("select p from Projeto p left join fetch p.funcionarios f where p.id = :id ")
    Optional<Projeto> findProjetoFetchFuncionarios(@Param("id") Integer id);

    @Query("SELECT p FROM Projeto p WHERE p.dataInicio BETWEEN :dataInicio AND :dataFim")
    List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);
}