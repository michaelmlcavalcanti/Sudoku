package br.com.poli.controle;

import br.com.poli.main.Main;

/* Classe que Chama a Cena de Informa��es */
public class ControleJogar implements Controle {
	public ControleJogar() {
		exibir();
	}

	public void exibir() {
		Main main = new Main();
		main.chamarCenaInformacoes();
	}

}
