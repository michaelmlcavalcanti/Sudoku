package br.com.poli.modelo.partida;

import br.com.poli.modelo.excecoes.*;
import br.com.poli.modelo.jogador.*;
import br.com.poli.modelo.score.*;

import java.util.Date;

public class Partida implements Movimento, ResolvedorSudoku {
	private int quantidadeDeErros;
	private int quantidadeDeAjuda;
	private boolean venceu;
	private int score;
	private boolean desistir;
	private Jogador jogador;
	private Tabuleiro tabuleiro;
	private Date tempoInicial;
	private Date tempoFinal;
	private DificuldadePartida dificuldade;
	private CalculaScorePadrao calculaScore;

	public Partida(Jogador jogador, DificuldadePartida dificuldade, CalculaScorePadrao calculaScore) {
		this.tabuleiro = new Tabuleiro();
		this.setJogador(jogador);
		this.setDificuldade(dificuldade);
		this.setCalculaScore(calculaScore);
		this.gerarTabuleiro(dificuldade);
		iniciaPartida();
	}

	/*
	 * Metodo que inicia a partida inicializando o tabuleiro, a quantidade de
	 * erros, a contagem do tempo
	 */
	public void iniciaPartida() {
		this.setVenceu(false);
		this.setTempoInicial();
		this.setScore(0);
		this.setQuantidadeDeErros(0);
		this.setQuantidadeDeAjuda(0);

	}

	/* Metodo que trata e relança as exceções */
	public void executaMovimento(int x, int y, int valor)
			throws MovimentoInvalidoException, MovimentoIncorretoException, NumberFormatException {
		try {
			this.tabuleiro.executaMovimento(x, y, valor);
			this.setScore(this.getScore() + 1);
			if (this.tabuleiro.isTabuleiroPreenchido() == true) {
				this.setVenceu(true);
			}
		} catch (MovimentoIncorretoException movimentoIncorreto) {
			this.setQuantidadeDeErros(this.getQuantidadeDeErros() + 1);
			if (isFimDeJogo() == true) {
				resolveTabuleiro();
				throw movimentoIncorreto;
			} else {
				throw movimentoIncorreto;
			}

		}

	}

	public boolean isFimDeJogo() {
		return this.quantidadeDeErros == this.dificuldade.getQuantidadeMaximaDeErros();
	}

	public void setDesistir(boolean desistir) {
		this.desistir = desistir;
		this.setTempoFinal();
	}

	public boolean getDesistir() {
		return this.desistir;
	}

	public void setVenceu(boolean venceu) {
		this.venceu = venceu;
		this.setTempoFinal();
	}

	public boolean isVenceu() {
		return this.venceu;
	}

	public int getTempo() {
		return ((int) this.getTempoFinal().getTime() - (int) this.getTempoInicial().getTime()) / 60000;
	}

	public void gerarTabuleiro(DificuldadePartida dificuldade) {
		this.tabuleiro.gerarTabuleiro(dificuldade);
	}

	public void resolveTabuleiro() {
		this.tabuleiro.resolveTabuleiro();
		setDesistir(true);
	}

	public void pedirAjuda() {
		if (this.dificuldade.getQuantidadeDeAjuda() > this.getQuantidadeDeAjuda()) {
			this.setQuantidadeDeAjuda(getQuantidadeDeAjuda() + 1);
			this.tabuleiro.pedirAjuda();
		}
	}

	public boolean checarGameOver() {
		if (getDesistir() == true || isFimDeJogo() == true || isVenceu() == true) {
			return true;
		} else
			return false;
	}

	public Jogador getJogador() {
		return this.jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public int getQuantidadeDeErros() {
		return this.quantidadeDeErros;
	}

	public void setQuantidadeDeErros(int quantidadeDeErros) {
		this.quantidadeDeErros = quantidadeDeErros;
	}

	public Date getTempoInicial() {
		return this.tempoInicial;
	}

	public void setTempoInicial() {
		this.tempoInicial = new Date();
	}

	public Date getTempoFinal() {
		return this.tempoFinal;
	}

	public void setTempoFinal() {
		this.tempoFinal = new Date();
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public DificuldadePartida getDificuldade() {
		return this.dificuldade;
	}

	public void setDificuldade(DificuldadePartida dificuldade) {
		this.dificuldade = dificuldade;
	}

	public CalculaScorePadrao getCalculaScore() {
		return this.calculaScore;
	}

	public void setCalculaScore(CalculaScorePadrao calculaScore) {
		this.calculaScore = calculaScore;
	}

	public int getQuantidadeDeAjuda() {
		return this.quantidadeDeAjuda;
	}

	public void setQuantidadeDeAjuda(int quantidadeDeAjuda) {
		this.quantidadeDeAjuda = quantidadeDeAjuda;
	}

}
