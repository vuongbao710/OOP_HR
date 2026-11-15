
package oop.entity.Object;

public class PhieuLuong {
    private String MaPhieu;
    private double LuongThucTe;
    private double LuongCoSo;
    private double PhuCap;
    private double TienLamOT;
    private double TienThuongPhat;
    private int Thang;
    private int Nam;
    private String MaNV;

    public PhieuLuong(double LuongThucTe, double LuongCoSo, double PhuCap, double TienLamOT, double TienThuongPhat, int Thang, int Nam, String MaNV) {
        this.LuongThucTe = LuongThucTe;
        this.LuongCoSo = LuongCoSo;
        this.PhuCap = PhuCap;
        this.TienLamOT = TienLamOT;
        this.TienThuongPhat = TienThuongPhat;
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

    @Override
    public String toString() {
        return "PhieuLuong{" + "MaPhieu=" + MaPhieu + "\n LuongThucTe=" + String.format("%,.0fVND", LuongThucTe) + "\n LuongCoSo=" + String.format("%,.0fVND", LuongCoSo) 
                + "\n PhuCap=" + String.format("%,.0fVND", PhuCap) + "\n TienLamOT=" + String.format("%,.0fVND", TienLamOT) + "\n TienThuongPhat=" + TienThuongPhat + "\n Thang=" 
                + Thang + "\n Nam=" + Nam + "\n MaNV=" + MaNV + '}';
    }
    
   
}
