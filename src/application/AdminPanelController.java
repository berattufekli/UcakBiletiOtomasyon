package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.animation.animate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminPanelController {
	
	private double x;
	
	private double y;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminler_Btn;

    @FXML
    private Button anasayfa_Btn;

    @FXML
    private Button biletler_Btn;

    @FXML
    private Pane leftPanel;

    @FXML
    private Button logout_Btn;

    @FXML
    private AnchorPane main_panel_anchor_pane;

    @FXML
    private Button menu_Btn;
    
    AdminAnasayfaController adminAnasayfaController;
    
    AdminBiletlerController adminBiletlerController;
    
    AdminHesapController adminHesapController;

    @FXML
    void adminler_BtnClick(ActionEvent event) {
    	try {
    		animate.fade_out(main_panel_anchor_pane);
        	FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminHesap.fxml"));
        	AnchorPane pane=(AnchorPane) loader.load();
        	adminHesapController = loader.getController();
        	main_panel_anchor_pane.getChildren().setAll(pane);
        	animate.fade_in(main_panel_anchor_pane);
        	if(anasayfa_Btn.getText().isEmpty()) {
        		//hesapController.animasyon_ac();
        	}else {
        		////hesapController.animasyon_kapa();
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void anasayfaBtn_Click(ActionEvent event) {
    	try {
    		animate.fade_out(main_panel_anchor_pane);
        	FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminAnasayfa.fxml"));
        	AnchorPane pane=(AnchorPane) loader.load();
        	adminAnasayfaController = loader.getController();
        	main_panel_anchor_pane.getChildren().setAll(pane);
        	animate.fade_in(main_panel_anchor_pane);
        	if(anasayfa_Btn.getText().isEmpty()) {
        		adminAnasayfaController.animasyon_ac();
        	}else {
        		adminAnasayfaController.animasyon_kapa();
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void biletler_BtnClick(ActionEvent event) {
    	try {
    		animate.fade_out(main_panel_anchor_pane);
        	FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminBiletler.fxml"));
        	AnchorPane pane=(AnchorPane) loader.load();
        	adminBiletlerController = loader.getController();
        	main_panel_anchor_pane.getChildren().setAll(pane);
        	animate.fade_in(main_panel_anchor_pane);
        	if(anasayfa_Btn.getText().isEmpty()) {
        		adminBiletlerController.animasyon_ac();
        	}else {
        		//adminBiletlerController.animasyon_kapa();
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
    void menuBtn_Click(ActionEvent event) {
    	if(!menu_Btn.getText().isEmpty()) {
    		menu_Btn.setText("");
        	anasayfa_Btn.setText("");
        	biletler_Btn.setText("");
        	adminler_Btn.setText("");
        	logout_Btn.setText("");
        	
        	menu_Btn.setPrefWidth(54);
        	anasayfa_Btn.setPrefWidth(54);
        	biletler_Btn.setPrefWidth(54);
        	adminler_Btn.setPrefWidth(54);
        	logout_Btn.setPrefWidth(54);
        	leftPanel.setPrefWidth(74);
        	
        	
        	try {
        		if(adminAnasayfaController != null) {
        			adminAnasayfaController.animasyon_ac();
        		}
        		
        		if(adminBiletlerController != null) {
        			adminBiletlerController.animasyon_ac();
        		}
        		
        		
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
        	
    	} 
    	else if(menu_Btn.getText().isEmpty()) {
    		menu_Btn.setText("Menu");
        	anasayfa_Btn.setText("Anasayfa");
        	biletler_Btn.setText("Biletlerim");
        	adminler_Btn.setText("Hesap");
        	logout_Btn.setText("Log Out");
        	
        	menu_Btn.setPrefWidth(200);
        	anasayfa_Btn.setPrefWidth(200);
        	biletler_Btn.setPrefWidth(200);
        	adminler_Btn.setPrefWidth(200);
        	logout_Btn.setPrefWidth(200);
        	leftPanel.setPrefWidth(220);
        	
        	
        	try {
        		if(adminAnasayfaController != null) {
        			adminAnasayfaController.animasyon_kapa();
        		}
        		
        		if(adminBiletlerController != null) {
        			adminBiletlerController.animasyon_kapa();
        		}
        		
        		
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
			
    	}
    }
    
    

    @FXML
    void initialize() {
    	try {
    		animate.fade_out(main_panel_anchor_pane);
        	FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminAnasayfa.fxml"));
        	AnchorPane pane=(AnchorPane) loader.load();
        	adminAnasayfaController = loader.getController();
        	main_panel_anchor_pane.getChildren().setAll(pane);
        	animate.fade_in(main_panel_anchor_pane);
        	if(anasayfa_Btn.getText().isEmpty()) {
        		adminAnasayfaController.animasyon_ac();
        	}else {
        		adminAnasayfaController.animasyon_kapa();
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}

    }

}
