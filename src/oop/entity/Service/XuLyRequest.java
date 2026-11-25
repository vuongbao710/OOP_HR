package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

public class XuLyRequest {

    public static boolean daHuyTacVu = false;

    public static void DuyetDonXinPhep(String MaNguoiDuyetDon, String MaNguoiGuiDon, ThongBao tb, Scanner sc) {
        if (KiemTraLoi.HuyTacVu(sc)) {
            return;
        }
        NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiGuiDon);
        if (nv == null) {
            System.out.println("Không tìm thấy nhân viên");
            return;
        }
        LocalDate date = tb.getDate();
        LocalTime time = tb.getTime();
        DonXinPhep don = nv.getDsDon().timKiem(date, time);
        if (don == null) {
            System.out.println("Không tìm thấy đơn xin nghỉ phép tương ứng");
            return;
        }
        LocalDate ngayBatDau = don.getNgayBatDau();
        LocalDate ngayKetThuc = don.getNgayKetThuc();
        int khoangTgian = NgayNghiepVu.TinhNgay(ngayBatDau, ngayKetThuc);
        System.out.println(don);

        int chon = KiemTraLoi.NhapSoNguyen(sc, "bạn có duyệt đơn của nhân viên không ? \n0. Không duyệt\n1. Duyệt", 0, 1);
        LocalDate homNay = HeThong.date;
        LocalTime bayGio = LocalTime.now();

        boolean khongDuPhep = chon == 1 && don.getLoaiNghi() == LoaiNghiPhep.NGHI_PHEP_NAM
                && nv.getSoNgayNghiPhepNam() < khoangTgian;
        if (chon == 0 || khongDuPhep) {
            String lyDo;
            if (khongDuPhep) {
                lyDo = "bạn không còn đủ ngày nghỉ phép năm";
                System.out.println("Nhân viên không còn đủ ngày phép năm, đơn sẽ bị từ chối");
            } else {
                System.out.println("Nhập lý do không duyệt đơn cho nhân viên của bạn biết: ");
                lyDo = sc.nextLine().trim();
            }
            don.setTrangThai(LoaiTrangThaiCuaDon.KHONG_DUYET);
            HomThu.GuiThongBao(MaNguoiDuyetDon, MaNguoiGuiDon, homNay, bayGio, "Đơn nghỉ phép của bạn không được duyệt vì: " + lyDo);
            NhatKyHoatDong.them("Nhân viên mã " + MaNguoiDuyetDon + " không duyệt đơn nghỉ phép vào " + bayGio + " ngày " + homNay
                    + " cho nhân viên mã " + MaNguoiGuiDon);
            return;
        }

        don.setTrangThai(LoaiTrangThaiCuaDon.DUYET);
        if (don.getLoaiNghi() == LoaiNghiPhep.NGHI_PHEP_NAM) {
            nv.setSoNgayNghiPhepNam(nv.getSoNgayNghiPhepNam() - khoangTgian);
            ChucNang.LapDayNghiPhep(ngayBatDau, ngayKetThuc, nv, LoaiTrangThaiChamCong.NGHI_PHEP_NAM);
        } else {
            ChucNang.LapDayNghiPhep(ngayBatDau, ngayKetThuc, nv, LoaiTrangThaiChamCong.NGHI_PHEP);
        }
        HomThu.GuiThongBao(MaNguoiDuyetDon, MaNguoiGuiDon, homNay, bayGio, "đã duyệt đơn nghỉ phép có mã " + don.getMaDon());
        NhatKyHoatDong.them("Nhân viên mã " + MaNguoiDuyetDon + " duyệt đơn nghỉ phép vào " + bayGio + " ngày " + homNay
                + " cho nhân viên mã " + MaNguoiGuiDon);
    }

    public static boolean XuLy(String MaNguoiNhan, String MaNguoiGui, ThongBao tb, Scanner sc, LocalDate date, LocalTime time) {
        daHuyTacVu = false;
        switch (tb.getRequest()) {
            case XIN_NGHI_PHEP:
                DuyetDonXinPhep(MaNguoiNhan, MaNguoiGui, tb, sc);
                break;
            case DANH_GIA_KPI:
                DanhGiaKhenThuong.DanhGiaKPINhanVienPhuTrach(sc, date, time, MaNguoiNhan);
                break;
            case XEM_XET_TANG_LUONG:
                XemXetTangLuong(sc, tb.getMaLienQuan(), MaNguoiNhan, date, time);
                break;
            case DANG_KY_LAM_OT:
                DuyetLamOT(sc, MaNguoiNhan, MaNguoiGui, date, time, tb.getNgayDangKy());
                break;
            case DANG_TAI_DOT_TUYEN_DUNG:
                DuyetDotTuyenDung(sc, MaNguoiNhan, MaNguoiGui, date, time, tb.getMaLienQuan());
                break;
            case BO_NHIEM:
                if (tb.getMaLienQuan() == null) {
                    System.out.println("Request bổ nhiệm thiếu thông tin người bị cách chức");
                    daHuyTacVu = true;
                    break;
                }
                NhanSu.ChonRaNguoiThayThe(date, time, tb.getMaLienQuan(), sc, MaNguoiNhan);
                break;
            case CHAM_DIEM_DAO_TAO:
                do {
                    TuyenDung.ChamDiemUngVien(sc, MaNguoiNhan, date, time);
                } while (!daHuyTacVu && KiemTraLoi.NhapSoNguyen(sc, "Chấm điểm ứng viên khác không?\n0.Không\n1.Có", 0, 1) == 1);
                break;
            case KHIEN_TRACH_CHAM_CONG:
                break;
            case GIA_HAN_HOP_DONG:
                DuyetGiaHanHopDong(sc, MaNguoiNhan, tb.getMaLienQuan(), date, time);
                break;
            case XIN_NGHI_VIEC:
                DuyetXinNghiViec(sc, MaNguoiNhan, MaNguoiGui);
                break;
            case DANH_GIA_KPI_QUAN_LY:
                DanhGiaKhenThuong.DanhGiaKPIQuanLy(sc, date, time, MaNguoiNhan, tb.getMaLienQuan());
                break;
            default:
                System.out.println("Loại request " + tb.getRequest().getMoTa() + " chưa có chức năng xử lý");
                return false;
        }
        return !daHuyTacVu;
    }

    public static void XemXetTangLuong(Scanner sc, String MaNV, String MaQuanLyPhuTrach, LocalDate date, LocalTime time) {

        if (KiemTraLoi.HuyTacVu(sc)) {
            return;
        }
        NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
        if (nv == null) {
            System.out.println("Không tìm thấy nhân viên cần xét lương");
            return;
        }
        System.out.println(nv);
        int chon = KiemTraLoi.NhapSoNguyen(sc, "Đồng ý tăng lương cho nhân viên mã " + MaNV + " không?\n0.Không\n1.Có", 0, 1);
        if (chon == 1) {
            boolean full = KiemTraLoi.KiemTraIsFullTime(nv);
            double hienTai = nv.getLuongCoSo();
            double moi;
            while (true) {
                moi = KiemTraLoi.NhapSo(sc, "Hiện tại " + (full ? "lương cơ bản" : "lương một giờ") + " là " + hienTai
                        + ", bạn muốn nâng lên thành bao nhiêu?");
                if (moi > hienTai) {
                    break;
                }
                System.out.println("Mức mới phải lớn hơn mức hiện tại");
            }
            HopDong hd = nv.getHopDong();
            hd.setMucLuongCoSo(moi);
            nv.setHopDong(hd);
            nv.setNgayXetNangLuong(date.plusYears(1));
            HomThu.GuiThongBao(MaQuanLyPhuTrach, MaNV, date, time, "Quản lý đã đồng ý nâng lương cho bạn").setRequest(LoaiRequest.THONG_BAO);
            NhatKyHoatDong.them("Quản lý mã " + MaQuanLyPhuTrach + " nâng lương thành " + moi
                    + " vào " + time + " ngày " + date + " cho nhân viên mã " + MaNV);
        } else {
            System.out.println("Nhập lý do tại sao không tăng lương: ");
            String Message = sc.nextLine().trim();
            nv.setNgayXetNangLuong(date.plusMonths(6));
            HomThu.GuiThongBao(MaQuanLyPhuTrach, MaNV, date, time, "từ chối nâng lương vì " + Message).setRequest(LoaiRequest.THONG_BAO);
            NhatKyHoatDong.them("Quản lý mã " + MaQuanLyPhuTrach + " từ chối nâng lương vào " + time + " ngày " + date
                    + " cho nhân viên mã " + MaNV);
        }
    }

    public static void DuyetLamOT(Scanner sc, String MaQuanLy, String MaNguoiGui, LocalDate date, LocalTime time, LocalDate NgayDangKy) {
        if (KiemTraLoi.HuyTacVu(sc)) {
            return;
        }
        while (true) {
            System.out.println("Có duyệt không?\n0.Không\n1.Có");
            int input = sc.nextInt();
            sc.nextLine();
            if (input == 0) {
                System.out.println("Nhập lý do: ");
                String lydo = sc.nextLine().trim();
                HomThu.GuiThongBao(MaQuanLy, MaNguoiGui, date, time, "Quản lý của bạn đã từ chối với lý do: " + lydo);
                NhatKyHoatDong.them("Quản lý mã " + MaQuanLy + " từ chối duyệt làm OT vào " + time + " ngày " + date
                        + " cho nhân viên mã " + MaNguoiGui);
                break;
            }
            if (input == 1) {
                NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiGui);
                ChiTietChamCong cc = nv.getBcc().timKiem(NgayDangKy);
                if (cc == null) {
                    System.out.println("Không tìm thấy dòng chấm công ngày " + NgayDangKy);
                    break;
                }
                if (nv.getBcc().getSoGioOT() + 2 > ChucNang.GIO_OT_TOI_DA) {
                    System.out.println("Nhân viên đã đủ " + ChucNang.GIO_OT_TOI_DA + " giờ OT, không thể duyệt thêm");
                    break;
                }
                LocalTime gioRaCa;
                if (nv instanceof NhanVienFullTime) {
                    gioRaCa = ((NhanVienFullTime) nv).getGioRaCa();
                } else {
                    gioRaCa = ((NhanVienPartTime) nv).getGioRaCa();
                }
                cc.LamOT(gioRaCa);
                HomThu.GuiThongBao(MaQuanLy, MaNguoiGui, date, time, "Quản lý của bạn đã duyệt đăng ký làm OT ngày " + NgayDangKy.toString());
                NhatKyHoatDong.them("Quản lý mã " + MaQuanLy + " duyệt ca làm OT vào " + time + " ngày " + date
                        + " cho nhân viên mã " + MaNguoiGui);
                break;
            }
            System.out.println("vui lòng nhập đúng định dạng");
        }
    }

    public static void DuyetDotTuyenDung(Scanner sc, String MaADMIN, String MaNguoiTuyen, LocalDate date, LocalTime time, String MaDotTuyenDung) {

        if (KiemTraLoi.HuyTacVu(sc)) {
            return;
        }
        DotTuyenDung dot = DanhSachDotTuyenDung.timKiem(MaDotTuyenDung);
        System.out.println(dot);
        while (true) {
            System.out.println("Có duyệt không?\n0.Không\n1.Có");
            int input = sc.nextInt();
            sc.nextLine();
            if (input == 0) {
                System.out.println("Nhập lý do: ");
                String lydo = sc.nextLine().trim();
                DanhSachDotTuyenDung.xoa(MaDotTuyenDung);
                HomThu.GuiThongBao(MaADMIN, MaNguoiTuyen, date, time, "Giám đốc của bạn đã từ chối với lý do: " + lydo);
                NhatKyHoatDong.them("Giám đốc mã " + MaADMIN + " từ chối duyệt làm OT vào " + time + " ngày " + date
                        + "cho nhân viên mã " + MaNguoiTuyen);
                break;
            }
            if (input == 1) {
                HomThu.GuiThongBao(MaADMIN, MaNguoiTuyen, date, time, "Quản lý của bạn đã duyệt đợt tuyển dụng mã " + MaDotTuyenDung + " của bạn ");
                NhatKyHoatDong.them("Quản lý mã " + MaADMIN + " duyệt đợt tuyển dụng " + time + " ngày " + date
                        + "cho nhân viên mã " + MaNguoiTuyen);
                dot.setTrangThai(TRANG_THAI_DOT.DANG_TUYEN_DUNG);
                /////
                break;
            }
            System.out.println("vui lòng nhập đúng định dạng");
        }
    }

    public static void DuyetGiaHanHopDong(Scanner sc, String MaNguoiDuyet, String MaNV, LocalDate date, LocalTime time) {
        if (KiemTraLoi.HuyTacVu(sc)) {
            return;
        }
        NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
        if (nv == null) {
            System.out.println("Không tìm thấy nhân viên cần gia hạn hợp đồng");
            return;
        }
        HopDong hd = nv.getHopDong();
        if (hd == null || hd.getTrangThaiHopDong() != TRANG_THAI_HOP_DONG.HET_HAN) {
            System.out.println("Hợp đồng này không còn chờ gia hạn (đã được xử lý rồi)");
            return;
        }
        System.out.println("Nhân viên " + nv.getHoTen() + " (mã " + MaNV + ")");
        System.out.println(hd);

        int chon = KiemTraLoi.NhapSoNguyen(sc, "Quyết định?\n0.Từ chối gia hạn (chấm dứt hợp đồng)\n1.Gia hạn", 0, 1);
        if (chon == 0) {
            System.out.println("Nhập lý do: ");
            String lyDo = sc.nextLine().trim();
            NhanSu.ChamDutNhanVien(nv, hd.getNgayHetHan(), date, time, MaNguoiDuyet, "không gia hạn hợp đồng, lý do: " + lyDo);
            return;
        }

        LOAI_HOP_DONG loai = hd.getLoaiHopDong();
        if (loai != LOAI_HOP_DONG.PART_TIME) {
            int c = KiemTraLoi.NhapSoNguyen(sc, "Loại hợp đồng mới:\n1." + LOAI_HOP_DONG.CHINH_THUC_NGAN_HAN.getMoTa()
                    + "\n2." + LOAI_HOP_DONG.CHINH_THUC_DAI_HAN.getMoTa(), 1, 2);
            loai = (c == 1) ? LOAI_HOP_DONG.CHINH_THUC_NGAN_HAN : LOAI_HOP_DONG.CHINH_THUC_DAI_HAN;
        }
        LocalDate ngayHetHanMoi;
        while (true) {
            ngayHetHanMoi = KiemTraLoi.NhapNgay(sc, "Nhập ngày hết hạn mới của hợp đồng");
            if (ngayHetHanMoi.isAfter(date)) {
                break;
            }
            System.out.println("Ngày hết hạn mới phải sau hôm nay (" + date + ")");
        }

        LocalDate ngayHetHanCu = hd.getNgayHetHan();
        hd.setLoaiHopDong(loai);
        hd.setNgayHetHan(ngayHetHanMoi);
        hd.setTrangThaiHopDong(TRANG_THAI_HOP_DONG.DANG_CO_HIEU_LUC);
        String cu = hd.getGhiChu() == null || hd.getGhiChu().isEmpty() ? "" : hd.getGhiChu() + "; ";
        hd.setGhiChu(cu + "Gia hạn ngày " + date + ", hạn cũ " + ngayHetHanCu);

        HomThu.GuiThongBao(MaNguoiDuyet, MaNV, date, time, "Hợp đồng " + hd.getMaHopDong() + " của bạn đã được gia hạn đến ngày " + ngayHetHanMoi);
        NhatKyHoatDong.them("Người dùng mã " + MaNguoiDuyet + " gia hạn hợp đồng " + hd.getMaHopDong()
                + " của nhân viên " + MaNV + " đến " + ngayHetHanMoi + " vào " + time + " ngày " + date);
        System.out.println("Đã gia hạn hợp đồng đến " + ngayHetHanMoi);
    }

    public static void DuyetXinNghiViec(Scanner sc, String MaNguoiDuyet, String MaNV) {
        if (KiemTraLoi.HuyTacVu(sc)) {
            daHuyTacVu = true;   // thoát thì đơn vẫn còn chờ duyệt
            return;
        }
        LocalDate date = HeThong.date;
        LocalTime time = HeThong.time;
        NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
        if (nv == null || nv.isDaNghiViec()) {
            System.out.println("Nhân viên không còn trong hệ thống hoặc đã nghỉ việc");
            return;
        }
        System.out.println(nv);
        int chon = KiemTraLoi.NhapSoNguyen(sc, "Quyết định?\n0.Không đồng ý\n1.Đồng ý cho nghỉ việc", 0, 1);
        if (chon == 0) {
            System.out.println("Nhập lý do không đồng ý: ");
            String lyDo = sc.nextLine().trim();
            HomThu.GuiThongBao(MaNguoiDuyet, MaNV, date, time, "Đơn xin nghỉ việc của bạn không được duyệt vì: " + lyDo);
            NhatKyHoatDong.them("Người dùng mã " + MaNguoiDuyet + " không duyệt đơn xin nghỉ việc của nhân viên " + MaNV
                    + " vào " + time + " ngày " + date);
            return;
        }
        NhanSu.ChamDutNhanVien(nv, date, date, time, MaNguoiDuyet, "xin nghỉ việc");
    }
}
