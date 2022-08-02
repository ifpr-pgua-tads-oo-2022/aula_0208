package ifpr.pgua.eic.quizapp;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaCadastroPergunta {
    
    private ControladorQuiz controladorQuiz;

    private VBox root;
    private Label lbEnunciado;
    private TextField tfEnunciado;

    private Label lbAlternativaCorreta;
    private TextField tfAlternativaCorreta;

    private Label lbAlternativaErrada1;
    private TextField tfAlternativaErrada1;

    private Label lbAlternativaErrada2;
    private TextField tfAlternativaErrada2;

    private Label lbAlternativaErrada3;
    private TextField tfAlternativaErrada3;

    private Button btCadastrar;
    private Button btCancelar;


    public TelaCadastroPergunta(ControladorQuiz controladorQuiz){
        this.controladorQuiz = controladorQuiz;
        inicializaComponentes();
    }

    private void inicializaComponentes(){

        root = new VBox();

        lbEnunciado = new Label("Enunciado:");
        tfEnunciado = new TextField();
        tfEnunciado.setPrefWidth(400);

        lbAlternativaCorreta = new Label("Alternativa Correta:");
        tfAlternativaCorreta = new TextField();
        tfAlternativaCorreta.setPrefWidth(400);

        lbAlternativaErrada1 = new Label("Alternativa Errada 1:");
        tfAlternativaErrada1 = new TextField();

        lbAlternativaErrada2 = new Label("Alternativa Errada 2:");
        tfAlternativaErrada2 = new TextField();
        
        lbAlternativaErrada3 = new Label("Alternativa Errada 3:");
        tfAlternativaErrada3 = new TextField();

        btCadastrar = new Button("Cadastrar");
        btCadastrar.setOnAction(this::cadastrar);
        btCancelar = new Button("Cancelar");
        btCancelar.setOnAction(this::cancelar);
        

        root.getChildren().add(lbEnunciado);
        root.getChildren().add(tfEnunciado);
        
        root.getChildren().add(lbAlternativaCorreta);
        root.getChildren().add(tfAlternativaCorreta);
        
        root.getChildren().add(lbAlternativaErrada1);
        root.getChildren().add(tfAlternativaErrada1);

        root.getChildren().add(lbAlternativaErrada2);
        root.getChildren().add(tfAlternativaErrada2);

        root.getChildren().add(lbAlternativaErrada3);
        root.getChildren().add(tfAlternativaErrada3);

        HBox hboxBotoes = new HBox();
        hboxBotoes.getChildren().add(btCadastrar);
        hboxBotoes.getChildren().add(btCancelar);

        hboxBotoes.setSpacing(10.0);
        hboxBotoes.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().add(hboxBotoes);


        root.setSpacing(10.0);

        //coloca um espaço ao redor do componente
        root.setPadding(new Insets(50.0, 50.0, 50.0, 50.0));


        //root.setPrefWidth(400);
    }

    public Parent getRoot(){
        return root;
    }

    private void cancelar(ActionEvent actionevent1) {
        AppGUI.popScreen();
    }

    private void cadastrar(ActionEvent actionevent1) {

        String enunciado = tfEnunciado.getText();
        String alternativaCorreta = tfAlternativaCorreta.getText();
        String alternativaErrada1 = tfAlternativaErrada1.getText();
        String alternativaErrada2 = tfAlternativaErrada2.getText();
        String alternativaErrada3 = tfAlternativaErrada3.getText();

        String[] alternativasErradas = {alternativaErrada1,alternativaErrada2,alternativaErrada3};

        controladorQuiz.cadastrarQuestao(enunciado,alternativaCorreta,alternativasErradas);
        
    
        tfEnunciado.clear();
        tfAlternativaCorreta.clear();
        tfAlternativaErrada1.clear();
        tfAlternativaErrada2.clear();
        tfAlternativaErrada3.clear();

        Alert alerta = new Alert(AlertType.INFORMATION,"Cadastrado com sucesso!");
        alerta.showAndWait();
        
    }



}
