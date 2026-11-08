
package oop.entity.Object;
import java.time.LocalDate;
public class UngVien {
    private String MaUngVien;
    private String CCCD;
    private String HoTen;
    private String SDT;
    private LocalDate NgayThangNamSinh;
    private String MaDotTuyenDung;
    private String ViTriUngTuyen;
    private DotTuyenDung Dot;
    private KhoaDaoTao Khoa;
    private KetQuaDaoTao kq;
    public UngVien(String MaUngVien, String CCCD, String HoTen, String SDT, LocalDate NgayThangNamSinh, String MaDotTuyenDung, String ViTriUngTuyen) {
        this.MaUngVien = MaUngVien;
        this.CCCD = CCCD;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.NgayThangNamSinh = NgayThangNamSinh;
        this.MaDotTuyenDung = MaDotTuyenDung;
        this.ViTriUngTuyen = ViTriUngTuyen;
        this.Dot = new DotTuyenDung();
        this.Khoa= new KhoaDaoTao();
        this.kq=new KetQuaDaoTao();
    }
    public String getMaUngVien() {
        return MaUngVien;
    }

    public void setMaUngVien(String MaUngVien) {
        this.MaUngVien = MaUngVien;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
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

    public LocalDate getNgayThangNamSinh() {
        return NgayThangNamSinh;
    }

    public void setNgayThangNamSinh(LocalDate NgayThangNamSinh) {
        this.NgayThangNamSinh = NgayThangNamSinh;
    }

    public String getMaDotTuyenDung() {
        return MaDotTuyenDung;
    }

    public void setMaDotTuyenDung(String MaDotTuyenDung) {
        this.MaDotTuyenDung = MaDotTuyenDung;
    }

    public String getViTriUngTuyen() {
        return ViTriUngTuyen;
    }

    public void setViTriUngTuyen(String ViTriUngTuyen) {
        this.ViTriUngTuyen = ViTriUngTuyen;
    }

    @Override
    public String toString() {
        return "UngVien{" + "MaUngVien=" + MaUngVien + ", CCCD=" + CCCD + ", HoTen=" + HoTen + ", SDT=" + SDT + ", NgayThangNamSinh=" + NgayThangNamSinh + ", MaDotTuyenDung=" + MaDotTuyenDung + ", ViTriUngTuyen=" + ViTriUngTuyen + ", Dot=" + Dot + ", Khoa=" + Khoa + ", kq=" + kq + '}';
    }
    
    
}
