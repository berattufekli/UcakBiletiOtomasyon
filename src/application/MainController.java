package application;

import com.animation.animate;


import java.lang.reflect.Method;
import java.net.SecureCacheResponse;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.database.databaseUtil;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class MainController {
	
	private double x;
	private double y;
	
    @FXML
    private Button anasayfa_Btn;

    @FXML
    private Button biletlerim_Btn;

    @FXML
    private Button hesap_Btn;

    @FXML
    private Pane leftPanel;

    @FXML
    private Button logout_Btn;

    @FXML
    private AnchorPane main_panel_anchor_pane;

    @FXML
    private Button menu_Btn;
    
    AnasayfaController anasayfaController;
    
    BiletlerimController biletlerimController;
    
    HesapController hesapController;
    
    @FXML
    void hesap_BtnClick(ActionEvent event) {
    	try {
    		animate.fade_out(main_panel_anchor_pane);
        	FXMLLoader loader=new FXMLLoader(getClass().getResource("Hesap.fxml"));
        	AnchorPane pane=(AnchorPane) loader.load();
        	hesapController = loader.getController();
        	main_panel_anchor_pane.getChildren().setAll(pane);
        	animate.fade_in(main_panel_anchor_pane);
        	if(anasayfa_Btn.getText().isEmpty()) {
        		hesapController.animasyon_ac();
        	}else {
        		hesapController.animasyon_kapa();
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void logout_BtnClick(ActionEvent event) {
    	Stage currnentStage = (Stage)logout_Btn.getScene().getWindow();
    	currnentStage.close();
    	try {
    		Stage primaryStage = new Stage();
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
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    @FXML
    void anasayfaBtn_Click(ActionEvent event) {		
    	try {
    		animate.fade_out(main_panel_anchor_pane);
        	FXMLLoader loader=new FXMLLoader(getClass().getResource("Anasayfa.fxml"));
        	AnchorPane pane=(AnchorPane) loader.load();
        	anasayfaController = loader.getController();
        	main_panel_anchor_pane.getChildren().setAll(pane);
        	animate.fade_in(main_panel_anchor_pane);
        	if(anasayfa_Btn.getText().isEmpty()) {
        		anasayfaController.animasyon_ac();
        	}else {
        		anasayfaController.animasyon_kapa();
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    @FXML
    void biletlerim_BtnClick(ActionEvent event) {
    	try {
    		animate.fade_out(main_panel_anchor_pane);
        	FXMLLoader loader=new FXMLLoader(getClass().getResource("Biletlerim.fxml"));
        	AnchorPane pane=(AnchorPane) loader.load();
        	biletlerimController = loader.getController();
        	main_panel_anchor_pane.getChildren().setAll(pane);
        	animate.fade_in(main_panel_anchor_pane);
        	if(anasayfa_Btn.getText().isEmpty()) {
        		biletlerimController.animasyon_ac();
        	}else {
        		biletlerimController.animasyon_kapa();
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    

    @FXML
    void menuBtn_Click(ActionEvent event) {
    	if(!menu_Btn.getText().isEmpty()) {
    		menu_Btn.setText("");
        	anasayfa_Btn.setText("");
        	biletlerim_Btn.setText("");
        	hesap_Btn.setText("");
        	logout_Btn.setText("");
        	
        	menu_Btn.setPrefWidth(54);
        	anasayfa_Btn.setPrefWidth(54);
        	biletlerim_Btn.setPrefWidth(54);
        	hesap_Btn.setPrefWidth(54);
        	logout_Btn.setPrefWidth(54);
        	leftPanel.setPrefWidth(74);
        	
        	try {
        		if(anasayfaController != null) {
        			anasayfaController.animasyon_ac();
        		}
        		if(hesapController != null) {
        			hesapController.animasyon_ac();
        		}
        		if(biletlerimController != null) {
        			biletlerimController.animasyon_ac();
        		}
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
        	
    	} 
    	else if(menu_Btn.getText().isEmpty()) {
    		menu_Btn.setText("Menu");
        	anasayfa_Btn.setText("Anasayfa");
        	biletlerim_Btn.setText("Biletlerim");
        	hesap_Btn.setText("Hesap");
        	logout_Btn.setText("Log Out");
        	
        	menu_Btn.setPrefWidth(200);
        	anasayfa_Btn.setPrefWidth(200);
        	biletlerim_Btn.setPrefWidth(200);
        	hesap_Btn.setPrefWidth(200);
        	logout_Btn.setPrefWidth(200);
        	leftPanel.setPrefWidth(220);
        	
        	try {
        		if(anasayfaController != null) {
        			anasayfaController.animasyon_kapa();
        		}
        		if(hesapController != null) {
        			hesapController.animasyon_kapa();
        		}
        		if(biletlerimController != null) {
        			biletlerimController.animasyon_kapa();
        		}
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    	}
    }
    
    @FXML
    void initialize() {
        try {
        	FXMLLoader loader=new FXMLLoader(getClass().getResource("Anasayfa.fxml"));
        	AnchorPane pane=(AnchorPane) loader.load();
        	anasayfaController = loader.getController();
        	main_panel_anchor_pane.getChildren().setAll(pane);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    
    
}
