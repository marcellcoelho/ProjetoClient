package br.com.marcell.esb.entidade;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Permissao {

	private String descricao;
	private String permissao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}
