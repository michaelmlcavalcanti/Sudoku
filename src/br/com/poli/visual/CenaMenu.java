package br.com.poli.visual;

import br.com.poli.controle.ControleInstrucoes;
import br.com.poli.controle.ControleJogar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CenaMenu implements Cena {

	/* Cena do Menu */
	@Override
	public BorderPane exibirCena() {

		BorderPane menuTela = new BorderPane();
		menuTela.setPrefSize(800, 600);
		menuTela.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, white 0%, red 100%);");

		Label titulo;

		menuTela.setTop(titulo = new Label("Menu"));
		BorderPane.setAlignment(titulo, Pos.CENTER);
		titulo.setStyle("-fx-font-size: 36");

		VBox opcoes = new VBox(20);
		opcoes.setAlignment(Pos.CENTER);
		opcoes.setStyle("-fx-font-size: 16");

		Button jogar = new Button("Jogar");
		jogar.setOnAction(e -> new ControleJogar());

		Button instrucoes = new Button("Instruções");
		instrucoes.setOnAction(e -> new ControleInstrucoes());

		Button sair = new Button("Sair");
		sair.setOnAction(e -> System.exit(0));

		Label desenvolvedores = new Label("Desenvolvido por Michael Cavalcanti e Vitor Felix");
		BorderPane.setAlignment(desenvolvedores, Pos.CENTER);
		desenvolvedores.setStyle("-fx-font-size: 16");

		opcoes.getChildren().addAll(jogar, instrucoes, sair);

		menuTela.setCenter(opcoes);
		menuTela.setBottom(desenvolvedores);

		return menuTela;
	}

}
