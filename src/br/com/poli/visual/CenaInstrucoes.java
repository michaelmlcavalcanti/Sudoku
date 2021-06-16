package br.com.poli.visual;

import br.com.poli.controle.ControleVoltar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CenaInstrucoes implements Cena {

	/* Cena Das Instrucoes */
	@Override
	public BorderPane exibirCena() {
		BorderPane instrucoesTela = new BorderPane();
		instrucoesTela.setPrefSize(800, 600);
		instrucoesTela.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, white 0%, red 100%);");

		Label titulo;

		instrucoesTela.setTop(titulo = new Label("Instru��es"));
		BorderPane.setAlignment(titulo, Pos.CENTER);
		titulo.setStyle("-fx-font-size: 36");

		VBox labelInstrucoes = new VBox(20);
		labelInstrucoes.setStyle("-fx-font-size: 14");
		labelInstrucoes.setAlignment(Pos.CENTER);
		Label instrucao1 = new Label("*O Sudoku � um jogo de racioc�nio e l�gica que, al�m de tudo, � divertido.");
		Label instrucao2 = new Label("*O jogador deve preencher os espa�os em branco com n�meros de 1 a 9.");
		Label instrucao3 = new Label("*Nenhum n�mero deve repetir na linha, na coluna ou no quadrante 3x3.");
		Label instrucao4 = new Label("*Melhore suas habilidades mentais com este jogo.");
		Label instrucao5 = new Label("*De acordo com a dificuldade escolhida voce ter�:");
		Label instrucao6 = new Label("-Quantidade de espa�os a serem preenchidos.");
		Label instrucao7 = new Label("-Quantidade limitada de erros.");
		Label instrucao8 = new Label("-Quantidade limitadas de ajuda.");
		Label instrucao9 = new Label("*Caso n�o consiga resolver um sudoku � poss�vel desistir e finalizar o jogo.");
		labelInstrucoes.getChildren().addAll(instrucao1, instrucao2, instrucao3, instrucao4, instrucao5, instrucao6,
				instrucao7, instrucao8, instrucao9);

		Button voltar;
		voltar = new Button("Voltar");
		BorderPane.setAlignment(voltar, Pos.CENTER);
		voltar.setStyle("-fx-font-size: 16");
		voltar.setOnAction(e -> new ControleVoltar());

		Label desenvolvedores = new Label("Desenvolvido por Michael Cavalcanti e Vitor Felix");

		VBox dev = new VBox(20);
		dev.setAlignment(Pos.CENTER);
		dev.setStyle("-fx-font-size: 16");
		dev.getChildren().addAll(voltar, desenvolvedores);

		instrucoesTela.setCenter(labelInstrucoes);
		instrucoesTela.setBottom(dev);

		return instrucoesTela;
	}

}
