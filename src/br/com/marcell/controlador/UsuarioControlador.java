package br.com.marcell.controlador;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.marcell.esb.entidade.Usuario;
import br.com.marcell.esb.servico.UsuarioEsbServico;

@ManagedBean
@javax.faces.bean.ViewScoped
public class UsuarioControlador implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioEsbServico usuarioEsbServico;

	private List<Usuario> usuarioJPAList;

	private List<Usuario> usuarioMorphiaList;

	private Usuario usuarioCadastro;

	@PostConstruct
	public void init() throws InterruptedException, ExecutionException {
		Future<List<Usuario>> jpa = usuarioEsbServico.getAllProjetoJPA();
		Future<List<Usuario>> morphia = usuarioEsbServico.getAllProjetoMorphia();

		this.usuarioJPAList = jpa.get();
		this.usuarioMorphiaList = morphia.get();
		this.usuarioCadastro = new Usuario();
	}
	
	public void salvar() {
		usuarioEsbServico.cadastrar(usuarioCadastro);
	}

	public List<Usuario> getUsuarioJPAList() {
		return usuarioJPAList;
	}

	public void setUsuarioJPAList(List<Usuario> usuarioJPAList) {
		this.usuarioJPAList = usuarioJPAList;
	}

	public List<Usuario> getUsuarioMorphiaList() {
		return usuarioMorphiaList;
	}

	public void setUsuarioMorphiaList(List<Usuario> usuarioMorphiaList) {
		this.usuarioMorphiaList = usuarioMorphiaList;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	

}
