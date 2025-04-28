package com.example.projetoempresa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projetoempresa.models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {
    @Query("select st from Setor st left join fetch st.funcionarios f")
    List<Setor> findAllFetchFuncionarios();
}