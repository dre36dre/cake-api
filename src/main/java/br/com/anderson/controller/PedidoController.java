package br.com.anderson.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.entities.Pedido;
import br.com.anderson.enums.PedidoStatus;
import br.com.anderson.repository.PedidoRepository;

@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://leticialuizdocesartesanais-dre36dres-projects.vercel.app",
        "https://leticialuizdocesartesanais-cuspuaxym-dre36dres-projects.vercel.app"
})
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping
    public ResponseEntity<Pedido> receber(@RequestBody Pedido pedido) {
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(PedidoStatus.CONFIRMED);

        Pedido salvo = repo.save(pedido);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public List<Pedido> listar() {
        return repo.findAllByOrderByDataHoraDesc();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> atualizarStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> dados) {
        return alterarStatus(id, dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> substituirStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> dados) {
        return alterarStatus(id, dados);
    }

    private ResponseEntity<Pedido> alterarStatus(Long id, Map<String, String> dados) {
        return repo.findById(id)
                .map(pedido -> {
                    liberarNovosStatusNoBanco();
                    PedidoStatus status = converterStatus(dados.get("status"));
                    pedido.setStatus(status);

                    Pedido atualizado = repo.save(pedido);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private void liberarNovosStatusNoBanco() {
        jdbcTemplate.execute("""
                DO $$
                DECLARE
                    constraint_name text;
                BEGIN
                    FOR constraint_name IN
                        SELECT c.conname
                        FROM pg_constraint c
                        JOIN pg_class t ON t.oid = c.conrelid
                        JOIN pg_namespace n ON n.oid = t.relnamespace
                        WHERE t.relname = 'pedido'
                          AND c.contype = 'c'
                          AND pg_get_constraintdef(c.oid) ILIKE '%status%'
                    LOOP
                        EXECUTE format('ALTER TABLE pedido DROP CONSTRAINT IF EXISTS %I', constraint_name);
                    END LOOP;
                END $$;
                """);
    }

    private PedidoStatus converterStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status nao informado");
        }

        return switch (status.trim().toUpperCase()) {
            case "COMPLETED", "CONCLUIDO", "CONCLUÍDO" -> PedidoStatus.COMPLETED;
            case "CANCELED", "CANCELLED", "CANCELADO" -> PedidoStatus.CANCELLED;
            case "CONFIRMED", "CONFIRMADO" -> PedidoStatus.CONFIRMED;
            default -> PedidoStatus.valueOf(status.trim().toUpperCase());
        };
    }
}
