package br.com.ampara.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "atendimentos_clinicos")
public class AtendimentoClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoAtendimento tipo;

    @Column(name = "data_atendimento", nullable = false)
    private LocalDateTime dataAtendimento = LocalDateTime.now();

    @Column(name = "veterinario_responsavel", length = 150)
    private String veterinarioResponsavel;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    public AtendimentoClinico() {
    }

    public Long getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public TipoAtendimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtendimento tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public String getVeterinarioResponsavel() {
        return veterinarioResponsavel;
    }

    public void setVeterinarioResponsavel(String veterinarioResponsavel) {
        this.veterinarioResponsavel = veterinarioResponsavel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
