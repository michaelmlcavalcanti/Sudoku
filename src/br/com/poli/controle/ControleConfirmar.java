package br.com.poli.controle;

import br.com.poli.main.Main;
import br.com.poli.modelo.jogador.Jogador;
import br.com.poli.modelo.partida.DificuldadePartida;
import br.com.poli.modelo.partida.Partida;
import br.com.poli.modelo.score.CalculaScorePadrao;

/* Classe Que Controla os Dados Antes de Iniciar a Partida */
public class ControleConfirmar {
	public ControleConfirmar(String nome, DificuldadePartida dificuldade, CalculaScorePadrao score, String idade)
			throws IllegalArgumentException {

		int idadeJogo = 0;

		if (nome == null || nome.equals("")) {
			throw new IllegalArgumentException("Digite Seu Nome");
		}

		if (idade == null || idade.equals("")) {
			throw new IllegalArgumentException("Digite Sua Idade");
		} else {
			try {
				idadeJogo = Integer.parseInt(idade);
			} catch (NumberFormatException ex) {
				throw new IllegalArgumentException("O Campo Idade Aceita Apenas Números");
			}
		}

		if (dificuldade == null) {
			throw new IllegalArgumentException("Selecione Uma Dificuldade");
		}

		if (score == null) {
			throw new IllegalArgumentException("Selecione Um Tipo De Score");
		}

		Main main = new Main();
		Jogador jogadorJogo = new Jogador(nome, idadeJogo);
		Partida partida = new Partida(jogadorJogo, dificuldade, score);
		main.chamarCenaSudoku(partida, main);

	}

}
