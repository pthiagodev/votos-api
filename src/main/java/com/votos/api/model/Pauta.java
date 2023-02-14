package com.votos.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.votos.api.dto.AtualizaPautaDTO;
import com.votos.api.dto.CadastroPautaDTO;
import com.votos.api.model.Votacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pautas")
@Entity(name = "Pauta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_pauta")
public class Pauta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pauta")
    private Long id;

    @NotBlank
    @Column(name = "tema")
    private String tema;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "votacao_id")
    private Votacao votacao;

    public Pauta(CadastroPautaDTO dados) {
        this.tema = dados.tema();
    }

    public void vinculaVotacao(Votacao votacao) {
        this.votacao = votacao;
    }

    public void desvinculaVotacao() {
       this.votacao = null;
    }

    public void atualizaPauta(AtualizaPautaDTO dados) {
        if (dados.tema() != null)
            this.tema = dados.tema();
    }
}
