package com.votos.api.model;

import com.votos.api.dto.AtualizaAssociadoDTO;
import com.votos.api.dto.CadastroAssociadoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "associados")
@Entity(name = "Associado")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_associado")
public class Associado {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_associado")
    private Long id;

    @Column(name = "nome")
    @NotBlank
    private String nome;

    @Column(name = "cpf")
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String cpf;

    public Associado(CadastroAssociadoDTO dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
    }

    public void atualizaAssociado(AtualizaAssociadoDTO dados) {
        if (dados.nome() != null)
            this.nome = dados.nome();
        if (dados.cpf() != null)
            this.cpf = dados.cpf();
    }
}
