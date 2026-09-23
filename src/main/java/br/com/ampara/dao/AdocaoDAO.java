package br.com.ampara.dao;

import br.com.ampara.model.Adocao;
import br.com.ampara.model.StatusAdocao;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class AdocaoDAO extends GenericDAO<Adocao, Long> {

    public AdocaoDAO() {
        super(Adocao.class);
    }

    public List<Adocao> listarPorStatus(StatusAdocao status) {
        return em.createQuery(
                        "SELECT a FROM Adocao a WHERE a.status = :status", Adocao.class)
                .setParameter("status", status)
                .getResultList();
    }
}
