package br.com.ampara.dao;

import br.com.ampara.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

import java.util.Optional;

@ApplicationScoped
public class UsuarioDAO extends GenericDAO<Usuario, Long> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        try {
            Usuario usuario = em.createQuery(
                            "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(usuario);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public boolean existeEmail(String email) {
        Long total = em.createQuery(
                        "SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return total > 0;
    }
}
