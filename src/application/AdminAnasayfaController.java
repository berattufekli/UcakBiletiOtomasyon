package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.BitSet;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.DTD;

import com.database.databaseUtil;
import com.mysql.cj.protocol.Warning;
import com.mysql.cj.result.IntegerValueFactory;
import com.mysql.cj.result.ValueFactory;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminAnasayfaController {
	
	public AdminAnasayfaController() {
		baglanti = databaseUtil.baglan();
	}
	
	String styleString = "-fx-font-family:'Noto Sans';"
	   		   + "-fx-font-weight: bold;"
	   		   + "-fx-font-size: 14px;";
	
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button close_btn;

    @FXML
    private HBox hbox;
    
    @FXML
    private Pane hbox_inside;

    @FXML
    private Pane hbox_inside2;

    @FXML
    private Pane hbox_pane;

    @FXML
    private Pane hbox_pane2;

    @FXML
    private ComboBox<Integer> indirimMiktari1;

    @FXML
    private BorderPane insideBorderPane;

    @FXML
    private Pane insidePanel_gelirler;

    @FXML
    private Label lbl_ay;

    @FXML
    private Label lbl_hafta;

    @FXML
    private Label lbl_tum;

    @FXML
    private Label mevcutFiyat1;

    @FXML
    private Label mevcutFiyat2;

    @FXML
    private Button minimize_btn;

    @FXML
    private Button onayla1;

    @FXML
    private Button onayla2;

    @FXML
    private Pane panel_top;

    @FXML
    private ComboBox<String> sehirlerCombo1;

    @FXML
    private ComboBox<String> sehirlerCombo2;

    @FXML
    private ComboBox<Integer> zamMiktari1;

    
    Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql = null;
	
	ObservableList<String> sehirlerObservableList = FXCollections.observableArrayList();
	ObservableList<Integer> ucretlerObservableList = FXCollections.observableArrayList();
	ObservableList<Integer> zam = FXCollections.observableArrayList();

    @FXML
    void close_btnClick(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void minimize_btnClick(ActionEvent event) {
    	Stage stage = (Stage)minimize_btn.getScene().getWindow();
    	stage.setIconified(true);
    }

    @FXML
    void onayla1_Click(ActionEvent event) {
    	MessageBox("Onay Mesajý", "Zam yapmak istediðinizden emin misiniz", "sehir_zam");
    }

    @FXML
    void onayla2_Click(ActionEvent event) {
    	MessageBox("Onay Mesajý", "Ýndirim yapmak istediðinizden emin misiniz", "sehir_indirim");
    }
   

    @FXML
    void sehirlerCombo1_Click(ActionEvent event) {
    	try {
    		int index = sehirlerCombo1.getSelectionModel().getSelectedIndex();
        	String ucret = ucretlerObservableList.get(index).toString();
        	mevcutFiyat1.setText(ucret);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    	
    }

    @FXML
    void sehirlerCombo2_Click(ActionEvent event) {
    	try {
    		int index = sehirlerCombo2.getSelectionModel().getSelectedIndex();
        	String ucret = ucretlerObservableList.get(index).toString();
        	mevcutFiyat2.setText(ucret);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}

    }
    
    @FXML
    void initialize() {
    	zamMiktari1.setStyle(styleString);
    	indirimMiktari1.setStyle(styleString);
    	
    	for(int i=5; i<=100; i+=5) {
    		zam.add(i);
    	}
    	
    	zamMiktari1.getItems().setAll(zam);
    	indirimMiktari1.getItems().setAll(zam);
    	
    	sehirleri_getir();
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    	LocalDateTime now = LocalDateTime.now(); 
    	LocalDateTime weekBefore = now.minusWeeks(1);
    	LocalDateTime monthBefore = now.minusMonths(1);
    	System.out.println(dtf.format(now));
    	System.out.println(dtf.format(weekBefore));
    	gelirleri_getir(dtf.format(weekBefore).toString() , dtf.format(now).toString() , lbl_hafta);
    	gelirleri_getir(dtf.format(monthBefore), dtf.format(now), lbl_ay);
    	gelirleri_getir(lbl_tum);
    }
    
    public void sehirleri_getir() {
    	sehirlerCombo1.getItems().clear();
		sehirlerCombo2.getItems().clear();
		sehirlerObservableList.clear();
		ucretlerObservableList.clear();
		
    	sql = "select * from sehirler";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			
			while (getirilen.next()) {
				sehirlerObservableList.add(getirilen.getString("sehir_adi"));
				ucretlerObservableList.add(getirilen.getInt("ucret"));
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
		sehirlerCombo1.setItems(sehirlerObservableList);
    	sehirlerCombo2.setItems(sehirlerObservableList);
    	sehirlerCombo1.setStyle(styleString);
    	sehirlerCombo2.setStyle(styleString);
    }
    
    public void sehir_zam_yap() {
    	if(zamMiktari1.getSelectionModel().getSelectedItem() != null) {
    		int ucret = Integer.valueOf(mevcutFiyat1.getText());
    		ucret = ucret + (ucret*zamMiktari1.getSelectionModel().getSelectedItem()/100);
    		String sehir =  sehirlerCombo1.getSelectionModel().getSelectedItem();
    		System.out.println(ucret);
    		sql = "update sehirler set ucret=? where sehir_adi=?";
    		try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setInt(1, ucret);
				sorguIfadesi.setString(2, sehir);
				sorguIfadesi.executeUpdate();
				MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Zam miktarý seçilen þehire baþarýlý þekilde yapýldý.");
				mevcutFiyat1.setText("");
				sehirleri_getir();
				zamMiktari1.getSelectionModel().clearSelection();
			
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    		
    		sql = "update ucuslar set ucret=? where nereden=?";
    		try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setInt(1, ucret);
				sorguIfadesi.setString(2, sehir);
				sorguIfadesi.executeUpdate();
			
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    	}
    	else {
    		MessageBox(AlertType.ERROR,"Hata" , "Zam yapmak için zam miktarý seçiniz");
    	}
	}
    
    public void sehir_indirim_yap() {
    	if(indirimMiktari1.getSelectionModel().getSelectedItem() != null) {
    		int ucret = Integer.valueOf(mevcutFiyat2.getText());
    		ucret = ucret - (ucret*indirimMiktari1.getSelectionModel().getSelectedItem()/100);
    		String sehir =  sehirlerCombo2.getSelectionModel().getSelectedItem();
    		System.out.println(ucret);
    		sql = "update sehirler set ucret=? where sehir_adi=?";
    		try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setInt(1, ucret);
				sorguIfadesi.setString(2, sehir);
				sorguIfadesi.executeUpdate();
				MessageBox(AlertType.CONFIRMATION, "Baþarýlý", "Ýndirim miktarý seçilen þehire baþarýlý þekilde yapýldý.");
				mevcutFiyat2.setText("");
				sehirleri_getir();
				indirimMiktari1.getSelectionModel().clearSelection();
			
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    		
    		sql = "update ucuslar set ucret=? where nereden=?";
    		try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setInt(1, ucret);
				sorguIfadesi.setString(2, sehir);
				sorguIfadesi.executeUpdate();
			
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    	}
    	else {
    		MessageBox(AlertType.ERROR,"Hata" , "Ýndirim yapmak için indirim miktarý seçiniz");
    	}
	}

    private void MessageBox(AlertType alerttype, String title, String header) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.showAndWait();
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
    		if(fonksiyon.equals("sehir_zam")) {
    			sehir_zam_yap();
    		}
    		else if(fonksiyon.equals("sehir_indirim")) {
    			sehir_indirim_yap();
    		}	
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("Ýptal Edildi.");
    	}	
    }  
    
    public void gelirleri_getir(String baslangic, String bitis, Label lbl) {
    	sql = "select sum(ucret) from biletler where tarih between ? and ?";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, baslangic);
			sorguIfadesi.setString(2, bitis);
		 	getirilen = sorguIfadesi.executeQuery();
			if(getirilen.next()) {
				lbl.setText(String.valueOf(getirilen.getInt(1)));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    public void gelirleri_getir(Label lbl) {
    	sql = "select sum(ucret) from biletler";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
		 	getirilen = sorguIfadesi.executeQuery();
			if(getirilen.next()) {
				lbl.setText(String.valueOf(getirilen.getInt(1)));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    public void animasyon_ac() {
    	close_btn.setLayoutX(852);
    	minimize_btn.setLayoutX(820);
    	borderPane.setPrefWidth(905);
    	anchorPane.setPrefWidth(905);
    	panel_top.setPrefWidth(905);
    	insidePanel_gelirler.setLayoutX(72.5);
    	insideBorderPane.setPrefWidth(905);
    	hbox.setPrefWidth(905);
    	hbox_pane.setPrefWidth(500);
    	hbox_pane2.setPrefWidth(500);
    	hbox_inside.setLayoutX(72.5);	
    }
    
    public void animasyon_kapa() {
    	close_btn.setLayoutX(706);
    	minimize_btn.setLayoutX(674);
    	borderPane.setPrefWidth(760);
    	anchorPane.setPrefWidth(760);
    	hbox.setPrefWidth(760);
    	insidePanel_gelirler.setLayoutX(0);
    	insideBorderPane.setPrefWidth(7);
    	hbox.setPrefWidth(760);
    	hbox_pane.setPrefWidth(365);
    	hbox_pane2.setPrefWidth(365);
    	hbox_inside.setLayoutX(0);
	}
}
