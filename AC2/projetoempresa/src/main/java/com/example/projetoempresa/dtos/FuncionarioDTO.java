package com.example.projetoempresa.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioDTO {
    private Integer id;
    private String nome;
    private Integer setorId;
}