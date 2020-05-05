package br.com.ruan.cursomc.model;

import java.util.Date;

import javax.persistence.Entity;

import br.com.ruan.cursomc.model.enums.EstadoPagamento;

@Entity
public class PagamentoComBoletoModel extends PagamentoModel {

	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoComBoletoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PagamentoComBoletoModel(Integer id, EstadoPagamento estado, PedidoModel pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
	
	
	
}
