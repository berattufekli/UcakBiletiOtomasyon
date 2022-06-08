package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BiletlerimController {

	public BiletlerimController(){
		baglanti = databaseUtil.baglan();
	}
	

    @FXML
    private AnchorPane anchorPane;
	
    @FXML
    private Button btn_iptal;

    @FXML
    private Button close_btn;
    
    @FXML
    private Pane panel_bottom;

    @FXML
    private Pane panel_top;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private TableColumn<biletlerim_tableview, Integer> column_biletID;

    @FXML
    private TableColumn<biletlerim_tableview, String> column_koltuk;

    @FXML
    private TableColumn<biletlerim_tableview, String> column_nereden;

    @FXML
    private TableColumn<biletlerim_tableview, String> column_nereye;

    @FXML
    private TableColumn<biletlerim_tableview, String> column_saat;

    @FXML
    private TableColumn<biletlerim_tableview, String> column_tarih;

    @FXML
    private TableColumn<biletlerim_tableview, Integer> column_ucret;

    @FXML
    private Button minimize_btn;

    @FXML
    private TableView<biletlerim_tableview> tableview_biletler;
    
    List<Integer> list = new ArrayList<>();
    
    Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql = null;
	
	int index;

    @FXML
    void btn_iptalClick(ActionEvent event) {
    	MessageBox();
    }

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
    void initialize() {
    	DegerleriGetir(tableview_biletler, "where userID='"+LoginController.userID+"';");
    }
    
    @FXML
    void tableViewMouseClick(MouseEvent event) {
    	index = tableview_biletler.getSelectionModel().getSelectedIndex();
    }
    
    public void DegerleriGetir(TableView table, String sql) {
		sql = "select * from biletler " + sql;
		ObservableList<biletlerim_tableview> biletler_liste = FXCollections.observableArrayList();
		
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			
			while (getirilen.next()) {
				biletler_liste.add(new biletlerim_tableview(getirilen.getInt("biletID"), getirilen.getString("nereden"), getirilen.getString("nereye"),
						getirilen.getString("tarih"), getirilen.getString("saat"), getirilen.getInt("ucret"), getirilen.getString("koltuk_no")));
				list.add(getirilen.getInt("biletID"));
				column_biletID.setCellValueFactory(new PropertyValueFactory<>("biletID"));
				column_nereden.setCellValueFactory(new PropertyValueFactory<>("nereden"));
				column_nereye.setCellValueFactory(new PropertyValueFactory<>("nereye"));
				column_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
				column_saat.setCellValueFactory(new PropertyValueFactory<>("saat"));
				column_ucret.setCellValueFactory(new PropertyValueFactory<>("ucret"));
				column_koltuk.setCellValueFactory(new PropertyValueFactory<>("koltuk_no"));
				table.setItems(biletler_liste);
			} 
		}	
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    
    private void MessageBox() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Bilet Silme");
    	alert.setHeaderText("Bilet iptal edilecek. Bundan emin misiniz?");

    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayýr");
    	alert.getButtonTypes().setAll(btn1, btn2);
    	alert.getButtonTypes().setAll(btn1, btn2);
    	
    	Optional<ButtonType> sonuc= alert.showAndWait();
    	if(sonuc.get()==btn1) {
    		sql = "delete from biletler where biletID=?";
    		try {
    			sorguIfadesi = baglanti.prepareStatement(sql);
    			sorguIfadesi.setInt(1, list.get(index));
    			sorguIfadesi.executeUpdate();
    			tableview_biletler.getItems().clear();
    			DegerleriGetir(tableview_biletler, "where userID='"+LoginController.userID+"';");
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
    	}
    	else if(sonuc.get()==btn2) {
    		System.out.println("Ýptal Edildi.");
    	}	
    }
    
    public void animasyon_ac() {
    	close_btn.setLayoutX(852);
    	minimize_btn.setLayoutX(820);
    	borderPane.setPrefWidth(905);
    	anchorPane.setPrefWidth(905);
    	tableview_biletler.setLayoutX(117.5);
    	btn_iptal.setLayoutX(317.5);
    }
    
    public void animasyon_kapa() {
    	close_btn.setLayoutX(706);
    	minimize_btn.setLayoutX(674);
    	borderPane.setPrefWidth(760);
    	anchorPane.setPrefWidth(760);
    	tableview_biletler.setLayoutX(45);
    	btn_iptal.setLayoutX(245);
	}
}
    
