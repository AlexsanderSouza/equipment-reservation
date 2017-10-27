package model.ENTITY;

public class restricaoRecurso {
	private int id, id_tipo_recurso, id_funcao;
	private String nomeTipoRecurso, nomeFuncao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_tipo_recurso() {
		return id_tipo_recurso;
	}

	public void setId_tipo_recurso(int id_tipo_recurso) {
		this.id_tipo_recurso = id_tipo_recurso;
	}

	public int getId_funcao() {
		return id_funcao;
	}

	public void setId_funcao(int id_funcao) {
		this.id_funcao = id_funcao;
	}

	public String getNomeFuncao() {
		return nomeFuncao;
	}

	public void setNomeFuncao(String nomeFuncao) {
		this.nomeFuncao = nomeFuncao;
	}

	public String getNomeTipoRecurso() {
		return nomeTipoRecurso;
	}

	public void setNomeTipoRecurso(String nomeTipoRecurso) {
		this.nomeTipoRecurso = nomeTipoRecurso;
	}
	
	
}
