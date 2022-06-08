package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import com.animation.animate;
import com.database.databaseUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class YeniHesapController {
	
	public YeniHesapController(){
		baglanti = databaseUtil.baglan();
	}

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button close_btn;

    @FXML
    private Button minimize_btn;

    @FXML
    private Button goBackBtn;

    @FXML
    private TextField txt_eposta;

    @FXML
    private PasswordField txt_password;

    @FXML
    private PasswordField txt_password2;

    @FXML
    private TextField txt_username;

    @FXML
    private Button yeniHesapBtn;
    
    Connection baglanti = null;
   	PreparedStatement sorguIfadesi = null;
   	ResultSet getirilen = null;
   	String sql = null;

    @FXML
    void close_btnClick(ActionEvent event) {
    	Stage stage = (Stage)close_btn.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void minimize_btnClick(ActionEvent event) {
    	Stage stage = (Stage)minimize_btn.getScene().getWindow();
    	stage.setIconified(true);
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
    void yeniHesapBtnClick(ActionEvent event) {
    	if(!txt_username.getText().isEmpty() && !txt_password.getText().isEmpty() 
    	&& !txt_eposta.getText().isEmpty() && !txt_password2.getText().isEmpty()) {
    		
    		if(txt_username.getText().length() >= 10) {
    			if(txt_password.getText().length() >= 6 && txt_password2.getText().length() >= 6) {
    				if(txt_password.getText().equals(txt_password2.getText())) {
        				if(txt_eposta.getText().contains("@gmail.com") || txt_eposta.getText().contains("@iste.edu.tr") ||
        				   txt_eposta.getText().contains("@icloud.com") || txt_eposta.getText().contains("@outlook.com")) {
        					
        					sql = "insert into kullanicilar(username, password, email) values(?,?,?)";
        					try {
        						sorguIfadesi = baglanti.prepareStatement(sql);
        						sorguIfadesi.setString(1, txt_username.getText());
        						sorguIfadesi.setString(2, databaseUtil.md5sifrele(txt_password.getText()));
        						sorguIfadesi.setString(3, txt_eposta.getText());
        						sorguIfadesi.executeUpdate();
        						MessageBox();
        					} catch (Exception e) {
        						MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý daha önceden alýnmýþ.", "Lütfen baþka bir kullanýcý adý deneyiniz.");
        					}
        				}
        				else {
        					MessageBox(AlertType.ERROR, "Hata", "E-Posta yanlýþ girildi.", "Lütfen gmail, iste, icloud, outlook uzantýlý e-postanýzý girin.");
        				}
        			}
        			else {
        				MessageBox(AlertType.ERROR, "Hata", "Þifreler birbiriyle eþleþmiyor.", "Lütfen tekrar deneyiniz.");
        			}
    			}else {
    				MessageBox(AlertType.ERROR, "Hata", "Þifre 6 karakterden az olamaz.", "Lütfen tekrar deneyiniz.");
    			}
    		}
    		else {
    			MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý 10 karakterden az olamaz", "Lütfen tekrar deneyiniz.");
    		}
    		
    		
    	}
    	else {
    		if(txt_eposta.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "E-Posta boþ olamaz", "Lütfen tekrar deneyiniz.");
    		}
    		else if(txt_username.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý boþ olamaz", "Lütfen tekrar deneyiniz.");
    		}
    		else if(txt_password.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "Þifre adý boþ olamaz", "Lütfen tekrar deneyiniz.");
    		}
    		else if(txt_password2.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "Þifre (Tekrar) boþ olamaz", "Lütfen tekrar deneyiniz.");
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
    	alert.setHeaderText("Kullanýcý baþarýyla oluþturuldu.");
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
