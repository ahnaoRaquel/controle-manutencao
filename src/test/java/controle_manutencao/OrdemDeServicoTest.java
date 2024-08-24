package controle_manutencao;

import controle_manutencao.model.Cliente;
import controle_manutencao.model.Equipamento;
import controle_manutencao.model.OrdemDeServico;
import controle_manutencao.repository.ClienteRepository;
import controle_manutencao.repository.EquipamentoRepository;
import controle_manutencao.repository.OrdemDeServicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrdemDeServicoTest {

    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Test
    @Transactional
    @Rollback
    public void criarOrdemDeServico() {
        Cliente cliente = new Cliente(null, "João da Silva", "Rua A", "123456789", "joao@exemplo.com");
        Equipamento equipamento = new Equipamento(null, "Computador", "Dell", "Não liga");
        OrdemDeServico ordem = new OrdemDeServico(null, cliente, equipamento, "Pendente", null, null, null, null);
        OrdemDeServico novaOrdem = ordemDeServicoRepository.save(ordem);
        assertThat(novaOrdem.getId()).isNotNull();
        assertThat(novaOrdem.getStatus()).isEqualTo("Pendente");
    }

    @Test
    public void testListarOrdensPendentes() {
        Cliente cliente = new Cliente(null, "Maria dos Santos", "Rua B", "987654321", "maria@exemplo.com");
        Equipamento equipamento = new Equipamento(null, "Impressora", "HP", "Não imprime");

        cliente = clienteRepository.save(cliente);
        equipamento = equipamentoRepository.save(equipamento);

        OrdemDeServico ordem = new OrdemDeServico(null, cliente, equipamento, "Pendente", null, null, null, null);
        ordemDeServicoRepository.save(ordem);

        List<OrdemDeServico> ordensPendentes = ordemDeServicoRepository.findByStatus("Pendente");
        assertThat(ordensPendentes).isNotEmpty();
    }
}
