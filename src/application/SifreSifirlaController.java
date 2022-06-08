package application;

import java.nio.channels.SelectableChannel;
import java.nio.file.attribute.DosFileAttributeView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import com.animation.animate;
import com.database.databaseUtil;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SifreSifirlaController {
	
	public SifreSifirlaController(){
		baglanti = databaseUtil.baglan();
	}

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button close_btn;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button minimize_btn;

    @FXML
    private Button passwordBtn;

    @FXML
    private TextField txt_eposta;

    @FXML
    private PasswordField txt_password;

    @FXML
    private PasswordField txt_password2;

    @FXML
    private TextField txt_username;
    
    Connection baglanti = null;
   	PreparedStatement sorguIfadesi = null;
   	ResultSet getirilen = null;
   	String sql = null;

    @FXML
    void close_btnClick(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void goBackBtnClick(ActionEvent event) {
    	try {
    		animate.fade_out(anchorpane);
        	AnchorPane pane= (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
        	anchorpane.getChildren().setAll(pane);
        	animate.fade_in(anchorpane);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());		
		}
    }
    @FXML
    void minimize_btnClick(ActionEvent event) {
    	Stage stage = (Stage)minimize_btn.getScene().getWindow();
    	stage.setIconified(true);
    }

    @FXML
    void passwordBtnClick(ActionEvent event) {
    	if(!txt_username.getText().isEmpty() && !txt_password.getText().isEmpty() && !txt_password2.getText().isEmpty() 
    			&& !txt_eposta.getText().isEmpty()) {
    		if(txt_password.getText().length() >= 6) {
    			if(txt_password.getText().equals(txt_password2.getText())) {
    				sql = "update kullanicilar set password=? where username=? and email=?";
    				try {
						sorguIfadesi = baglanti.prepareStatement(sql);
						sorguIfadesi.setString(1, databaseUtil.md5sifrele(txt_password.getText()));
						sorguIfadesi.setString(2, txt_username.getText());
						sorguIfadesi.setString(3, txt_eposta.getText());
						sorguIfadesi.executeUpdate();
						MessageBox();
					} catch (Exception e) {
						System.out.println(e.getMessage().toString());
						MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý ve E-Posta bilgisi eþleþmedi.", "Lütfen tekrar deneyiniz");
					}
    			}
    			else {
    				MessageBox(AlertType.ERROR, "Hata", "Þifreler birbirleriyle uyuþmuyor.", "Lütfen tekrar deneyiniz");
    			}
    		}
    		else {
    			MessageBox(AlertType.ERROR, "Hata", "Þifre 6 karakterden az olamaz.", "Lütfen tekrar deneyiniz");
    		}
    	}
    	else {
    		if(txt_username.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý boþ olamaz.", "Lütfen kullanýcý adý giriniz");
    		}
    		else if(txt_password.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "Þifre boþ olamaz.", "Lütfen þifre giriniz");
    		}
    		else if(txt_password2.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "Þifre (Tekrar) boþ olamaz.", "Lütfen þifre giriniz.");
    		}
    		else if(txt_eposta.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "E-Posta boþ olamaz.", "Lütfen e-posta giriniz");
    		}
    	}
    }
    
    private void MessageBox(AlertType alerttype, String title, String header, String content) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
    
    private void MessageBox() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Baþarýlý");
    	alert.setHeaderText("Þifre baþarýyla sýfýrlandý.");
    	alert.setContentText("Giriþ ekranýna dönülsün mü?");
    	
    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayýr");
    	alert.getButtonTypes().setAll(btn1, btn2);
    	alert.getButtonTypes().setAll(btn1, btn2);
    	
    	Optional<ButtonType> sonuc= alert.showAndWait();
    	if(sonuc.get()==btn1) {
    		try {
        		animate.fade_out(anchorpane);
            	AnchorPane pane= (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
            	anchorpane.getChildren().setAll(pane);
            	animate.fade_in(anchorpane);
    		} catch (Exception e) {
    			System.out.println(e.getMessage().toString());		
    		}
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("Ýptal Edildi.");
    	}	
    }

}
