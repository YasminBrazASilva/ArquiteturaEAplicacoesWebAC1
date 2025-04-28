package com.example.projetoempresa.controllers;

import com.example.projetoempresa.dtos.DadosProjetoDTO;
import com.example.projetoempresa.dtos.ProjetoDTO;
import com.example.projetoempresa.services.ProjetoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping("/adicionar")
    @Transactional
    public void adicionar(@RequestBody ProjetoDTO projetoDTO) {
        projetoService.salvar(projetoDTO);
    }

    @GetMapping("/{id}")
    public DadosProjetoDTO buscarProjetoPorId(@PathVariable Integer id) {
        return projetoService.obterPorId(id);
    }

    @PostMapping("/vincular/{idProjeto}/{idFuncionario}")
    @Transactional
    public void vincularFuncionario(@PathVariable Integer idProjeto, @PathVariable Integer idFuncionario) {
        projetoService.addFuncionario(idProjeto, idFuncionario);
    }
}