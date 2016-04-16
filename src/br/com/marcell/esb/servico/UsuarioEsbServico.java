package br.com.marcell.esb.servico;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.marcell.esb.entidade.Usuario;

@Stateless
public class UsuarioEsbServico {

	@Asynchronous
	public Future<List<Usuario>> getAllProjetoMorphia() {
		long start = System.currentTimeMillis();
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/ProjetoMorphia/rest/usuario");
		Response response = target.request().get();
		String json = response.readEntity(String.class);
		response.close();
		long delay = System.currentTimeMillis() - start;
		System.out.println("Morphia Demorou " + delay + " milissegundos");
		return new AsyncResult<List<Usuario>>(Arrays.asList(new Gson().fromJson(json, Usuario[].class)));
	}

	@Asynchronous
	public Future<List<Usuario>> getAllProjetoJPA() {
		long start = System.currentTimeMillis();
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/ProjetoJPA/rest/usuario");
		Response response = target.request().get();
		String json = response.readEntity(String.class);
		response.close();
		long delay = System.currentTimeMillis() - start;
		System.out.println("Jpa Demorou " + delay + " milissegundos");
		return new AsyncResult<List<Usuario>>(Arrays.asList(new Gson().fromJson(json, Usuario[].class)));
	}
	
	public void cadastrar(Usuario usuario) {
		long start = System.currentTimeMillis();
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/ProjetoMorphia/rest/usuario");
		Response response = target.request().property("content-type", "application/json").accept(MediaType.APPLICATION_JSON).post(Entity.json(new Gson().toJson(usuario)));
		String json = response.readEntity(String.class);
		response.close();
		long delay = System.currentTimeMillis() - start;
		System.out.println("Demorou " + delay + " milissegundos");
	}

}
