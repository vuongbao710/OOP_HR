
package oop.entity.Object;

import java.time.LocalDate;

public class KhoaDaoTao {
    private String MaKhoaHoc;
    private String TenKhoaHoc;
    private LocalDate NgayBatDau;
    private LocalDate NgayKetThuc;

    public KhoaDaoTao(String MaKhoaHoc, String TenKhoaHoc, LocalDate NgayBatDau, LocalDate NgayKetThuc) {
        this.MaKhoaHoc = MaKhoaHoc;
        this.TenKhoaHoc = TenKhoaHoc;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
    }

    public KhoaDaoTao() {
    }
    
    public String getMaKhoaHoc() {
        return MaKhoaHoc;
    }

    public void setMaKhoaHoc(String MaKhoaHoc) {
        this.MaKhoaHoc = MaKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return TenKhoaHoc;
    }

    public void setTenKhoaHoc(String TenKhoaHoc) {
        this.TenKhoaHoc = TenKhoaHoc;
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
    
    
}
