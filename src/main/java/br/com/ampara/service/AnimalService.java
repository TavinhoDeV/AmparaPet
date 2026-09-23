package br.com.ampara.service;

import br.com.ampara.dao.AnimalDAO;
import br.com.ampara.model.Animal;
import br.com.ampara.model.StatusAnimal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AnimalService {

    @Inject
    private AnimalDAO animalDAO;

    public Animal registrarResgate(Animal animal) {
        animal.setStatus(StatusAnimal.AGUARDANDO_TRIAGEM);
        return animalDAO.salvar(animal);
    }

    @Transactional
    public void registrarVacinacao(Long animalId) {
        Animal animal = animalDAO.buscarPorId(animalId);
        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }
        animal.setVacinado(true);
        atualizarStatusSeElegivel(animal);
    }

    @Transactional
    public void registrarCastracao(Long animalId) {
        Animal animal = animalDAO.buscarPorId(animalId);
        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }
        animal.setCastrado(true);
        atualizarStatusSeElegivel(animal);
    }

    private void atualizarStatusSeElegivel(Animal animal) {
        if (animal.isElegivelParaAdocao() && animal.getStatus() != StatusAnimal.ADOTADO) {
            animal.setStatus(StatusAnimal.DISPONIVEL_ADOCAO);
        }
        animalDAO.atualizar(animal);
    }

    public List<Animal> listarDisponiveisParaAdocao() {
        return animalDAO.listarDisponiveisParaAdocao();
    }
}
