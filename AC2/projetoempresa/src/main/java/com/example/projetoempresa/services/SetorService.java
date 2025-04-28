package com.example.projetoempresa.services;

import java.util.List;

import com.example.projetoempresa.dtos.DadosSetorDTO;
import com.example.projetoempresa.dtos.SetorDTO;

public interface SetorService {
    List<DadosSetorDTO> obterTodos();
    DadosSetorDTO obterPorId(Integer id);
    
    SetorDTO salvar(SetorDTO setorDTO);

    void editar(Integer id, SetorDTO setorDTO);
    void excluir(Integer id);
}