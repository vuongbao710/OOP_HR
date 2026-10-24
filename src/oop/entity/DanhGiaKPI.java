
package oop.entity;

public class DanhGiaKPI {
    private String MaDanhGia;
    private float DiemKPI;
    private String XepLoai;
    private int Thang;
    private int Nam;
    private String MaNVDanhGia;
    private String MaNVNhanDanhGia;

    public DanhGiaKPI(String MaDanhGia, float DiemKPI, String XepLoai, int Thang, int Nam, String MaNVDanhGia, String MaNVNhanDanhGia) {
        this.MaDanhGia = MaDanhGia;
        if(DiemKPI<0 && DiemKPI>10){
            System.out.println(MaDanhGia + " khong hop le");
        }
        else{this.DiemKPI = DiemKPI;}
        this.XepLoai = XepLoai;
        this.Thang = Thang;
        this.Nam = Nam;
        this.MaNVDanhGia = MaNVDanhGia;
        this.MaNVNhanDanhGia = MaNVNhanDanhGia;
    }

    public String getMaDanhGia() {
        return MaDanhGia;
    }

    public void setMaDanhGia(String MaDanhGia) {
        this.MaDanhGia = MaDanhGia;
    }

    public float getDiemKPI() {
        return DiemKPI;
    }

    public void setDiemKPI(float DiemKPI) {
        this.DiemKPI = DiemKPI;
    }

    public String getXepLoai() {
        return XepLoai;
    }

    public void setXepLoai(String XepLoai) {
        this.XepLoai = XepLoai;
    }

    public int getThang() {
        return Thang;
    }

    public void setThang(int Thang) {
        this.Thang = Thang;
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int Nam) {
        this.Nam = Nam;
    }

    public String getMaNVDanhGia() {
        return MaNVDanhGia;
    }

    public void setMaNVDanhGia(String MaNVDanhGia) {
        this.MaNVDanhGia = MaNVDanhGia;
    }

    public String getMaNVNhanDanhGia() {
        return MaNVNhanDanhGia;
    }

    public void setMaNVNhanDanhGia(String MaNVNhanDanhGia) {
        this.MaNVNhanDanhGia = MaNVNhanDanhGia;
    }
    
    
}
