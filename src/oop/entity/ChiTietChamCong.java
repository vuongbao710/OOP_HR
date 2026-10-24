
package oop.entity;
import java.time.LocalDate;

public class ChiTietChamCong {
    private String MaChiTietChamCong;
    private String TrangThai;
    private LocalDate Date;
    private String MaNVduocChamCong;
    private String MaNVChamCong;
    private int SoGioCong;
    private int SoGioOTNgay;

    public ChiTietChamCong(String MaChiTietChamCong, String TrangThai, LocalDate Date, String MaNVduocChamCong, String MaNVChamCong,int SoGioCong, int SoGioOTNgay) {
        this.MaChiTietChamCong = MaChiTietChamCong;
        this.TrangThai = TrangThai;
        this.Date=Date;
        this.MaNVduocChamCong = MaNVduocChamCong;
        this.MaNVChamCong = MaNVChamCong;
        this.SoGioCong=SoGioCong;
        this.SoGioOTNgay = SoGioOTNgay;
    }

    public String getMaChiTietChamCong() {
        return MaChiTietChamCong;
    }

    public void setMaChiTietChamCong(String MaChiTietChamCong) {
        this.MaChiTietChamCong = MaChiTietChamCong;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public String getMaNVduocChamCong() {
        return MaNVduocChamCong;
    }

    public void setMaNVduocChamCong(String MaNVduocChamCong) {
        this.MaNVduocChamCong = MaNVduocChamCong;
    }

    public String getMaNVChamCong() {
        return MaNVChamCong;
    }

    public void setMaNVChamCong(String MaNVChamCong) {
        this.MaNVChamCong = MaNVChamCong;
    }

    public int getSoGioCong() {
        return SoGioCong;
    }

    public void setSoGioCong(int SoGioCong) {
        this.SoGioCong = SoGioCong;
    }
    
    
    
    public int getSoGioOTNgay() {
        return SoGioOTNgay;
    }

    public void setSoGioOTNgay(int SoGioOTNgay) {
        this.SoGioOTNgay = SoGioOTNgay;
    }
    
    public String toString(){
        return "Ma chi tiet cham cong :" + MaChiTietChamCong + "| Trang thai :" + TrangThai 
                +"| Ngay cham cong :" + Date + "|Ma nhan vien duoc cham cong:" + MaNVduocChamCong +"| Ma nhan vien cham cong :"
                + MaNVChamCong + "|So gio cong:" + SoGioCong + "|So gio OT:" + SoGioOTNgay;
    }
    
}
