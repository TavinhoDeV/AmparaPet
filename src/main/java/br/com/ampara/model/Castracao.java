package br.com.ampara.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "castracoes")
public class Castracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "animal_id", nullable = false, unique = true)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "atendimento_id")
    private AtendimentoClinico atendimento;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @Column(name = "data_castracao", nullable = false)
    private LocalDate dataCastracao;

    public Castracao() {
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

    public AtendimentoClinico getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(AtendimentoClinico atendimento) {
        this.atendimento = atendimento;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public LocalDate getDataCastracao() {
        return dataCastracao;
    }

    public void setDataCastracao(LocalDate dataCastracao) {
        this.dataCastracao = dataCastracao;
    }
}
