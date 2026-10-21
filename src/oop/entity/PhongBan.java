
package oop.entity;
import java.util.ArrayList;

public class PhongBan {
    private String MaPhong;
    private String TenPhongBan;
    private String MaTruongPhong;
    private String TenTruongPhong;
    
    private ArrayList<NhanVien> dsnvpb;

    public PhongBan(){
        this.MaPhong=null;
        this.MaTruongPhong=null;
        this.TenPhongBan=null;
        this.TenTruongPhong=null;
    }
    
    public PhongBan(String MaPhong, String TenPhongBan, String MaTruongPhong, String TenTruongPhong) {
        this.MaPhong = MaPhong;
        this.TenPhongBan = TenPhongBan;
        this.MaTruongPhong = MaTruongPhong;
        this.TenTruongPhong = TenTruongPhong;
        this.dsnvpb= new ArrayList<>();
    }
    
    public void ThemNhanVien(NhanVien nv){
        this.dsnvpb.add(nv);
    }
    
    public int TimKiemViTriNhanVien(String MaNV){
        for(int i=0; i<dsnvpb.size();i++){
            if(MaNV.equals(dsnvpb.get(i).getMaNV())){
                return i;
            }
        }
        return -1;
    }
    
    public void XoaNhanVien(String MaNV){
        if(TimKiemViTriNhanVien(MaNV)==-1){
            System.out.println("khong tim thay nhan vien can xoa");
            return;
        }
        this.dsnvpb.remove(TimKiemViTriNhanVien(MaNV));
    }
    
    public void XuatDanhSachNVPB(){
        for(int i=0;i<dsnvpb.size();i++){
            System.out.println(dsnvpb.get(i));
        }
    }
    
    public void SkipQuanLy(String MaNV){
        for(int i=0;i<dsnvpb.size();i++){
            if(dsnvpb.get(i).getMaNV().equals(MaNV))continue;
            System.out.println(dsnvpb.get(i));
        }
    }
    
    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getTenPhongBan() {
        return TenPhongBan;
    }

    public void setTenPhongBan(String TenPhongBan) {
        this.TenPhongBan = TenPhongBan;
    }

    public String getMaTruongPhong() {
        return MaTruongPhong;
    }

    public void setMaTruongPhong(String MaTruongPhong) {
        this.MaTruongPhong = MaTruongPhong;
    }

    public String getTenTruongPhong() {
        return TenTruongPhong;
    }

    public void setTenTruongPhong(String TenTruongPhong) {
        this.TenTruongPhong = TenTruongPhong;
    }

    public ArrayList<NhanVien> getDsnvpb() {
        return dsnvpb;
    }
    
    
}
