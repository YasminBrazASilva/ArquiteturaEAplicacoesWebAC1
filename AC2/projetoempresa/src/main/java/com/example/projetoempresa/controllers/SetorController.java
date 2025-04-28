package com.example.projetoempresa.controllers;

import com.example.projetoempresa.dtos.DadosSetorDTO;
import com.example.projetoempresa.dtos.SetorDTO;
import com.example.projetoempresa.services.SetorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setores")
public class SetorController {
    private SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping()
    public void postSetor(@RequestBody SetorDTO setorDTO) {
        setorService.salvar(setorDTO);
    }

    @GetMapping("{id}")
    public DadosSetorDTO obterPorId(@PathVariable Integer id) {
        return setorService.obterPorId(id);
    }
}