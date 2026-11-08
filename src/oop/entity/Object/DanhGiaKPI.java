
package oop.entity.Object;


public class DanhGiaKPI{
    private String MaDanhGia;
    private int DiemKPI;
    private String XepLoai;
    private int Thang;
    private int Nam;
    private double HeSoKPI;
    private String MaNVDanhGia;
    private String MaNVNhanDanhGia;

    public DanhGiaKPI(String MaDanhGia, int DiemKPI, String XepLoai, int Thang, int Nam, String MaNVDanhGia, String MaNVNhanDanhGia) {
        this.MaDanhGia = MaDanhGia;
        if(DiemKPI<0 && DiemKPI>100){
            System.out.println(MaDanhGia + " khong hop le");
            return;
        }
        else{this.DiemKPI = DiemKPI;}
        this.XepLoai = PhanXepLoai();
        this.Thang = Thang;
        this.Nam = Nam;
        this.MaNVDanhGia = MaNVDanhGia;
        this.MaNVNhanDanhGia = MaNVNhanDanhGia;
    }
    
    public String PhanXepLoai(){
        if(this.DiemKPI>90){
            this.HeSoKPI=2;
            return "Xuat sac";
        }
        else if(this.DiemKPI>70){
            this.HeSoKPI=1.5;
            return "Tot";
        }
        else if(this.DiemKPI>50){
            this.HeSoKPI=1.2;
            return "Dat";
        }
        else{
            this.HeSoKPI=1;
            return "Khong Dat";
        }
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

    public void setDiemKPI(int DiemKPI) {
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

    public double getHeSoKPI() {
        return HeSoKPI;
    }

    public void setHeSoKPI(double HeSoKPI) {
        this.HeSoKPI = HeSoKPI;
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

    @Override
    public String toString() {
        return "DanhGiaKPI{" + "MaDanhGia=" + MaDanhGia + "}\n DiemKPI=\t" + DiemKPI + 
                "\n XepLoai=\t" + XepLoai + "\n Thang=\t" + Thang + "\nNam\t" + Nam + 
                "\n HeSoKPI\t" + HeSoKPI + "\n MaNVDanhGia=\t" + MaNVDanhGia + "\n MaNVNhanDanhGia=\t" + MaNVNhanDanhGia;
    }
    
    
}
