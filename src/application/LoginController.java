package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.animation.animate;
import com.database.databaseUtil;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
	
	private double x;
	private double y;
	
	public static int userID;

	public static int adminID;
	
	public LoginController() {
		baglanti = databaseUtil.baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close_btn;

    @FXML
    private Button girisBtn;

    @FXML
    private Button minimize_btn;

    @FXML
    private Button newAccountBtn;

    @FXML
    private Button passwordBtn;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;
    
    @FXML
    private AnchorPane anchorpane;
    
    Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql = null;

    @FXML
    void close_btnClick(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void girisBtnClick(ActionEvent event) {
    	if(txt_username.getText().contains("admin@")) {
    		sql = "select * from admin where username=? and password=?";
    		try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setString(1, txt_username.getText());
				sorguIfadesi.setString(2, databaseUtil.md5sifrele(txt_password.getText()));
				getirilen = sorguIfadesi.executeQuery();
				if(getirilen.next()) {
					adminID = getirilen.getInt("adminID");
					Stage primaryStage = new Stage();
					AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
					Scene scene = new Scene(root,1000,800, javafx.scene.paint.Color.TRANSPARENT);
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
					
					Stage currentStage = (Stage)close_btn.getScene().getWindow();
					currentStage.close();
				}else {
					MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý veya þifre hatalý.", "Lütfen yöneticiye baþvurarak þifrenizi sýfýrlayýn.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    	}
    	else {
    		if(!txt_password.getText().isEmpty() && !txt_username.getText().isEmpty()) {
        		sql = "select * from kullanicilar where username=? and password=?";
        		try {
    				sorguIfadesi = baglanti.prepareStatement(sql);
    				sorguIfadesi.setString(1, txt_username.getText());
    				sorguIfadesi.setString(2, databaseUtil.md5sifrele(txt_password.getText()));
    				getirilen = sorguIfadesi.executeQuery();
    				if(getirilen.next()) {
    					userID = getirilen.getInt("userID");
    					Stage primaryStage = new Stage();
    					AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainForm.fxml"));
    					Scene scene = new Scene(root,1000,800, javafx.scene.paint.Color.TRANSPARENT);
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
    					
    					Stage currentStage = (Stage)close_btn.getScene().getWindow();
    					currentStage.close();
    				}else {
    					MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý veya þifre hatalý.", "Lütfen tekrar deneyiniz veya þifrenizi sýfýrlayýnýz.");
    				}
    			} catch (Exception e) {
    				System.out.println(e.getMessage().toString());
    			}
        	}else if(txt_password.getText().isEmpty()) {
        		MessageBox(AlertType.ERROR, "Hata", "Þifre boþ olamaz.", "Lütfen tekrar deneyiniz");
        	}else if(txt_username.getText().isEmpty()) {
        		MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý boþ olamaz.", "Lütfen tekrar deneyiniz");
        	}
    	}
    	
    }

    @FXML
    void minimize_btnClick(ActionEvent event) {
    	Stage stage = (Stage)minimize_btn.getScene().getWindow();
    	stage.setIconified(true);
    }

    @FXML
    void newAccountBtnClick(ActionEvent event) {
    	try {
    		animate.fade_out(anchorpane);
        	AnchorPane pane= (AnchorPane) FXMLLoader.load(getClass().getResource("YeniHesap.fxml"));
        	anchorpane.getChildren().setAll(pane);
        	Scene scene = (Scene)anchorpane.getScene();
        	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	animate.fade_in(anchorpane);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void passwordBtnClick(ActionEvent event) {
    	try {
    		animate.fade_out(anchorpane);
        	AnchorPane pane= (AnchorPane) FXMLLoader.load(getClass().getResource("SifreSifirla.fxml"));
        	anchorpane.getChildren().setAll(pane);
        	Scene scene = (Scene)anchorpane.getScene();
        	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	animate.fade_in(anchorpane);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    private void MessageBox(AlertType alerttype, String title, String header, String content) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }

}
