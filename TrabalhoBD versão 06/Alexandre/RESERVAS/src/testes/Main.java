package testes;

import java.io.File;

import javafx.application.Application;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	private RadioButton teste;
	
    @Override
    public void start(Stage stage) {
    
    teste = new RadioButton();
    
    System.out.println(teste.isSelected());
    
    
    }
    public static void main(String[] args) {
        launch(args);
    }
}
