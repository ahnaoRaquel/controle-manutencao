package controle_manutencao.controller;

import controle_manutencao.model.Acompanhamentos;
import controle_manutencao.model.OrdemDeServico;
import controle_manutencao.service.OrdemDeServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens-de-servico")
public class OrdemDeServicoController {

    @Autowired
    private OrdemDeServicoService ordemDeServicoService;

    @PostMapping
    public ResponseEntity<OrdemDeServico> criarOrdemDeServico(@RequestBody OrdemDeServico ordemDeServico) {
        OrdemDeServico novaOrdem = ordemDeServicoService.criarOrdemDeServico(ordemDeServico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOrdem);
    }

    @GetMapping("/pendentes")
    public List<OrdemDeServico> listarOrdensPendentes() {
        return ordemDeServicoService.listarOrdemDeServicosPendentes();
    }

    @PutMapping("/{id}/iniciar")
    public OrdemDeServico iniciarOrdemDeServico(@PathVariable Long id) {
        return ordemDeServicoService.iniciarServico(id);
    }

    @PutMapping("/{id}/finalizar")
    public OrdemDeServico finalizarOrdemDeServico(@PathVariable Long id) {
        return ordemDeServicoService.finalizarServico(id);
    }

    @PostMapping("/{id}/acompanhamento")
    public Acompanhamentos adicionarAcompanhamento(@PathVariable Long id, @RequestBody Acompanhamentos companhamentos) {
        return ordemDeServicoService.adicionarAcompanhamento(id, companhamentos);
    }

}
