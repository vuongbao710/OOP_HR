
package oop.entity.Object;

public class PhieuLuong {
    private String MaPhieu;
    private double LuongThucTe;
    private int Thang;
    private int Nam;
    private String MaNV;

    public PhieuLuong(double LuongThucTe, int Thang, int Nam, String MaNV) {
        this.LuongThucTe = LuongThucTe;
        this.Thang = Thang;
        this.Nam = Nam;
        this.MaNV = MaNV;
    }

    public PhieuLuong(String MaPhieu, double LuongThucTe, int Thang, int Nam, String MaNV) {
        this.MaPhieu = MaPhieu;
        this.LuongThucTe = LuongThucTe;
        this.Thang = Thang;
        this.Nam = Nam;
        this.MaNV = MaNV;
    }

    public String getMaPhieu() {
        return MaPhieu;
    }

    public void setMaPhieu(String MaPhieu) {
        this.MaPhieu = MaPhieu;
    }


    public double getLuongThucTe() {
        return LuongThucTe;
    }

    public void setLuongThucTe(double LuongThucTe) {
        this.LuongThucTe = LuongThucTe;
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

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }
    
    public String toString(){
        return "Ma phieu luong la \t\t" + MaPhieu + "\nMa nhan vien: \t\t"+ MaNV + 
                "\nLuong thuc te nhan duoc thang " + Thang + "/" + Nam + ": \t\t" + LuongThucTe;
    }
}
