
package oop.entity.Object;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import oop.entity.Enum.LoaiKyLuat;
import oop.entity.Enum.LoaiTrangThaiChamCong;
import oop.entity.Enum.LoaiViPham;
import oop.entity.Service.ThaoTac;
public class ChiTietChamCong {
    private String MaChiTietChamCong;
    private LoaiTrangThaiChamCong TrangThai = LoaiTrangThaiChamCong.KHONG_CHAM_CONG;
    private LocalDate Date;
    private String MaNVduocChamCong;
    private LocalTime GioVao;
    private LocalTime GioRa;
    private LocalTime GioVaoCuaNV;
    private LocalTime GioRaCuaNV;
    private boolean KiemTraCheckIn;
    private boolean KiemTraCheckOut;
    private boolean isDangKyOT = false;
    private LocalTime GioXongOT;
    private double SoGioLamOT = 0;

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

    public ChiTietChamCong(String MaChiTietChamCong, LocalDate Date, String MaNVduocChamCong) {
        this.MaChiTietChamCong = MaChiTietChamCong;
        this.Date = Date;
        this.MaNVduocChamCong = MaNVduocChamCong;
        
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

    public boolean isIsDangKyOT() {
        return isDangKyOT;
    }

    public void setIsDangKyOT(boolean isDangKyOT) {
        this.isDangKyOT = isDangKyOT;
    }

    public LocalTime getGioXongOT() {
        return GioXongOT;
    }

    public void setGioXongOT(LocalTime GioXongOT) {
        this.GioXongOT = GioXongOT;
    }

    public double getSoGioLamOT() {
        return SoGioLamOT;
    }

    public void setSoGioLamOT(double SoGioLamOT) {
        this.SoGioLamOT = SoGioLamOT;
    }
    
    

    @Override
    public String toString() {
        return "ChiTietChamCong{" + "MaChiTietChamCong=" +
                MaChiTietChamCong + ", TrangThai=" + TrangThai +
                ", Date=" + Date + ", MaNVduocChamCong=" + MaNVduocChamCong +
                ", GioVao=" + GioVao + ", GioRa=" + GioRa + ", GioVaoCuaNV=" +
                GioVaoCuaNV + ", GioRaCuaNV=" + GioRaCuaNV + '}';
    }
    
    
    public boolean isGioLam(LocalTime time, String CaLam){
        LocalTime gioRa = isDangKyOT ? GioXongOT : GioRa;
        if(CaLam.equalsIgnoreCase("Tối")){
            return !time.isBefore(GioVao.minusMinutes(30)) || !time.isAfter(gioRa.plusMinutes(30));
        }
            return !time.isBefore(GioVao.minusMinutes(30)) && !time.isAfter(gioRa.plusMinutes(30));
    }
   

    public long checkGioVao(LocalTime time){
    if(time.isAfter(GioVao.plusMinutes(5))){
        setTrangThai(LoaiTrangThaiChamCong.DI_TRE);
        return Duration.between(GioVao, time).toMinutes();
    }
    return 0;
}
    
    public long checkGioRa(LocalTime time){
    if(time.isBefore(GioRa.minusMinutes(5))){
        setTrangThai(LoaiTrangThaiChamCong.VE_SOM);
        return Duration.between(time, GioRa).toMinutes();
    }
    return 0;
}
    
    public KyLuat taoKyLuatChamCong(LocalDate date, NhanVien nv ){
        String MaNV = nv.getMaNV();
                LoaiViPham vp = LoaiViPham.VI_PHAM_CHAM_CONG;
                LoaiKyLuat k = nv.getDsViPham().HoanChinhKyLuat(vp, date, nv);
                KyLuat kl = new KyLuat(k, vp, date, "HETHONG" , MaNV);
        return kl;        
    }
    
    public void checkChamCong(LocalTime time, LocalDate date, NhanVien nv){
        String CaLam = nv.getCaLam();
        if(!isGioLam(time, CaLam)){
            System.out.println("Không thể chấm công, hiện đang ngoài ca làm việc của bạn");
            return;
        }
        if(nv.getDsDon().coTrongNgayNghiPhepKhong(date)){
            System.out.println("Hôm nay trong ngày nghỉ phép của bạn, không thể chấm công");
            return;
        }
        if(this.KiemTraCheckIn==false){
            long SoPhutTre = checkGioVao(time);
            setGioVaoCuaNV(time);
            this.KiemTraCheckIn=true;
            if(SoPhutTre!=0){
                KyLuat kl = taoKyLuatChamCong(date, nv);
                
                kl.setSoPhutPhatChamCong(SoPhutTre);
                double TienTacDong = kl.tinhTacDongLuong(nv);
                kl.setSoTienTacDong(TienTacDong);
                nv.getDsktkl().them(kl);
                
                ThaoTac.XuLyKyLuat(kl.getLoaiKyLuat(), kl.getLoaiViPham(), date, time, nv);
            }
        }
        else if(this.KiemTraCheckOut==false){
            long SoPhutTanSom = checkGioRa(time);
            setGioRaCuaNV(time);
            tinhGioOT();
            this.KiemTraCheckOut=true;
            setTrangThai(LoaiTrangThaiChamCong.DUNG_GIO);
            if(SoPhutTanSom!=0){
                KyLuat kl = taoKyLuatChamCong(date, nv);
                
                kl.setSoPhutPhatChamCong(SoPhutTanSom);
                double TienTacDong = kl.tinhTacDongLuong(nv);
                kl.setSoTienTacDong(TienTacDong);
                nv.getDsktkl().them(kl);
                
                ThaoTac.XuLyKyLuat(kl.getLoaiKyLuat(), kl.getLoaiViPham(), date, time, nv);
            }
        }
        
    }
    
    public void LamOT(LocalTime gioRaCa){
    this.isDangKyOT = true;
    this.GioXongOT = gioRaCa.plusHours(2);
    }
    public void tinhGioOT(){
    if(!isDangKyOT || GioRaCuaNV == null){
        return;
    }
    long phut = Duration.between(GioRa, GioRaCuaNV).toMinutes();
    if(phut < -720){          // ra sau nửa đêm (ca tối)
        phut = phut + 1440;
    }
    else if(phut > 720){      // về sớm trước nửa đêm trong khi giờ ra của ca nằm sau nửa đêm
        phut = phut - 1440;
    }
    if(phut <= 0){            // về đúng giờ ca hoặc sớm hơn: trốn OT
        this.SoGioLamOT = 0;
    } else {
        this.SoGioLamOT = Math.min(phut, 120) / 60.0;
    }
}
    
}
