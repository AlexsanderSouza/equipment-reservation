package model.ENTITY;

public class Recurso {
	private String  nomeTipoRecurso, nomeUnidade, etiqueta, observacao;
	private int id, id_unidade, id_tipo_recurso;
	private boolean ativo;
		
	public String getNomeTipoRecurso() {
		return nomeTipoRecurso;
	}
	public void setNomeTipoRecurso(String nomeTipoRecurso) {
		this.nomeTipoRecurso = nomeTipoRecurso;
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
	public String getNomeUnidade() {
		return nomeUnidade;
	}
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}
	public boolean GetAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
