
package oop.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLy extends NhanVien{
    private ArrayList<NhanVien> dsnvcd;
    
       public QuanLy(String MaNV, String HoTen, LocalDate NgayThangNamSinh, String SDT, String MaPhongBan, LocalDate NgayVaoLam) {
        super.setMaNV(MaNV);
        super.setHoTen(HoTen);
        super.setNgayThangNamSinh(NgayThangNamSinh);
        super.setSDT(SDT);
        super.setMaPhongBan(MaPhongBan);
        super.setNgayVaoLam(NgayVaoLam);
        this.dsnvcd= new ArrayList<>();
    }
       
       public long TinhLuongCoBan(){
           return 10000000;
       }

    public ArrayList<NhanVien> getDsnvcd() {
        return dsnvcd;
    }
     
    public void ThemNVCD(NhanVien nv){
        this.dsnvcd.add(nv);
    }
       
       
       
}
