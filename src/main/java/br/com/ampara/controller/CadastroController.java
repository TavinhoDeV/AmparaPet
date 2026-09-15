package br.com.ampara.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.util.Objects;

@Named
@RequestScoped
public class CadastroController {

    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String confirmarSenha;
    private boolean termos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public boolean isTermos() {
        return termos;
    }

    public void setTermos(boolean termos) {
        this.termos = termos;
    }

    public void cadastrar() {

        if (!Objects.equals(confirmarSenha, senha)) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "As senhas não coincidem.",
                            null));

            return;

        }

        if (!termos) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Você deve aceitar os termos e condições para prosseguir.",
                            null));

            return;

        }

        System.out.println("Nome recebido: " + nome);
        System.out.println("Email recebido: " + email);
        System.out.println("Telefone recebido: " + telefone);
    
    }

}
