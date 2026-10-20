
package oop.entity;
import java.time.LocalDate;

public abstract class NhanVien {

    private String MaNV;
    private String Hoten;
    private LocalDate NgayThangNamSinh;
    private String SDT;
    private String MaPhongBan;
    private LocalDate NgayVaoLam;

    
    
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public LocalDate getNgayThangNamSinh() {
        return NgayThangNamSinh;
    }

    public void setNgayThangNamSinh(LocalDate NgayThangNamSinh) {
        this.NgayThangNamSinh = NgayThangNamSinh;
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

    public LocalDate getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(LocalDate NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }
    
    
    
}
