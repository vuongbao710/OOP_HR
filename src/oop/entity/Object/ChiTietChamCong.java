package oop.entity.Object;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import oop.entity.Enum.LoaiKyLuat;
import oop.entity.Enum.LoaiTrangThaiChamCong;
import oop.entity.Enum.LoaiViPham;
import oop.entity.Service.NhanSu;

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
        this.KiemTraCheckIn = false;
        this.KiemTraCheckOut = false;
    }

    public ChiTietChamCong(String MaChiTietChamCong, LocalDate Date, String MaNVduocChamCong, LocalTime GioVao, LocalTime GioRa, LocalTime GioVaoCuaNV, LocalTime GioRaCuaNV) {
        this.MaChiTietChamCong = MaChiTietChamCong;
        this.Date = Date;
        this.MaNVduocChamCong = MaNVduocChamCong;
        this.GioVao = GioVao;
        this.GioRa = GioRa;
        this.GioVaoCuaNV = GioVaoCuaNV;
        this.GioRaCuaNV = GioRaCuaNV;
        this.KiemTraCheckIn = false;
        this.KiemTraCheckOut = false;
    }

    public ChiTietChamCong(String MaChiTietChamCong, LocalDate Date, String MaNVduocChamCong, LocalTime GioVao, LocalTime GioRa) {
        this.MaChiTietChamCong = MaChiTietChamCong;
        this.Date = Date;
        this.MaNVduocChamCong = MaNVduocChamCong;
        this.GioVao = GioVao;
        this.GioRa = GioRa;
        this.KiemTraCheckIn = false;
        this.KiemTraCheckOut = false;
    }

    public ChiTietChamCong(String MaChiTietChamCong, LocalDate Date, String MaNVduocChamCong) {
        this.MaChiTietChamCong = MaChiTietChamCong;
        this.Date = Date;
        this.MaNVduocChamCong = MaNVduocChamCong;

        this.KiemTraCheckIn = false;
        this.KiemTraCheckOut = false;
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
        return "ChiTietChamCong{" + "MaChiTietChamCong="
                + MaChiTietChamCong + ", TrangThai=" + TrangThai
                + ", Date=" + Date + ", MaNVduocChamCong=" + MaNVduocChamCong
                + ", GioVao=" + GioVao + ", GioRa=" + GioRa + ", GioVaoCuaNV="
                + GioVaoCuaNV + ", GioRaCuaNV=" + GioRaCuaNV + '}';
    }

public boolean isGioLam(LocalTime time, String CaLam){
    LocalTime gioRa = isDangKyOT ? GioXongOT : GioRa;
    LocalTime batDau = GioVao.minusMinutes(30);
    LocalTime ketThuc = gioRa.plusMinutes(30);

    if (ketThuc.isBefore(batDau)) {      // khung giờ vắt qua nửa đêm
        return !time.isBefore(batDau) || !time.isAfter(ketThuc);
    }
    return !time.isBefore(batDau) && !time.isAfter(ketThuc);
}

    public long checkGioVao(LocalTime time) {
        if (time.isAfter(GioVao.plusMinutes(5))) {
            setTrangThai(LoaiTrangThaiChamCong.DI_TRE);
            return Duration.between(GioVao, time).toMinutes();
        }
        return 0;
    }

public long checkGioRa(LocalTime time){
    long phut = Duration.between(time, GioRa).toMinutes();   // dương = ra trước giờ ca
    if (phut > 720) {          // giờ chấm ở sau nửa đêm so với giờ ra của ca
        phut = phut - 1440;
    } else if (phut < -720) {  // giờ ra của ca nằm sau nửa đêm (ca tối)
        phut = phut + 1440;
    }
    if (phut > 5) {
        setTrangThai(LoaiTrangThaiChamCong.VE_SOM);
        return phut;
    }
    return 0;
}

    public KyLuat taoKyLuatChamCong(LocalDate date, NhanVien nv) {
        String MaNV = nv.getMaNV();
        LoaiViPham vp = LoaiViPham.VI_PHAM_CHAM_CONG;
        nv.getDsViPham().them(new ViPham(vp, date));
        LoaiKyLuat k = nv.getDsViPham().HoanChinhKyLuat(vp, date, nv);
        KyLuat kl = new KyLuat(k, vp, date, "HETHONG", MaNV);
        return kl;
    }

    public void checkChamCong(LocalTime time, LocalDate date, NhanVien nv) {
        if (isNgayNghi()) {
        if (isDangKyOT) {
            System.out.println("Không hợp lệ: không được làm OT vào chủ nhật hoặc ngày lễ");
            return;
        }
        this.TrangThai = LoaiTrangThaiChamCong.NGAY_NGHI;
        System.out.println("Hôm nay là chủ nhật/ngày lễ, không cần chấm công");
        return;
    }
        String CaLam = nv.getCaLam();
        if (!isGioLam(time, CaLam)) {
            System.out.println("Không thể chấm công, hiện đang ngoài ca làm việc của bạn");
            return;
        }
        if (nv.getDsDon().coTrongNgayNghiPhepKhong(date)) {
            System.out.println("Hôm nay trong ngày nghỉ phép của bạn, không thể chấm công");
            return;
        }
        if (this.KiemTraCheckIn == false) {
            long SoPhutTre = checkGioVao(time);
            setGioVaoCuaNV(time);
            this.KiemTraCheckIn = true;
            if (SoPhutTre != 0) {
                KyLuat kl = taoKyLuatChamCong(date, nv);

                kl.setSoPhutPhatChamCong(SoPhutTre);
                double TienTacDong = kl.tinhTacDongLuong(nv);
                kl.setSoTienTacDong(TienTacDong);
                nv.getDsktkl().them(kl);

                NhanSu.XuLyKyLuat(kl.getLoaiKyLuat(), kl.getLoaiViPham(), date, time, nv);
            }
        } else if (this.KiemTraCheckOut == false) {
            long SoPhutTanSom = checkGioRa(time);
            setGioRaCuaNV(time);
            tinhGioOT();
            this.KiemTraCheckOut = true;
            if (SoPhutTanSom == 0 && this.TrangThai != LoaiTrangThaiChamCong.DI_TRE) {
                setTrangThai(LoaiTrangThaiChamCong.DUNG_GIO);
            }
            if (SoPhutTanSom != 0) {
                KyLuat kl = taoKyLuatChamCong(date, nv);

                kl.setSoPhutPhatChamCong(SoPhutTanSom);
                double TienTacDong = kl.tinhTacDongLuong(nv);
                kl.setSoTienTacDong(TienTacDong);
                nv.getDsktkl().them(kl);

                NhanSu.XuLyKyLuat(kl.getLoaiKyLuat(), kl.getLoaiViPham(), date, time, nv);
            }
        }

    }

    public void LamOT(LocalTime gioRaCa) {
        if (isNgayNghi()) {
        System.out.println("Không thể làm OT vào chủ nhật hoặc ngày lễ");
        return;
    }
        this.isDangKyOT = true;
        this.GioXongOT = gioRaCa.plusHours(2);
    }

    public void tinhGioOT() {
        if (!isDangKyOT || GioRaCuaNV == null) {
            return;
        }
        long phut = Duration.between(GioRa, GioRaCuaNV).toMinutes();
        if (phut < -720) {          // ra sau nửa đêm (ca tối)
            phut = phut + 1440;
        } else if (phut > 720) {      // về sớm trước nửa đêm trong khi giờ ra của ca nằm sau nửa đêm
            phut = phut - 1440;
        }
        if (phut <= 0) {            // về đúng giờ ca hoặc sớm hơn: trốn OT
            this.SoGioLamOT = 0;
        } else {
            this.SoGioLamOT = Math.min(phut, 120) / 60.0;
        }
    }

    public boolean isNgayChuNhat() {
        return Date != null && Date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isNgayLe() {
        return Date != null && NgayLe.isNgayLe(Date);
    }

    public boolean isNgayNghi() {
        return isNgayChuNhat() || isNgayLe();
    }

    public void capNhatNgayNghi() {
        if (isNgayNghi() && !isDangKyOT) {
            this.TrangThai = LoaiTrangThaiChamCong.NGAY_NGHI;
        }
    }

}
