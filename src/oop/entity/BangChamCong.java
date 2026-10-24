
package oop.entity;
import java.util.ArrayList;

public class BangChamCong {
    private String MaChamCong;
    private String MaNVDuocChamCong;
    private String MaNVChamCong;
    private int SoNgayCong;
    private int SoGioLamOT;
    private int thang;
    private int nam;
    private ArrayList<ChiTietChamCong> bcc;

    public BangChamCong(){};
    
    public BangChamCong(String MaChamCong, String MaNVDuocChamCong, String MaNVChamCong, int SoNgayCong, int SoGioLamOT, int thang, int nam) {
        this.MaChamCong = MaChamCong;
        this.MaNVDuocChamCong = MaNVDuocChamCong;
        this.MaNVChamCong = MaNVChamCong;
        this.SoNgayCong = SoNgayCong;
        this.SoGioLamOT = SoGioLamOT;
        this.thang=thang;
        this.nam=nam;
        this.bcc=new ArrayList<>();
        
    }

    public BangChamCong(String MaNVDuocChamCong, String MaNVChamCong) {
        this.MaNVDuocChamCong = MaNVDuocChamCong;
        this.MaNVChamCong = MaNVChamCong;
    }
    
    public String getMaChamCong() {
        return MaChamCong;
    }

    public void setMaChamCong(String MaChamCong) {
        this.MaChamCong = MaChamCong;
    }

    public String getMaNVDuocChamCong() {
        return MaNVDuocChamCong;
    }

    public void setMaNVDuocChamCong(String MaNVDuocChamCong) {
        this.MaNVDuocChamCong = MaNVDuocChamCong;
    }

    public String getMaNVChamCong() {
        return MaNVChamCong;
    }

    public void setMaNVChamCong(String MaNVChamCong) {
        this.MaNVChamCong = MaNVChamCong;
    }

    public int getSoNgayCong() {
        return SoNgayCong;
    }

    public void setSoNgayCong(int SoNgayCong) {
        this.SoNgayCong = SoNgayCong;
    }

    public int getSoGioLamOT() {
        return SoGioLamOT;
    }

    public void setSoGioLamOT(int SoGioLamOT) {
        this.SoGioLamOT = SoGioLamOT;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }
    
    

    public ArrayList<ChiTietChamCong> getBcc() {
        return bcc;
    }

    public void setBcc(ArrayList<ChiTietChamCong> bcc) {
        this.bcc = bcc;
    }
    
    public String toString(){
        return "========== Bang cham cong ==========\n" + "Ma nhan vien duoc cham cong\t\t" + MaNVDuocChamCong +"\n"
                + "Ma nhan vien cham cong\t\t" + MaNVChamCong + "\n" + "So ngay cong\t\t" + SoNgayCong + "\n"
                + "So gio lam OT\t\t" + SoGioLamOT;
    }
    
    
    //===============================================
    
    public void ThemChiTietChamCong(ChiTietChamCong cc){
        this.bcc.add(cc);
        }
    
     public int TinhSoGioCong(){
        int TongSoGioCong=0;
        for(ChiTietChamCong cc : bcc){
            TongSoGioCong=TongSoGioCong + cc.getSoGioCong();
        }
        return TongSoGioCong;
    }
    
    public int TinhSoGioOT(){
        int TongSoGioOT=0;
        for(ChiTietChamCong cc : bcc){
            TongSoGioOT=TongSoGioOT + cc.getSoGioOTNgay();
        }
        return TongSoGioOT;
    }
    
    }
    
    
    
    

