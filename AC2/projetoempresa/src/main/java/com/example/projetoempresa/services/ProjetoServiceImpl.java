package com.example.projetoempresa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.projetoempresa.config.RegraNegocioException;
import com.example.projetoempresa.dtos.DadosProjetoDTO;
import com.example.projetoempresa.dtos.FuncionarioDTO;
import com.example.projetoempresa.dtos.ProjetoDTO;
import com.example.projetoempresa.models.Funcionario;
import com.example.projetoempresa.models.Projeto;
import com.example.projetoempresa.repositories.FuncionarioRepository;
import com.example.projetoempresa.repositories.ProjetoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService{
    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository; 

    @Override
    @Transactional
    public ProjetoDTO salvar(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataFim(projetoDTO.getDataFim());

        Projeto p = projetoRepository.save(projeto);

        ProjetoDTO projetoResponse = new ProjetoDTO();
        projetoResponse.setId(p.getId());
        projetoResponse.setDescricao(p.getDescricao());
        projetoResponse.setDataInicio(p.getDataInicio());
        projetoResponse.setDataFim(p.getDataFim());

        return projetoResponse;
    }

    @Override
    public DadosProjetoDTO obterPorId(Integer id) {
        return projetoRepository.findProjetoFetchFuncionarios(id).map((Projeto p) -> {
            List<FuncionarioDTO> funcionarios = p.getFuncionarios().stream().map(f -> 
                FuncionarioDTO.builder()
                        .id(f.getId())
                        .nome(f.getNome())
                        .setorId(f.getSetor().getId())
                        .build()
            ).collect(Collectors.toList());

            return DadosProjetoDTO.builder()
                    .id(p.getId())
                    .descricao(p.getDescricao())
                    .dataInicio(p.getDataInicio())
                    .dataFim(p.getDataFim())
                    .funcionarios(funcionarios)
                    .build();
        }).orElseThrow(() -> new RegraNegocioException("Projeto não encontrado."));
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        projetoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editar(Integer id, ProjetoDTO projetoDTO) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataFim(projetoDTO.getDataFim());
        projetoRepository.save(projeto);
    }

    @Override
    public List<DadosProjetoDTO> obterTodos() {
        return projetoRepository.findAll().stream().map((Projeto p) -> {
            List<FuncionarioDTO> funcionarios = p.getFuncionarios().stream().map(f -> FuncionarioDTO.builder()
                    .id(f.getId())
                    .nome(f.getNome())
                    .setorId(f.getSetor().getId())
                    .build()).collect(Collectors.toList());

            return DadosProjetoDTO.builder()
                    .id(p.getId())
                    .descricao(p.getDescricao())
                    .dataInicio(p.getDataInicio())
                    .dataFim(p.getDataFim())
                    .funcionarios(funcionarios)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));

        // Verifica se o funcionário já está vinculado ao projeto
        if (projeto.getFuncionarios().contains(funcionario)) {
            throw new RegraNegocioException("Funcionário já está vinculado ao projeto.");
        }

        // Adiciona o funcionário ao projeto
        projeto.getFuncionarios().add(funcionario);
        funcionario.getProjetos().add(projeto);

        // Salva as alterações no banco
        projetoRepository.save(projeto);
        funcionarioRepository.save(funcionario);
    }
}