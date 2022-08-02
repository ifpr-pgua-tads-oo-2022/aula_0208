package ifpr.pgua.eic.quizapp;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TelaHome {


    private HBox root;
    private Button btCadastrarPergunta;
    private Button btResponderQuiz;

    private ControladorQuiz controladorQuiz;


    public TelaHome(ControladorQuiz controladorQuiz){
        this.controladorQuiz = controladorQuiz;

        inicializaComponentes();
    }

    private void inicializaComponentes(){
        btCadastrarPergunta = new Button("Cadastrar Pergunta");
        btCadastrarPergunta.setOnAction(this::mostrarTelaCadastroPergunta);
        btResponderQuiz = new Button("Responder Quiz");
        btResponderQuiz.setOnAction(this::mostrarTelaResponderQuiz);

        root = new HBox();
        root.getChildren().add(btCadastrarPergunta);
        root.getChildren().add(btResponderQuiz);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10.0);

        root.setPrefHeight(600);
        root.setPrefWidth(800);
    }

    public Parent getRoot(){
        return root;
    }

    private void mostrarTelaResponderQuiz(ActionEvent evt) {
        AppGUI.pushScreen(new TelaResponderQuiz(controladorQuiz).getRoot());
    }

    private void mostrarTelaCadastroPergunta(ActionEvent actionevent1) {
        AppGUI.pushScreen(new TelaCadastroPergunta(controladorQuiz).getRoot());
    }
    
}
