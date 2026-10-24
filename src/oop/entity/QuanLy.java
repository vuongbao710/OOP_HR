
package oop.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLy extends NhanVien{
    private ArrayList<NhanVien> dsnvcd;
    
    
       public QuanLy(String MaNV, String HoTen, String SDT, String MaPhongBan, LocalDate BirthDate, LocalDate NgayVaoLam) {
        super.setMaNV(MaNV);
        super.setHoTen(HoTen);
        super.setSDT(SDT);
        super.setMaPhongBan(MaPhongBan);
        super.setBirthDate(BirthDate);
        super.setNgayVaoLam(NgayVaoLam);
        this.dsnvcd= new ArrayList<>();
        super.setChucVu("QuanLy");
        super.setLuongCoBan(10000000);
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
       
       

