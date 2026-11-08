
package oop.entity.Object;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import oop.entity.Enum.LoaiNghiPhep;
import oop.entity.Enum.LoaiTrangThaiCuaDon;

public class DonXinPhep {
    private String MaDon;
    private String MaNVGuiDon;
    private String MaNVDuyetDon;
    private LocalDate NgayNopDon;
    private LocalTime ThoiGianNopDon;
    private LocalDate NgayBatDau;
    private LocalDate NgayKetThuc;
    private LoaiTrangThaiCuaDon TrangThai;
    private LoaiNghiPhep LoaiNghi;
    private String Message;

    public DonXinPhep(String MaDon, String MaNVGuiDon, String MaNVDuyetDon, LocalDate NgayNopDon,LocalTime ThoiGianNopDon) {
        this.MaDon = MaDon;
        this.MaNVGuiDon = MaNVGuiDon;
        this.MaNVDuyetDon = MaNVDuyetDon;
        this.NgayNopDon = NgayNopDon;
        this.ThoiGianNopDon = ThoiGianNopDon;
        this.TrangThai = LoaiTrangThaiCuaDon.CHO_DUYET;
    }

    public String getMaDon() {
        return MaDon;
    }

    public void setMaDon(String MaDon) {
        this.MaDon = MaDon;
    }

    public String getMaNVGuiDon() {
        return MaNVGuiDon;
    }

    public void setMaNVGuiDon(String MaNVGuiDon) {
        this.MaNVGuiDon = MaNVGuiDon;
    }

    public String getMaNVDuyetDon() {
        return MaNVDuyetDon;
    }

    public void setMaNVDuyetDon(String MaNVDuyetDon) {
        this.MaNVDuyetDon = MaNVDuyetDon;
    }

    public LocalDate getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(LocalDate NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(LocalDate NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public LoaiTrangThaiCuaDon getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(LoaiTrangThaiCuaDon TrangThai) {
        this.TrangThai = TrangThai;
    }

    public LocalDate getNgayNopDon() {
        return NgayNopDon;
    }

    public void setNgayNopDon(LocalDate NgayNopDon) {
        this.NgayNopDon = NgayNopDon;
    }

    public LocalTime getThoiGianNopDon() {
        return ThoiGianNopDon;
    }

    public void setThoiGianNopDon(LocalTime ThoiGianNopDon) {
        this.ThoiGianNopDon = ThoiGianNopDon;
    }

    public LoaiNghiPhep getLoaiNghi() {
        return LoaiNghi;
    }

    public void setLoaiNghi(LoaiNghiPhep LoaiNghi) {
        this.LoaiNghi = LoaiNghi;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    @Override
    public String toString() {
        return "DonXinPhep{" + "MaDon=" + MaDon + ", MaNVGuiDon=" + MaNVGuiDon + ", MaNVDuyetDon=" + MaNVDuyetDon + ", NgayNopDon=" + NgayNopDon + ", ThoiGianNopDon=" + ThoiGianNopDon + ", NgayBatDau=" + NgayBatDau + ", NgayKetThuc=" + NgayKetThuc + ", TrangThai=" + TrangThai + ", LoaiNghi=" + LoaiNghi + ", Message=" + Message + '}';
    }
    
}
