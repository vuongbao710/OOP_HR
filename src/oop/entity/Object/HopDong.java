
package oop.entity.Object;

import java.time.LocalDate;
import oop.entity.Enum.LOAI_HOP_DONG;
import oop.entity.Enum.TRANG_THAI_HOP_DONG;

public class HopDong {
    private String MaHopDong;
    private String MaNV;
    private LocalDate NgayKy;
    private LocalDate NgayBatDauHopDong;
    private LocalDate NgayHetHan;
    private ViTri loaiViTri;
    private String ViTriLamViec;
    private double LuongCoBan;
    private double LuongTheoGio;
    private double PhuCap;
    private LOAI_HOP_DONG LoaiHopDong;
    private TRANG_THAI_HOP_DONG TrangThaiHopDong;
    private String GhiChu;

    public HopDong() {
    }
    
    public HopDong(String MaHD, String MaNV, LocalDate NgayKy, LocalDate NgayBatDauHopDong, LocalDate NgayHetHan, String ViTriLamViec, double LuongCoBan, double LuongTheoGio, double PhuCap, LOAI_HOP_DONG LoaiHopDong, TRANG_THAI_HOP_DONG TrangThaiHopDong, String GhiChu) {
        this.MaHopDong = MaHD;
        this.MaNV = MaNV;
        this.NgayKy = NgayKy;
        this.NgayBatDauHopDong = NgayBatDauHopDong;
        this.NgayHetHan = NgayHetHan;
        this.ViTriLamViec = ViTriLamViec;
        this.LuongCoBan = LuongCoBan;
        this.LuongTheoGio = LuongTheoGio;
        this.PhuCap = PhuCap;
        this.LoaiHopDong = LoaiHopDong;
        this.TrangThaiHopDong = TrangThaiHopDong;
        this.GhiChu = GhiChu;
        this.loaiViTri = ViTri.NhanDien(ViTriLamViec);
    }

    public HopDong(String MaNV, LocalDate NgayKy, LocalDate NgayBatDauHopDong, LocalDate NgayHetHan, String ViTriLamViec, double LuongCoBan, double LuongTheoGio, double PhuCap, LOAI_HOP_DONG LoaiHopDong, TRANG_THAI_HOP_DONG TrangThaiHopDong, String GhiChu) {
        
        this.MaNV = MaNV;
        this.NgayKy = NgayKy;
        this.NgayBatDauHopDong = NgayBatDauHopDong;
        this.NgayHetHan = NgayHetHan;
        this.ViTriLamViec = ViTriLamViec;
        this.LuongCoBan = LuongCoBan;
        this.LuongTheoGio = LuongTheoGio;
        this.PhuCap = PhuCap;
        this.LoaiHopDong = LoaiHopDong;
        this.TrangThaiHopDong = TrangThaiHopDong;
        this.GhiChu = GhiChu;
        this.loaiViTri = ViTri.NhanDien(ViTriLamViec);
    }

    public ViTri getLoaiViTri() {
        return loaiViTri;
    }

    public void setLoaiViTri(ViTri loaiViTri) {
        this.loaiViTri = loaiViTri;
        setViTriLamViec(loaiViTri.getMoTa());
    }

    
    public String getMaHopDong() {
        return MaHopDong;
    }

    public void setMaHopDong(String MaHopDong) {
        this.MaHopDong = MaHopDong;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public LocalDate getNgayKy() {
        return NgayKy;
    }

    public void setNgayKy(LocalDate NgayKy) {
        this.NgayKy = NgayKy;
    }

    public LocalDate getNgayBatDauHopDong() {
        return NgayBatDauHopDong;
    }

    public void setNgayBatDauHopDong(LocalDate NgayBatDauHopDong) {
        this.NgayBatDauHopDong = NgayBatDauHopDong;
    }

    public LocalDate getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(LocalDate NgayHetHan) {
        this.NgayHetHan = NgayHetHan;
    }

    public String getViTriLamViec() {
        return ViTriLamViec;
    }

    private void setViTriLamViec(String ViTriLamViec) {
        this.ViTriLamViec = ViTriLamViec;
    }

    public double getPhuCap() {
        return PhuCap;
    }

    public void setPhuCap(double PhuCap) {
        this.PhuCap = PhuCap;
    }

    public LOAI_HOP_DONG getLoaiHopDong() {
        return LoaiHopDong;
    }

    public void setLoaiHopDong(LOAI_HOP_DONG LoaiHopDong) {
        this.LoaiHopDong = LoaiHopDong;
    }

    public TRANG_THAI_HOP_DONG getTrangThaiHopDong() {
        return TrangThaiHopDong;
    }

    public void setTrangThaiHopDong(TRANG_THAI_HOP_DONG TrangThaiHopDong) {
        this.TrangThaiHopDong = TrangThaiHopDong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
    public double getMucLuongCoSo() {
        if (this.LoaiHopDong == LOAI_HOP_DONG.PART_TIME) {
            return this.LuongTheoGio;
        }
        return this.LuongCoBan;
    }
    
    public void setMucLuongCoSo(double LuongCoSo){
        if (this.LoaiHopDong == LOAI_HOP_DONG.PART_TIME) {
            this.LuongTheoGio = LuongCoSo;
        }
        else{
            this.LuongCoBan = LuongCoSo;
        }
    }

    @Override
    public String toString() {
        return "HopDong{" + "MaHopDong=" + MaHopDong + ", MaNV=" + MaNV + ", NgayKy=" + NgayKy + ", NgayBatDauHopDong=" 
                + NgayBatDauHopDong + ", NgayHetHan=" + NgayHetHan + ", ViTriLamViec=" + ViTriLamViec + ", LuongCoBan=" 
                + LuongCoBan + ", LuongTheoGio=" + LuongTheoGio + ", PhuCap=" + PhuCap + ", LoaiHopDong=" + LoaiHopDong 
                + ", TrangThaiHopDong=" + TrangThaiHopDong + ", GhiChu=" + GhiChu + '}';
    }
    
    
    
}
