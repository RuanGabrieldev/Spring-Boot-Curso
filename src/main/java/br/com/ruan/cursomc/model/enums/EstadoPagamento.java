package br.com.ruan.cursomc.model.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	PARCELADO(3, "Parcelado");
	
	private Integer id;
	private String msg;
	
	private EstadoPagamento(Integer id, String msg) {
		this.id = id;
		this.msg = msg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento obj : EstadoPagamento.values()) {
			if(cod.equals(obj.getId())) {
				return obj;
			}
		}
		
		throw new IllegalArgumentException("O Id " + cod + " não corresponmde com nenhum Estado de pagamento");
	}
	
	
}
