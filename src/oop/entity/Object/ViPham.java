
package oop.entity.Object;

import java.time.LocalDate;
import oop.entity.Enum.LoaiViPham;

public class ViPham {
    private LoaiViPham Loai;
    private LocalDate NgayViPham;
    private LocalDate NgayReset;
    private String ghiChu;

    public ViPham(LoaiViPham Loai, LocalDate NgayViPham) {
        this.Loai = Loai;
        this.NgayViPham = NgayViPham;
    }

    public LocalDate getNgayReset() {
        return NgayReset;
    }

    public void setNgayReset(LocalDate NgayReset) {
        this.NgayReset = NgayReset;
    }

    public LoaiViPham getLoai() {
        return Loai;
    }

    public void setLoai(LoaiViPham Loai) {
        this.Loai = Loai;
    }

    public LocalDate getNgayViPham() {
        return NgayViPham;
    }

    public void setNgayViPham(LocalDate NgayViPham) {
        this.NgayViPham = NgayViPham;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
