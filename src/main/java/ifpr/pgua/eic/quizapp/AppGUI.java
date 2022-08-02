package ifpr.pgua.eic.quizapp;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppGUI extends Application{

    private ControladorQuiz controladorQuiz;

    private Scene cena;
    private VBox vbox;
    private Text indiceQuestao;
    private Text enunciado;
    private Button alternativa1;
    private Button alternativa2;
    private Button alternativa3;
    private Button alternativa4;
    private Text resultado;
    private Button proxima;

    private VBox vboxResultado;
    private Text acertos;
    private Text erros;
    private Button reiniciar;
    
    @Override
    public void init() throws Exception {
        super.init();

        ArrayList<Questao> lista = new ArrayList<>();
        
        lista.add(new Questao("Qual a capital do Paraná?", "Curitiba", new String[]{"Floripa", "Porto Alegre", "São Paulo"}));
        lista.add(new Questao("Qual a capital de São Paulo?", "São Paulo", new String[]{"Floripa", "Rio de Janeiro", "Campo Grande"}));
        lista.add(new Questao("Qual a capital do Acre?", "Rio Branco", new String[]{"Manaus","Palmas", "João Pessoa"}));
        lista.add(new Questao("Qual a capital de Rondonia?", "Porto Velho", new String[]{"Belém", "Macapá", "Manaus"}));

        controladorQuiz = new ControladorQuiz(lista);
    }

    private void atualizaTela(){

        Questao questao = controladorQuiz.getQuestao();

        int questaoAtual = controladorQuiz.indiceQuestaoAtual()+1;
        int totalQuestoes = controladorQuiz.totalQuestoes();

        indiceQuestao.setText("Pergunta "+questaoAtual+"/"+totalQuestoes);
        enunciado.setText(questao.getEnunciado());
        alternativa1.setText(questao.getAlternativas().get(0));
        alternativa2.setText(questao.getAlternativas().get(1));
        alternativa3.setText(questao.getAlternativas().get(2));
        alternativa4.setText(questao.getAlternativas().get(3));
        
        resultado.setVisible(false);
        proxima.setVisible(false);

        

    }

    private void inicializaComponentes(){
        enunciado = new Text("Olá Mundo!! Agora com GUI!!!");
        indiceQuestao = new Text("");

        alternativa1 = new Button("Alternativa 1");
        alternativa1.setPrefWidth(200);
        alternativa1.setOnAction(respondePergunta());
        alternativa1.setTooltip(new Tooltip("Clique para responder..."));
        alternativa2 = new Button("Alternativa 2");
        alternativa2.setPrefWidth(200);
        alternativa2.setOnAction(respondePergunta());
        alternativa3 = new Button("Alternativa 3");
        alternativa3.setPrefWidth(200);
        alternativa3.setOnAction(respondePergunta());
        alternativa4 = new Button("Alternativa 4");
        alternativa4.setPrefWidth(200);
        alternativa4.setOnAction(respondePergunta());
        
        resultado = new Text("Resultado");
        proxima = new Button("Próxima");
        proxima.setOnAction(proximaPergunta());
        
        vbox = new VBox();

        vbox.getChildren().add(indiceQuestao);
        vbox.getChildren().add(enunciado);
        vbox.getChildren().add(alternativa1);
        vbox.getChildren().add(alternativa2);
        vbox.getChildren().add(alternativa3);
        vbox.getChildren().add(alternativa4);
        vbox.getChildren().add(resultado);
        vbox.getChildren().add(proxima);
        

        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10.0);

        resultado.setVisible(false);
        proxima.setVisible(false);


        acertos = new Text();
        erros = new Text();
        reiniciar = new Button("Reiniciar");
        reiniciar.setOnAction(this::reiniciar);


        vboxResultado = new VBox();
        vboxResultado.getChildren().add(acertos);
        vboxResultado.getChildren().add(erros);
        vboxResultado.getChildren().add(reiniciar);
        vboxResultado.setAlignment(Pos.CENTER);
        vboxResultado.setSpacing(10.0);

    }

    @Override
    public void start(Stage stage) throws Exception {
        
        inicializaComponentes();
        atualizaTela();

        cena = new Scene(vbox,300,300);

        stage.setScene(cena);
        stage.show();
    }

    private void ajustaVisibilidadeBotoes(){
        alternativa1.setDisable(!alternativa1.isDisabled());
        alternativa2.setDisable(!alternativa2.isDisabled());
        alternativa3.setDisable(!alternativa3.isDisabled());
        alternativa4.setDisable(!alternativa4.isDisabled());
    }

    private EventHandler respondePergunta(){
        return new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                Button clicado = (Button)event.getSource();
                String alternativa = clicado.getText();

                boolean ret = controladorQuiz.respondeQuestao(alternativa);

                if(ret){
                    resultado.setText("Acertou!!");
                }else{
                    resultado.setText("Errou!!!");
                }

                resultado.setVisible(true);
                proxima.setVisible(true);

                ajustaVisibilidadeBotoes();
        

                if(!controladorQuiz.temProximaQuestao()){
                    proxima.setText("Resultado");
                }

            }
            
        };
    }

    private EventHandler proximaPergunta(){
        return new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                
                if(controladorQuiz.temProximaQuestao()){
                    controladorQuiz.proximaQuestao();
                    atualizaTela();
                    ajustaVisibilidadeBotoes();
                }else{
                    acertos.setText("Acertos:"+controladorQuiz.getAcertos());
                    erros.setText("Erros:"+controladorQuiz.getErros());
                    cena.setRoot(vboxResultado);
                }

            }
        };
    }

    private void reiniciar(ActionEvent evt){
        controladorQuiz.reiniciar();
        
        
        proxima.setText("Próxima");
        cena.setRoot(vbox);
        atualizaTela();
        ajustaVisibilidadeBotoes();
    }



    public static void main(String[] args) {
        launch(args);
    }
    
}
