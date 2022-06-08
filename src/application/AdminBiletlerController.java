package application;

import java.lang.ProcessHandle.Info;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.animation.animate;
import com.database.databaseUtil;
import com.mysql.cj.Query;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class AdminBiletlerController {
	
	public AdminBiletlerController(){
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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close_btn;

    @FXML
    private TableColumn<adminBiletler_tableview, Integer> column_biletID;

    @FXML
    private TableColumn<adminBiletler_tableview, String> column_koltukNO;

    @FXML
    private TableColumn<adminBiletler_tableview, String> column_nereden;

    @FXML
    private TableColumn<adminBiletler_tableview, String> column_nereye;

    @FXML
    private TableColumn<adminBiletler_tableview, String> column_saat;

    @FXML
    private TableColumn<adminBiletler_tableview, String> column_tarih;

    @FXML
    private TableColumn<adminBiletler_tableview, Integer> column_ucret;

    @FXML
    private TableColumn<adminBiletler_tableview, Integer> column_userID;

    @FXML
    private ComboBox<String> combo_nereden;

    @FXML
    private ComboBox<String> combo_nereye;

    @FXML
    private ComboBox<String> combo_saat;

    @FXML
    private DatePicker datetime_baslangic;

    @FXML
    private DatePicker datetime_bitis;

    @FXML
    private Pane pane_bottom;

    @FXML
    private Pane pane_center;

    @FXML
    private Pane pane_inside_bottom;

    @FXML
    private Pane pane_inside_top;

    @FXML
    private Pane panel_top;

    @FXML
    private Button minimize_btn;
    
    @FXML
    private Button resetBtn;

    @FXML
    private TableView<adminBiletler_tableview> tableview_biletler;

    @FXML
    private Label lbl_info;

    @FXML
    private TextField txt_userID;
    
    Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql = "select * from biletler";
	
	ObservableList<String> query = FXCollections.observableArrayList();
	ObservableList<String> neredensehirler = FXCollections.observableArrayList();
	ObservableList<String> nereyesehirler = FXCollections.observableArrayList();
	
	ObservableList<String> saatList = FXCollections.observableArrayList();
	
	String nereden = null;
	String nereye = null;
	String tarih = null;
	String userID = null;
	String saat = null;

    @FXML
    void close_btnClick(ActionEvent event) {
    	Platform.exit();
    }
    
    @FXML
    void resetBtnClick(ActionEvent event) {
    	try {
    		combo_nereden.getSelectionModel().clearSelection();
        	combo_nereye.getSelectionModel().clearSelection();
        	combo_saat.getSelectionModel().clearSelection();
        	datetime_baslangic.getEditor().clear();
        	datetime_bitis.getEditor().clear();
        	txt_userID.clear();
        	query.set(0, null);
        	query.set(1, null);
        	query.set(2, null);
        	query.set(3, null);
        	query.set(4, null);
        	sorgu_olustur();
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}	
    }

    @FXML
    void combo_baslangicClick(ActionEvent event) {
    	try {
    		if(datetime_bitis.getValue() == null)
    		{
    			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    			LocalDateTime dateTime = LocalDateTime.now();
    			String str = "tarih between '" + datetime_baslangic.getValue() + "' and '" + dtf.format(dateTime) + "'";
    			query.set(2, str);
    			sorgu_olustur();
    		}else {
    			LocalDateTime dateTime = LocalDateTime.now();
    			String str = "tarih between '" + datetime_baslangic.getValue() + "' and '" + datetime_bitis.getValue() + "'";
    			query.set(2, str);
    			sorgu_olustur();
    		}
    		
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void combo_bitisClick(ActionEvent event) {
    	if(datetime_baslangic.getValue() == null)
		{
			MessageBox(AlertType.ERROR, "Hata", "Ýki tarih arasýnda alýnan biletleri görüntülemek "
					+ "istiyorsanýz baþlangýç zamaný seçmelisiniz.", "Lütfen baþlangýç zamaný seçimi yapýnýz.");
		}else {
			LocalDateTime dateTime = LocalDateTime.now();
			String str = "tarih between '" + datetime_baslangic.getValue() + "' and '" + datetime_bitis.getValue() + "'";
			query.set(2, str);
			sorgu_olustur();
		}
    }

    @FXML
    void combo_neredenClick(ActionEvent event) {
    	try {
    		query.set(0, "nereden='"+combo_nereden.getSelectionModel().getSelectedItem()+ "'");
        	sorgu_olustur();
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void combo_nereyeClick(ActionEvent event) {
    	try {
    		query.set(1, "nereye='" + combo_nereye.getSelectionModel().getSelectedItem() + "'");
        	sorgu_olustur();
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void combo_saatClick(ActionEvent event) {
    	try {
    		if(combo_saat.getValue() != null) {
    			query.set(4, "saat='" + combo_saat.getSelectionModel().getSelectedItem() + "'");
        		sorgu_olustur();
    		}
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
    void txt_userIDKeyTyped(KeyEvent event) {
    	try {
    		query.set(3, " ");
        	query.set(3, "userID='" + txt_userID.getText() + "'");
        	sorgu_olustur();
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    	
    }

    @FXML
    void initialize() {
    	DegerleriGetir(tableview_biletler);
    	
    	query.add(nereden);
    	query.add(nereye);
    	query.add(tarih);
    	query.add(saat);
    	query.add(userID);

    	saatList.add("09:00");
    	saatList.add("12:00");
    	saatList.add("15:00");
    	saatList.add("18:00");
    	combo_saat.setItems(saatList);
    	
    	sehirleri_getir();
    }
    
    public void DegerleriGetir(TableView table) {
    	ObservableList<adminBiletler_tableview> biletler_liste = FXCollections.observableArrayList();
    	biletler_liste.clear();
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			
			while (getirilen.next()) {
				biletler_liste.add(new adminBiletler_tableview(getirilen.getInt("biletID"), getirilen.getInt("userID"), getirilen.getString("nereden"),
						getirilen.getString("nereye"), getirilen.getString("tarih"), getirilen.getString("saat"), getirilen.getInt("ucret"), getirilen.getString("koltuk_no")));
				column_userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
				column_biletID.setCellValueFactory(new PropertyValueFactory<>("biletID"));
				column_nereden.setCellValueFactory(new PropertyValueFactory<>("nereden"));
				column_nereye.setCellValueFactory(new PropertyValueFactory<>("nereye"));
				column_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
				column_saat.setCellValueFactory(new PropertyValueFactory<>("saat"));
				column_ucret.setCellValueFactory(new PropertyValueFactory<>("ucret"));
				column_koltukNO.setCellValueFactory(new PropertyValueFactory<>("koltuk_no"));
				table.setItems(biletler_liste);
			}
		}	
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    private void sorgu_olustur() {
    	sql = "select * from biletler";
    	for(int i=0; i<query.size(); i++) {
    		if(query.get(i) != null && query.get(i) != "") {
    			if(!sql.contains("where")) {
    				sql += " where ";
    			}
    			sql += query.get(i) + " and ";
    		}
    	}
    	
    	if(sql.substring(sql.length()-4, sql.length()).trim().equals("and")) {
    		sql = sql.substring(0, sql.length()-4);
    	}
    	System.out.println(sql);
    	tableview_biletler.getItems().clear();
    	DegerleriGetir(tableview_biletler);
    }
    
    public void sehirleri_getir() {
    	combo_nereden.getItems().clear();
		combo_nereye.getItems().clear();
		neredensehirler.clear();
		nereyesehirler.clear();
    	sql = "select * from sehirler";
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			
			while (getirilen.next()) {
				neredensehirler.add(getirilen.getString("sehir_adi"));
				nereyesehirler.add(getirilen.getString("sehir_adi"));
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
		combo_nereden.setItems(neredensehirler);
		combo_nereye.setItems(nereyesehirler);
		
		combo_nereden.setStyle(styleString);
		combo_nereye.setStyle(styleString);
    }
    
    private void MessageBox(AlertType alerttype, String title, String header, String content) {
    	Alert alert = new Alert(alerttype);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
    
    public void animasyon_ac() {
    	close_btn.setLayoutX(852);
    	minimize_btn.setLayoutX(820);
    	borderPane.setPrefWidth(895);
    	anchorPane.setPrefWidth(895);
    	pane_bottom.setPrefWidth(895);
    	animate.translate_transition(pane_inside_bottom, 72.5);
    	
    }
    
    public void animasyon_kapa() {
    	close_btn.setLayoutX(706);
    	minimize_btn.setLayoutX(674);
    	borderPane.setPrefWidth(760);
    	anchorPane.setPrefWidth(760);
    	pane_bottom.setPrefWidth(760);
    	animate.translate_transition(pane_inside_bottom, -72.5);
	}
}
