package br.com.ampara.dao;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 * DAO genérico com as operações de CRUD comuns.
 * As classes concretas (UsuarioDAO, AnimalDAO, ...) estendem esta classe
 * e acrescentam apenas as consultas específicas de cada entidade.
 */
public abstract class GenericDAO<T, ID> {

    @Inject
    protected EntityManager em;

    private final Class<T> classe;

    protected GenericDAO(Class<T> classe) {
        this.classe = classe;
    }

    @Transactional
    public T salvar(T entidade) {
        em.persist(entidade);
        return entidade;
    }

    @Transactional
    public T atualizar(T entidade) {
        return em.merge(entidade);
    }

    @Transactional
    public void remover(ID id) {
        T entidade = em.find(classe, id);
        if (entidade != null) {
            em.remove(entidade);
        }
    }

    public T buscarPorId(ID id) {
        return em.find(classe, id);
    }

    public List<T> listarTodos() {
        return em.createQuery("SELECT e FROM " + classe.getSimpleName() + " e", classe)
                .getResultList();
    }
}
