package br.com.ampara.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adocoes")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Column(name = "adotante_nome", nullable = false, length = 150)
    private String adotanteNome;

    @Column(name = "adotante_documento", nullable = false, length = 20)
    private String adotanteDocumento;

    @Column(name = "adotante_telefone", length = 20)
    private String adotanteTelefone;

    @Column(name = "adotante_endereco", length = 250)
    private String adotanteEndereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAdocao status = StatusAdocao.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "aprovado_por")
    private Usuario aprovadoPor;

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    public Adocao() {
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

    public String getAdotanteNome() {
        return adotanteNome;
    }

    public void setAdotanteNome(String adotanteNome) {
        this.adotanteNome = adotanteNome;
    }

    public String getAdotanteDocumento() {
        return adotanteDocumento;
    }

    public void setAdotanteDocumento(String adotanteDocumento) {
        this.adotanteDocumento = adotanteDocumento;
    }

    public String getAdotanteTelefone() {
        return adotanteTelefone;
    }

    public void setAdotanteTelefone(String adotanteTelefone) {
        this.adotanteTelefone = adotanteTelefone;
    }

    public String getAdotanteEndereco() {
        return adotanteEndereco;
    }

    public void setAdotanteEndereco(String adotanteEndereco) {
        this.adotanteEndereco = adotanteEndereco;
    }

    public StatusAdocao getStatus() {
        return status;
    }

    public void setStatus(StatusAdocao status) {
        this.status = status;
    }

    public Usuario getAprovadoPor() {
        return aprovadoPor;
    }

    public void setAprovadoPor(Usuario aprovadoPor) {
        this.aprovadoPor = aprovadoPor;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
}
