
package oop.entity.Object;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import oop.entity.Enum.LoaiTrangThaiChamCong;
public class ChiTietChamCong {
    private String MaChiTietChamCong;
    private LoaiTrangThaiChamCong TrangThai;
    private LocalDate Date;
    private String MaNVduocChamCong;
    private LocalTime GioVao;
    private LocalTime GioRa;
    private LocalTime GioVaoCuaNV;
    private LocalTime GioRaCuaNV;
    private boolean KiemTraCheckIn;
    private boolean KiemTraCheckOut;

    public ChiTietChamCong(String MaChiTietChamCong, LoaiTrangThaiChamCong TrangThai, LocalDate Date, String MaNVduocChamCong, LocalTime GioVao, LocalTime GioRa, LocalTime GioVaoCuaNV, LocalTime GioRaCuaNV) {
        this.MaChiTietChamCong = MaChiTietChamCong;
        this.TrangThai = TrangThai;
        this.Date = Date;
        this.MaNVduocChamCong = MaNVduocChamCong;
        this.GioVao = GioVao;
        this.GioRa = GioRa;
        this.GioVaoCuaNV = GioVaoCuaNV;
        this.GioRaCuaNV = GioRaCuaNV;
        this.KiemTraCheckIn=false;
        this.KiemTraCheckOut=false;
    }

    public ChiTietChamCong(String MaChiTietChamCong, LocalDate Date, String MaNVduocChamCong, LocalTime GioVao, LocalTime GioRa, LocalTime GioVaoCuaNV, LocalTime GioRaCuaNV) {
        this.MaChiTietChamCong = MaChiTietChamCong;
        this.Date = Date;
        this.MaNVduocChamCong = MaNVduocChamCong;
        this.GioVao = GioVao;
        this.GioRa = GioRa;
        this.GioVaoCuaNV = GioVaoCuaNV;
        this.GioRaCuaNV = GioRaCuaNV;
        this.KiemTraCheckIn=false;
        this.KiemTraCheckOut=false;
    }

    public ChiTietChamCong(String MaChiTietChamCong, LocalDate Date, String MaNVduocChamCong, LocalTime GioVao, LocalTime GioRa) {
        this.MaChiTietChamCong = MaChiTietChamCong;
        this.Date = Date;
        this.MaNVduocChamCong = MaNVduocChamCong;
        this.GioVao = GioVao;
        this.GioRa = GioRa;
        this.KiemTraCheckIn=false;
        this.KiemTraCheckOut=false;
    }
    

    public String getMaChiTietChamCong() {
        return MaChiTietChamCong;
    }

    public void setMaChiTietChamCong(String MaChiTietChamCong) {
        this.MaChiTietChamCong = MaChiTietChamCong;
    }

    public LoaiTrangThaiChamCong getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(LoaiTrangThaiChamCong TrangThai) {
        this.TrangThai = TrangThai;
    }

    public LocalTime getGioVao() {
        return GioVao;
    }

    public void setGioVao(LocalTime GioVao) {
        this.GioVao = GioVao;
    }

    public LocalTime getGioRa() {
        return GioRa;
    }

    public void setGioRa(LocalTime GioRa) {
        this.GioRa = GioRa;
    }


    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public String getMaNVduocChamCong() {
        return MaNVduocChamCong;
    }

    public void setMaNVduocChamCong(String MaNVduocChamCong) {
        this.MaNVduocChamCong = MaNVduocChamCong;
    }

    public LocalTime getGioVaoCuaNV() {
        return GioVaoCuaNV;
    }

    public void setGioVaoCuaNV(LocalTime GioVaoCuaNV) {
        this.GioVaoCuaNV = GioVaoCuaNV;
    }

    public LocalTime getGioRaCuaNV() {
        return GioRaCuaNV;
    }

    public void setGioRaCuaNV(LocalTime GioRaCuaNV) {
        this.GioRaCuaNV = GioRaCuaNV;
    }

    public boolean isKiemTraCheckIn() {
        return KiemTraCheckIn;
    }

    public void setKiemTraCheckIn(boolean KiemTraCheckIn) {
        this.KiemTraCheckIn = KiemTraCheckIn;
    }

    public boolean isKiemTraCheckOut() {
        return KiemTraCheckOut;
    }

    public void setKiemTraCheckOut(boolean KiemTraCheckOut) {
        this.KiemTraCheckOut = KiemTraCheckOut;
    }

    @Override
    public String toString() {
        return "ChiTietChamCong{" + "MaChiTietChamCong=" +
                MaChiTietChamCong + ", TrangThai=" + TrangThai +
                ", Date=" + Date + ", MaNVduocChamCong=" + MaNVduocChamCong +
                ", GioVao=" + GioVao + ", GioRa=" + GioRa + ", GioVaoCuaNV=" +
                GioVaoCuaNV + ", GioRaCuaNV=" + GioRaCuaNV + '}';
    }
    
    
    public boolean isGioLam(LocalTime time){
        return time.isAfter(GioVao)&& time.isBefore(GioRa);
    }
   

    public long checkGioVao(LocalTime time){
        long SoPhutTre;
        if(time.isAfter(GioVao.plusMinutes(15))){
            setTrangThai(LoaiTrangThaiChamCong.DI_TRE);
            Duration tgian = Duration.between(time, GioVao.plusMinutes(15));
            SoPhutTre = tgian.toMinutes();
            return SoPhutTre;
        }
        else{
            setTrangThai(LoaiTrangThaiChamCong.DUNG_GIO);
            return 0;
        }
    }
    
    public long checkGioRa(LocalTime time){
        long SoPhutVeSom;
        if(time.isBefore(GioRa.minusMinutes(15))){
            setTrangThai(LoaiTrangThaiChamCong.VE_SOM);
            Duration tgian = Duration.between(time, GioRa);
            SoPhutVeSom = tgian.toMinutes();
            return SoPhutVeSom;
        }
        else{
            return 0;
        }
    }
    
    public void checkChamCong(LocalTime time, LocalDate date, NhanVien nv){
        if(!isGioLam(time)){
            System.out.println("Không thể chấm công, hiện đang ngoài ca làm việc của bạn");
            return;
        }
        if(this.KiemTraCheckIn==false){
            long SoPhutTre = checkGioVao(time);
            setGioVaoCuaNV(time);
            this.KiemTraCheckIn=true;
            if(SoPhutTre!=0){
                String MaQD = nv.getDsktkl().taoMa();
                String MaNV = nv.getMaNV();
                KyLuat kl = new KyLuat("đi làm trễ", MaQD, date, MaNV);
                double TienTacDong = kl.tinhTacDongLuong(SoPhutTre, nv);
                kl.setSoTienTacDong(TienTacDong);
                nv.getDsktkl().them(kl);
                
                int SoLanViPham = nv.getSoLanViPhamChamCong();
                nv.setSoLanViPhamChamCong(SoLanViPham+1);
                String MaThongBao = nv.getDsThongBao().taoMa();
                ThongBao tb = new ThongBao(MaThongBao, "HETHONG", date, time, GioVaoCuaNV
                        + "trễ hơn so với giờ ca làm việc của bạn là " + GioVao + " ,chúng tôi cảnh cáo bạn lần thứ " + SoLanViPham+1);
                nv.getDsThongBao().them(tb);
            }
        }
        else{
            long SoPhutTanSom = checkGioRa(time);
            setGioRaCuaNV(time);
            this.KiemTraCheckOut=true;
            if(SoPhutTanSom!=0){
                String MaQD = nv.getDsktkl().taoMa();
                String MaNV = nv.getMaNV();
                KyLuat kl = new KyLuat("tan làm sớm", MaQD, date, MaNV);
                double TienTacDong = kl.tinhTacDongLuong(SoPhutTanSom, nv);
                kl.setSoTienTacDong(TienTacDong);
                nv.getDsktkl().them(kl);
                
                int SoLanViPham = nv.getSoLanViPhamChamCong();
                nv.setSoLanViPhamChamCong(SoLanViPham+1);
                String MaThongBao = nv.getDsThongBao().taoMa();
                ThongBao tb = new ThongBao(MaThongBao, "HETHONG", date, time, GioRaCuaNV 
                        + "sớm hơn so với giờ tan ca làm việc của bạn là " + GioRa + " ,chúng tôi cảnh cáo bạn lần thứ " + SoLanViPham+1);
                nv.getDsThongBao().them(tb);
            }
        }
        
    }
    
    
}
