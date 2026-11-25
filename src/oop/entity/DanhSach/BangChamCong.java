
package oop.entity.DanhSach;
import java.time.LocalDate;
import java.util.ArrayList;
import oop.entity.Enum.LoaiTrangThaiChamCong;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.ChiTietChamCong;

public class BangChamCong implements DanhSach<ChiTietChamCong>{
    private String MaBangChamCong;
    private String MaNVDuocChamCong;
    private int SoNgayCong;
    private double SoGioLamOT=0;
    private int thang;
    private int nam;
    private int dem=0;
    private LocalDate NgayBatDauBang;
    private LocalDate NgayKetThucBang;
    private ArrayList<ChiTietChamCong> bcc;

    public BangChamCong(){
        this.bcc=new ArrayList<>();
    }

    public BangChamCong(String MaNVDuocChamCong, int thang, int nam) {
        this.MaNVDuocChamCong = MaNVDuocChamCong;
        this.thang = thang;
        this.nam = nam;
        this.bcc = new ArrayList<>();
        
        NgayBatDauBang = LocalDate.of(nam, thang, 6);
        NgayKetThucBang = NgayBatDauBang.plusMonths(1).minusDays(1);
        TaoDuLieuChamCong(NgayBatDauBang, NgayKetThucBang, MaNVDuocChamCong);
        
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
    
    public void TaoDuLieuChamCong(LocalDate NgayBatDau, LocalDate NgayKetThuc, String MaNV){
        for(LocalDate date = NgayBatDau; !date.isAfter(NgayKetThuc);date = date.plusDays(1)){
            String MaCC = taoMa();
            ChiTietChamCong cc = new ChiTietChamCong(MaCC, date, MaNV);
            cc.capNhatNgayNghi(); 
            them(cc);
        }
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


    public int getSoNgayCong(){
        int SoNgayChamCong = 0;
        for(ChiTietChamCong cc : bcc){
            LoaiTrangThaiChamCong tt = cc.getTrangThai();
        if(tt != LoaiTrangThaiChamCong.NGHI_PHEP && tt != LoaiTrangThaiChamCong.KHONG_CHAM_CONG && tt != LoaiTrangThaiChamCong.NGAY_NGHI){
            SoNgayChamCong++;
        }
        }
        setSoNgayCong(SoNgayChamCong);
        return SoNgayChamCong;
    }

    public void setSoNgayCong(int SoNgayCong) {
        this.SoNgayCong = SoNgayCong;
    }

    public double getSoGioLamOT() {
        return SoGioLamOT;
    }

    public void setSoGioLamOT(double SoGioLamOT) {
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

    public LocalDate getNgayBatDauBang() {
        return NgayBatDauBang;
    }

    public void setNgayBatDauBang(LocalDate NgayBatDauBang) {
        this.NgayBatDauBang = NgayBatDauBang;
    }

    public LocalDate getNgayKetThucBang() {
        return NgayKetThucBang;
    }

    public void setNgayKetThucBang(LocalDate NgayKetThucBang) {
        this.NgayKetThucBang = NgayKetThucBang;
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
        if(bcc==null){
            System.out.println("bảng chấm công rỗng");
            return null;
        }
        for(ChiTietChamCong cc : bcc){
            if(cc.getDate().equals(date)){
                return cc;
            }
        }
        return null;
    }
    
    public void xuat(){
        if(bcc==null){
            System.out.println("bảng chấm công rỗng");
            return;
        }
        for(ChiTietChamCong cc : bcc){
            System.out.println(cc);
        }
    }
    public String taoMa(){
        dem++;
        return String.format("CC%02d", dem);
    }

    
     public boolean KiemTraTonTai(LocalDate date){
         for(ChiTietChamCong cc : bcc){
             if(cc.getDate().equals(date)){
                 return true;
             }
         }
         return false;
     }

    public double getSoGioOT(){
        double tong = 0;
        for(ChiTietChamCong cc : bcc){
        tong = tong + cc.getSoGioLamOT();
        }
        this.SoGioLamOT = tong;
        return tong;
    }
     
     
    }
    
    
    
    

