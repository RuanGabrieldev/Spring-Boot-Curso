package br.com.ruan.cursomc.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedidoModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPkModel id = new ItemPedidoPkModel();
	
	
	public ItemPedidoModel() {
		// TODO Auto-generated constructor stub
	}


	public ItemPedidoModel(Double desconto, Integer quantidade, Double preco, PedidoModel pedido, ProdutoModel produto) {
		super();
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
		id.setPedido(pedido);
		id.setProduto(produto);
	}

	@JsonIgnore
	public PedidoModel getPedido() {
		return id.getPedido();
	}
	
	public ProdutoModel getProduto() {
		return id.getProduto();
	}
	
	
	public Double getDesconto() {
		return desconto;
	}


	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getPreco() {
		return preco;
	}


	public void setPreco(Double preco) {
		this.preco = preco;
	}


	public ItemPedidoPkModel getId() {
		return id;
	}


	public void setId(ItemPedidoPkModel id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoModel other = (ItemPedidoModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
