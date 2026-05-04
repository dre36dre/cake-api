package br.com.anderson.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.dto.PedidoDTO;
import br.com.anderson.entities.Pedido;
import br.com.anderson.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	private final PedidoService service;

	public PedidoController(PedidoService service) {
		this.service = service;
	}

	@GetMapping("/cliente/{id}")
	public List<Pedido> listarPorCliente(@PathVariable Long id) {
		return service.listarPorCliente(id);
	}

	@PostMapping
	public Pedido criarPedido(@RequestBody Pedido pedido) {
		pedido.setData(LocalDateTime.now());
		pedido.setStatus("PENDENTE");
		return service.salvar(pedido);
	}
	
	@PostMapping("/novo")
	public ResponseEntity<String> receberPedido(@RequestBody PedidoDTO pedido) {

	    // Aqui você envia WhatsApp, email, Telegram, etc.
	    System.out.println("Pedido recebido:");
	    System.out.println("Cliente: " + pedido.nome);
	    System.out.println("Telefone: " + pedido.telefone);
	    System.out.println("Endereço: " + pedido.endereco);
	    System.out.println("Total: " + pedido.total);

	    pedido.itens.forEach(item -> {
	        System.out.println(item.nome + " - R$ " + item.preco);
	    });

	    return ResponseEntity.ok("Pedido recebido com sucesso");
	}

}