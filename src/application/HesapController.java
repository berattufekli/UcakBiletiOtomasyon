package application;

import java.lang.reflect.Method;
import java.net.URL;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.animation.animate;
import com.database.databaseUtil;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HesapController {
	
	public HesapController(){
		baglanti = databaseUtil.baglan();
	}

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button close_btn;

    @FXML
    private ComboBox<String> combo_kayitliKartlarTP4;

    @FXML
    private Button deleteCardBtn;

    @FXML
    private Button epostaBtn;

    @FXML
    private Label lbl_gizli;

    @FXML
    private Button minimize_btn;

    @FXML
    private Pane panel_top;

    @FXML
    private Button passwordBtn;

    @FXML
    private Button saveCardBtn;

    @FXML
    private TabPane tabPage;

    @FXML
    private TextField txt_KartNoTP4;

    @FXML
    private PasswordField txt_NewPassword2TP2;

    @FXML
    private PasswordField txt_NewPasswordTP2;

    @FXML
    private TextField txt_ayTP4;

    @FXML
    private TextField txt_cvvTP4;

    @FXML
    private TextField txt_epostaTP3;

    @FXML
    private TextField txt_kartKayitAdiTP4;

    @FXML
    private TextField txt_kartUzerindekiAdTP4;

    @FXML
    private TextField txt_usernameTP1;

    @FXML
    private TextField txt_yilTP4;

    @FXML
    private Button updateCard;

    @FXML
    private Button usernameBtn;
    
    ObservableList<String> kartIDObservableList = FXCollections.observableArrayList(); 
	ObservableList<String> kartUzerindekiIsimObservableList = FXCollections.observableArrayList(); 
	ObservableList<String> kartNObservableList = FXCollections.observableArrayList();
	ObservableList<String> kartCVVObservableList = FXCollections.observableArrayList();
	ObservableList<String> kartAyObservableList = FXCollections.observableArrayList();
	ObservableList<String> kartYilObservableList = FXCollections.observableArrayList();
	ObservableList<String> kartKayitAdiObservableList = FXCollections.observableArrayList();
	
	Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql = null;

    @FXML
    void close_btnClick(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void combo_kayitliKartlar_click(ActionEvent event) {
    	try {
    		int index = combo_kayitliKartlarTP4.getSelectionModel().getSelectedIndex();
        	txt_kartUzerindekiAdTP4.setText(kartUzerindekiIsimObservableList.get(index));
        	txt_ayTP4.setText(kartAyObservableList.get(index));
        	txt_yilTP4.setText(kartYilObservableList.get(index));
        	txt_kartKayitAdiTP4.setText(kartKayitAdiObservableList.get(index));
        	txt_cvvTP4.setText(kartCVVObservableList.get(index));
        	txt_KartNoTP4.setText(kartNObservableList.get(index));
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void deleteCardBtnClick(ActionEvent event) {
    	MessageBox("Onay Mesajý", "Seçilen kartýn silinmesini onaylýyor musunuz?", "karti_sil");
    }

    @FXML
    void epostaBtnClick(ActionEvent event) {
    	MessageBox("Onay Mesajý", "E-Postanýzý güncellemek istiyor musunuz?", "eposta_guncelle");
    }

    @FXML
    void minimize_btnClick(ActionEvent event) {
    	Stage stage = (Stage)minimize_btn.getScene().getWindow();
    	stage.setIconified(true);
    }

    @FXML
    void passwordBtnClick(ActionEvent event) {
    	MessageBox("Onay Mesajý", "Þifrenizi güncellemek istiyor musunuz?", "sifre_guncelle");
    }

    @FXML
    void updateCardBtnClick(ActionEvent event) {
    	MessageBox("Onay Mesajý", "Kartýnýzý güncellemek istiyor musunuz?", "kart_guncelle");
    }

    @FXML
    void usernameBtnClick(ActionEvent event) {
    	MessageBox("Onay Mesajý", "Kullanýcý adýnýzý güncellemek istiyor musunuz?", "kul_ad_guncelle");
    }
    
    @FXML
    void saveCardBtnClick(ActionEvent event) {
    	MessageBox("Onay Mesajý", "Yeni kart eklemek istiyor musunuz?", "kart_ekle");
    }

    @FXML
    void initialize() {
    	kartlari_getir(String.valueOf(LoginController.userID));
    }
    
    public void kartlari_getir(String userID) {
    	sql = "select * from kartlar where userID=?";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, userID);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			
			while (getirilen.next()) {
				kartIDObservableList.add(getirilen.getString("kartID"));
				kartUzerindekiIsimObservableList.add(getirilen.getString("isim"));
				kartNObservableList.add(getirilen.getString("kart_no"));
				kartCVVObservableList.add(getirilen.getString("cvv"));
				kartAyObservableList.add(getirilen.getString("ay"));
				kartYilObservableList.add(getirilen.getString("yil"));
				kartKayitAdiObservableList.add(getirilen.getString("kayit_adi"));
				combo_kayitliKartlarTP4.setItems(kartKayitAdiObservableList);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
    
    private void MessageBox(String title, String header, String fonksiyon) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	
    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayýr");
    	alert.getButtonTypes().setAll(btn1, btn2);
    	
    	Optional<ButtonType> sonuc= alert.showAndWait();
    	if(sonuc.get()==btn1) {
    		if(fonksiyon.equals("karti_sil")) {
    			karti_sil();
    		}
    		else if(fonksiyon.equals("kul_ad_guncelle")) {
    			kul_ad_guncelle();
    		}
    		else if(fonksiyon.equals("sifre_guncelle")) {
    			sifre_guncelle();
    		}
    		else if(fonksiyon.equals("eposta_guncelle")) {
    			eposta_guncelle();
    		}
    		else if(fonksiyon.equals("kart_guncelle")) {
    			kart_guncelle();
    		}
    		else if(fonksiyon.equals("kart_ekle")) {
    			kart_ekle();
    		}
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("Ýptal Edildi.");
    	}	
    }
    
    private void MessageBox(AlertType alerttype, String title, String header, String content) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
    
    private void MessageBox(AlertType alerttype, String title, String header) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.showAndWait();
    }
    
    public void karti_sil() {
    	int index = combo_kayitliKartlarTP4.getSelectionModel().getSelectedIndex();
    	sql = "delete from kartlar where kartID=?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, kartIDObservableList.get(index));
			sorguIfadesi.executeUpdate();
			MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Kart baþarýlý þekilde silindi");
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    	verileri_sifirla();
    	kartlari_getir(String.valueOf(LoginController.userID));
	}
    
    public void kul_ad_guncelle() {
		sql = "UPDATE `kullanicilar` SET `username`=? WHERE `userID`=?";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, txt_usernameTP1.getText());
			sorguIfadesi.setInt(2, LoginController.userID);
			sorguIfadesi.executeUpdate();
			txt_usernameTP1.clear();
			MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Kullanýcý adýnýz baþarýlý þekilde güncellendi");
		} catch (Exception e) {
			MessageBox(AlertType.ERROR, "Hata", "Beklenmedik bir hata oluþtu", "Lütfen tekrar deneyiniz.");
		}
	}
    
    public void sifre_guncelle() {
    	if(!txt_NewPasswordTP2.getText().isEmpty() && !txt_NewPassword2TP2.getText().isEmpty()) {
    		if(txt_NewPasswordTP2.getText().equals(txt_NewPassword2TP2.getText())) {
    			if(txt_NewPasswordTP2.getText().length() >= 6) {
    				sql = "UPDATE `kullanicilar` SET `password`=? WHERE `userID`=?";
    				try {
    					sorguIfadesi = baglanti.prepareStatement(sql);
    					sorguIfadesi.setString(1, databaseUtil.md5sifrele(txt_NewPasswordTP2.getText()));
    					sorguIfadesi.setInt(2, LoginController.userID);
    					sorguIfadesi.executeUpdate();
    					MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Þifreniz baþarýlý þekilde güncellendi");
    				} catch (SQLException e) {
    					MessageBox(AlertType.ERROR, "Hata", "Beklenmedik bir hata oluþtu.", "Lütfen tekrar deneyiniz.");
    				}
    		    	txt_NewPassword2TP2.clear();
    		    	txt_NewPasswordTP2.clear();
    			}
    			else {
    				MessageBox(AlertType.ERROR, "Hata", "Þifreniz en az 6 karakter olmalýdýr.", "Lütfen tekrar deneyiniz.");
    				txt_NewPasswordTP2.clear();
    				txt_NewPassword2TP2.clear();
    			}
    		}else {
    			MessageBox(AlertType.ERROR, "Hata", "Þifreler birbiriyle uyuþmuyor.", "Lütfen tekrar deneyiniz.");
				txt_NewPasswordTP2.clear();
				txt_NewPassword2TP2.clear();
    		}
    	}
    	else {
    		if(txt_NewPasswordTP2.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "Þifre boþ olamaz.", "Lütfen tekrar deneyiniz.");
    		}else if(txt_NewPassword2TP2.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "Þifre (Tekrar) boþ olamaz.", "Lütfen tekrar deneyiniz.");
    		}
    	}
    }
    
    public void eposta_guncelle() {
    	if(txt_epostaTP3.getText().contains("@gmail.com") || txt_epostaTP3.getText().contains("@iste.edu.tr") ||
    			txt_epostaTP3.getText().contains("@icloud.com") || txt_epostaTP3.getText().contains("@outlook.com")) {
    		sql = "update kullanicilar set email=? where userID=?";
    		try {
    			sorguIfadesi = baglanti.prepareStatement(sql);
    			sorguIfadesi.setString(1, txt_epostaTP3.getText());
    			sorguIfadesi.setInt(2, LoginController.userID);
    			sorguIfadesi.executeUpdate();
    			txt_epostaTP3.clear();
    			MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "E-Postanýz baþarýlý þekilde güncellendi");
    		} catch (Exception e) {
    			MessageBox(AlertType.ERROR, "Hata", "Beklenmedik bir hata oluþtu.", "Lütfen tekrar deneyiniz.");
    		}
    	}else {
    		MessageBox(AlertType.ERROR, "Hata", "E-Posta yanlýþ girildi.", "Lütfen gmail, iste, icloud, outlook uzantýlý e-postanýzý girin.");
    	}
    }
    
    public void kart_guncelle() {
    	if(!txt_ayTP4.getText().isEmpty() && !txt_cvvTP4.getText().isEmpty() && !txt_kartKayitAdiTP4.getText().isEmpty() &&
        		!txt_KartNoTP4.getText().isEmpty() && !txt_kartUzerindekiAdTP4.getText().isEmpty() && !txt_yilTP4.getText().isEmpty() ) {
    		int index = combo_kayitliKartlarTP4.getSelectionModel().getSelectedIndex();
        	sql = "update kartlar set userID=?, isim=?, kart_no=?,cvv=?,ay=?, yil=?, kayit_adi=? where kartID=?";
        	try {
    			sorguIfadesi = baglanti.prepareStatement(sql);
    			sorguIfadesi.setInt(1, LoginController.userID);
    			sorguIfadesi.setString(2, txt_kartUzerindekiAdTP4.getText());
    			sorguIfadesi.setString(3, txt_KartNoTP4.getText());
    			sorguIfadesi.setString(4, txt_cvvTP4.getText());
    			sorguIfadesi.setString(5, txt_ayTP4.getText());
    			sorguIfadesi.setString(6, txt_yilTP4.getText());
    			sorguIfadesi.setString(7, txt_kartKayitAdiTP4.getText());
    			sorguIfadesi.setString(8, kartIDObservableList.get(index));
    			sorguIfadesi.executeUpdate();
    			MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Kart baþarýlý þekilde güncellendi");
    		} catch (Exception e) {
    			System.out.println(e.getMessage().toString());
    		}
        	verileri_sifirla();
        	kartlari_getir(String.valueOf(LoginController.userID));
    	}
    	else {
    		MessageBox(AlertType.ERROR, "Hata", "Kart bilgilerinde eksik var.", "Lütfen tekrar deneyiniz.");
    	}
    	
	}
    
    public void kart_ekle() {
    	if(!txt_ayTP4.getText().isEmpty() && !txt_cvvTP4.getText().isEmpty() && !txt_kartKayitAdiTP4.getText().isEmpty() &&
        		!txt_KartNoTP4.getText().isEmpty() && !txt_kartUzerindekiAdTP4.getText().isEmpty() && !txt_yilTP4.getText().isEmpty() ) {
        		sql = "insert into kartlar(userID, isim, kart_no, cvv, ay, yil, kayit_adi) values(?,?,?,?,?,?,?)";
        		try {
        			sorguIfadesi = baglanti.prepareStatement(sql);
        			sorguIfadesi.setInt(1, LoginController.userID);
        			sorguIfadesi.setString(2, txt_kartUzerindekiAdTP4.getText());
        			sorguIfadesi.setString(3, txt_KartNoTP4.getText());
        			sorguIfadesi.setString(4, txt_cvvTP4.getText());
        			sorguIfadesi.setString(5, txt_ayTP4.getText());
        			sorguIfadesi.setString(6, txt_yilTP4.getText());
        			sorguIfadesi.setString(7, txt_kartKayitAdiTP4.getText());
        			sorguIfadesi.executeUpdate();
        			MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Kart baþarýlý þekilde eklendi");
        			verileri_sifirla();
        	    	kartlari_getir(String.valueOf(LoginController.userID));
        		} catch (Exception e) {
        			System.out.println(e.getMessage().toString());
        		}
        	}else {
        		MessageBox(AlertType.ERROR, "Hata", "Kart bilgilerinde eksik var.", "Lütfen tekrar deneyiniz.");
        	}
	}
    
    public void verileri_sifirla() {
    	combo_kayitliKartlarTP4.getSelectionModel().clearSelection();
    	combo_kayitliKartlarTP4.getItems().clear();
    	kartAyObservableList.clear();
    	kartCVVObservableList.clear();
    	kartIDObservableList.clear();
    	kartNObservableList.clear();
    	kartUzerindekiIsimObservableList.clear();
    	kartYilObservableList.clear();
    	txt_ayTP4.clear();
    	txt_cvvTP4.clear();
    	txt_cvvTP4.clear();
    	txt_kartKayitAdiTP4.clear();
    	txt_kartUzerindekiAdTP4.clear();
    	txt_KartNoTP4.clear();
    	txt_yilTP4.clear();
    }
    
    public void animasyon_ac() {
    	close_btn.setLayoutX(852);
    	minimize_btn.setLayoutX(820);
    	borderPane.setPrefWidth(905);
    	anchorPane.setPrefWidth(905);
    	tabPage.setLayoutX(117.5);
    	
    }
    
    public void animasyon_kapa() {
    	close_btn.setLayoutX(706);
    	minimize_btn.setLayoutX(674);
    	borderPane.setPrefWidth(760);
    	anchorPane.setPrefWidth(760);
    	tabPage.setLayoutX(45);
	}

}
