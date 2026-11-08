
package oop.entity.DanhSach;
import java.time.LocalDate;
import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.ChiTietChamCong;

public class BangChamCong implements DanhSach<ChiTietChamCong>{
    private String MaBangChamCong;
    private String MaNVDuocChamCong;
    private int SoNgayCong;
    private int SoGioLamOT;
    private int thang;
    private int nam;
    private int dem=0;
    private ArrayList<ChiTietChamCong> bcc;

    public BangChamCong(){
        this.bcc=new ArrayList<>();
    }

    public BangChamCong(String MaNVDuocChamCong, int thang, int nam) {
        this.MaNVDuocChamCong = MaNVDuocChamCong;
        this.thang = thang;
        this.nam = nam;
        this.bcc = new ArrayList<>();
    }
    
    public BangChamCong(String MaBangChamCong, String MaNVDuocChamCong, int SoNgayCong, int SoGioLamOT, int thang, int nam) {
        this.MaBangChamCong = MaBangChamCong;
        this.MaNVDuocChamCong = MaNVDuocChamCong;
        this.SoNgayCong = SoNgayCong;
        this.SoGioLamOT = SoGioLamOT;
        this.thang=thang;
        this.nam=nam;
        this.bcc=new ArrayList<>();
        
    }

    
    public String getMaBangChamCong() {
        return MaBangChamCong;
    }

    public void setMaBangChamCong(String MaChamCong) {
        this.MaBangChamCong = MaChamCong;
    }

    public String getMaNVDuocChamCong() {
        return MaNVDuocChamCong;
    }

    public void setMaNVDuocChamCong(String MaNVDuocChamCong) {
        this.MaNVDuocChamCong = MaNVDuocChamCong;
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
        return "========== Bang cham cong ==========\n" + "Ma nhan vien duoc cham cong\t\t" + MaNVDuocChamCong + "\n" + "So ngay cong\t\t" + SoNgayCong + "\n"
                + "So gio lam OT\t\t" + SoGioLamOT;
    }
    
    
    //===============================================
    
    public void them(ChiTietChamCong cc){
        this.bcc.add(cc);
        }
    public void xoa(ChiTietChamCong bcc){
        this.bcc.remove(bcc);
    }
    public ChiTietChamCong timKiem(String MaCC){
        for(ChiTietChamCong cc : bcc){
            if(cc.getMaChiTietChamCong().equals(MaCC)){
                return cc;
            }
        }
        return null;
    }
    
    public ChiTietChamCong timKiem(LocalDate date){
        for(ChiTietChamCong cc : bcc){
            if(cc.getDate().equals(date)){
                return cc;
            }
        }
        return null;
    }
    
    public void xuat(){
        for(ChiTietChamCong cc : bcc){
            System.out.println(cc);
        }
    }
    public String taoMa(){
        dem++;
        return String.format("CC%04d", dem);
    }
    
     public int TongSoNgayCong(){
        return bcc.size();
    }
    /*
    public int TongSoGioOT(){
        int TongSoGioOT=0;
        for(ChiTietChamCong cc : bcc){
            TongSoGioOT=TongSoGioOT + cc.getSoGioOTNgay();
        }
        return TongSoGioOT;
    }*/
    
     public boolean KiemTraTonTai(LocalDate date){
         for(ChiTietChamCong cc : bcc){
             if(cc.getDate().equals(date)){
                 return true;
             }
         }
         return false;
     }
     
     
    }
    
    
    
    

