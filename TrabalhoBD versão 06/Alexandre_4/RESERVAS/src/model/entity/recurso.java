package model.entity;

public class recurso {
	private String nome, etiqueta, observacao, status;
	private boolean ativo;
	int id, id_unidade, id_tipo_recurso;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_unidade() {
		return id_unidade;
	}
	public void setId_unidade(int id_unidade) {
		this.id_unidade = id_unidade;
	}
	public int getId_tipo_recurso() {
		return id_tipo_recurso;
	}
	public void setId_tipo_recurso(int id_tipo_recurso) {
		this.id_tipo_recurso = id_tipo_recurso;
	}
	
	
}
