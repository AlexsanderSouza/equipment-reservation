package model.ENTITY;

public class reserva {
	private int id, id_responsavel, id_destinatario, id_recurso, qtde_gasto, qtde_estoque, qtde_disponivel; 
	private String  data_hora_reserva, data_hora_final, repeticao,status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData_hora_reserva() {
		return data_hora_reserva;
	}
	public void setData_hora_reserva(String data_hora_reserva) {
		this.data_hora_reserva = data_hora_reserva;
	}
	public String getData_hora_final() {
		return data_hora_final;
	}
	public void setData_hora_final(String data_hora_final) {
		this.data_hora_final = data_hora_final;
	}
	public String getRepeticao() {
		return repeticao;
	}
	public void setRepeticao(String repeticao) {
		this.repeticao = repeticao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId_responsavel() {
		return id_responsavel;
	}
	public void setId_responsavel(int id_responsavel) {
		this.id_responsavel = id_responsavel;
	}
	public int getId_destinatario() {
		return id_destinatario;
	}
	public void setId_destinatario(int id_destinatario) {
		this.id_destinatario = id_destinatario;
	}
	public int getId_recurso() {
		return id_recurso;
	}
	public void setId_recurso(int id_recurso) {
		this.id_recurso = id_recurso;
	}
	public int getQtde_gasto() {
		return qtde_gasto;
	}
	public void setQtde_gasto(int qtde_gasto) {
		this.qtde_gasto = qtde_gasto;
	}
	public int getQtde_disponivel() {
		return qtde_disponivel;
	}
	public void setQtde_disponivel(int qtde_disponivel) {
		this.qtde_disponivel = qtde_disponivel;
	}
	public int getQtde_estoque() {
		return qtde_estoque;
	}
	public void setQtde_estoque(int qtde_estoque) {
		this.qtde_estoque = qtde_estoque;
	}
	 
	
	
	
	
}
