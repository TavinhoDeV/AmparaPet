package br.com.ampara.service;

import br.com.ampara.dao.UsuarioDAO;
import br.com.ampara.model.PerfilUsuario;
import br.com.ampara.model.Usuario;
import br.com.ampara.util.PasswordUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class UsuarioService {

    @Inject
    private UsuarioDAO usuarioDAO;

    /**
     * Cadastra um novo usuário. Lança IllegalArgumentException quando algo
     * viola uma regra de negócio, para o controller traduzir em mensagem de tela.
     */
    public Usuario cadastrar(String nome, String email, String telefone, String senha) {
        if (usuarioDAO.existeEmail(email)) {
            throw new IllegalArgumentException("Já existe uma conta cadastrada com este e-mail.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setSenhaHash(PasswordUtil.hash(senha));
        usuario.setPerfil(PerfilUsuario.VOLUNTARIO);

        return usuarioDAO.salvar(usuario);
    }

    /**
     * Autentica um usuário por e-mail e senha.
     * Retorna Optional vazio se as credenciais forem inválidas ou o usuário estiver inativo.
     */
    public Optional<Usuario> autenticar(String email, String senha) {
        return usuarioDAO.buscarPorEmail(email)
                .filter(Usuario::isAtivo)
                .filter(u -> PasswordUtil.confere(senha, u.getSenhaHash()));
    }
}
