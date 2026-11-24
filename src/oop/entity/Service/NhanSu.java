package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

public class NhanSu {

    public static NhanVienFullTime DownCastingNhanVienFullTime(NhanVien nv) {
        NhanVienFullTime nvFull = (NhanVienFullTime) nv;
        return nvFull;
    }

    public static NhanVienPartTime DownCastingNhanVienPartTime(NhanVien nv) {
        NhanVienPartTime nvFull = (NhanVienPartTime) nv;
        return nvFull;
    }

    private static String ThongBaoKyLuat(LoaiKyLuat kl, LoaiViPham vp, LocalDate date, NhanVien nv) {
        String Message = null;
        int SoLanViPham = nv.getDsViPham().SoLanViPham(vp, date);
        switch (kl) {
            case LoaiKyLuat.KHIEN_TRACH:
                Message = "Bạn bị khiển trách vì " + vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            case LoaiKyLuat.KEO_DAI_THOI_HAN_NANG_LUONG:
                Message = "Bạn bị kéo dài thời hạn nâng lương vì " + vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            case LoaiKyLuat.CACH_CHUC:
                Message = "Bạn bị cách chức vì " + vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            case LoaiKyLuat.SA_THAI:
                Message = "Bạn bị sa thải vì " + vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            default:
                System.out.println(" Không có trong danh sách hình thức kỷ luật");
                break;
        }
        return Message;
    }

    public static void XuLyKyLuat(LoaiKyLuat kl, LoaiViPham vp, LocalDate date, LocalTime time, NhanVien nv) {
        if (nv.isDaNghiViec() || kl == null) {
            return;
        }
        String Message = ThongBaoKyLuat(kl, vp, date, nv);
        if (Message != null) {
            HomThu.GuiThongBao("HETHONG", nv.getMaNV(), date, time, Message);
        }
        switch (kl) {
            case KHIEN_TRACH:
                break;
            case KEO_DAI_THOI_HAN_NANG_LUONG:
                if (nv.getNgayXetNangLuong() != null) {
                    nv.setNgayXetNangLuong(nv.getNgayXetNangLuong().plusMonths(6));
                }
                break;
            case CACH_CHUC:
                YeuCauBoNhiemThayThe(nv, date, time);
                break;
            case SA_THAI:
                ChamDutNhanVien(nv, date, date, time, "HETHONG", "bị sa thải");
                break;
            default:
                System.out.println(" Không có trong danh sách hình thức kỷ luật");
                break;
        }
        NhatKyHoatDong.them("Hệ thống áp dụng kỷ luật " + kl.getMoTa() + " cho nhân viên mã " + nv.getMaNV()
                + " vào " + time + " ngày " + date);
    }

    private static void DoiViTri(NhanVien nv, ViTri vt) {
        HopDong hd = nv.getHopDong();
        hd.setLoaiViTri(vt);
        hd.setMucLuongCoSo(ViTri.XacDinhChucVu(vt.getMaViTri()).getLuongCoBan());
        nv.setHopDong(hd);
    }

    private static ViTri NhapViTriMoi(Scanner sc, NhanVien nvXuong, String phongBanBatBuoc) {
        while (true) {
            System.out.println("Nhập mã vị trí mới cho " + nvXuong.getHoTen() + " sau khi bị cách chức");
            ViTri vt = ViTri.timKiem(sc.nextLine().trim());
            if (vt == null) {
                System.out.println("Không tìm thấy vị trí");
                continue;
            }
            if (!vt.isFullTime() || ViTri.XacDinhChucVu(vt.getMaViTri()) != LoaiChucVu.NHAN_VIEN) {
                System.out.println("Phải là vị trí nhân viên full-time ");
                continue;
            }
            if (phongBanBatBuoc != null && !vt.getPhongBan().equalsIgnoreCase(phongBanBatBuoc)) {
                System.out.println("Vị trí phải thuộc phòng ban " + phongBanBatBuoc);
                continue;
            }
            return vt;
        }
    }

    private static ViTri LayViTriMoi(Scanner sc, NhanVien nvXuong, String phongBanBatBuoc) {
        if (!nvXuong.isDaNghiViec()) {
            return NhapViTriMoi(sc, nvXuong, phongBanBatBuoc);
        }
        for (ViTri vt : ViTri.values()) {
            if (vt.isFullTime() && ViTri.XacDinhChucVu(vt.getMaViTri()) == LoaiChucVu.NHAN_VIEN
                    && vt.getPhongBan() != null && vt.getPhongBan().equalsIgnoreCase(nvXuong.getMaPhongBan())) {
                return vt;
            }
        }
        return ViTri.NHAN_VIEN_KINH_DOANH;
    }

    public static void ThayChuc(NhanVien nvXuong, NhanVien nvLen, ViTri viTriMoiCuaNvXuong) {
        ViTri viTriNvLenNhan = nvXuong.getHopDong().getLoaiViTri();

        nvLen.setMaQuanLyPhuTrach(nvXuong.getMaQuanLyPhuTrach());
        nvXuong.setMaQuanLyPhuTrach(nvLen.getMaNV());
        for (NhanVien n : DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrach(nvXuong.getMaNV())) {
            n.setMaQuanLyPhuTrach(nvLen.getMaNV());
        }

        DoiViTri(nvLen, viTriNvLenNhan);
        DoiViTri(nvXuong, viTriMoiCuaNvXuong);
    }

    public static void BoNhiem(LocalDate date, LocalTime time, String MaNVCachChuc, String MaNVBoNhiem, Scanner sc, String MaNguoiThucHien) {
        NhanVien nvXuong = DanhSachNhanVien.timKiem(MaNVCachChuc);
        NhanVien nvLen = DanhSachNhanVien.timKiem(MaNVBoNhiem);
        if (nvXuong == null || nvLen == null) {
            System.out.println("Không tìm thấy nhân viên");
            return;
        }
        if (nvXuong == nvLen) {
            System.out.println("Không thể bổ nhiệm chính người bị cách chức");
            return;
        }
        if (nvLen instanceof NhanVienPartTime) {
            System.out.println("Không thể bổ nhiệm người làm part-time");
            return;
        }

        if (nvLen.getChucVu() == LoaiChucVu.NHAN_VIEN && nvXuong.getChucVu() == LoaiChucVu.QUAN_LY) {
            if (!nvLen.getMaPhongBan().equalsIgnoreCase(nvXuong.getMaPhongBan())) {
                System.out.println("Người được bổ nhiệm phải thuộc cùng phòng ban với quản lý bị cách chức");
                return;
            }
            ViTri viTriMoi = LayViTriMoi(sc, nvXuong, nvXuong.getMaPhongBan());
            ThayChuc(nvXuong, nvLen, viTriMoi);
            NhatKyHoatDong.them("Người dùng mã " + MaNguoiThucHien + " bổ nhiệm nhân viên " + nvLen.getMaNV()
                    + " thay quản lý " + nvXuong.getMaNV() + " vào " + time + " ngày " + date);
        } else if (nvLen.getChucVu() == LoaiChucVu.QUAN_LY && nvXuong.getChucVu() == LoaiChucVu.GIAM_DOC) {
            ViTri viTriMoi = LayViTriMoi(sc, nvXuong, null);
            ThayChuc(nvXuong, nvLen, viTriMoi);
            NhatKyHoatDong.them("Người dùng mã " + MaNguoiThucHien + " bổ nhiệm quản lý " + nvLen.getMaNV()
                    + " lên giám đốc thay " + nvXuong.getMaNV() + " vào " + time + " ngày " + date);
            System.out.println("Ghế quản lý phòng ban " + viTriMoi.getPhongBan() + " cũ của "
                    + nvLen.getHoTen() + " đang trống, cần bổ nhiệm người thay thế");
        } else {
            System.out.println("Chỉ bổ nhiệm được: nhân viên lên quản lý (thay quản lý) hoặc quản lý lên giám đốc (thay giám đốc)");
        }
    }

    public static void ChonRaNguoiThayThe(LocalDate date, LocalTime time, String MaNVCachChuc, Scanner sc, String MaNguoiThucHien) {
        DanhSachNhanVien.SortTheoThamNien();
        System.out.println("Chọn người thay thế cho nhân viên mã " + MaNVCachChuc);
        int input = KiemTraLoi.NhapSoNguyen(sc, "\n0.Thoát\n1.Bổ nhiệm nội bộ\n2.Tuyển ngoài", 0, 2);
        if (input == 0) {
            XuLyRequest.daHuyTacVu = true;
            return;
        }
        if (input == 1) {
            System.out.println("Nhập mã nhân viên bạn muốn bổ nhiệm");
            String nhap = KiemTraLoi.NhapMaNV(sc);
            while (nhap == null) {
                nhap = KiemTraLoi.NhapMaNV(sc);
            }
            BoNhiem(date, time, MaNVCachChuc, nhap, sc, MaNguoiThucHien);
            return;
        }
        if (!MaNguoiThucHien.equalsIgnoreCase(Boss.MA)) {
            NhanVien nguoiThucHien = DanhSachNhanVien.timKiem(MaNguoiThucHien);
            if (nguoiThucHien == null) {
                XuLyRequest.daHuyTacVu = true;
                return;
            }
            TuyenDung.DangTaiTuyenDung(sc, MaNguoiThucHien, nguoiThucHien.getMaQuanLyPhuTrach(), date, time);
            return;
        }
        DotTuyenDung dot = TuyenDung.TaoDotTuyenDung(sc, MaNguoiThucHien);
        if (dot == null) {
            XuLyRequest.daHuyTacVu = true;
            return;
        }
        dot.setTrangThai(TRANG_THAI_DOT.DANG_TUYEN_DUNG);
        DanhSachDotTuyenDung.them(dot);
        NhanVien nvXuong = DanhSachNhanVien.timKiem(MaNVCachChuc);
        if (nvXuong != null) {
            for (NhanVien n : DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrach(MaNVCachChuc)) {
                n.setMaQuanLyPhuTrach(nvXuong.getMaQuanLyPhuTrach());
            }
        }
    }

    private static void YeuCauBoNhiemThayThe(NhanVien nv, LocalDate date, LocalTime time) {
        if (nv.getChucVu() != LoaiChucVu.QUAN_LY && nv.getChucVu() != LoaiChucVu.GIAM_DOC) {
            return;
        }
        if (nv.getMaQuanLyPhuTrach() == null) {
            return;
        }
        HomThu.GuiYeuCau("HETHONG", nv.getMaQuanLyPhuTrach(), date, time,
                "Nhân viên mã " + nv.getMaNV() + " mất chức vụ " + nv.getChucVu() + ", cần bổ nhiệm người thay thế",
                LoaiRequest.BO_NHIEM, nv.getMaNV());
    }

    public static void ChamDutNhanVien(NhanVien nv, LocalDate ngayKetThuc, LocalDate date, LocalTime time,
            String MaNguoiThucHien, String lyDo) {
        if (nv.isDaNghiViec()) {
            return;
        }

        nv.ThemBangChamCong();
        BangChamCong bang = nv.getBcc();
        PhieuLuong pl = nv.TaoPhieuLuong(bang.getThang(), bang.getNam());
        pl.setMaPhieu(nv.getDspl().taoMa());
        nv.ThemPhieuLuong(pl);

        HopDong hd = nv.getHopDong();
        if (hd != null) {
            hd.setTrangThaiHopDong(TRANG_THAI_HOP_DONG.DA_HUY);
            hd.setNgayHetHan(ngayKetThuc);
            String cu = hd.getGhiChu() == null || hd.getGhiChu().isEmpty() ? "" : hd.getGhiChu() + "; ";
            hd.setGhiChu(cu + "Chấm dứt ngày " + ngayKetThuc + ": " + lyDo);
        }

        if (nv.getTaiKhoan() != null) {
            nv.getTaiKhoan().setTrangThai(TaiKhoan.TrangThaiHoatDong.BI_KHOA);
        }
        for (TaiKhoan tk : DanhSachTaiKhoan.getDstk()) {
            if (tk.getMaNV() != null && tk.getMaNV().equalsIgnoreCase(nv.getMaNV())) {
                tk.setTrangThai(TaiKhoan.TrangThaiHoatDong.BI_KHOA);
            }
        }

        if (nv.getMaQuanLyPhuTrach() != null) {
            HomThu.GuiThongBao("HETHONG", nv.getMaQuanLyPhuTrach(), date, time,
                    "Nhân viên " + nv.getMaNV() + " (" + nv.getHoTen() + ") đã chấm dứt hợp đồng: " + lyDo
                    + ". Phiếu lương cuối đã được xuất.");
        }
        YeuCauBoNhiemThayThe(nv, date, time);

        NhatKyHoatDong.them("Người dùng mã " + MaNguoiThucHien + " chấm dứt hợp đồng nhân viên " + nv.getMaNV()
                + " (" + lyDo + "), phiếu lương cuối " + pl.getMaPhieu() + " vào " + time + " ngày " + date);
        System.out.println("Đã chấm dứt hợp đồng, xuất phiếu lương cuối và khóa tài khoản của " + nv.getHoTen());
    }

    public static void KyLuatNhanVien(Scanner sc, LocalDate date, LocalTime time, String maNguoiRa) {
        System.out.println("Nhập mã nhân viên cần kỷ luật:");
        NhanVien nv = DanhSachNhanVien.timKiem(sc.nextLine().trim());

        if (nv == null || nv.isDaNghiViec()) {
            System.out.println("Không tìm thấy nhân viên");
            return;
        }
        if (nv.getMaNV().equalsIgnoreCase(maNguoiRa)) {
            System.out.println("Không thể tự kỷ luật chính mình");
            return;
        }
        if (!maNguoiRa.equals(Boss.MA) && !maNguoiRa.equalsIgnoreCase(nv.getMaQuanLyPhuTrach())) {
            System.out.println("Bạn chỉ được kỷ luật nhân viên cấp dưới của mình");
            return;
        }

        LoaiViPham[] dsVP = {LoaiViPham.VI_PHAM_TRAT_TU_NOI_LAM_VIEC, LoaiViPham.VI_PHAM_AN_TOAN_VE_SINH,
            LoaiViPham.VI_PHAM_TAI_SAN_CONG_TY, LoaiViPham.QUAY_ROI};
        for (int i = 0; i < dsVP.length; i++) {
            System.out.println((i + 1) + ". " + dsVP[i].getMoTa());
        }
        int chonVP = KiemTraLoi.NhapSoNguyen(sc, "Chọn loại vi phạm (0 để hủy):", 0, dsVP.length);
        if (chonVP == 0) {
            return;
        }
        LoaiViPham vp = dsVP[chonVP - 1];

        nv.getDsViPham().them(new ViPham(vp, date));
        LoaiKyLuat kl = nv.getDsViPham().HoanChinhKyLuat(vp, date, nv);
        if (kl == null) {
            System.out.println("Không xác định được hình thức kỷ luật");
            return;
        }
        nv.getDsktkl().them(new KyLuat(kl, vp, date, maNguoiRa, nv.getMaNV()));
        XuLyKyLuat(kl, vp, date, time, nv);
        System.out.println("Đã kỷ luật " + nv.getHoTen() + ": " + kl.getMoTa());
    }

    public static NhanVien ChonNhanVienDeQuanLy(Scanner sc, String hanhDong) {
        System.out.println("Nhập mã nhân viên cần " + hanhDong + " (nhập 'thoát' để hủy):");
        String ma = sc.nextLine().trim();
        if (ma.equalsIgnoreCase("thoát")) {
            return null;
        }
        NhanVien nv = DanhSachNhanVien.timKiem(ma);
        if (nv == null || nv.isDaNghiViec()) {
            System.out.println("Không tìm thấy nhân viên (hoặc nhân viên đã nghỉ việc)");
            return null;
        }
        return nv;
    }

   
    public static void XoaNhanVien(Scanner sc, LocalDate date, LocalTime time, String maNguoiThucHien) {
        NhanVien nv = ChonNhanVienDeQuanLy(sc, "xóa");
        if (nv == null) {
            return;
        }
        if (nv.getMaNV().equalsIgnoreCase(maNguoiThucHien)) {
            System.out.println("Không thể tự xóa chính mình");
            return;
        }
        System.out.println(nv);
        System.out.println("Thao tác này sẽ chấm dứt hợp đồng, xuất phiếu lương cuối và khóa tài khoản của nhân viên.");
        System.out.println("Nhập 'XOA' để xác nhận, nhập bất kỳ để hủy:");
        if (!sc.nextLine().trim().equals("XOA")) {
            System.out.println("Đã hủy thao tác xóa");
            return;
        }
        System.out.println("Nhập lý do (Enter để dùng mặc định):");
        String lyDo = sc.nextLine().trim();
        if (lyDo.isEmpty()) {
            lyDo = "Admin xóa khỏi hệ thống";
        }
        ChamDutNhanVien(nv, date, date, time, maNguoiThucHien, lyDo);
    }

    private static NhanVien NguoiDangGiuViTri(ViTri vt) {
        for (NhanVien n : DanhSachNhanVien.getDsnv()) {
            if (!n.isDaNghiViec() && vt.getMaViTri().equalsIgnoreCase(n.getMaViTri())) {
                return n;
            }
        }
        return null;
    }

    private static NhanVien TimGiamDocDangLam() {
        for (NhanVien n : DanhSachNhanVien.getDsnv()) {
            if (!n.isDaNghiViec() && n.getChucVu() == LoaiChucVu.GIAM_DOC) {
                return n;
            }
        }
        return null;
    }

    private static NhanVien TimQuanLyPhongBan(String maPhongBan) {
        for (NhanVien n : DanhSachNhanVien.getDsnv()) {
            if (!n.isDaNghiViec() && n.getChucVu() == LoaiChucVu.QUAN_LY
                    && n.getMaPhongBan() != null && n.getMaPhongBan().equalsIgnoreCase(maPhongBan)) {
                return n;
            }
        }
        return null;
    }

    public static void ThemNhanVien(Scanner sc, LocalDate date, LocalTime time, String maNguoiThucHien) {
        System.out.println("--- THÊM NHÂN VIÊN MỚI ---");
        String CCCD = KiemTraLoi.NhapCCCD(sc);
        String HoTen = KiemTraLoi.NhapHoTen(sc);
        String SDT = KiemTraLoi.NhapSDT(sc);
        LocalDate ngaySinh = KiemTraLoi.NhapNgaySinh(sc);

        ViTri vt;
        LoaiChucVu chucVu;
        while (true) {
            vt = KiemTraLoi.NhapMaViTri(sc);
            chucVu = ViTri.XacDinhChucVu(vt.getMaViTri());
            if (chucVu != LoaiChucVu.NHAN_VIEN) {
                NhanVien dangGiu = NguoiDangGiuViTri(vt);
                if (dangGiu != null) {
                    System.out.println("Vị trí không hợp lệ: " + vt.getMoTa() + " đang do "
                            + dangGiu.getHoTen() + " (" + dangGiu.getMaNV() + ") đảm nhiệm");
                    continue;
                }
            }
            break;
        }

        String maQuanLy;
        if (chucVu == LoaiChucVu.GIAM_DOC) {
            maQuanLy = Boss.MA;
        } else if (chucVu == LoaiChucVu.QUAN_LY) {
            NhanVien giamDoc = TimGiamDocDangLam();
            maQuanLy = giamDoc != null ? giamDoc.getMaNV() : Boss.MA;
        } else {
            NhanVien quanLy = TimQuanLyPhongBan(vt.getPhongBan());
            if (quanLy != null) {
                maQuanLy = quanLy.getMaNV();
            } else {
                System.out.println("Phòng ban " + vt.getPhongBan() + " chưa có quản lý, hãy chọn người phụ trách");
                maQuanLy = KiemTraLoi.NhapMaQuanLy(sc);
            }
        }
        System.out.println("Quản lý phụ trách: " + maQuanLy);

        String caLam = KiemTraLoi.NhapCaLam(sc, vt.isFullTime());
        System.out.println("--- KÝ HỢP ĐỒNG CHO " + HoTen + " ---");
        HopDong hd = TuyenDung.KyHopDong(sc, vt, date);

        System.out.println("\nHọ tên: " + HoTen + " | SĐT: " + SDT + " | Vị trí: " + vt.getMoTa()
                + " | Phòng ban: " + vt.getPhongBan() + " | Ca: " + caLam + " | Quản lý: " + maQuanLy);
        System.out.println(hd);
        while (true) {
            System.out.println("Xác nhận thêm nhân viên và ký hợp đồng?\n0.Không\n1.Có");
            String c = sc.nextLine().trim();
            if (c.equals("0")) {
                System.out.println("Đã hủy, chưa tạo nhân viên");
                return;
            }
            if (c.equals("1")) {
                break;
            }
            System.out.println("Vui lòng nhập 0 hoặc 1");
        }

        String maNV = DanhSachNhanVien.taoMa();
        NhanVien nv;
        if (vt.isFullTime()) {
            nv = new NhanVienFullTime(maNV, CCCD, HoTen, SDT, maQuanLy, ngaySinh, caLam);
        } else {
            nv = new NhanVienPartTime(maNV, CCCD, HoTen, SDT, maQuanLy, ngaySinh, caLam);
        }
        hd.setMaNV(maNV);
        hd.setMaHopDong("HD" + maNV);
        nv.setHopDong(hd);
        DanhSachNhanVien.them(nv);

        NhatKyHoatDong.them("Người dùng mã " + maNguoiThucHien + " thêm nhân viên " + maNV
                + " và ký hợp đồng " + hd.getMaHopDong() + " vào " + time + " ngày " + date);
        if (!maQuanLy.equalsIgnoreCase(Boss.MA)) {
            HomThu.GuiThongBao("HETHONG", maQuanLy, date, time,
                    "Nhân viên mới " + maNV + " (" + HoTen + ") đã được thêm vào dưới quyền quản lý của bạn");
        }
        System.out.println("Đã thêm nhân viên " + HoTen + " mã " + maNV);
        System.out.println("Tài khoản đăng nhập: " + maNV + " | mật khẩu mặc định: số điện thoại (phải đổi ở lần đăng nhập đầu)");
    }
}
