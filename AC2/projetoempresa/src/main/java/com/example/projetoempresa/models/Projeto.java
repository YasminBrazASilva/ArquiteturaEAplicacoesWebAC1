package com.example.projetoempresa.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200, nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @JoinTable(name = "Projeto_Funcionario", joinColumns = {
            @JoinColumn(name = "Funcionario_ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "Projeto_ID") })
    private List<Funcionario> funcionarios;

    public void addPessoa(Funcionario f) {
        funcionarios.add(f);
    }
}