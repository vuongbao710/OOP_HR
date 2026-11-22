
package oop.entity.Object;

import java.time.LocalDate;
import oop.entity.DanhSach.DanhSachUngVien;

public class KhoaDaoTao {
    private String MaKhoaHoc;
    private String TenKhoaHoc;
    private ViTri ViTriTuyenDung;
    private LocalDate NgayBatDau;
    private LocalDate NgayKetThuc;
    private String MaNVDaoTao;
    
    private DanhSachUngVien dsuv;

    public KhoaDaoTao(String TenKhoaHoc, LocalDate NgayBatDau, LocalDate NgayKetThuc,ViTri ViTriTuyenDung, String MaNVDaoTao) {
        this.TenKhoaHoc = TenKhoaHoc;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.ViTriTuyenDung = ViTriTuyenDung;
        this.MaNVDaoTao = MaNVDaoTao;
        this.dsuv = new DanhSachUngVien();
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

    public DanhSachUngVien getDsuv() {
        return dsuv;
    }

    public void setDsuv(DanhSachUngVien dsuv) {
        this.dsuv = dsuv;
    }

    public String getMaNVDaoTao() {
        return MaNVDaoTao;
    }

    public void setMaNVDaoTao(String MaNVDaoTao) {
        this.MaNVDaoTao = MaNVDaoTao;
    }

    public ViTri getViTriTuyenDung() {
        return ViTriTuyenDung;
    }

    public void setViTriTuyenDung(ViTri ViTriTuyenDung) {
        this.ViTriTuyenDung = ViTriTuyenDung;
    }
    
    
}
