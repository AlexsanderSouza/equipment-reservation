/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ENTITY;

/**
 *
 * @author WigorPaulo
 */
public class funcao {
    
    private int id,id_permissao1,id_permissao2,id_permissao3,id_permissao4,id_permissao5,id_permissao6;
    private String nome;
    private String descricao;
    private boolean ativo;

    
    
    public int getId_permissao1() {
		return id_permissao1;
	}

	public void setId_permissao1(int id_permissao1) {
		this.id_permissao1 = id_permissao1;
	}

	public int getId_permissao2() {
		return id_permissao2;
	}

	public void setId_permissao2(int id_permissao2) {
		this.id_permissao2 = id_permissao2;
	}

	public int getId_permissao3() {
		return id_permissao3;
	}

	public void setId_permissao3(int id_permissao3) {
		this.id_permissao3 = id_permissao3;
	}

	public int getId_permissao4() {
		return id_permissao4;
	}

	public void setId_permissao4(int id_permissao4) {
		this.id_permissao4 = id_permissao4;
	}

	public int getId_permissao5() {
		return id_permissao5;
	}

	public void setId_permissao5(int id_permissao5) {
		this.id_permissao5 = id_permissao5;
	}

	public int getId_permissao6() {
		return id_permissao6;
	}

	public void setId_permissao6(int id_permissao6) {
		this.id_permissao6 = id_permissao6;
	}

	/**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
           
}
