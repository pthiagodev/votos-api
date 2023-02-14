package com.votos.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.votos.api.dto.AtualizaVotacaoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "votacoes")
@Entity(name = "Votacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_votacao")
public class Votacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_votacao")
    private Long id;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @Column(name = "data_final")
    private ZonedDateTime dataFinal;

    @JsonManagedReference
    @OneToMany(mappedBy = "votacao", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Voto> votos = new ArrayList<>();

    public Votacao(Pauta pauta, ZonedDateTime dataFinal) {
        this.pauta = pauta;
        this.dataFinal = dataFinal;
    }

    public void atualizaVotacao(AtualizaVotacaoDTO dados) {
        if (dados.dataFinal() != null && dados.dataFinal().isAfter(ZonedDateTime.now()))
            this.dataFinal = dados.dataFinal();

    }

}
