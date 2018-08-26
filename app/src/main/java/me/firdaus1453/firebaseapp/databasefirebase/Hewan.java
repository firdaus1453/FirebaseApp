package me.firdaus1453.firebaseapp.databasefirebase;

public class Hewan {
    String idhewan;
    String nama;
    String detail;
    String urlgambar;


    public Hewan() {
    }

    public Hewan(String idhewan, String nama, String detail, String urlgambar) {
        this.idhewan = idhewan;
        this.nama = nama;
        this.detail = detail;
        this.urlgambar = urlgambar;
    }


    public String getIdhewan() {
        return idhewan;
    }

    public void setIdhewan(String idhewan) {
        this.idhewan = idhewan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrlgambar() {
        return urlgambar;
    }

    public void setUrlgambar(String urlgambar) {
        this.urlgambar = urlgambar;
    }

}
