package model.entity;

public class reserva {
	private int id, id_responsavel, id_destinatario; 
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
	 
	
	
	
	
}
