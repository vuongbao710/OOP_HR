
package oop.entity;

import java.time.LocalDate;

public class NhanVienCapDuoi extends NhanVien {
    private String MaQuanLyPhuTrach;

    public NhanVienCapDuoi() {
        this.MaQuanLyPhuTrach = null;
    }
    
    public NhanVienCapDuoi(String MaNV, String HoTen, LocalDate NgayThangNamSinh, String SDT, String MaPhongBan, LocalDate NgayVaoLam, String MaQuanLyPhuTrach) {
        super.setMaNV(MaNV);
        super.setHoTen(HoTen);
        super.setNgayThangNamSinh(NgayThangNamSinh);
        super.setSDT(SDT);
        super.setMaPhongBan(MaPhongBan);
        super.setNgayVaoLam(NgayVaoLam);
        this.MaQuanLyPhuTrach=MaQuanLyPhuTrach;
        
    }
    
    public long TinhLuongCoBan(){
        return 5000000;
    }

    public String getMaQuanLyPhuTrach() {
        return MaQuanLyPhuTrach;
    }

    public void setMaQuanLyPhuTrach(String MaQuanLyPhuTrach) {
        this.MaQuanLyPhuTrach = MaQuanLyPhuTrach;
    }
    
}
