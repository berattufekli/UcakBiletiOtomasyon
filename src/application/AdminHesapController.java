package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import com.database.databaseUtil;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminHesapController {
	
	public AdminHesapController(){
		baglanti = databaseUtil.baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button close_btn;

    @FXML
    private TableColumn<adminler_tableview, Integer> column_adminID;

    @FXML
    private TableColumn<adminler_tableview, String> column_password;

    @FXML
    private TableColumn<adminler_tableview, String> column_username;

    @FXML
    private Button insertBtn;

    @FXML
    private Label lbl_adminID;

    @FXML
    private Button minimize_btn;

    @FXML
    private Pane panel_top;

    @FXML
    private Button passwordBtn;

    @FXML
    private Button passwordBtn1;

    @FXML
    private TabPane tabPage;

    @FXML
    private TableView<adminler_tableview> tableView_admin;

    @FXML
    private PasswordField txt_NewPassword2TP2;

    @FXML
    private PasswordField txt_NewPasswordTP2;

    @FXML
    private PasswordField txt_passwordTP4;

    @FXML
    private TextField txt_usernameTP1;

    @FXML
    private TextField txt_usernameTP4;

    @FXML
    private Button updateBtn;

    @FXML
    private Button usernameBtn;
    
    Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql = null;

    @FXML
    void close_btnClick(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void deleteBtnClick(ActionEvent event) {
    	if(tableView_admin.getSelectionModel().getSelectedItem() != null) {
    		MessageBox("Onay Mesajý", "Seçilen admin silinecek.", "Bundan emin misiniz?", "delete_admin");
    	}else {
    		MessageBox(AlertType.ERROR, "Hata", "Admin silmek için tablodan admin seçimi yapmalýsýnýz.");
    	}
    }

    @FXML
    void insertBtnClick(ActionEvent event) {
    	if(!txt_passwordTP4.getText().isEmpty() && !txt_usernameTP4.getText().isEmpty()) {
			if(txt_passwordTP4.getText().length()>=6 && txt_usernameTP4.getText().length()>=5) {
				MessageBox("Onay Mesajý", "Yeni admin eklenecek.", "Bundan emin misiniz?", "add_admin");
			}
			else {
				if(txt_usernameTP4.getText().length()<5) {
    				MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý 5 karakterden az olamaz");
    			}
    			if(txt_passwordTP4.getText().length()<6) {
    				MessageBox(AlertType.ERROR, "Hata", "Þifre 6 karakterden az olamaz.");
    			}
			}
		}else {
			if(txt_usernameTP4.getText().isEmpty()) {
				MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý boþ olamaz");
			}
			if(txt_passwordTP4.getText().isEmpty()) {
				MessageBox(AlertType.ERROR, "Hata", "Þifre boþ olamaz.");
			}
		}
    }

    @FXML
    void minimize_btnClick(ActionEvent event) {
    	Stage stage = (Stage)minimize_btn.getScene().getWindow();
    	stage.setIconified(true);
    }

    @FXML
    void passwordBtnClick(ActionEvent event) {
    	MessageBox("Onay Mesajý", "Þifrenizi güncellemek istiyor musunuz?", "Bundan emin misiniz", "update_password");
    }

    @FXML
    void updateBtnClick(ActionEvent event) {
    	if(tableView_admin.getSelectionModel().getSelectedItem() != null) {
    		if(!txt_passwordTP4.getText().isEmpty() && !txt_usernameTP4.getText().isEmpty()) {
    			if(txt_passwordTP4.getText().length()>=6 && txt_usernameTP4.getText().length()>=5) {
    				MessageBox("Onay Mesajý", "Seçilen admin güncellenecek.", "Bundan emin misiniz?", "update_admin");
    			}
    			else {
    				if(txt_usernameTP4.getText().length()<5) {
        				MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý 6 karakterden az olamaz");
        			}
        			if(txt_passwordTP4.getText().length()<6) {
        				MessageBox(AlertType.ERROR, "Hata", "Þifre 6 karakterden az olamaz.");
        			}
    			}
    		}else {
    			if(txt_usernameTP4.getText().isEmpty()) {
    				MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý boþ olamaz");
    			}
    			if(txt_passwordTP4.getText().isEmpty()) {
    				MessageBox(AlertType.ERROR, "Hata", "Þifre boþ olamaz.");
    			}
    		}
    	}else {
    		MessageBox(AlertType.ERROR, "Hata", "Admin güncellemek için tablodan admin seçimi yapmalýsýnýz.");
    	}
    }

    @FXML
    void usernameBtnClick(ActionEvent event) {
    	if(!txt_usernameTP1.getText().isEmpty()) {
    		if(txt_usernameTP1.getText().length()>=5) {
    			MessageBox("Onay Mesajý", "Kullanýcý adýnýz güncellenecek.", "Bundan emin misiniz", "update_username");
    		}
    		else {
    			MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý 5 karakterden az olamaz");
    		}
    	}else {
    		if(txt_usernameTP1.getText().isEmpty()) {
    			MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý boþ olamaz");
    		}
    	}
    }
    
    @FXML
    void tableview_MouseClick(MouseEvent event) {
    	if(tableView_admin.getSelectionModel().getSelectedItem() != null) {
    		lbl_adminID.setText(String.valueOf(tableView_admin.getSelectionModel().getSelectedItem().adminID));
    		txt_passwordTP4.setText(tableView_admin.getSelectionModel().getSelectedItem().password);
    		txt_usernameTP4.setText(tableView_admin.getSelectionModel().getSelectedItem().username.replace("admin@", ""));
    	}
    }

    @FXML
    void initialize() {
        DegerleriGetir(tableView_admin);
    }
    
    public void DegerleriGetir(TableView table) {
    	ObservableList<adminler_tableview> biletler_liste = FXCollections.observableArrayList();
    	biletler_liste.clear();
    	sql = "select * from admin";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			
			while (getirilen.next()) {
				biletler_liste.add(new adminler_tableview(getirilen.getInt(1), getirilen.getString(2), getirilen.getString(3)));
				column_adminID.setCellValueFactory(new PropertyValueFactory<>("adminID"));
				column_username.setCellValueFactory(new PropertyValueFactory<>("username"));
				column_password.setCellValueFactory(new PropertyValueFactory<>("password"));
				table.setItems(biletler_liste);
			}
		}	
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    public void delete_admin() {
    	sql = "delete from admin where adminID=?";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, lbl_adminID.getText());
			sorguIfadesi.executeUpdate();
		}	
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
		txt_usernameTP4.setText("");
		txt_passwordTP4.setText("");
		lbl_adminID.setText("");
		DegerleriGetir(tableView_admin);
    }
    
    public void add_admin() {
    	sql = "insert into admin(username, password) values(?,?)";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, "admin@" + txt_usernameTP4.getText());
			sorguIfadesi.setString(2, databaseUtil.md5sifrele(txt_passwordTP4.getText()));
			sorguIfadesi.executeUpdate();
			MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Admin baþarýlý þekilde eklendi.",
					"Kullanýcý Adýnýz: admin@" + txt_usernameTP4.getText() + "\nÞifreniz: " + 
			txt_passwordTP4.getText() + " Lütfen unutmayýnýz.");
		}	
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
			MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý daha önceden alýnmýþ.", "Tekrar deneyiniz.");
		}
		txt_usernameTP4.setText("");
		txt_passwordTP4.setText("");
		lbl_adminID.setText("");
		DegerleriGetir(tableView_admin);
    }
    
    public void update_admin() {
    	sql = "update admin set username=?, password=? where adminID=?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, "admin@" + txt_usernameTP4.getText());
			sorguIfadesi.setString(2, databaseUtil.md5sifrele(txt_passwordTP4.getText()));
			sorguIfadesi.setString(3, lbl_adminID.getText());
			sorguIfadesi.executeUpdate();
			MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Admin baþarýlý þekilde güncellendi.",
					"Kullanýcý Adýnýz: admin@" + txt_usernameTP4.getText() + "\nÞifreniz: " + 
			txt_passwordTP4.getText() + " Lütfen unutmayýnýz.");
		}catch (Exception e) {
			System.out.println(e.getMessage().toString());
			MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý daha önceden alýnmýþ.", "Tekrar deneyiniz.");
		}
		txt_usernameTP4.setText("");
		txt_passwordTP4.setText("");
		lbl_adminID.setText("");
		DegerleriGetir(tableView_admin);
    }
    
    public void update_username() {
    	sql = "update admin set username=? where adminID=?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, "admin@" + txt_usernameTP1.getText());
			sorguIfadesi.setInt(2, LoginController.adminID);
			sorguIfadesi.executeUpdate();
			
			MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Kullanýcý adý baþarýlý þekilde güncellendi.",
					"Kullanýcý Adýnýz: admin@" + txt_usernameTP1.getText().replace("admin@", ""));
		}catch (Exception e) {
			System.out.println(e.getMessage().toString());
			if(e.getMessage().toString().contains("duplicate")) {
				MessageBox(AlertType.ERROR, "Hata", "Kullanýcý adý daha önceden alýnmýþ.", "Tekrar deneyiniz.");
			}else {
				MessageBox(AlertType.ERROR, "Hata", "Þifreniz yanlýþ.", "Tekrar deneyiniz.");
			}
			
		}
		txt_usernameTP1.setText("");
		DegerleriGetir(tableView_admin);
    }
    
    public void update_password() {
    	if(!txt_NewPasswordTP2.getText().isEmpty() && !txt_NewPassword2TP2.getText().isEmpty()) {
    		if(txt_NewPasswordTP2.getText().equals(txt_NewPassword2TP2.getText())) {
    			if(txt_NewPasswordTP2.getText().length() >= 6) {
    				sql = "update admin set password=? where adminID=?";
    		    	try {
    					sorguIfadesi = baglanti.prepareStatement(sql);
    					sorguIfadesi.setString(1, databaseUtil.md5sifrele(txt_NewPasswordTP2.getText()));
    					sorguIfadesi.setInt(2, LoginController.adminID);
    					sorguIfadesi.executeUpdate();
    					MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Þifre baþarýyla güncellendi");
    				}catch (Exception e) {
    					System.out.println(e.getMessage().toString());
    					MessageBox(AlertType.ERROR, "Hata", "Bilinmeyen bir hata oluþtu.", "Lütfen tekrar deneyiniz.");
    				}
    				txt_usernameTP4.setText("");
    				txt_passwordTP4.setText("");
    				lbl_adminID.setText("");
    				DegerleriGetir(tableView_admin);
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
    	
    	DegerleriGetir(tableView_admin);
    }
    
    private void MessageBox(AlertType alerttype, String title, String header) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.showAndWait();
    }
    
    private void MessageBox(AlertType alerttype, String title, String header, String content) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
    
    private void MessageBox(String title, String header, String content, String fonksiyon) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);

    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayýr");
    	alert.getButtonTypes().setAll(btn1, btn2);
    	alert.getButtonTypes().setAll(btn1, btn2);
    	
    	Optional<ButtonType> sonuc= alert.showAndWait();
    	if(sonuc.get()==btn1) {
    		if(fonksiyon.equals("delete_admin")) {
    			delete_admin();
    		}
    		else if(fonksiyon.equals("add_admin")) {
    			add_admin();
    		}
    		else if(fonksiyon.equals("update_admin")) {
    			update_admin();
    		}
    		else if(fonksiyon.equals("update_username")) {
    			update_username();
    		}
    		else if(fonksiyon.equals("update_password")) {
    			update_password();
    		}
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("Ýptal Edildi.");
    	}	
    }

}
