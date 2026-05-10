package br.com.anderson.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
