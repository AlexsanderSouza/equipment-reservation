package model.ENTITY;

public class permissao {
	
	private int id;
    private String nome;
    private String descricao;
    private boolean ativo;
    
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean b) {
		this.ativo = b;
	}
    
    
}
