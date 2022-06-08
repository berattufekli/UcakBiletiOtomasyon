package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.database.databaseUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KoltukSecmeController {
	
	public KoltukSecmeController() {
		baglanti = databaseUtil.baglan();
	}
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_pay;

    @FXML
    private Button btn_reset;

    @FXML
    private Button close_btn;

    @FXML
    private HBox harflerHBox;

    @FXML
    private VBox DonusKoltuklarVBox;

    @FXML
    private VBox GidisKoltuklarVBox;

    @FXML
    private Button minimize_btn;
    
    @FXML
    private VBox GidisSayilarVBox;

    @FXML
    private VBox DonusSayilarVBox;
    
    @FXML
    private Tab tabPage1;

    @FXML
    private Tab tabPage2;
    
    @FXML
    private Label lbl1_ucus;

    @FXML
    private Label lbl2_ucus;
    

    @FXML
    private Label lbl_koltuklar1;

    @FXML
    private Label lbl_koltuklar2;
    
    @FXML
    private AnchorPane anchor_pane;
    
    
    public static List<String> GidisSecilenKoltukList = new ArrayList<>();
    public static List<String> DonusSecilenKoltukList = new ArrayList<>();
    
    List<String> GidisAlinmisKoltukList = new ArrayList<>();
    List<String> DonusAlinmisKoltukList = new ArrayList<>();
    
    List<String> selectedGidis = AnasayfaController.selectedGidis;
	List<String> selectedDonus = AnasayfaController.selectedDonus;
	List<ToggleButton> gidisButtons = new ArrayList<>();
	List<ToggleButton> donusButtons = new ArrayList<>();
	
	private double x;
	private double y;
	
    Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql = null;
    
	@FXML
    void btn_pay_click(ActionEvent event) {
    	
    	if(selectedGidis.get(0).equals("Tek Yön")) {
    		if(GidisSecilenKoltukList.size() == Integer.valueOf(selectedGidis.get(6))) {
    			MessageBox();	
    		}else {
				MessageBox(AlertType.ERROR, "Hata", "Seçilen yolcu sayýsý kadar bilet seçilmesi gereklidir.",
						"Lütfen tekrar deneyiniz");
			}
    	}else if(selectedGidis.get(0).equals("Gidiþ Dönüþ")) {
    		if(GidisSecilenKoltukList.size() == Integer.valueOf(selectedGidis.get(6)) &&
    		   DonusSecilenKoltukList.size() == Integer.valueOf(selectedDonus.get(6))) {
    			MessageBox();	
    		}else {
    			if(GidisSecilenKoltukList.size() < Integer.valueOf(selectedDonus.get(6))) {
    				MessageBox(AlertType.ERROR, "Hata", "Gidiþ için seçilen yolcu sayýsý kadar bilet seçilmesi gereklidir.",
    						"Lütfen tekrar deneyiniz");
    			}else if(DonusSecilenKoltukList.size() < Integer.valueOf(selectedDonus.get(6))) {
    				MessageBox(AlertType.ERROR, "Hata", "Dönüþ için seçilen yolcu sayýsý kadar bilet seçilmesi gereklidir.",
    						"Lütfen tekrar deneyiniz");
    			}
				
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
    	alert.setTitle("Uçuþ Bilgileri");
    	alert.setHeaderText("Ödeme sayfasýna geçiliyor.");
    	alert.setContentText("Koltuk bilgilerinizi onaylýyor musunuz?");

    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayýr");
    	alert.getButtonTypes().setAll(btn1, btn2);
    	alert.getButtonTypes().setAll(btn1, btn2);
    	
    	Optional<ButtonType> sonuc= alert.showAndWait();
    	if(sonuc.get()==btn1) {
    		try {
    			AnchorPane pane= (AnchorPane) FXMLLoader.load(getClass().getResource("OdemeYap.fxml"));
            	anchor_pane.getChildren().setAll(pane);
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    		
    		System.out.println("Ödeme Sayfasýna Geçiliyor");
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("Ýptal Edildi.");
    	}	
    }

    @FXML
    void close_btnClick(ActionEvent event) {
    	Stage obj = (Stage)close_btn.getScene().getWindow();
    	obj.close();
    }

    @FXML
    void minimize_btnClick(ActionEvent event) {
    	Stage obj = (Stage) minimize_btn.getScene().getWindow();
    	obj.setIconified(true);
    }
    
    @FXML
    void btn_reset_click(ActionEvent event) {
    	GidisSecilenKoltukList.clear();
    	GidisSecilenKoltukList.clear();
    	koltuklariAktifEt(donusButtons);
    	koltuklariAktifEt(gidisButtons);
    	
    	if(selectedGidis.get(0).toString().equals("Gidiþ Dönüþ")) {
    		for(int i=0; i<gidisButtons.size();i++) {
        		gidisButtons.get(i).setSelected(false);
        		donusButtons.get(i).setSelected(false);
        	}
    	}else {
    		for(int i=0; i<gidisButtons.size();i++) {
        		gidisButtons.get(i).setSelected(false);
        	}
    	}
    	
    	
    	lbl_koltuklar1.setText("Seçilen Koltuklar: ");
    	lbl_koltuklar2.setText("Seçilen Koltuklar: ");
    }

    @FXML
    void initialize() {
    	deneme();
    }
    
    private void createLabel(VBox box, int index) {
    	try {
    		Label lbl = new Label(String.valueOf(index));
        	lbl.setPrefSize(70, 70);
        	lbl.setAlignment(Pos.CENTER);
        	lbl.setStyle("-fx-font-family: 'Noto Sans';"
        			   + "-fx-font-size: 16px;"
        			   + "-fx-font-weight:Bold;");
        	box.getChildren().add(lbl);
		} catch (Exception e) {
			System.out.println("Label: " + e.getMessage().toString());
		}
    	
    }
    
    private void createHBox(VBox vbox, Insets margin, String siraSayisi, List<ToggleButton> buttonlist) {
    	try {
    		HBox box = new HBox();
        	String toggleString;
        	String[] harfler = {"A", "B", "C", "D"};
        	for(int i=0;i<4;i++) {
        		toggleString = harfler[i] + siraSayisi;
        		ToggleButton toggle = new ToggleButton();
            	toggle.setPrefSize(50, 50);
            	if(i == 2) {
            		HBox.setMargin(toggle, new Insets(5,10,5,80));
            	}else {
            		HBox.setMargin(toggle, margin);
            	}
            	toggle.getStyleClass().add("ucus-toggle");
            	
            	buttonlist.add(toggle);
            	toggle.setId(toggleString);
            	box.getChildren().add(toggle);
            	System.out.println("Button Adý: " + toggle.getId().toString());
            	toggle.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent arg0) {
    					if(selectedGidis.get(0).equals("Tek Yön")) {
    						if(toggle.isSelected()) {
    							GidisSecilenKoltukList.add(toggle.getId().toString());
    							lbl_koltuklar1.setText(lbl_koltuklar1.getText() + " - " + toggle.getId().toString());
    						}else {
    							if(GidisSecilenKoltukList.size() == Integer.valueOf(selectedGidis.get(6))) {
    								koltuklariAktifEt(buttonlist);
    							}
    							lbl_koltuklar1.setText(lbl_koltuklar1.getText().replace(" - "+toggle.getId().toString(), "").trim());
    							GidisSecilenKoltukList.remove(toggle.getId().toString());
    						}
    						yolcuSayisiKontrol(buttonlist, GidisSecilenKoltukList);
    					}
    					
    					else if(selectedGidis.get(0).equals("Gidiþ Dönüþ")) {
    						if(tabPage1.isSelected()) {
    							if(toggle.isSelected()) {
    								GidisSecilenKoltukList.add(toggle.getId().toString());
    								lbl_koltuklar1.setText(lbl_koltuklar1.getText() + " - " + toggle.getId().toString());
    							}else {
    								if(GidisSecilenKoltukList.size() == Integer.valueOf(selectedGidis.get(6))) {
    									koltuklariAktifEt(buttonlist);
    								}
    								lbl_koltuklar1.setText(lbl_koltuklar1.getText().replace(" - "+toggle.getId().toString(), "").trim());
    								GidisSecilenKoltukList.remove(toggle.getId().toString());
    							}
    							yolcuSayisiKontrol(buttonlist, GidisSecilenKoltukList);
    						}
    						else if(tabPage2.isSelected()) {
    							if(toggle.isSelected()) {
    								DonusSecilenKoltukList.add(toggle.getId().toString());
    								lbl_koltuklar2.setText(lbl_koltuklar2.getText() + " - " + toggle.getId().toString());
    							}else {
    								if(DonusSecilenKoltukList.size() == Integer.valueOf(selectedDonus.get(6))) {
    									koltuklariAktifEt(buttonlist);
    								}
    								lbl_koltuklar2.setText(lbl_koltuklar2.getText().replace(" - "+toggle.getId().toString(), "").trim());
    								DonusSecilenKoltukList.remove(toggle.getId().toString());
    							}
    							yolcuSayisiKontrol(buttonlist, DonusSecilenKoltukList);
    						}
    					}
    					
    				}
    			});
        	}
        	vbox.getChildren().add(box);
		} catch (Exception e) {
			System.out.println("HBox: " + e.getMessage().toString());
		}
    }
    
    private void yolcuSayisiKontrol(List<ToggleButton> btn,List<String> secilenKoltukList) {
		try {
			if(secilenKoltukList.size() == Integer.valueOf(selectedGidis.get(6))) {
				for(int i=0; i<btn.size(); i++) {
					if(!btn.get(i).isSelected()) {
						btn.get(i).setDisable(true);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("yolcuSayisiKontrol: " + e.getMessage().toString());
		}
	}
    
    private void koltuklariAktifEt(List<ToggleButton> btn) {
    	try {
    		for(int i=0; i<btn.size(); i++) {
    			if(btn.get(i).isDisable()) {
    				btn.get(i).setDisable(false);
    			}
    		}
		} catch (Exception e) {
			System.out.println("koltuklariAktifEt: " + e.getMessage().toString());
		}
    }
    
    private void alinmis_koltuklari_getir(List<String> koltuklar,String nereden, String nereye, String tarih, String saat) {
    	sql = "select koltuk_no from biletler where nereden=? and nereye=? and tarih=? and saat=?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, nereden);
			sorguIfadesi.setString(2, nereye);
			sorguIfadesi.setString(3, tarih);
			sorguIfadesi.setString(4, saat);
			getirilen = sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				koltuklar.add(getirilen.getString(1));
				System.out.println(getirilen.getString(1));
			}
		} catch (Exception e) {
			System.out.println("alinmis_koltuklari_getir: " + e.getMessage().toString());
		}
    }
    
    private void devre_disi_yap(List<String> alinmiskoltuklar, List<ToggleButton> tumkoltuklar) {
    	try {
    		for(int i=0; i<tumkoltuklar.size(); i++) {
        		for(int j=0; j<alinmiskoltuklar.size(); j++)
        			if(tumkoltuklar.get(i).getId().equals(alinmiskoltuklar.get(j))) {
        				tumkoltuklar.get(i).getStyleClass().remove(0);
        				tumkoltuklar.get(i).getStyleClass().add("login-button");
        				tumkoltuklar.get(i).setDisable(true);
        				tumkoltuklar.remove(i);
        			}
        	}
		} catch (Exception e) {
			System.out.println("devre_disi_yap: " + e.getMessage().toString());
		}
    	
    }
    
    public void deneme() {
    	try {
    		if(selectedGidis.get(0).toString().equals("Tek Yön")) {
    			GidisKoltuklarVBox.getChildren().clear();
            	tabPage2.setDisable(true);
            	lbl1_ucus.setText(selectedGidis.get(1) + "-" + selectedGidis.get(2) + " " + selectedGidis.get(5)
    			+ " - " + selectedGidis.get(3));
            	for(int i=1; i<=15; i++) {
                	createLabel(GidisSayilarVBox,i);
                	createHBox(GidisKoltuklarVBox, new Insets(5,10,5,10),String.valueOf(i), gidisButtons);
                }
            	
            	alinmis_koltuklari_getir(GidisAlinmisKoltukList, selectedGidis.get(1), 
            			selectedGidis.get(2), selectedGidis.get(5), selectedGidis.get(3));
            	devre_disi_yap(GidisAlinmisKoltukList, gidisButtons);
            }
            else if(selectedDonus.get(0).toString().equals("Gidiþ Dönüþ")) {
            	tabPage2.setDisable(false);
            	lbl1_ucus.setText(selectedGidis.get(0) + "-" + selectedGidis.get(1) + " " + selectedGidis.get(2)
    			+ " - " + selectedGidis.get(3));
            	lbl2_ucus.setText(selectedDonus.get(0) + "-" + selectedDonus.get(1) + " " + selectedDonus.get(2)
    			+ " - " + selectedDonus.get(3));
            	
            	for(int i=1; i<=15; i++) {
                	createLabel(GidisSayilarVBox,i);
                	createHBox(GidisKoltuklarVBox, new Insets(5,10,5,10),String.valueOf(i), gidisButtons);
                	createLabel(DonusSayilarVBox,i);
                	createHBox(DonusKoltuklarVBox, new Insets(5,10,5,10),String.valueOf(i), donusButtons);
                }
            	
            	alinmis_koltuklari_getir(GidisAlinmisKoltukList, selectedGidis.get(1), 
            			selectedGidis.get(2), selectedGidis.get(5), selectedGidis.get(3));
            	devre_disi_yap(GidisAlinmisKoltukList, gidisButtons);
            	
            	alinmis_koltuklari_getir(DonusAlinmisKoltukList, selectedDonus.get(1), 
            			selectedDonus.get(2), selectedDonus.get(5), selectedDonus.get(3));
            	devre_disi_yap(DonusAlinmisKoltukList, donusButtons);
            }
    		
		} catch (Exception e) {
			System.out.println("deneme: " + e.getMessage().toString());
		}
        
        
    }
    

}
