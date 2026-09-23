package br.com.ampara.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacinacoes")
public class Vacinacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "atendimento_id")
    private AtendimentoClinico atendimento;

    @Column(name = "tipo_vacina", nullable = false, length = 100)
    private String tipoVacina;

    @Column(name = "data_aplicacao", nullable = false)
    private LocalDate dataAplicacao;

    @Column(name = "data_proxima_dose")
    private LocalDate dataProximaDose;

    public Vacinacao() {
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

    public String getTipoVacina() {
        return tipoVacina;
    }

    public void setTipoVacina(String tipoVacina) {
        this.tipoVacina = tipoVacina;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public LocalDate getDataProximaDose() {
        return dataProximaDose;
    }

    public void setDataProximaDose(LocalDate dataProximaDose) {
        this.dataProximaDose = dataProximaDose;
    }
}
