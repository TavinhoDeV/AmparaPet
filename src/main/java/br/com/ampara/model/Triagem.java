package br.com.ampara.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "triagens")
public class Triagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Usuario responsavel;

    @Column(name = "data_triagem", nullable = false)
    private LocalDateTime dataTriagem = LocalDateTime.now();

    @Column(name = "estado_saude", length = 100)
    private String estadoSaude;

    @Column(name = "necessita_tratamento", nullable = false)
    private boolean necessitaTratamento = false;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    public Triagem() {
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

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDateTime getDataTriagem() {
        return dataTriagem;
    }

    public String getEstadoSaude() {
        return estadoSaude;
    }

    public void setEstadoSaude(String estadoSaude) {
        this.estadoSaude = estadoSaude;
    }

    public boolean isNecessitaTratamento() {
        return necessitaTratamento;
    }

    public void setNecessitaTratamento(boolean necessitaTratamento) {
        this.necessitaTratamento = necessitaTratamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
