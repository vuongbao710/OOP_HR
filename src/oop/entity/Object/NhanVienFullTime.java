
package oop.entity.Object;

import java.time.LocalDate;
import java.time.LocalTime;
import oop.entity.DanhSach.DanhSachDanhGiaKPI;
import oop.entity.Enum.LoaiCaLamFullTime;

public class NhanVienFullTime extends NhanVien {
    private DanhSachDanhGiaKPI dskpi;
    private LoaiCaLamFullTime Ca;
    private double LuongCoBan;
    private final int SO_GIO_CONG=8;
    private final int SO_NGAY_CONG_CHUAN = 26;

    public NhanVienFullTime() {
    }

    public NhanVienFullTime(DanhSachDanhGiaKPI dskpi, String MaNV, String CCCD, String HoTen, String SDT, String MaQuanLyPhuTrach, String MaPhongBan, LocalDate BirthDate, LocalDate NgayVaoLam, String ChucVu,String HinhThucDiLam, String CaLam) {
        super(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, MaPhongBan, BirthDate, NgayVaoLam, ChucVu, HinhThucDiLam, CaLam);
        this.Ca=LoaiCaLamFullTime.NhanDien(CaLam);
        this.LuongCoBan=this.ChucVu.getLuongCoBan();
        this.dskpi = dskpi;
    }

    
    
    public double TinhLuongThucTe(){
        double LuongTangCaMotGio = (LuongCoBan/SO_NGAY_CONG_CHUAN)/SO_GIO_CONG*1.5;
        double LuongMotThang = LuongTangCaMotGio*bcc.TongSoGioOT() + LuongCoBan;
        double LuongChiuThue = LuongMotThang - 0.105*LuongMotThang;
        
        double ThueTNCN;
        double Moc1=5000000;double Thue1=0.05;
        double Moc2=10000000;double Thue2=0.1;
        double Moc3=18000000;double Thue3=0.15;
        double Moc4=32000000;double Thue4=0.2;
        double Moc5=52000000;double Thue5=0.25;
        double Moc6=80000000;double Thue6=0.3;
        double Thue7=0.35;
        if(LuongMotThang<9000000){
            return LuongMotThang;
        }
        else{
            if(LuongChiuThue<Moc1){
                return LuongChiuThue*Thue1;
            }
            else{
            ThueTNCN=Moc1*Thue1;
                if(LuongChiuThue<Moc2){
                    return ThueTNCN+(LuongChiuThue-Moc1)*Thue2;
                    }
                    else{
                    ThueTNCN = ThueTNCN + (Moc2-Moc1)*Thue2;
                        if(LuongChiuThue<Moc3){
                        return ThueTNCN+(LuongChiuThue-Moc2)*Thue3;
                        }
                        
                        else{
                    ThueTNCN = ThueTNCN + (Moc3-Moc2)*Thue3;
                            if(LuongChiuThue<Moc4){
                            return ThueTNCN+(LuongChiuThue-Moc3)*Thue4;
                                }
                    else{
                    ThueTNCN = ThueTNCN + (Moc4-Moc3)*Thue3;
                    if(LuongChiuThue<Moc5){
                            return ThueTNCN+(LuongChiuThue-Moc4)*Thue5;
                    }
                    
                    else{
                    ThueTNCN = ThueTNCN + (Moc5-Moc4)*Thue5;
                        if(LuongChiuThue<Moc6){
                        return ThueTNCN+(LuongChiuThue-Moc5)*Thue6;
                    }
                    
                    else{
                        ThueTNCN=ThueTNCN + (Moc6-Moc5)*Thue6;
                        return ThueTNCN+(LuongChiuThue - 80000000)*Thue7;
                    }
                    }
                        }
                    }
            }
        }
        
    }
    }
    public String getMaQuanLyPhuTrach() {
        return MaQuanLyPhuTrach;
    }

    public void setMaQuanLyPhuTrach(String MaQuanLyPhuTrach) {
        this.MaQuanLyPhuTrach = MaQuanLyPhuTrach;
    }


    @Override
    public String toString() {
        return super.toString() + ", SO_GIO_CONG=" + SO_GIO_CONG + ", SO_NGAY_CONG_CHUAN=" + SO_NGAY_CONG_CHUAN + ", MaQuanLyPhuTrach=" + MaQuanLyPhuTrach + '}';
    }

    public DanhSachDanhGiaKPI getDskpi() {
        return dskpi;
    }

    public void setDskpi(DanhSachDanhGiaKPI dskpi) {
        this.dskpi = dskpi;
    }
    


    public int getSO_GIO_CONG() {
        return SO_GIO_CONG;
    }

    public double getLuongCoBan() {
        return LuongCoBan;
    }

    public void setLuongCoBan(double LuongCoBan) {
        this.LuongCoBan = LuongCoBan;
    }

    public LoaiCaLamFullTime getCa() {
        return Ca;
    }
    
    public String getCaMoTa(){
        return Ca.getMoTa();
    }

    public void setCa(LoaiCaLamFullTime Ca) {
        this.Ca = Ca;
    }

    public LocalTime getGioVaoCa(){
        return Ca.getGioVao();
    }
    public LocalTime getGioRaCa(){
        return Ca.getGioRa();
    }
    
    
}
