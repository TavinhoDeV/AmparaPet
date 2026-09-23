package br.com.ampara.dao;

import br.com.ampara.model.Animal;
import br.com.ampara.model.StatusAnimal;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class AnimalDAO extends GenericDAO<Animal, Long> {

    public AnimalDAO() {
        super(Animal.class);
    }

    public List<Animal> listarPorStatus(StatusAnimal status) {
        return em.createQuery(
                        "SELECT a FROM Animal a WHERE a.status = :status", Animal.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Animal> listarDisponiveisParaAdocao() {
        return listarPorStatus(StatusAnimal.DISPONIVEL_ADOCAO);
    }
}
