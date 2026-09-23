package br.com.ampara.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resgates")
public class Resgate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Usuario voluntario;

    @Column(name = "data_resgate", nullable = false)
    private LocalDateTime dataResgate = LocalDateTime.now();

    @Column(nullable = false, length = 250)
    private String local;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    public Resgate() {
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

    public Usuario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Usuario voluntario) {
        this.voluntario = voluntario;
    }

    public LocalDateTime getDataResgate() {
        return dataResgate;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
