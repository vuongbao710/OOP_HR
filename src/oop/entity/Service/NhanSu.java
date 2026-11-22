package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

// Biến động nhân sự: kỷ luật, cách chức, bổ nhiệm, chấm dứt hợp đồng
public class NhanSu {
    public static NhanVienFullTime DownCastingNhanVienFullTime(NhanVien nv){
        NhanVienFullTime nvFull = (NhanVienFullTime) nv;
        return nvFull;
    }

    public static NhanVienPartTime DownCastingNhanVienPartTime(NhanVien nv){
        NhanVienPartTime nvFull = (NhanVienPartTime) nv;
        return nvFull;
    }

    private static String ThongBaoKyLuat(LoaiKyLuat kl, LoaiViPham vp, LocalDate date, NhanVien nv){
        String Message = null;
        int SoLanViPham = nv.getDsViPham().SoLanViPham(vp, date);
        switch(kl){
            case LoaiKyLuat.KHIEN_TRACH:
                Message = "Bạn bị khiển trách vì " + vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            case LoaiKyLuat.KEO_DAI_THOI_HAN_NANG_LUONG:
                Message = "Bạn bị kéo dài thời hạn nâng lương vì " + vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            case LoaiKyLuat.CACH_CHUC:
                Message = "Bạn bị cách chức vì " + vp.getMoTa()+ " lần thứ " + SoLanViPham;
                break;
            case LoaiKyLuat.SA_THAI:
                Message = "Bạn bị sa thải vì "+ vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            default:
                System.out.println(" Không có trong danh sách hình thức kỷ luật");
                break;
        }
        return Message;
    }

    public static void XuLyKyLuat(LoaiKyLuat kl, LoaiViPham vp, LocalDate date, LocalTime time, NhanVien nv){
        if(nv.isDaNghiViec() || kl == null) return;
        String Message = ThongBaoKyLuat(kl, vp, date, nv);
        if(Message != null){
        HomThu.GuiThongBao("HETHONG", nv.getMaNV(), date, time, Message);
    }
        switch(kl){
        case KHIEN_TRACH:
            break;
        case KEO_DAI_THOI_HAN_NANG_LUONG:
            if(nv.getNgayXetNangLuong() != null){
                nv.setNgayXetNangLuong(nv.getNgayXetNangLuong().plusMonths(6));
            }
            break;
        case CACH_CHUC:
            // nhân viên thường không có chức để cách, chỉ quản lý và giám đốc mới cần người thay
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
    // Lương theo chức vụ mới. Tính từ vị trí vì ChucVu của nv chỉ đổi sau setHopDong.
    hd.setMucLuongCoSo(ViTri.XacDinhChucVu(vt.getMaViTri()).getLuongCoBan());
    nv.setHopDong(hd);
}

// Hỏi vị trí mới cho người bị cách chức. phongBanBatBuoc = null nghĩa là không ràng buộc phòng ban.
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

    private static ViTri LayViTriMoi(Scanner sc, NhanVien nvXuong, String phongBanBatBuoc){
    if(!nvXuong.isDaNghiViec()){
        return NhapViTriMoi(sc, nvXuong, phongBanBatBuoc);
    }
    for(ViTri vt : ViTri.values()){
        if(vt.isFullTime() && ViTri.XacDinhChucVu(vt.getMaViTri()) == LoaiChucVu.NHAN_VIEN
                && vt.getPhongBan() != null && vt.getPhongBan().equalsIgnoreCase(nvXuong.getMaPhongBan())){
            return vt;
        }
    }
    return ViTri.NHAN_VIEN_KINH_DOANH; // giám đốc không thuộc phòng ban nào
}

    public static void ThayChuc(NhanVien nvXuong, NhanVien nvLen, ViTri viTriMoiCuaNvXuong) {
    ViTri viTriNvLenNhan = nvXuong.getHopDong().getLoaiViTri(); // vị trí nvLen sẽ nhận

    // 1. Đổi cấp báo cáo (thứ tự giữ như code cũ)
    nvLen.setMaQuanLyPhuTrach(nvXuong.getMaQuanLyPhuTrach());
    nvXuong.setMaQuanLyPhuTrach(nvLen.getMaNV());
    for (NhanVien n : DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrach(nvXuong.getMaNV())) {
        n.setMaQuanLyPhuTrach(nvLen.getMaNV());
    }

    // 2. Đổi vị trí (chức vụ, phòng ban, quyền, lương tự cập nhật)
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
        // Vị trí quản lý thuộc về một phòng ban, nên người được bổ nhiệm phải cùng phòng ban
        if (!nvLen.getMaPhongBan().equalsIgnoreCase(nvXuong.getMaPhongBan())) {
            System.out.println("Người được bổ nhiệm phải thuộc cùng phòng ban với quản lý bị cách chức");
            return;
        }
        ViTri viTriMoi = LayViTriMoi(sc, nvXuong, nvXuong.getMaPhongBan());
        ThayChuc(nvXuong, nvLen, viTriMoi);
        NhatKyHoatDong.them("Người dùng mã " + MaNguoiThucHien + " bổ nhiệm nhân viên " + nvLen.getMaNV()
                + " thay quản lý " + nvXuong.getMaNV() + " vào " + time + " ngày " + date);
    }
    else if (nvLen.getChucVu() == LoaiChucVu.QUAN_LY && nvXuong.getChucVu() == LoaiChucVu.GIAM_DOC) {
        ViTri viTriMoi = LayViTriMoi(sc, nvXuong, null);
        ThayChuc(nvXuong, nvLen, viTriMoi);
        NhatKyHoatDong.them("Người dùng mã " + MaNguoiThucHien + " bổ nhiệm quản lý " + nvLen.getMaNV()
                + " lên giám đốc thay " + nvXuong.getMaNV() + " vào " + time + " ngày " + date);
        // TODO: ghế quản lý của nvLen đang trống, cần chọn người thay thế (xem ghi chú bên dưới)
        System.out.println("Ghế quản lý phòng ban " + viTriMoi.getPhongBan() + " cũ của "
                + nvLen.getHoTen() + " đang trống, cần bổ nhiệm người thay thế");
    }
    else {
        System.out.println("Chỉ bổ nhiệm được: nhân viên lên quản lý (thay quản lý) hoặc quản lý lên giám đốc (thay giám đốc)");
    }
}

    public static void ChonRaNguoiThayThe(LocalDate date, LocalTime time, String MaNVCachChuc, Scanner sc, String MaNguoiThucHien){
            DanhSachNhanVien.SortTheoThamNien();
            System.out.println("Chọn người thay thế cho nhân viên mã " + MaNVCachChuc);
            int input = KiemTraLoi.NhapSoNguyen(sc, "\n0.Thoát\n1.Bổ nhiệm nội bộ\n2.Tuyển ngoài", 0, 2);
            if(input == 0){
            XuLyRequest.daHuyTacVu = true;   // thoát thì request vẫn còn mở
            return;
        }
            if(input == 1){
            System.out.println("Nhập mã nhân viên bạn muốn bổ nhiệm");
            String nhap = KiemTraLoi.NhapMaNV(sc);
            while(nhap == null){
            nhap = KiemTraLoi.NhapMaNV(sc);
        }
            BoNhiem(date, time, MaNVCachChuc, nhap, sc, MaNguoiThucHien);
            return;
    }
            if(!MaNguoiThucHien.equalsIgnoreCase(Boss.MA)){
            NhanVien nguoiThucHien = DanhSachNhanVien.timKiem(MaNguoiThucHien);
            if(nguoiThucHien == null){
            XuLyRequest.daHuyTacVu = true;
            return;
        }
            TuyenDung.DangTaiTuyenDung(sc, MaNguoiThucHien, nguoiThucHien.getMaQuanLyPhuTrach(), date, time);
            return;
    }
            DotTuyenDung dot = TuyenDung.TaoDotTuyenDung(sc, MaNguoiThucHien);
            if(dot == null){
        XuLyRequest.daHuyTacVu = true;
        return;
    }
            dot.setTrangThai(TRANG_THAI_DOT.DANG_TUYEN_DUNG);
            DanhSachDotTuyenDung.them(dot);
            NhanVien nvXuong = DanhSachNhanVien.timKiem(MaNVCachChuc);
            if(nvXuong != null){
            for(NhanVien n : DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrach(MaNVCachChuc)){
            n.setMaQuanLyPhuTrach(nvXuong.getMaQuanLyPhuTrach());
        }
    }
}

    private static void YeuCauBoNhiemThayThe(NhanVien nv, LocalDate date, LocalTime time){
        if(nv.getChucVu() != LoaiChucVu.QUAN_LY && nv.getChucVu() != LoaiChucVu.GIAM_DOC) return;
        if(nv.getMaQuanLyPhuTrach() == null) return;
            HomThu.GuiYeuCau("HETHONG", nv.getMaQuanLyPhuTrach(), date, time,
            "Nhân viên mã " + nv.getMaNV() + " mất chức vụ " + nv.getChucVu() + ", cần bổ nhiệm người thay thế",
            LoaiRequest.BO_NHIEM, nv.getMaNV());
}

// Chấm dứt quan hệ lao động (dùng chung cho sa thải và từ chối gia hạn):
// xuất phiếu lương cuối, hủy hợp đồng, khóa tài khoản, báo người liên quan.
// ngayKetThuc: ngày hợp đồng chấm dứt (sa thải = hôm nay, hết hạn = ngày hết hạn cũ)
        public static void ChamDutNhanVien(NhanVien nv, LocalDate ngayKetThuc, LocalDate date, LocalTime time,
                                   String MaNguoiThucHien, String lyDo){
        if(nv.isDaNghiViec()) return;

    // 1. Phiếu lương cuối: chốt bảng chấm công hiện tại rồi tính như cuối kỳ
        nv.ThemBangChamCong();
        BangChamCong bang = nv.getBcc();
        PhieuLuong pl = nv.TaoPhieuLuong(bang.getThang(), bang.getNam());
        pl.setMaPhieu(nv.getDspl().taoMa());
        nv.ThemPhieuLuong(pl);

    // 2. Hợp đồng: hủy và chốt ngày kết thúc (DangTrongHopDong tự dừng chấm công sau ngày này)
        HopDong hd = nv.getHopDong();
        if(hd != null){
        hd.setTrangThaiHopDong(TRANG_THAI_HOP_DONG.DA_HUY);
        hd.setNgayHetHan(ngayKetThuc);
        String cu = hd.getGhiChu() == null || hd.getGhiChu().isEmpty() ? "" : hd.getGhiChu() + "; ";
        hd.setGhiChu(cu + "Chấm dứt ngày " + ngayKetThuc + ": " + lyDo);
    }

    // 3. Khóa tài khoản (giữ lại trong danh sách để còn tra cứu)
        if(nv.getTaiKhoan() != null){
        nv.getTaiKhoan().setTrangThai(TaiKhoan.TrangThaiHoatDong.BI_KHOA);
    }
        for(TaiKhoan tk : DanhSachTaiKhoan.getDstk()){
        if(tk.getMaNV() != null && tk.getMaNV().equalsIgnoreCase(nv.getMaNV())){
            tk.setTrangThai(TaiKhoan.TrangThaiHoatDong.BI_KHOA);
        }
    }

    // 4. Báo quản lý và yêu cầu người thay thế nếu người này đang giữ chức
        if(nv.getMaQuanLyPhuTrach() != null){
        HomThu.GuiThongBao("HETHONG", nv.getMaQuanLyPhuTrach(), date, time,
                "Nhân viên " + nv.getMaNV() + " (" + nv.getHoTen() + ") đã chấm dứt hợp đồng: " + lyDo
                + ". Phiếu lương cuối đã được xuất.");
    }
        YeuCauBoNhiemThayThe(nv, date, time);

        NhatKyHoatDong.them("Người dùng mã " + MaNguoiThucHien + " chấm dứt hợp đồng nhân viên " + nv.getMaNV()
            + " (" + lyDo + "), phiếu lương cuối " + pl.getMaPhieu() + " vào " + time + " ngày " + date);
        System.out.println("Đã chấm dứt hợp đồng, xuất phiếu lương cuối và khóa tài khoản của " + nv.getHoTen());
}
}
