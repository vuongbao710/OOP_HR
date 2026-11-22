package oop.entity.Object;

import oop.entity.DanhSach.DanhSachThongBao;

public class Boss {
    public static final String MA = "BOSS"; 
    private static TaiKhoan tk;
    private static DanhSachThongBao dsThongBao = new DanhSachThongBao();  

    public static TaiKhoan getTk() { return tk; }
    public static void setTk(TaiKhoan tk) { Boss.tk = tk; }
    public static DanhSachThongBao getDsThongBao() { return dsThongBao; }
    public static void setDsThongBao(DanhSachThongBao dsThongBao) { Boss.dsThongBao = dsThongBao; }
}