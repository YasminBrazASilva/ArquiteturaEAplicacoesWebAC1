package com.example.projetoempresa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.projetoempresa.config.RegraNegocioException;
import com.example.projetoempresa.dtos.DadosSetorDTO;
import com.example.projetoempresa.dtos.FuncionarioDTO;
import com.example.projetoempresa.dtos.SetorDTO;
import com.example.projetoempresa.models.Setor;
import com.example.projetoempresa.repositories.SetorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetorServiceImpl implements SetorService{
    private final SetorRepository setorRepository;

    @Override
    @Transactional
    public SetorDTO salvar(SetorDTO setorDTO) {
        Setor setor = new Setor();
        setor.setNome(setorDTO.getNome());
        
        Setor s = setorRepository.save(setor);

        SetorDTO setorResponse = new SetorDTO();
        setorResponse.setId(s.getId());
        setorResponse.setNome(s.getNome());

        return setorResponse;
    }

    @Override
    public DadosSetorDTO obterPorId(Integer id) {
        return setorRepository.findById(id).map((Setor s) -> {
            List<FuncionarioDTO> funcionarios = s.getFuncionarios().stream().map(f -> 
                FuncionarioDTO.builder()
                        .id(f.getId())
                        .nome(f.getNome())
                        .setorId(f.getSetor().getId())
                        .build()
            ).collect(Collectors.toList());

            return DadosSetorDTO.builder()
                    .id(s.getId())
                    .nome(s.getNome())
                    .funcionarios(funcionarios)
                    .build();
        }).orElseThrow(() -> new RegraNegocioException("Setor não encontrado."));
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        setorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editar(Integer id, SetorDTO setorDTO) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        setor.setNome(setorDTO.getNome());
        setorRepository.save(setor);
    }

    @Override
    public List<DadosSetorDTO> obterTodos() {
        return setorRepository.findAll().stream().map((Setor s) -> {
            List<FuncionarioDTO> funcionarios = s.getFuncionarios().stream().map(f -> FuncionarioDTO.builder()
                    .id(f.getId())
                    .nome(f.getNome())
                    .setorId(f.getSetor().getId())
                    .build()).collect(Collectors.toList());

            return DadosSetorDTO.builder()
                    .id(s.getId())
                    .nome(s.getNome())
                    .funcionarios(funcionarios)
                    .build();
        }).collect(Collectors.toList());
    }
}