package br.com.ruan.cursomc.model;

import javax.persistence.Entity;

import br.com.ruan.cursomc.model.enums.EstadoPagamento;

@Entity
public class PagamentoComCartaoModel extends PagamentoModel{

	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartaoModel() {
		// TODO Auto-generated constructor stub
	}

	public PagamentoComCartaoModel(Integer id, EstadoPagamento estado, PedidoModel pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	
	
	
	
	
}
