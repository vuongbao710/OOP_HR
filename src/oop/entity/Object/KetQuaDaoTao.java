
package oop.entity.Object;

import oop.entity.Enum.LoaiKetQua;

public class KetQuaDaoTao {
    private String MaKetQua;
    private int DiemSo;
    private LoaiKetQua KetQua;
    private String MaUngVien;
    private String MaKhoaHoc;

    public KetQuaDaoTao() {
    }
    
    
    public KetQuaDaoTao(String MaKetQua, int DiemSo, LoaiKetQua KetQua, String MaUngVien, String MaKhoaHoc) {
        this.MaKetQua = MaKetQua;
        this.DiemSo = DiemSo;
        this.KetQua = KetQua;
        this.MaUngVien = MaUngVien;
        this.MaKhoaHoc = MaKhoaHoc;
    }

    public String getMaKetQua() {
        return MaKetQua;
    }

    public void setMaKetQua(String MaKetQua) {
        this.MaKetQua = MaKetQua;
    }

    public int getDiemSo() {
        return DiemSo;
    }

    public void setDiemSo(int DiemSo) {
        this.DiemSo = DiemSo;
    }

    public LoaiKetQua getKetQua() {
        return KetQua;
    }

    public void setKetQua(LoaiKetQua KetQua) {
        this.KetQua = KetQua;
    }

    public String getMaUngVien() {
        return MaUngVien;
    }

    public void setMaUngVien(String MaUngVien) {
        this.MaUngVien = MaUngVien;
    }

    public String getMaKhoaHoc() {
        return MaKhoaHoc;
    }

    public void setMaKhoaHoc(String MaKhoaHoc) {
        this.MaKhoaHoc = MaKhoaHoc;
    }

    @Override
    public String toString() {
        return "KetQuaDaoTao{" + "MaKetQua=" + MaKetQua + ", DiemSo=" + DiemSo + ", KetQua=" + KetQua + ", MaUngVien=" + MaUngVien + ", MaKhoaHoc=" + MaKhoaHoc + '}';
    }
    
    
}
