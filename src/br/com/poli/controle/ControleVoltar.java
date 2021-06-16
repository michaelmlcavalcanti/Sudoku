package br.com.poli.controle;

import br.com.poli.main.Main;

/*Classe que Chama a Cena de Menu*/
public class ControleVoltar implements Controle {
	public ControleVoltar() {
		exibir();
	}

	public void exibir() {
		Main main = new Main();
		main.chamarCenaMenu();
	}

}
