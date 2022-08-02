package ifpr.pgua.eic.quizapp;

import java.util.ArrayList;

import ifpr.pgua.eic.quizapp.utils.BaseAppNavigator;
import ifpr.pgua.eic.quizapp.utils.ScreenRegistry;
import javafx.scene.Parent;

public class AppGUI extends BaseAppNavigator{

    private ControladorQuiz controladorQuiz;

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


    @Override
    public ScreenRegistry getHomeFXML() {
        return null;
    }

    @Override
    public Parent getHomeParent() {
        return new TelaHome(controladorQuiz).getRoot();
    }

    @Override
    public String getAppTitle() {
        return "Quiz de Perguntas";
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
