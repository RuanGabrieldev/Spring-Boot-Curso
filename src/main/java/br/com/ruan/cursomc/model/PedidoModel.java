package br.com.ruan.cursomc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PedidoModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date instante;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private PagamentoModel pagamento;
	
	@ManyToOne
	@JoinColumn(name = ("CLIENTE_ID"))
	private ClienteModel cliente;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedidoModel> itens = new HashSet<>();
	
	///private List<ProdutoModel> itens = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "ENDERECO_DE_ENTREGA_ID")
	private EnderecoModel enderecoDeEntrega;
	
	
	public PedidoModel() {}
	


	public PedidoModel(Integer id, Date instante,ClienteModel cliente,
			EnderecoModel enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public List<ProdutoModel> getProdutos(){
		List<ProdutoModel> lista = new ArrayList<>();
		
		for (ItemPedidoModel elem : itens) {
			lista.add(elem.getProduto());
		}
		return lista;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getInstante() {
		return instante;
	}


	public void setInstante(Date instante) {
		this.instante = instante;
	}



	public PagamentoModel getPagamento() {
		return pagamento;
	}



	public void setPagamento(PagamentoModel pagamento) {
		this.pagamento = pagamento;
	}


	public Set<ItemPedidoModel> getItens() {
		return itens;
	}



	public void setItens(Set<ItemPedidoModel> itens) {
		this.itens = itens;
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
		PedidoModel other = (PedidoModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
