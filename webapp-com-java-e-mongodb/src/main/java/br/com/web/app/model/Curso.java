package br.com.web.app.model;

public class Curso {
	private String nome;

	public Curso() { }
	
	public Curso(String nomeCurso) {
		this.nome = nomeCurso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}