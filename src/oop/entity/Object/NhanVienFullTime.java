
package oop.entity.Object;

import java.time.LocalDate;
import java.time.LocalTime;
import oop.entity.DanhSach.BangChamCong;
import oop.entity.DanhSach.DanhSachDanhGiaKPI;
import oop.entity.Enum.LoaiCaLamFullTime;

public class NhanVienFullTime extends NhanVien {
    private DanhSachDanhGiaKPI dskpi;
    private LoaiCaLamFullTime Ca;
    private final int SO_GIO_CONG=8;
    private final int SO_NGAY_CONG_CHUAN = 26;


   

    public NhanVienFullTime(String MaNV, String CCCD, String HoTen, String SDT, String MaQuanLyPhuTrach, LocalDate BirthDate, String CaLam) {
        super(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, BirthDate, CaLam);
        this.Ca=LoaiCaLamFullTime.NhanDien(CaLam);
        this.dskpi = new DanhSachDanhGiaKPI();
    }

    public double TinhLuongThucTe(){
    double luongTheoNgayCong = TinhLuongMotNgay() * super.getBcc().getSoNgayCong();
    return TinhLuongThucNhan(luongTheoNgayCong);
}
    
    public double TinhHeSoKPI(){
        int thang = super.getBcc().getThang();
        int nam = super.getBcc().getNam();
        int quyHienTai = (thang - 1) / 3 + 1;
        int quyTruoc;
        int namQuyTruoc;
        if(quyHienTai == 1){
        quyTruoc = 4;
        namQuyTruoc = nam - 1;
    } else {
        quyTruoc = quyHienTai - 1;
        namQuyTruoc = nam;
    }
        double heSo = 1.0;
        for(DanhGiaKPI kpi : dskpi.getDskpi()){
        int quyCuaKPI = (kpi.getThang() - 1) / 3 + 1;
        if(kpi.getNam() == namQuyTruoc && quyCuaKPI == quyTruoc){
            heSo = kpi.getHeSoKPI();
        }
    }
        return heSo;
    }

    @Override
    public String toString() {
        return super.toString() + ", SO_GIO_CONG=" + SO_GIO_CONG + ", SO_NGAY_CONG_CHUAN=" + SO_NGAY_CONG_CHUAN + ", MaQuanLyPhuTrach=" + super.getMaQuanLyPhuTrach() + '}';
    }

    public DanhSachDanhGiaKPI getDskpi() {
        return dskpi;
    }

    public void setDskpi(DanhSachDanhGiaKPI dskpi) {
        this.dskpi = dskpi;
    }

    public int getSO_NGAY_CONG_CHUAN() {
        return SO_NGAY_CONG_CHUAN;
    }
    
    public double TinhLuongMotGio(){
        return (super.getLuongCoSo()/SO_NGAY_CONG_CHUAN)/SO_GIO_CONG;
    }
    public double TinhLuongMotPhut(){
        return TinhLuongMotGio()/60;
    }
    
    public double TinhLuongMotNgay(){
        return TinhLuongMotGio()*SO_GIO_CONG;
    }

    public int getSO_GIO_CONG() {
        return SO_GIO_CONG;
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
    
    
        
    public double TinhToanTienThuongTet(LocalDate MocBatDau){
        int thang = MocBatDau.getMonthValue();
        int nam = MocBatDau.getYear();
        int SoNgayChamCong = 0;
        double luongCoSo = super.getLuongCoSo();
        while(thang!=13){
            BangChamCong bcc = super.getDsbcc().timKiem(thang, nam);
            if(bcc != null){
                SoNgayChamCong = SoNgayChamCong + bcc.getSoNgayCong();
                }
            thang = thang+1;
            
        }
        if(SoNgayChamCong>24){
            if(SoNgayChamCong>71){
                if(SoNgayChamCong>143){
                    if(SoNgayChamCong>215){
                        if(SoNgayChamCong>287){
                           return luongCoSo;  
                        }
                        return luongCoSo*80/100;
                    }
                    return luongCoSo*50/100;
                }
                return luongCoSo*25/100;
            }
            return luongCoSo*10/100;
        }
        return 0;
        
    }
    
}
