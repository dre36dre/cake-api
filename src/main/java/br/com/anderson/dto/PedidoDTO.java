package br.com.anderson.dto;

import java.util.List;

public class PedidoDTO {
    public String nome;
    public String telefone;
    public String endereco;
    public Double total;
    public List<ItemPedidoDTO> itens;
}
