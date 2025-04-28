package com.example.projetoempresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.projetoempresa.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    @Query("select f from Funcionario f left join fetch f.projetos p where f.id = :id ")
    Funcionario findFuncionarioFetchProjetos(@Param("id") Integer id);
}