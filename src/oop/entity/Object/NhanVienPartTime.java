
package oop.entity.Object;

import java.time.LocalDate;
import java.time.LocalTime;
import oop.entity.Enum.LoaiCaLamPartTime;

public class NhanVienPartTime extends NhanVien{
    private LoaiCaLamPartTime Ca;
    private final int SO_GIO_CONG=4;

    public NhanVienPartTime(String MaNV, String CCCD, String HoTen, String SDT, String MaQuanLyPhuTrach, LocalDate BirthDate, String CaLam) {
        super(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, BirthDate, CaLam);
        this.Ca=LoaiCaLamPartTime.NhanDien(CaLam);
    }

    public NhanVienPartTime() {
    }
    
       public double TinhLuongThucTe(){
        double LuongMotGio = super.getLuongCoSo();
        double TienTacDongKhenPhat = super.getDsktkl().getTienTacDong(super.getBcc().getNgayBatDauBang(), super.getBcc().getNgayKetThucBang(), this);
        double LuongMotThang = TinhTienLamOT() + LuongMotGio*SO_GIO_CONG*super.getBcc().getSoNgayCong()+ super.getPhuCap() + TienTacDongKhenPhat;
        double LuongChiuThue = LuongMotThang - 0.105*LuongMotThang; //trừ bảo hiểm
        double ThueTNCN = TinhThue(LuongChiuThue);
        return LuongChiuThue - ThueTNCN;
    }

    public double TinhLuongMotPhut() {
    return super.getLuongCoSo() / 60;
    }
    
    public double TinhLuongMotGio(){
        return super.getLuongCoSo();
    }
    
    public int getSO_GIO_CONG() {
        return SO_GIO_CONG;
    }

    @Override
    public String toString() {
        return super.toString();
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
       
       

