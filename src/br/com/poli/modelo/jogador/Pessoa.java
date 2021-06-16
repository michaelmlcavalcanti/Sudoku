package br.com.poli.modelo.jogador;

/* Classe abstrata do jogador */
public abstract class Pessoa {
	protected int idade;
	protected String nome;

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}