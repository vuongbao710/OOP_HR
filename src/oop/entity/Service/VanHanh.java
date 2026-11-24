package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

public class VanHanh {

    private static Random random = new Random();

    public static void KiemTraToiNgay(NhanVien nv, LocalDate date, LocalTime time, String MaADMIN) {
        if (nv.isDaNghiViec()) {
            return;
        }
        String maQL = nv.getMaQuanLyPhuTrach();

        for (ThongBao tb : nv.getDsThongBao().timKiemChuaGiaiQuyet(date)) {
            HomThu.GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "Request mã " + tb.getMaThongBao()
                    + " (" + tb.getRequest().getMoTa() + ") sẽ hết hạn vào ngày mai, vui lòng giải quyết");
        }
        nv.getDsThongBao().chuyenQuaTrangThaiHetHan(date);

        HopDong hd = nv.getHopDong();
        if (hd != null && hd.getNgayHetHan() != null && date.isAfter(hd.getNgayHetHan())
                && hd.getTrangThaiHopDong() == TRANG_THAI_HOP_DONG.DANG_CO_HIEU_LUC) {
            hd.setTrangThaiHopDong(TRANG_THAI_HOP_DONG.HET_HAN);
            HomThu.GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "Hợp đồng " + hd.getMaHopDong() + " của bạn đã hết hạn, đang chờ quản lý quyết định gia hạn");
            if (maQL != null) {
                HomThu.GuiYeuCau(MaADMIN, maQL, date, time,
                        "Hợp đồng " + hd.getMaHopDong() + " của nhân viên " + nv.getMaNV() + " đã hết hạn, cần gia hạn hoặc từ chối gia hạn",
                        LoaiRequest.GIA_HAN_HOP_DONG, nv.getMaNV());
            }
        }

        if (nv.ToiNgayTongKetDinhKy(date)) {
            nv.ResetQuyTrinhChamCong(date);
            HomThu.GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "Đã reset bảng chấm công, chuyển qua tính theo tháng mới");
        }

        if (nv.ToiNgayXetNangLuong(date) && maQL != null && DangTrongHopDong(nv, date)) {
            HomThu.GuiYeuCau(MaADMIN, maQL, date, time, "Tới ngày xét nâng lương của nhân viên có mã " + nv.getMaNV(),
                    LoaiRequest.XEM_XET_TANG_LUONG, nv.getMaNV());
        }

        if (date.getMonthValue() == 1 && date.getDayOfMonth() == 1) {
            nv.setSoNgayNghiPhepNam(12);
            if (nv.getHinhThucDiLam() != null && nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")
                    && DangTrongHopDong(nv, date)) {
                NhanVienFullTime nvFull = (NhanVienFullTime) nv;
                double TienThuongTet = nvFull.TinhToanTienThuongTet(LocalDate.of(date.getYear() - 1, 1, 1));
                KhenThuong khen = new KhenThuong(LoaiKhenThuong.THUONG_TET, date, TienThuongTet, MaADMIN, nv.getMaNV());
                nvFull.getDsktkl().them(khen);
                HomThu.GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "thưởng tết " + TienThuongTet + " vnd ").setRequest(LoaiRequest.THONG_BAO);
            }
        }
    }

    public static void KiemTraToiNgayHeThong(LocalDate date, LocalTime time, String MaADMIN) {
        Boss.getDsThongBao().chuyenQuaTrangThaiHetHan(date);
        if ("ngày kết thúc quý".equalsIgnoreCase(NgayNghiepVu.kiemTraToiNgay(date))) {
            for (NhanVien NV : DanhSachNhanVien.getDsnv()) {
                if (NV.getChucVu() == LoaiChucVu.QUAN_LY && !NV.isDaNghiViec()) {
                    HomThu.GuiYeuCau(MaADMIN, NV.getMaNV(), date, time,
                            "Đã đến thời hạn chấm KPI của nhân viên bạn phụ trách", LoaiRequest.DANH_GIA_KPI, null);
                }
            }
        }
        for (DotTuyenDung dot : new ArrayList<>(DanhSachDotTuyenDung.getDsDot())) {
            if (dot.getTrangThai() == TRANG_THAI_DOT.DANG_TUYEN_DUNG && date.isAfter(dot.getNgayHetHan())) {
                dot.setTrangThai(TRANG_THAI_DOT.KET_THUC);
                HomThu.GuiThongBao(MaADMIN, dot.getMaNguoiTuyen(), date, time,
                        "Đợt tuyển dụng " + dot.getMaDotTuyenDung() + " đã quá hạn và được đóng tự động");
            }
        }
        for (KhoaDaoTao khoa : DanhSachKhoaDaoTao.getDskdt()) {
            if (khoa.getNgayKetThuc() != null && date.isEqual(khoa.getNgayKetThuc().plusDays(1))) {
                HomThu.GuiYeuCau(MaADMIN, khoa.getMaNVDaoTao(), date, time,
                        "Khóa đào tạo " + khoa.getMaKhoaHoc() + " đã kết thúc, vui lòng chấm điểm ứng viên",
                        LoaiRequest.CHAM_DIEM_DAO_TAO, khoa.getMaKhoaHoc());
            }
        }
    }

    public static void KiemTraVanHanhLuongNhanSu(LocalDate date, LocalTime time, String MaADMIN, LocalDate NgayKetThuc) {
        for (LocalDate d = date; !d.isAfter(NgayKetThuc); d = d.plusDays(1)) {
            KiemTraToiNgayHeThong(d, time, MaADMIN);
            for (NhanVien nv : new ArrayList<>(DanhSachNhanVien.getDsnv())) {
                KiemTraToiNgay(nv, d, time, MaADMIN);
                if (!nv.isDaNghiViec() && DangTrongHopDong(nv, d)) {
                    if (!NgauNhienTinhHuong(nv, d, time)) {
                        ChucNang.LapDayChamCong(nv, d);
                    }
                }
            }
        }
    }

    private static boolean DangTrongHopDong(NhanVien nv, LocalDate date) {
        HopDong hd = nv.getHopDong();
        if (hd == null) {
            return true;
        }
        if (hd.getNgayBatDauHopDong() != null && date.isBefore(hd.getNgayBatDauHopDong())) {
            return false;
        }
        if (hd.getNgayHetHan() != null && date.isAfter(hd.getNgayHetHan())) {
            return false;
        }
        return true;
    }

// Tạo vi phạm cho nhân viên (giống cách ChiTietChamCong.taoKyLuatChamCong đang làm)
    private static void TaoViPham(NhanVien nv, LoaiViPham loai, LocalDate date, LocalTime time) {
        nv.getDsViPham().them(new ViPham(loai, date));
        LoaiKyLuat k = nv.getDsViPham().HoanChinhKyLuat(loai, date, nv);
        KyLuat kl = new KyLuat(k, loai, date, "HETHONG", nv.getMaNV());
        kl.setSoTienTacDong(kl.tinhTacDongLuong(nv));
        nv.getDsktkl().them(kl);
        NhanSu.XuLyKyLuat(k, loai, date, time, nv);
    }

// Random tình huống cho 1 nhân viên trong 1 ngày
// Trả về true nếu đã tự chấm công xong, false nếu để hệ thống chấm công bình thường
    public static boolean NgauNhienTinhHuong(NhanVien nv, LocalDate date, LocalTime time) {

        // Đang trong ngày nghỉ phép thì không random nữa
        if (nv.getDsDon().coTrongNgayNghiPhepKhong(date)) {
            return false;
        }
        ChiTietChamCong cc = nv.getBcc().timKiem(date);
        if (cc == null) {
            return false;
        }

        // Lấy giờ vào, giờ ra của ca
        LocalTime gioVao;
        LocalTime gioRa;
        if (nv instanceof NhanVienFullTime) {
            gioVao = ((NhanVienFullTime) nv).getGioVaoCa();
            gioRa = ((NhanVienFullTime) nv).getGioRaCa();
        } else {
            gioVao = ((NhanVienPartTime) nv).getGioVaoCa();
            gioRa = ((NhanVienPartTime) nv).getGioRaCa();
        }

        int r = random.nextInt(100);

        if (r < 4) {                       // 4%: nghỉ phép
            ChucNang.LapDayNghiPhep(date, date, nv, LoaiTrangThaiChamCong.NGHI_PHEP);
            return true;
        }
        if (r < 6) {                       // 2%: nghỉ không phép
            TaoViPham(nv, LoaiViPham.NGHI_KHONG_PHEP, date, time);
            return true;
        }
        if (r < 13) {                      // 7%: đi trễ
            ChucNang.ChamCong(gioVao.plusMinutes(15), date, nv);
            ChucNang.ChamCong(gioRa, date, nv);
            return true;
        }
        if (r < 17) {                      // 4%: về sớm
            ChucNang.ChamCong(gioVao, date, nv);
            ChucNang.ChamCong(gioRa.minusMinutes(20), date, nv);
            return true;
        }
        if (r < 27) {                      // 10%: làm OT 1 đến 2 giờ
            int soGio = random.nextInt(2) + 1;
            cc.LamOT(gioRa);
            ChucNang.ChamCong(gioVao, date, nv);
            ChucNang.ChamCong(gioRa.plusHours(soGio), date, nv);
            return true;
        }
        if (r < 29) {                      // 2%: vi phạm an toàn vệ sinh (vẫn đi làm bình thường)
            TaoViPham(nv, LoaiViPham.VI_PHAM_AN_TOAN_VE_SINH, date, time);
            return false;
        }
        return false;                    // còn lại: bình thường
    }
}
