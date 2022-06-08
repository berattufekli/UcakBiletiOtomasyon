package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.animation.animate;
import com.database.databaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OdemeYapController {
	
	public OdemeYapController() {
		baglanti = databaseUtil.baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_pay;

    @FXML
    private CheckBox check_kaydet;

    @FXML
    private Button close_btn;

    @FXML
    private ComboBox<String> combo_kayitliKartlar;

    @FXML
    private Label lbl_gizli;

    @FXML
    private Label lbl_tutar;

    @FXML
    private ListView<String> listview_koltuklar;

    @FXML
    private Button minimize_btn;

    @FXML
    private TextField txt_KartNo;

    @FXML
    private TextField txt_ay;

    @FXML
    private TextField txt_cvv;

    @FXML
    private TextField txt_kartKayitAdi;

    @FXML
    private TextField txt_kartUzerindekiAd;

    @FXML
    private TextField txt_yil;
    
    Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql = null;
	
	List<String> GidisSecilenKoltukList = KoltukSecmeController.GidisSecilenKoltukList;
    List<String> DonusSecilenKoltukList = KoltukSecmeController.DonusSecilenKoltukList;
    
    List<String> selectedGidis = AnasayfaController.selectedGidis;
	List<String> selectedDonus = AnasayfaController.selectedDonus;
	

	KoltukSecmeController controller;
	
	ObservableList<String> kartUzerindekiIsimObservableList = FXCollections.observableArrayList(); 
	ObservableList<String> kartNObservableList = FXCollections.observableArrayList();
	ObservableList<String> kartCVVObservableList = FXCollections.observableArrayList();
	ObservableList<String> kartAyObservableList = FXCollections.observableArrayList();
	ObservableList<String> kartYilObservableList = FXCollections.observableArrayList();
	ObservableList<String> kartKayitAdiObservableList = FXCollections.observableArrayList();
	
    @FXML
    void check_kaydet_click(ActionEvent event) {
    	if(check_kaydet.isSelected()) {
    		animate.fade_in(lbl_gizli);
    		animate.fade_in(txt_kartKayitAdi);
    	}else {
    		animate.fade_out(lbl_gizli);
    		animate.fade_out(txt_kartKayitAdi);
    	}
    }

    @FXML
    void combo_kayitliKartlar_click(ActionEvent event) {
    	if(combo_kayitliKartlar.getSelectionModel().getSelectedIndex()>=0) {
    		int index = combo_kayitliKartlar.getSelectionModel().getSelectedIndex();
        	txt_kartUzerindekiAd.setText(kartUzerindekiIsimObservableList.get(index));
        	txt_ay.setText(kartAyObservableList.get(index));
        	txt_yil.setText(kartYilObservableList.get(index));
        	txt_kartKayitAdi.setText(kartKayitAdiObservableList.get(index));
        	txt_cvv.setText(kartCVVObservableList.get(index));
        	txt_KartNo.setText(kartNObservableList.get(index));
    	}
    }

    @FXML
    void btn_pay_click(ActionEvent event) {
    	
    	if(!txt_kartUzerindekiAd.getText().isEmpty() && txt_KartNo.getText().length() == 19 && txt_ay.getText().length() == 2 &&
    		txt_yil.getText().length() == 4 && txt_cvv.getText().length() == 3) {
    		if(selectedGidis.get(0).equals("Tek Yön")) {
    			MessageBox();
    		}else if(selectedDonus.get(0).equals("Gidiş Dönüş")) {
    			MessageBox("Gidiş Dönüş");
    		}
    	}
    	
    }
    
    public void biletleri_kaydet(List<String> koltukList, List<String> selectedList) {
    	for(int i=0; i<koltukList.size(); i++) {
			sql = "insert into biletler(userID, nereden, nereye, tarih, saat, ucret, koltuk_no) values(?,?,?,?,?,?,?)";
			try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setString(1, String.valueOf(LoginController.userID));
				sorguIfadesi.setString(2, selectedList.get(1));
				sorguIfadesi.setString(3, selectedList.get(2));
				sorguIfadesi.setString(4, selectedList.get(5));
				sorguIfadesi.setString(5, selectedList.get(3));
				sorguIfadesi.setString(6, selectedList.get(4).replace("₺", ""));
				sorguIfadesi.setString(7, koltukList.get(i));
				sorguIfadesi.executeUpdate();
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
		}
    	MessageBox(AlertType.CONFIRMATION, "Başarılı", "Biletler başarılı şekilde alındı.");
    	koltukList.clear();
    	ucus_sifirla(AnasayfaController.donusPaneller);
    	ucus_sifirla(AnasayfaController.gidisPaneller);
    	Stage stage = (Stage)close_btn.getScene().getWindow();
    	stage.close();
	}
    
    private void ucus_sifirla(List<Pane> panels) {
    	for(int i=0; i<panels.size(); i++) {
    		try {
				panels.get(i).getStyleClass().remove("ucus-button-selected");
				panels.get(i).getStyleClass().add("ucus-button");
				
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    	}
    }
    
    public void karti_kaydet(String userID, String kartisim, String kartno,String kartcvv,
    						 String kart_ay, String kart_yil, String kartkayit) {
    	combo_kayitliKartlar.getItems().clear();
    	kartUzerindekiIsimObservableList.clear();
    	kartAyObservableList.clear();
    	kartCVVObservableList.clear();
    	kartYilObservableList.clear();
    	kartKayitAdiObservableList.clear();
    	kartNObservableList.clear();
		sql = "insert into kartlar(userID, isim, kart_no, cvv, ay, yil, kayit_adi) values(?,?,?,?,?,?,?)";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, userID);
			sorguIfadesi.setString(2, kartisim);
			sorguIfadesi.setString(3, kartno);
			sorguIfadesi.setString(4, kartcvv);
			sorguIfadesi.setString(5, kart_ay);
			sorguIfadesi.setString(6, kart_yil);
			sorguIfadesi.setString(7, kartkayit);
			sorguIfadesi.executeUpdate();
			System.out.println("Biletler Başarıyla eklendi");
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
    
    @FXML
    void close_btnClick(ActionEvent event) {
    	Stage stage = (Stage)close_btn.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void minimize_btnClick(ActionEvent event) {
    	Stage stage = (Stage)close_btn.getScene().getWindow();
    	stage.setIconified(true);
    }

    @FXML
    void initialize() {
    	kartlari_getir(String.valueOf(LoginController.userID));
    	
    	
    	
    	if(GidisSecilenKoltukList.size() > 0 && DonusSecilenKoltukList.size() == 0) {
    		int toplam = 0;
    		for(int i=0; i<GidisSecilenKoltukList.size(); i++) {
    			listview_koltuklar.getItems().add("Koltuk: " + GidisSecilenKoltukList.get(i) + " - Tutar: " 
    		+ selectedGidis.get(4));
    			toplam += Integer.valueOf(selectedGidis.get(4).replace("₺", ""));
    		}
    		lbl_tutar.setText("Toplam Tutar: " + String.valueOf(toplam) + "₺");
    	}
    	else if(GidisSecilenKoltukList.size() > 0 && DonusSecilenKoltukList.size() > 0){
    		int toplam = 0;
    		for(int i=0; i<GidisSecilenKoltukList.size(); i++) {
    			listview_koltuklar.getItems().add("Koltuk: " + GidisSecilenKoltukList.get(i) + " - Tutar: " 
    					+ selectedGidis.get(4));
    			toplam += Integer.valueOf(selectedGidis.get(4).replace("₺", ""));
    			listview_koltuklar.getItems().add("Koltuk: " + DonusSecilenKoltukList.get(i) + " - Tutar: " 
    		    		+ selectedDonus.get(4));
    		    toplam += Integer.valueOf(selectedDonus.get(4).replace("₺", ""));
    		}
    		lbl_tutar.setText("Toplam Tutar: " + String.valueOf(toplam) + "₺");
    	}
    	
    }
    
    public void kartlari_getir(String userID) {
    	sql = "select * from kartlar where userID=?";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, userID);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			
			while (getirilen.next()) {
				kartUzerindekiIsimObservableList.add(getirilen.getString("isim"));
				kartNObservableList.add(getirilen.getString("kart_no"));
				kartCVVObservableList.add(getirilen.getString("cvv"));
				kartAyObservableList.add(getirilen.getString("ay"));
				kartYilObservableList.add(getirilen.getString("yil"));
				kartKayitAdiObservableList.add(getirilen.getString("kayit_adi"));
				combo_kayitliKartlar.setItems(kartKayitAdiObservableList);
			}
		}
		catch (Exception e) {
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
    
    private void MessageBox(AlertType alerttype, String title, String header) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.showAndWait();
    }

    private void MessageBox() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Bilgilendirme");
    	alert.setHeaderText(lbl_tutar.getText() + " tutarında satın alım gerçekleşecek.");
    	alert.setContentText("Bu işlemi onaylıyor musunuz?");

    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayır");
    	alert.getButtonTypes().setAll(btn1, btn2);
    	alert.getButtonTypes().setAll(btn1, btn2);
    	
    	Optional<ButtonType> sonuc= alert.showAndWait();
    	if(sonuc.get()==btn1) {
    		try {
    			biletleri_kaydet(GidisSecilenKoltukList, selectedGidis);
    			if(check_kaydet.isSelected() && kartKayitAdiObservableList.size() < 5) {
    				karti_kaydet(String.valueOf(LoginController.userID), txt_kartKayitAdi.getText(), txt_KartNo.getText(), txt_cvv.getText(),
    						txt_ay.getText(), txt_yil.getText(), txt_kartKayitAdi.getText());
    				kartlari_getir("1");
    			}else if(check_kaydet.isSelected() && kartKayitAdiObservableList.size() >= 5) {
    				MessageBox(AlertType.ERROR, "Hata", "Maksimum 5 kart kayıt edilebilir.");
    			}
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("İptal Edildi.");
    	}	
    }
    
    private void MessageBox(String yön) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Bilgilendirme");
    	alert.setHeaderText(lbl_tutar.getText() + " tutarında satın alım gerçekleşecek.");
    	alert.setContentText("Bu işlemi onaylıyor musunuz?");

    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayır");
    	alert.getButtonTypes().setAll(btn1, btn2);
    	alert.getButtonTypes().setAll(btn1, btn2);
    	
    	Optional<ButtonType> sonuc= alert.showAndWait();
    	if(sonuc.get()==btn1) {
    		try {
    			biletleri_kaydet(GidisSecilenKoltukList, selectedGidis);
    			biletleri_kaydet(DonusSecilenKoltukList, selectedDonus);
    			if(check_kaydet.isSelected() && kartKayitAdiObservableList.size() < 5) {
    				karti_kaydet("1", txt_kartKayitAdi.getText(), txt_KartNo.getText(), txt_cvv.getText(),
    						txt_ay.getText(), txt_yil.getText(), txt_kartKayitAdi.getText());
    				kartlari_getir("1");
    			}else if(check_kaydet.isSelected() && kartKayitAdiObservableList.size() >= 5) {
    				MessageBox(AlertType.ERROR, "Hata", "Maksimum 5 kart kayıt edilebilir.");
    			}
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    		
    		
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("İptal Edildi.");
    	}	
    }
}
