
package oop.entity.Object;

import java.time.LocalDate;
import java.time.LocalTime;
import oop.entity.Enum.LoaiCaLamPartTime;

public class NhanVienPartTime extends NhanVien{
    private LoaiCaLamPartTime Ca;
    private final int SO_GIO_CONG=4;
    private final double LUONG_MOT_GIO=25000;

    public NhanVienPartTime(String MaNV, String CCCD, String HoTen, String SDT, String MaQuanLyPhuTrach, String MaPhongBan, LocalDate BirthDate, LocalDate NgayVaoLam, String ChucVu,String HinhThucDiLam, String CaLam) {
        super(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, MaPhongBan, BirthDate, NgayVaoLam, ChucVu, HinhThucDiLam, CaLam);
        this.HinhThucDiLam="part-time";
        this.Ca=LoaiCaLamPartTime.NhanDien(CaLam);
    }

    public NhanVienPartTime() {
    }
    
       public double TinhLuongThucTe(){
           return bcc.TongSoNgayCong()*SO_GIO_CONG*LUONG_MOT_GIO;
       }


    public int getSO_GIO_CONG() {
        return SO_GIO_CONG;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public double getLUONG_MOT_GIO() {
        return LUONG_MOT_GIO;
    }
     
    
    public LoaiCaLamPartTime getCa() {
        return Ca;
    }
    
    public String getCaMoTa(){
        return Ca.getMoTa();
    }

    public void setCa(LoaiCaLamPartTime Ca) {
        this.Ca = Ca;
    }

    public LocalTime getGioVaoCa(){
        return Ca.getGioVao();
    }
    public LocalTime getGioRaCa(){
        return Ca.getGioRa();
    }
    }
       
       

