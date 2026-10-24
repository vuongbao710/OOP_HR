
package oop.entity;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class NhanVien {


    protected String MaNV;
    protected String HoTen;
    protected String SDT;
    protected String MaPhongBan;
    protected LocalDate BirthDate;
    protected LocalDate NgayVaoLam;
    protected String ChucVu;
    protected long LuongCoBan;

    
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    
    public abstract long TinhLuongCoBan();


    public String getMaPhongBan() {
        return MaPhongBan;
    }

    public void setMaPhongBan(String MaPhongBan) {
        this.MaPhongBan = MaPhongBan;
    }

    public LocalDate getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(LocalDate BirthDate) {
        this.BirthDate = BirthDate;
    }

    public LocalDate getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(LocalDate NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    public long getLuongCoBan() {
        return LuongCoBan;
    }

    public void setLuongCoBan(long LuongCoBan) {
        this.LuongCoBan = LuongCoBan;
    }
    
    

    public String getChucVu(){
        return ChucVu;
    }
    public void setChucVu(String Loai){
        if(Loai.equals("NhanVienCapDuoi")||Loai.equals("QuanLy")){
            this.ChucVu=Loai;
        }
        else{
            System.out.println("Loi loai nhan vien");
        }
    }
    public String toString(){
        return "Ma nhan vien :" + MaNV + "| Ho ten :" + HoTen + "| SDT :" + SDT + "| Ma phong ban :" + MaPhongBan;
    }
}
