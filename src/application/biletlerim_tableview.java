package application;

public class biletlerim_tableview {
	int biletID;
	String nereden;
	String nereye;
	String tarih;
	String saat;
	int ucret;
	String koltuk_no;
	
	public biletlerim_tableview(int biletID,String nereden, String nereye, String tarih, String saat, int ucret, String koltuk_no) {
		this.biletID = biletID;
		this.nereden = nereden;
		this.nereye = nereye;
		this.tarih = tarih;
		this.saat = saat;
		this.ucret = ucret;
		this.koltuk_no = koltuk_no;
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

	public int getUserID() {
		return biletID;
	}

	public void setUserID(int biletID) {
		this.biletID = biletID;
	}
	
	
	
	
}
