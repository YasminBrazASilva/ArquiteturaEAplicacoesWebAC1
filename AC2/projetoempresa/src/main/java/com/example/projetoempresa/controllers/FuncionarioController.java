package com.example.projetoempresa.controllers;

import com.example.projetoempresa.dtos.FuncionarioDTO;
import com.example.projetoempresa.dtos.DadosProjetoDTO;
import com.example.projetoempresa.services.FuncionarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("")
    public void salvar(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.salvar(funcionarioDTO);
    }

    @GetMapping("/{id}/projetos")
    public List<DadosProjetoDTO> findProjetos(@PathVariable Integer id) {
        return funcionarioService.findProjetos(id);
    }
}