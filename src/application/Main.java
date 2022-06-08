package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private double x;
	private double y;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,400,600, javafx.scene.paint.Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//Formu hareket ettirme
			root.setOnMousePressed(mouseEvent ->{
				x = mouseEvent.getSceneX();
				y = mouseEvent.getSceneY();
			});
			
			root.setOnMouseDragged(mouseEvent ->{
				primaryStage.setX(mouseEvent.getScreenX() - x);
				primaryStage.setY(mouseEvent.getScreenY() - y);
			});
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
