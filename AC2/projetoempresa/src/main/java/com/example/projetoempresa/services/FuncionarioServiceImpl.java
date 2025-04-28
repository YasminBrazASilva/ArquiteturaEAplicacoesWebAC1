package com.example.projetoempresa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.projetoempresa.config.RegraNegocioException;
import com.example.projetoempresa.dtos.DadosFuncionarioDTO;
import com.example.projetoempresa.dtos.DadosProjetoDTO;
import com.example.projetoempresa.dtos.FuncionarioDTO;
import com.example.projetoempresa.dtos.SetorDTO;
import com.example.projetoempresa.models.Funcionario;
import com.example.projetoempresa.models.Setor;
import com.example.projetoempresa.repositories.FuncionarioRepository;
import com.example.projetoempresa.repositories.SetorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    @Override
    @Transactional
    public FuncionarioDTO salvar(FuncionarioDTO funcionarioDTO) {
        Setor setor = setorRepository
                .findById(funcionarioDTO.getSetorId())
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setSetor(setor);

        Funcionario f = funcionarioRepository.save(funcionario);

        FuncionarioDTO funcionarioResponse = new FuncionarioDTO();
        funcionarioResponse.setId(f.getId());
        funcionarioResponse.setNome(f.getNome());

        return funcionarioResponse;
    }

    @Override
    public DadosFuncionarioDTO obterPorId(Integer id) {
        return funcionarioRepository.findById(id).map((Funcionario f) -> {
            return DadosFuncionarioDTO.builder()
                    .id(f.getId())
                    .nome(f.getNome())
                    .setor(SetorDTO.builder()
                            .id(f.getSetor().getId())
                            .nome(f.getSetor().getNome())
                            .build())
                    .build();
        }).orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado."));
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editar(Integer id, FuncionarioDTO funcionarioDto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));
        Setor setor = setorRepository.findById(
                funcionarioDto.getSetorId())
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setSetor(setor);
        funcionarioRepository.save(funcionario);
    }

    @Override
    public List<DadosFuncionarioDTO> obterTodos() {
        return funcionarioRepository.findAll().stream().map((Funcionario f) -> {
            return DadosFuncionarioDTO.builder()
                    .id(f.getId())
                    .nome(f.getNome())
                    .setor(SetorDTO.builder()
                            .id(f.getSetor().getId())
                            .nome(f.getSetor().getNome())
                            .build())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<DadosProjetoDTO> findProjetos(Integer idFuncionario) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioFetchProjetos(idFuncionario);
        return funcionario.getProjetos().stream().map(projeto -> 
            DadosProjetoDTO.builder()
                .id(projeto.getId())
                .descricao(projeto.getDescricao())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .build()
        ).collect(Collectors.toList());
    }
}