package com.example.projetoempresa.services;

import java.util.List;

import com.example.projetoempresa.dtos.DadosProjetoDTO;
import com.example.projetoempresa.dtos.ProjetoDTO;

public interface ProjetoService {
    List<DadosProjetoDTO> obterTodos();
    DadosProjetoDTO obterPorId(Integer id);
    
    ProjetoDTO salvar(ProjetoDTO projetoDTO);

    void editar(Integer id, ProjetoDTO projetoDTO);
    void excluir(Integer id);

    void addFuncionario(Integer idProjeto, Integer idFuncionario);
}