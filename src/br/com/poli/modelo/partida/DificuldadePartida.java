package br.com.poli.modelo.partida;

/* Enum para configurar os atributos envolvendo a dificuldade da partida */
public enum DificuldadePartida {

	FACIL(1, 5, 10, 2), MEDIO(2, 10, 15, 3), DIFICIL(3, 10, 20, 4);

	private int dificuldade;
	private int quantidadeMaximaDeErros;
	private int quantidadeDeEspacosVazios;
	private int quantidadeDeAjuda;

	private DificuldadePartida(int dificuldade, int quantidadeMaximaDeErros, int quantidadeDeEspacosVazios,int quantidadeDeAjuda) {
		this.dificuldade = dificuldade;
		this.quantidadeMaximaDeErros = quantidadeMaximaDeErros;
		this.quantidadeDeEspacosVazios = quantidadeDeEspacosVazios;
		this.quantidadeDeAjuda = quantidadeDeAjuda;
	}

	public int getDificuldade() {
		return this.dificuldade;
	}

	public int getQuantidadeMaximaDeErros() {
		return this.quantidadeMaximaDeErros;
	}

	public int getQuantidadeDeEspacosVazios() {
		return this.quantidadeDeEspacosVazios;
	}

	public int getQuantidadeDeAjuda(){
		return this.quantidadeDeAjuda;
	}

}