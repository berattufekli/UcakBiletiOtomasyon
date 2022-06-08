package application;

public class adminBiletler_tableview {
	int biletID;
	int userID;
	String nereden;
	String nereye;
	String tarih;
	String saat;
	int ucret;
	String koltuk_no;
	
	public adminBiletler_tableview(int biletID, int userID, String nereden, String nereye, String tarih, String saat,
			int ucret, String koltuk_no) {
		this.biletID = biletID;
		this.userID = userID;
		this.nereden = nereden;
		this.nereye = nereye;
		this.tarih = tarih;
		this.saat = saat;
		this.ucret = ucret;
		this.koltuk_no = koltuk_no;
	}
	public int getBiletID() {
		return biletID;
	}
	public void setBiletID(int biletID) {
		this.biletID = biletID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getNereden() {
		return nereden;
	}
	public void setNereden(String nereden) {
		this.nereden = nereden;
	}
	public String getNereye() {
		return nereye;
	}
	public void setNereye(String nereye) {
		this.nereye = nereye;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	public String getSaat() {
		return saat;
	}
	public void setSaat(String saat) {
		this.saat = saat;
	}
	public int getUcret() {
		return ucret;
	}
	public void setUcret(int ucret) {
		this.ucret = ucret;
	}
	public String getKoltuk_no() {
		return koltuk_no;
	}
	public void setKoltuk_no(String koltuk_no) {
		this.koltuk_no = koltuk_no;
	}
	
	
	
}
