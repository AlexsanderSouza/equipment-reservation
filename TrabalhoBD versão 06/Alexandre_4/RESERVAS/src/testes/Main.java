package testes;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.COMPONENTE_DATAHORA.DateTimePicker;

public class Main extends Application {
	private RadioButton teste;
	
    @Override
    public void start(Stage primaryStage) {
    
    	final VBox vBox = new VBox();
		vBox.getChildren().add(new Label("Date/Time"));
		vBox.getChildren().add(new DateTimePicker());

		final Scene scene = new Scene(vBox);

		primaryStage.setScene(scene);
		primaryStage.sizeToScene();

		primaryStage.show();
    	
    
    
    }
    public static void main(String[] args) {
        launch(args);
    }
}
