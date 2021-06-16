package br.com.poli.controle;

import br.com.poli.main.Main;

/* Classe que Chama a Cena de Instruções */
public class ControleInstrucoes implements Controle {

	public ControleInstrucoes() {
		exibir();
	}

	@Override
	public void exibir() {
		Main main = new Main();
		main.chamarCenaInstrucoes();
	}

}
