package controle_manutencao.service;

import controle_manutencao.model.Acompanhamentos;
import controle_manutencao.model.OrdemDeServico;
import controle_manutencao.repository.OrdemDeServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdemDeServicoService {

    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;

    public OrdemDeServico criarOrdemDeServico(OrdemDeServico ordem) {
        ordem.setStatus("Pendente");
        ordem.setDataCadastro(LocalDateTime.now());
        return ordemDeServicoRepository.save(ordem);
    }

    public List<OrdemDeServico> listarOrdemDeServicosPendentes() {
        return ordemDeServicoRepository.findByStatus("Pendente");
    }

    public OrdemDeServico iniciarServico(Long id) {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(id).orElseThrow();
        ordem.setStatus("Em execução");
        ordem.setDataInicio(LocalDateTime.now());
        return ordemDeServicoRepository.save(ordem);
    }

    public OrdemDeServico finalizarServico(Long id) {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(id).orElseThrow();
        ordem.setStatus("Finalizado");
        ordem.setDataTermino(LocalDateTime.now());
        return ordemDeServicoRepository.save(ordem);
    }

    public Acompanhamentos adicionarAcompanhamento(Long ordemId, Acompanhamentos acompanhamento) {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(ordemId).orElseThrow();
        acompanhamento.setData(LocalDateTime.now());
        acompanhamento.setOrdemDeServico(ordem);
        ordem.getAcompanhamentos().add(acompanhamento);
        ordemDeServicoRepository.save(ordem);
        return acompanhamento;
    }
}
