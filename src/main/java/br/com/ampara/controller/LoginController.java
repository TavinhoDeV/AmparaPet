package br.com.ampara.controller;

import br.com.ampara.model.Usuario;
import br.com.ampara.service.UsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.Optional;

@Named
@RequestScoped
public class LoginController {

    @Inject
    private UsuarioService usuarioService;

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String entrar() {
        Optional<Usuario> usuario = usuarioService.autenticar(email, senha);

        if (usuario.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "E-mail ou senha inválidos.",
                            null));
            return null;
        }

        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("usuarioLogado", usuario.get());

        return "dashboard.xhtml?faces-redirect=true";
    }

}
