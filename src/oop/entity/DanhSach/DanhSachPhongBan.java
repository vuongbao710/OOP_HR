
package oop.entity.DanhSach;

import java.util.ArrayList;
import oop.entity.Object.PhongBan;

public class DanhSachPhongBan {
    private final ArrayList<PhongBan> DanhSachPhongBan;
    private static int dem=0;
    public DanhSachPhongBan() {
        this.DanhSachPhongBan = new ArrayList<>();
    }

    
    public boolean xoaPhongBan(String MaPhongBan) {
        for (PhongBan pb : DanhSachPhongBan) {
            if (pb.getMaPhong().equals(MaPhongBan)) {
                this.DanhSachPhongBan.remove(pb);
                return true;
            }
        }
        return false; 
    }

    public boolean suaPhongBan(String MaPhongBan, String TenPhongBanMoi, String MaTruongPhongMoi, String TenTruongPhongMoi) {
        for (PhongBan pb : DanhSachPhongBan) {
            if (pb.getMaPhong().equals(MaPhongBan)) {
                pb.setTenPhongBan(TenPhongBanMoi);
                pb.setMaTruongPhong(MaTruongPhongMoi);
                return true; 
            }
        }
        return false; 
    }

    public PhongBan timPhongBan(String MaPhongBan) {
        for (PhongBan pb : DanhSachPhongBan) {
            if (pb.getMaPhong().equals(MaPhongBan)) {
                return pb; 
            }
        }
        return null; 
    }

    public ArrayList<PhongBan> getdanhsachphongban() {
        return this.DanhSachPhongBan;
    }
    public void xuat() {
        for (PhongBan pb : this.DanhSachPhongBan) {
            System.out.println(pb);
        }
    }
    
    public void them(PhongBan pb){
        this.DanhSachPhongBan.add(pb);
    }
    public static String taoMa(){
        dem++;
        return String.format("PB%02d", dem);
    }
}

