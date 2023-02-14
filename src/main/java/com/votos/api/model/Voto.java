package com.votos.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.votos.api.model.Associado;
import com.votos.api.model.Votacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.ZonedDateTime;

@Table(name = "votos")
@Entity(name = "Voto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_voto")
public class Voto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voto")
    private Long id;

    @Column(name = "favoravel")
    @NotNull
    private Boolean favoravel;

    @Column(name = "data_registro")
    private ZonedDateTime dataRegistro;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "votacao_id")
    private Votacao votacao;

    @OneToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    public Voto(Boolean favoravel) {
        this.favoravel = favoravel;
    }
}
