package br.com.ampara.service;

import br.com.ampara.dao.AdocaoDAO;
import br.com.ampara.dao.AnimalDAO;
import br.com.ampara.model.Adocao;
import br.com.ampara.model.Animal;
import br.com.ampara.model.StatusAdocao;
import br.com.ampara.model.StatusAnimal;
import br.com.ampara.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@ApplicationScoped
public class AdocaoService {

    @Inject
    private AdocaoDAO adocaoDAO;

    @Inject
    private AnimalDAO animalDAO;

    public Adocao solicitar(Long animalId, String adotanteNome, String adotanteDocumento,
                             String adotanteTelefone, String adotanteEndereco) {
        Animal animal = animalDAO.buscarPorId(animalId);
        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }
        if (animal.getStatus() != StatusAnimal.DISPONIVEL_ADOCAO) {
            throw new IllegalStateException("Este animal ainda não está disponível para adoção.");
        }

        Adocao adocao = new Adocao();
        adocao.setAnimal(animal);
        adocao.setAdotanteNome(adotanteNome);
        adocao.setAdotanteDocumento(adotanteDocumento);
        adocao.setAdotanteTelefone(adotanteTelefone);
        adocao.setAdotanteEndereco(adotanteEndereco);
        adocao.setStatus(StatusAdocao.PENDENTE);

        return adocaoDAO.salvar(adocao);
    }

    /**
     * Regra central do README: a adoção só pode ser concluída se o animal já
     * estiver vacinado e castrado. Reforçada aqui na service, além do check
     * feito na tela.
     */
    @Transactional
    public void concluir(Long adocaoId, Usuario responsavel) {
        Adocao adocao = adocaoDAO.buscarPorId(adocaoId);
        if (adocao == null) {
            throw new IllegalArgumentException("Solicitação de adoção não encontrada.");
        }

        Animal animal = adocao.getAnimal();
        if (!animal.isElegivelParaAdocao()) {
            throw new IllegalStateException(
                    "Adoção bloqueada: o animal precisa estar vacinado e castrado antes da adoção.");
        }

        adocao.setStatus(StatusAdocao.CONCLUIDA);
        adocao.setAprovadoPor(responsavel);
        adocao.setDataConclusao(LocalDateTime.now());
        adocaoDAO.atualizar(adocao);

        animal.setStatus(StatusAnimal.ADOTADO);
        animalDAO.atualizar(animal);
    }

    @Transactional
    public void recusar(Long adocaoId, Usuario responsavel) {
        Adocao adocao = adocaoDAO.buscarPorId(adocaoId);
        if (adocao == null) {
            throw new IllegalArgumentException("Solicitação de adoção não encontrada.");
        }
        adocao.setStatus(StatusAdocao.RECUSADA);
        adocao.setAprovadoPor(responsavel);
        adocaoDAO.atualizar(adocao);
    }
}
