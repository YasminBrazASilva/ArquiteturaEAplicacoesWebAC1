package com.example.projetoempresa.services;

import java.util.List;

import com.example.projetoempresa.dtos.DadosFuncionarioDTO;
import com.example.projetoempresa.dtos.DadosProjetoDTO;
import com.example.projetoempresa.dtos.FuncionarioDTO;

public interface FuncionarioService {
    List<DadosFuncionarioDTO>obterTodos();
    DadosFuncionarioDTO obterPorId(Integer id);
    
    FuncionarioDTO salvar(FuncionarioDTO dadosFuncionarioDTO);

    void editar(Integer id, FuncionarioDTO dadosFuncionarioDTO);
    void excluir(Integer id);

    List<DadosProjetoDTO> findProjetos(Integer id);
}