
package oop.entity.Object;

import java.time.LocalDate;
import oop.entity.DanhSach.DanhSachUngVien;
import oop.entity.Enum.TRANG_THAI_DOT;

public class DotTuyenDung {
    private String MaDotTuyenDung;
    private int SoLuongCanTuyen;
    private String MaNguoiTuyen;
    private LocalDate NgayTuyenDung;
    private LocalDate NgayHetHan;
    private TRANG_THAI_DOT TrangThai;
    private ViTri ViTri;
    private DanhSachUngVien dsUV;

    public DotTuyenDung(int SoLuongCanTuyen, String MaNguoiTuyen, LocalDate NgayTuyenDung, LocalDate NgayHetHan, ViTri ViTri) {
        
        this.SoLuongCanTuyen = SoLuongCanTuyen;
        this.MaNguoiTuyen = MaNguoiTuyen;
        this.NgayTuyenDung = NgayTuyenDung;
        this.NgayHetHan = NgayHetHan;
        this.ViTri = ViTri;
        this.TrangThai = TRANG_THAI_DOT.CHO_DUYET;
        this.dsUV = new DanhSachUngVien();
    }


    public TRANG_THAI_DOT getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(TRANG_THAI_DOT TrangThai) {
        this.TrangThai = TrangThai;
    }
    

    public String getMaDotTuyenDung() {
        return MaDotTuyenDung;
    }

    public void setMaDotTuyenDung(String MaDotTuyenDung) {
        this.MaDotTuyenDung = MaDotTuyenDung;
    }

    public int getSoLuongCanTuyen() {
        return SoLuongCanTuyen;
    }

    public void setSoLuongCanTuyen(int SoLuongCanTuyen) {
        this.SoLuongCanTuyen = SoLuongCanTuyen;
    }

    public String getMaNguoiTuyen() {
        return MaNguoiTuyen;
    }

    public void setMaNguoiTuyen(String MaNguoiTuyen) {
        this.MaNguoiTuyen = MaNguoiTuyen;
    }

    public LocalDate getNgayTuyenDung() {
        return NgayTuyenDung;
    }

    public void setNgayTuyenDung(LocalDate NgayTuyenDung) {
        this.NgayTuyenDung = NgayTuyenDung;
    }

    public LocalDate getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(LocalDate NgayHetHan) {
        this.NgayHetHan = NgayHetHan;
    }

    public ViTri getViTri() {
        return ViTri;
    }

    public void setViTri(ViTri ViTri) {
        this.ViTri = ViTri;
    }


    @Override
    public String toString() {
        return "DotTuyenDung{" + "MaDotTuyenDung=" + MaDotTuyenDung + ", SoLuongCanTuyen=" + SoLuongCanTuyen 
                + ", MaNguoiTuyen=" + MaNguoiTuyen + ", NgayTuyenDung=" + NgayTuyenDung + ", NgayHetHan=" + NgayHetHan 
                + ", TrangThai=" + TrangThai + ", ViTri=" + ViTri + '}';
    }
    
    public void themUngVien(UngVien uv){
        this.dsUV.them(uv);
    }
    public void xoaUngVien(String MaUngVien){
        UngVien uv = this.dsUV.timKiem(MaUngVien);
        if(uv==null){
            System.out.println("Không có ứng viên có mã bạn nhập");
            return;
        }
        this.dsUV.xoa(uv);
    }
    public UngVien timKiem(String MaUngVien){
        UngVien uv = this.dsUV.timKiem(MaUngVien);
        if(uv==null){
            System.out.println("Không có ứng viên có mã bạn nhập");
        }
        return uv;
    }
   
    
    
}
