package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AnasayfaController {
	private double x;
	private double y;

	public AnasayfaController(){
		baglanti = databaseUtil.baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ara;

    @FXML
    private Button btn_koltuksec;

    @FXML
    private Button btn_reset;

    @FXML
    private Pane child_panel;

    @FXML
    private Button close_btn;

    @FXML
    private ComboBox<String> combo_nereden;

    @FXML
    private ComboBox<String> combo_nereye;

    @FXML
    private DatePicker datetime_donus;

    @FXML
    private DatePicker datetime_gidis;

    @FXML
    private Label donus1nereden;

    @FXML
    private Label donus1nereye;

    @FXML
    private Pane donus1panel;

    @FXML
    private Label donus1saat;

    @FXML
    private Label donus1ucret;

    @FXML
    private Label donus2nereden;

    @FXML
    private Label donus2nereye;

    @FXML
    private Pane donus2panel;

    @FXML
    private Label donus2saat;

    @FXML
    private Label donus2ucret;

    @FXML
    private Label donus3nereden;

    @FXML
    private Label donus3nereye;

    @FXML
    private Pane donus3panel;

    @FXML
    private Label donus3saat;

    @FXML
    private Label donus3ucret;

    @FXML
    private Label donus4nereden;

    @FXML
    private Label donus4nereye;

    @FXML
    private Pane donus4panel;

    @FXML
    private Label donus4saat;

    @FXML
    private Label donus4ucret;

    @FXML
    private Pane fifthrow;

    @FXML
    private Pane firstrow;

    @FXML
    private VBox flightsVBox;

    @FXML
    private Pane fourthrow;

    @FXML
    private Label gidis1nereden;

    @FXML
    private Label gidis1nereye;

    @FXML
    private Pane gidis1panel;

    @FXML
    private Label gidis1saat;

    @FXML
    private Label gidis1ucret;

    @FXML
    private Label gidis2nereden;

    @FXML
    private Label gidis2nereye;

    @FXML
    private Pane gidis2panel;

    @FXML
    private Label gidis2saat;

    @FXML
    private Label gidis2ucret;

    @FXML
    private Label gidis3nereden;

    @FXML
    private Label gidis3nereye;

    @FXML
    private Pane gidis3panel;

    @FXML
    private Label gidis3saat;

    @FXML
    private Label gidis3ucret;

    @FXML
    private Label gidis4nereden;

    @FXML
    private Label gidis4nereye;

    @FXML
    private Pane gidis4panel;

    @FXML
    private Label gidis4saat;

    @FXML
    private Label gidis4ucret;

    @FXML
    private RadioButton gidisDonus;
    
    @FXML
    private Pane inside_panel;

    @FXML
    private Pane inside_panel1;

    @FXML
    private Pane inside_panel2;

    @FXML
    private Pane inside_panel3;

    @FXML
    private Pane inside_panel4;
    
    @FXML
    private Pane inside_panel5;

    @FXML
    private Pane labelPanel;

    @FXML
    private Label lbl_donus;

    @FXML
    private Label lbl_gidis;

    @FXML
    private AnchorPane main_panel_anchor_pane;

    @FXML
    private Button minimize_btn;

    @FXML
    private Pane parent_panel;

    @FXML
    private Pane secondrow;

    @FXML
    private RadioButton tekYon;

    @FXML
    private Pane thirdrow;

    @FXML
    private Spinner<Integer> yolcular;
    
    private boolean firstclick = false;
    private boolean tekyon = false;
    private boolean gidisdonus = false;
    private boolean tekyonselect = false;
    private boolean gidisdonusselect = false;
    
    Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql = null;
	
	public static List<String> selectedGidis = new ArrayList<>();
	public static List<String> selectedDonus = new ArrayList<>();
	
	public static List<String> ucus_bilgileriList = new ArrayList<>();
	
	String yon = null;
	String nereden = null;
	String nereye = null;
	String saat = null;
	String ucret = null;
	String gidisTarih = null;
	String donusTarih = null;
	String yolcuSayisi = null;
	
	List<Label> gidis_nereye_gunler =new ArrayList<Label>();
	List<Label> gidis_nereden_gunler =new ArrayList<Label>();
	List<Label> gidis_saat =new ArrayList<Label>();
    List<Label> gidis_ucret =new ArrayList<Label>();
    List<Label> donus_nereye_gunler =new ArrayList<Label>();
    List<Label> donus_nereden_gunler =new ArrayList<Label>();
    List<Label> donus_saat =new ArrayList<Label>();
    List<Label> donus_ucret =new ArrayList<Label>();
    
    public static List<Pane> gidisPaneller = new ArrayList<Pane>();
    public static List<Pane> donusPaneller = new ArrayList<Pane>();
    
    KoltukSecmeController controller;

    @FXML
    void YonClick(ActionEvent event) {
    	if(tekYon.isSelected()) {		
			gidisDonus.setStyle("-fx-text-fill: #a6abb7");
			tekYon.setStyle("-fx-text-fill:black;");
			datetime_donus.setDisable(true);
			datetime_gidis.setDisable(false);
			combo_nereden.setDisable(false);
			combo_nereye.setDisable(false);
			yolcular.setDisable(false);
			btn_ara.setDisable(false);
			selectedGidis.set(0, "Tek Yön");
			ucus_sifirla(gidisPaneller);
			ucus_sifirla(donusPaneller);
    	}
    	else {
			tekYon.setStyle("-fx-text-fill: #a6abb7");
			gidisDonus.setStyle("-fx-text-fill:black;");
			datetime_donus.setDisable(false);
			datetime_gidis.setDisable(false);
			combo_nereden.setDisable(false);
			combo_nereye.setDisable(false);
			yolcular.setDisable(false);
			btn_ara.setDisable(false);
			selectedGidis.set(0, "Gidiş Dönüş");
			selectedDonus.set(0, "Gidiş Dönüş");
			ucus_sifirla(gidisPaneller);
			ucus_sifirla(donusPaneller);
    	}
    }

    @FXML
    void btn_ara_click(ActionEvent event) {
    	if(combo_nereden.getSelectionModel().getSelectedItem() == null) {
    		MessageBox(AlertType.ERROR, "Hata", "Kalkış yapılacak şehir seçilmedi.", "Lütfen bir seçim yapınız.");
    	}else if(combo_nereye.getSelectionModel().getSelectedItem() == null) {
    		MessageBox(AlertType.ERROR, "Hata", "Uçuş yapılacak şehir seçilmedi.", "Lütfen bir seçim yapınız.");
    	}else {
    		if(combo_nereden.getSelectionModel().getSelectedIndex() != combo_nereye.getSelectionModel().getSelectedIndex()) {
    			if(tekYon.isSelected()) {
    				if(datetime_gidis.getValue() != null) {
    					firstrow.setDisable(false);
    			    	secondrow.setDisable(false);
    			    	thirdrow.setDisable(false);
    			    	fourthrow.setDisable(false);
    			    	fifthrow.setDisable(false);
    			    	labelPanel.setDisable(false);
    			    	animate.fade_in(firstrow);
    			    	animate.fade_in(secondrow);
    			    	animate.fade_in(thirdrow);
    			    	animate.fade_in(fourthrow);
    			    	animate.fade_in(fifthrow);
    			    	animate.fade_in(labelPanel);
    					tekyonselected();
        				ucus_bilgilerini_getir(combo_nereden.getSelectionModel().getSelectedItem(), combo_nereye.getSelectionModel().getSelectedItem(),
        						gidis_nereye_gunler, gidis_nereden_gunler, gidis_saat, gidis_ucret);
    				}else {
    					MessageBox(AlertType.ERROR, "Hata", "Gidiş tarihi girilmedi.", "Lütfen bir seçim yapınız.");
    				}
    				
    			}else if(gidisDonus.isSelected()) {
    				if(datetime_gidis.getValue() != null && datetime_donus.getValue() != null) {
    					firstrow.setDisable(false);
    			    	secondrow.setDisable(false);
    			    	thirdrow.setDisable(false);
    			    	fourthrow.setDisable(false);
    			    	fifthrow.setDisable(false);
    			    	labelPanel.setDisable(false);
    			    	animate.fade_in(firstrow);
    			    	animate.fade_in(secondrow);
    			    	animate.fade_in(thirdrow);
    			    	animate.fade_in(fourthrow);
    			    	animate.fade_in(fifthrow);
    			    	animate.fade_in(labelPanel);
        				gidisdonusselected();
        				ucus_bilgilerini_getir(combo_nereden.getSelectionModel().getSelectedItem(), combo_nereye.getSelectionModel().getSelectedItem(),
        						gidis_nereye_gunler, gidis_nereden_gunler, gidis_saat, gidis_ucret);
        				ucus_bilgilerini_getir(combo_nereye.getSelectionModel().getSelectedItem(), combo_nereden.getSelectionModel().getSelectedItem(),
        						donus_nereye_gunler, donus_nereden_gunler, donus_saat, donus_ucret);
    				}
    				else if(datetime_gidis.getValue() == null) {
    					MessageBox(AlertType.ERROR, "Hata", "Gidiş tarihi girilmedi.", "Lütfen bir seçim yapınız.");
    				}else {
    					MessageBox(AlertType.ERROR, "Hata", "Dönüş tarihi girilmedi.", "Lütfen bir seçim yapınız.");
    				}
    			}else {
    				MessageBox(AlertType.ERROR, "Hata", "Tek yön veya gidiş dönüş seçili değil.", "Lütfen bir seçim yapınız.");
    			}
    		}else {
    			MessageBox(AlertType.ERROR, "Hata", "Kalkış ve uçuş yeri aynı olamaz.", "Lütfen tekrar deneyiniz");
    		}
    	}
    }
    

    @FXML
    void btn_koltuksec_click(ActionEvent event) {
    	if(tekYon.isSelected()) {
    		if(!selectedGidis.isEmpty()) {
    			MessageBox(selectedGidis);
    		}
    		else {
    			MessageBox(AlertType.ERROR, "Hata", "Sefer seçimi yapmadınız.", "Lütfen tekar deneyiniz");
    		}
    	}
    	else if(gidisDonus.isSelected()) {
    		if(!selectedGidis.isEmpty() || !selectedDonus.isEmpty()) {
    			MessageBox(selectedGidis, selectedDonus);
    		}
    		else {
    			MessageBox(AlertType.ERROR, "Hata", "Sefer seçimi yapmadınız.", "Lütfen tekar deneyiniz");
    		}
    	}
    }

    @FXML
    void btn_reset_click(ActionEvent event) {
    	ucus_secme(gidisPaneller);
    	ucus_secme(donusPaneller);
    	combo_nereden.setValue("");
    	combo_nereye.setValue("");
    	datetime_gidis.setValue(null);;
    	datetime_donus.setValue(null);
    	animate.fade_out(firstrow);
    	animate.fade_out(secondrow);
    	animate.fade_out(thirdrow);
    	animate.fade_out(fourthrow);
    	animate.fade_out(fifthrow);
    	animate.fade_out(labelPanel);
    	firstrow.setDisable(true);
    	secondrow.setDisable(true);
    	thirdrow.setDisable(true);
    	fourthrow.setDisable(true);
    	fifthrow.setDisable(true);
    	combo_nereden.setDisable(true);
    	combo_nereye.setDisable(true);
    	datetime_donus.setDisable(true);
    	datetime_gidis.setDisable(true);
    	yolcular.setDisable(true);
    	btn_ara.setDisable(true);
    	labelPanel.setDisable(true);
    	tekYon.setSelected(false);
    	gidisDonus.setSelected(false);
    	gidisDonus.setStyle("-fx-text-fill: #a6abb7");
		tekYon.setStyle("-fx-text-fill: #a6abb7;");
		ucus_sifirla(gidisPaneller);
		ucus_sifirla(donusPaneller);
    }

    @FXML
    void close_btnClick(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void gidis1click(MouseEvent event) {
    	ucus_secme(gidisPaneller, 0);
    	selectedGidis.set(3, gidis1saat.getText());
    	selectedGidis.set(4, gidis1ucret.getText());
    	ucus_bilgilerini_yazdir(selectedGidis);
    }

    @FXML
    void gidis2click(MouseEvent event) {
    	ucus_secme(gidisPaneller, 1);
    	selectedGidis.set(3, gidis2saat.getText());
    	selectedGidis.set(4, gidis2ucret.getText());
    	ucus_bilgilerini_yazdir(selectedGidis);
    }

    @FXML
    void gidis3click(MouseEvent event) {
    	ucus_secme(gidisPaneller, 2);
    	selectedGidis.set(3, gidis3saat.getText());
    	selectedGidis.set(4, gidis3ucret.getText());
    	ucus_bilgilerini_yazdir(selectedGidis);
    }

    @FXML
    void gidis4click(MouseEvent event) {
    	ucus_secme(gidisPaneller, 3);
    	selectedGidis.set(3, gidis4saat.getText());
    	selectedGidis.set(4, gidis4ucret.getText());
    	ucus_bilgilerini_yazdir(selectedGidis);
    }
    

    @FXML
    void donus1click(MouseEvent event) {
    	ucus_secme(donusPaneller, 0);
    	selectedDonus.set(3, donus1saat.getText());
    	selectedDonus.set(4, donus1ucret.getText());
    	ucus_bilgilerini_yazdir(selectedDonus);
    }

    @FXML
    void donus2click(MouseEvent event) {
    	ucus_secme(donusPaneller, 1);
    	selectedDonus.set(3, donus2saat.getText());
    	selectedDonus.set(4, donus2ucret.getText());
    	ucus_bilgilerini_yazdir(selectedDonus);
    }

    @FXML
    void donus3click(MouseEvent event) {
    	ucus_secme(donusPaneller, 2);
    	selectedDonus.set(3, donus3saat.getText());
    	selectedDonus.set(4, donus3ucret.getText());
    	ucus_bilgilerini_yazdir(selectedDonus);
    }

    @FXML
    void donus4click(MouseEvent event) {
    	ucus_secme(donusPaneller, 3);
    	selectedDonus.set(3, donus4saat.getText());
    	selectedDonus.set(4, donus4ucret.getText());
    	ucus_bilgilerini_yazdir(selectedDonus);
    }

    @FXML
    void minimize_btnClick(ActionEvent event) {
    	Stage stage = (Stage)minimize_btn.getScene().getWindow();
    	stage.setIconified(true);
    }

    @FXML
    void initialize() {
    	
    	selectedGidis.add(yon);
    	selectedGidis.add(nereden);
    	selectedGidis.add(nereye);
    	selectedGidis.add(saat);
    	selectedGidis.add(ucret);
    	selectedGidis.add(gidisTarih);
    	selectedGidis.add(yolcuSayisi);
    	
    	selectedDonus.add(yon);
    	selectedDonus.add(nereden);
    	selectedDonus.add(nereye);
    	selectedDonus.add(saat);
    	selectedDonus.add(ucret);
    	selectedDonus.add(donusTarih);
    	selectedDonus.add(yolcuSayisi);
    	
    	yolcular.valueProperty().addListener((obs, oldValue, newValue)->{
    		if(newValue != null) {
	    		selectedGidis.set(6, newValue.toString());
	    		selectedDonus.set(6, newValue.toString());
	    		ucus_bilgilerini_yazdir(selectedGidis);
	    		ucus_bilgilerini_yazdir(selectedDonus);
    		}
    	});
    	
    	combo_nereden.valueProperty().addListener((obs, oldValue, newValue)->{
    		if(newValue != null) {
	    		selectedGidis.set(1, newValue.toString());
	    		selectedDonus.set(2, newValue.toString());
	    		ucus_bilgilerini_yazdir(selectedGidis);
	    		ucus_bilgilerini_yazdir(selectedDonus);
    		}
    	});
    	
    	combo_nereye.valueProperty().addListener((obs, oldValue, newValue)->{
    		if(newValue != null) {
    			selectedGidis.set(2, newValue.toString());
        		selectedDonus.set(1, newValue.toString());
        		ucus_bilgilerini_yazdir(selectedGidis);
        		ucus_bilgilerini_yazdir(selectedDonus);
    		}
    	});
    	
    	datetime_gidis.valueProperty().addListener((obs, oldValue, newValue)->{
    		if(newValue != null) {
    			selectedGidis.set(5, newValue.toString());
        		ucus_bilgilerini_yazdir(selectedGidis);
    		}
    	});
    	
    	datetime_donus.valueProperty().addListener((obs, oldValue, newValue)->{
    		if(newValue != null) {
    			selectedDonus.set(5, newValue.toString());
    			ucus_bilgilerini_yazdir(selectedDonus);
    		}
    	});
    	
    	
    	
    	//yön bilgisi için ToggleGroup oluşturma
        ToggleGroup group = new ToggleGroup();
        tekYon.setToggleGroup(group);
        gidisDonus.setToggleGroup(group);
        
        //yolcu sayısı için ValueFactory oluşturma
        IntegerSpinnerValueFactory valueFactory = new IntegerSpinnerValueFactory(1, 5);
        yolcular.setValueFactory(valueFactory);
        
        //veritabanından şehirleri getirme ve comboboxa ekleme
        ObservableList<String> nereden_sehirler = FXCollections.observableArrayList();
        ObservableList<String> nereye_sehirler = FXCollections.observableArrayList();
        sehirleri_getir(nereden_sehirler);
        sehirleri_getir(nereye_sehirler);
        combo_nereden.setItems(nereden_sehirler);
        combo_nereye.setItems(nereye_sehirler);
        
        gidis_nereye_gunler.add(gidis1nereye);
        gidis_nereye_gunler.add(gidis2nereye);
        gidis_nereye_gunler.add(gidis3nereye);
        gidis_nereye_gunler.add(gidis4nereye);
        
        gidis_nereden_gunler.add(gidis1nereden);
        gidis_nereden_gunler.add(gidis2nereden);
        gidis_nereden_gunler.add(gidis3nereden);
        gidis_nereden_gunler.add(gidis4nereden);
            
        gidis_saat.add(gidis1saat);
        gidis_saat.add(gidis2saat);
        gidis_saat.add(gidis3saat);
        gidis_saat.add(gidis4saat);
    
        gidis_ucret.add(gidis1ucret);
        gidis_ucret.add(gidis2ucret);
        gidis_ucret.add(gidis3ucret);
        gidis_ucret.add(gidis4ucret);
         
        donus_nereye_gunler.add(donus1nereye);
        donus_nereye_gunler.add(donus2nereye);
        donus_nereye_gunler.add(donus3nereye);
        donus_nereye_gunler.add(donus4nereye);
       
        donus_nereden_gunler.add(donus1nereden);
        donus_nereden_gunler.add(donus2nereden);
        donus_nereden_gunler.add(donus3nereden);
        donus_nereden_gunler.add(donus4nereden);
        
        donus_saat.add(donus1saat);
        donus_saat.add(donus2saat);
        donus_saat.add(donus3saat);
        donus_saat.add(donus4saat);
        
        donus_ucret.add(donus1ucret);
        donus_ucret.add(donus2ucret);
        donus_ucret.add(donus3ucret);
        donus_ucret.add(donus4ucret);
        

        gidisPaneller.add(gidis1panel);
        gidisPaneller.add(gidis2panel);
        gidisPaneller.add(gidis3panel);
        gidisPaneller.add(gidis4panel);
        
        
        donusPaneller.add(donus1panel);
        donusPaneller.add(donus2panel);
        donusPaneller.add(donus3panel);
        donusPaneller.add(donus4panel);
        
        firstrow.setDisable(true);
    	secondrow.setDisable(true);
    	thirdrow.setDisable(true);
    	fourthrow.setDisable(true);
    	fifthrow.setDisable(true);
    	labelPanel.setDisable(true);
    	combo_nereden.setDisable(true);
    	combo_nereye.setDisable(true);
    	datetime_donus.setDisable(true);
    	datetime_gidis.setDisable(true);
    	yolcular.setDisable(true);
    	btn_ara.setDisable(true);
    }
    
    public void sehirleri_getir(ObservableList<String> list) {
    	sql = "select * from sehirler";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			
			while (getirilen.next()) {
				list.add(getirilen.getString("sehir_adi"));
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}

    }
    
    private void ucus_secme(List<Pane> panels, int index) {
    	for(int i=0; i<panels.size(); i++) {
    		if(i == index) {
    			panels.get(i).getStyleClass().remove("ucus-button");
    			panels.get(index).getStyleClass().add("ucus-button-selected");
    		}
    		else {
    			panels.get(i).getStyleClass().remove("ucus-button-selected");
    			panels.get(i).getStyleClass().add("ucus-button");
    		}
    	}
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
    
    private void ucus_secme(List<Pane> panels) {
    	for(int i=0; i<panels.size(); i++) {
    		panels.get(i).getStyleClass().remove("ucus-button-selected");
    		panels.get(i).getStyleClass().add("ucus-button");
    	}
	}
    
    public void ucus_bilgilerini_getir(String nereden, String nereye, List<Label> gidis_nereye,
        	List<Label> gidis_nereden, List<Label> gidis_saat, List<Label> gidis_ucret) {
        	sql = "select * from ucuslar where nereden=? and nereye=?";
        	try {
        		sorguIfadesi = baglanti.prepareStatement(sql);
    			sorguIfadesi.setString(1, nereden);
    			sorguIfadesi.setString(2, nereye);
    			
    			ResultSet getirilen = sorguIfadesi.executeQuery();
    			 
    			for(int i=0; i<4; i++){
    				if(getirilen.next()){
    					gidis_nereden.get(i).setText(getirilen.getString("nereden"));
    					gidis_nereye.get(i).setText(getirilen.getString("nereye"));
    					gidis_saat.get(i).setText(getirilen.getString("saat"));
    					gidis_ucret.get(i).setText(String.valueOf(getirilen.getInt("ucret")) + "₺");
    				}
    			}
    		} catch (Exception e) {
    			System.out.println(e.getMessage().toString());
    		}
    }
    
    private void tekyonselected() {
    	if(!tekyonselect) {
    		if(firstclick) {
    			animate.fade_out(donus1panel);
    			animate.fade_out(donus2panel);
    			animate.fade_out(donus3panel);
    			animate.fade_out(donus1panel);
    			animate.fade_out(donus4panel);
    			animate.fade_out(lbl_donus);
        		donus1panel.setDisable(true);
            	donus2panel.setDisable(true);
            	donus3panel.setDisable(true);
            	donus4panel.setDisable(true);
            	lbl_donus.setDisable(true);
            	
            	animate.fade_in(lbl_gidis);
            	animate.translate_transition(lbl_gidis, +180);

            	animate.translate_transition(gidis1panel, 180);
            	animate.translate_transition(gidis2panel, 180);
            	animate.translate_transition(gidis3panel, 180);
            	animate.translate_transition(gidis4panel, 180);
        		
            	animate.fade_in(firstrow);
            	animate.fade_in(secondrow);
            	animate.fade_in(thirdrow);
            	animate.fade_in(fourthrow);
        		
        	}
        	else {
        		animate.fade_out(donus1panel);
        		animate.fade_out(donus2panel);
        		animate.fade_out(donus3panel);
        		animate.fade_out(donus1panel);
        		animate.fade_out(donus4panel);
        		animate.fade_out(lbl_donus);
        		donus1panel.setDisable(true);
            	donus2panel.setDisable(true);
            	donus3panel.setDisable(true);
            	donus4panel.setDisable(true);
            	lbl_donus.setDisable(true);
            	
            	
            	animate.translate_transition(lbl_gidis, +180);
            	animate.fade_in(lbl_gidis);

            	animate.translate_transition(gidis1panel, 180);
            	animate.translate_transition(gidis2panel, 180);
            	animate.translate_transition(gidis3panel, 180);
            	animate.translate_transition(gidis4panel, 180);
        		
            	animate.fade_in(firstrow);
            	animate.fade_in(secondrow);
            	animate.fade_in(thirdrow);
            	animate.fade_in(fourthrow);
        		animate.fade_in(labelPanel);
        		animate.fade_in(fifthrow);
        		firstclick = true;
        	}
    		tekyonselect = true;
    		gidisdonusselect = false;
    	}
    }
    
    private void gidisdonusselected() {
    	if(!gidisdonusselect) {
    		if(firstclick) {
    			lbl_gidis.setPrefWidth(250);
    			animate.translate_transition(lbl_gidis, -180);
    			
    			animate.fade_in(lbl_donus);
    			
    			animate.translate_transition(gidis1panel, -180);
    			animate.translate_transition(gidis2panel, -180);
    			animate.translate_transition(gidis3panel, -180);
    			animate.translate_transition(gidis4panel, -180);
        		
        		donus1panel.setDisable(false);
            	donus2panel.setDisable(false);
            	donus3panel.setDisable(false);
            	donus4panel.setDisable(false);
            	lbl_donus.setDisable(false);
        		
            	animate.fade_in(donus1panel);
        		animate.fade_in(donus2panel);
        		animate.fade_in(donus3panel);
        		animate.fade_in(donus4panel);
        	}
    		else {
    			animate.fade_in(lbl_gidis);
    			animate.fade_in(lbl_donus);
        		
    			animate.fade_in(gidis1panel);
    			animate.fade_in(gidis2panel);
    			animate.fade_in(gidis3panel);
    			animate.fade_in(gidis4panel);
        		
    			animate.fade_in(donus1panel);
    			animate.fade_in(donus2panel);
    			animate.fade_in(donus3panel);
    			animate.fade_in(donus4panel);
        		
        		
        		
    			animate.fade_in(firstrow);
    			animate.fade_in(secondrow);
    			animate.fade_in(thirdrow);
    			animate.fade_in(fourthrow);
    			animate.fade_in(labelPanel);
    			animate.fade_in(fifthrow);
        		firstclick = true;
        	}
    		tekyonselect = false;
    		gidisdonusselect = true;
    	}
    }
    
    private void MessageBox(AlertType alerttype, String title, String header, String content) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
    
    private void MessageBox(List<String> selectedList) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Uçuş Bilgileri");
    	alert.setHeaderText("Gidiş: " + selectedList.get(0) + "->" + selectedList.get(1) + " , " + selectedList.get(2) + " - " + selectedList.get(3));
    	alert.setContentText("Gidiş bilet bilgilerinizi onaylıyor musunuz?");
    	
    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayır");
    	alert.getButtonTypes().setAll(btn1, btn2);
    	alert.getButtonTypes().setAll(btn1, btn2);
    	
    	Optional<ButtonType> sonuc= alert.showAndWait();
    	if(sonuc.get()==btn1) {
    		System.out.println("Koltuk Seçimine Geçiliyor");
    		try {
    			Stage stage = new Stage();
    			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("KoltukSecme.fxml"));
    			Scene scene = new Scene(root,800,700, javafx.scene.paint.Color.TRANSPARENT);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			stage.initStyle(StageStyle.TRANSPARENT);
    			stage.setScene(scene);
    			stage.show();
    			
    			//Formu hareket ettirme
    			root.setOnMousePressed(mouseEvent ->{
    				x = mouseEvent.getSceneX();
    				y = mouseEvent.getSceneY();
    			});
    			
    			root.setOnMouseDragged(mouseEvent ->{
    				stage.setX(mouseEvent.getScreenX() - x);
    				stage.setY(mouseEvent.getScreenY() - y);
    			});
            	
            	
    		} catch (Exception e) {
    			// TODO: handle exception
    		}
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("İptal Edildi.");
    	}	
    }
    
    private void MessageBox(List<String> selectedGidis, List<String> selectedDonus) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Uçuş Bilgileri");
    	alert.setHeaderText("Gidiş: " + selectedGidis.get(0) + "->" + selectedGidis.get(1) + " , " + selectedGidis.get(2) + " - " + selectedGidis.get(3) + "\n"+
    						"Dönüş: " + selectedDonus.get(0) + "->" + selectedDonus.get(1) + " , " + selectedDonus.get(2) + " - " + selectedDonus.get(3));
    	alert.setContentText("Gidiş bilet bilgilerinizi onaylıyor musunuz?");

    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayır");
    	alert.getButtonTypes().setAll(btn1, btn2);
    	alert.getButtonTypes().setAll(btn1, btn2);
    	
    	Optional<ButtonType> sonuc= alert.showAndWait();
    	if(sonuc.get()==btn1) {	
    		try {
    			Stage stage = new Stage();
    			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("KoltukSecme.fxml"));
    			Scene scene = new Scene(root,800,700, javafx.scene.paint.Color.TRANSPARENT);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			stage.initStyle(StageStyle.TRANSPARENT);
    			stage.setScene(scene);
    			stage.show();
    			
    			//Formu hareket ettirme
    			root.setOnMousePressed(mouseEvent ->{
    				x = mouseEvent.getSceneX();
    				y = mouseEvent.getSceneY();
    			});
    			
    			root.setOnMouseDragged(mouseEvent ->{
    				stage.setX(mouseEvent.getScreenX() - x);
    				stage.setY(mouseEvent.getScreenY() - y);
    			});
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    		System.out.println("Koltuk Seçimine Geçiliyor");
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("İptal Edildi.");
    	}	
    }
    
    public void animasyon_ac() {
    	parent_panel.setPrefWidth(895);
    	child_panel.setLayoutX(72.5);
    	
    	flightsVBox.setPrefWidth(895);
    	labelPanel.setPrefWidth(895);
    	firstrow.setPrefWidth(895);
    	secondrow.setPrefWidth(895);
    	thirdrow.setPrefWidth(895);
    	fourthrow.setPrefWidth(895);
    	
    	inside_panel5.setLayoutX(75.5);
    	inside_panel.setLayoutX(75.5);
    	inside_panel1.setLayoutX(145.5);
    	inside_panel2.setLayoutX(145.5);
    	inside_panel3.setLayoutX(145.5);
    	inside_panel4.setLayoutX(145.5);
    	
    }
    
    public void animasyon_kapa() {
    	parent_panel.setPrefWidth(750);
    	child_panel.setLayoutX(0);
    	
    	flightsVBox.setPrefWidth(750);
    	labelPanel.setPrefWidth(750);
    	firstrow.setPrefWidth(750);
    	secondrow.setPrefWidth(750);
    	thirdrow.setPrefWidth(750);
    	fourthrow.setPrefWidth(750);
    	
    	inside_panel.setLayoutX(0);
    	inside_panel1.setLayoutX(70);
    	inside_panel2.setLayoutX(70);
    	inside_panel3.setLayoutX(70);
    	inside_panel4.setLayoutX(70);
    	inside_panel5.setLayoutX(0);
    }
    
    private void ucus_bilgilerini_yazdir(List<String> list) {
		for(int i = 0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
    
    
}
