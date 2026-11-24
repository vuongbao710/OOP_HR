package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

public class DanhGiaKhenThuong {

    public static void ThuongSangKien(Scanner sc, LocalDate date, LocalTime time, String MaQuanLy) {
        if (KiemTraLoi.HuyTacVu(sc)) {
            return;
        }
        NhanVien nv;
        while (true) {
            System.out.println("Nhân viên bạn muốn thưởng sáng kiến có mã nhân viên là :");
            nv = DanhSachNhanVien.timKiem(sc.nextLine().trim());
            if (nv != null) {
                break;
            }
            System.out.println("Không tìm thấy nhân viên tương ứng");
        }
        double tien;
        while (true) {
            tien = KiemTraLoi.NhapSo(sc, "Số tiền thưởng sáng kiến là ?\n Tối thiểu 100.000vnd - Tối đa 500.000vnd");
            if (tien >= 100000 && tien <= 500000) {
                break;
            }
            System.out.println("Số tiền phải trong khoảng 100.000 đến 500.000 vnd");
        }
        nv.getDsktkl().them(new KhenThuong(LoaiKhenThuong.THUONG_SANG_KIEN, date, tien, MaQuanLy, nv.getMaNV()));
        HomThu.GuiThongBao(MaQuanLy, nv.getMaNV(), date, time, "thưởng sáng kiến " + tien + " vnd");
        NhatKyHoatDong.them("Quản lý mã " + MaQuanLy + " thưởng sáng kiến có giá trị " + tien + " vào " + time
                + " ngày " + date + " cho nhân viên mã " + nv.getMaNV());
    }

    public static void DanhGiaKPI(Scanner sc, LocalDate date, LocalTime time, String MaQuanLy, String MaNV) {
        if (KiemTraLoi.HuyTacVu(sc)) {
            return;
        }
        NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
        NhanVienFullTime nvFull = (NhanVienFullTime) nv;

        // Xác định quý cần đánh giá
        LocalDate ngayTinhKPI = NgayNghiepVu.ngayKetThucQuy(date.minusDays(3));
        LocalDate ngayDauQuy = ngayTinhKPI.minusMonths(2).withDayOfMonth(1);
        int thang = ngayTinhKPI.getMonthValue();
        int nam = ngayTinhKPI.getYear();

        // Quản lý chỉ nhập chỉ tiêu
        System.out.println("Đánh giá KPI cho nhân viên " + nv.getMaNV());
        double chiTieuChung = KiemTraLoi.NhapSo(sc, "% hoàn thành chỉ tiêu chung là?");
        double chiTieuPhongBan = KiemTraLoi.NhapSo(sc, "% hoàn thành chỉ tiêu phòng ban là?");

        // 1. Điểm nền
        double diemNen = QuyDinh.DIEM_NEN;

        // 2. Điểm kết quả
        double phanTramDat = chiTieuChung * QuyDinh.TRONG_SO_CHI_TIEU_CHUNG
                + chiTieuPhongBan * QuyDinh.TRONG_SO_CHI_TIEU_PHONG_BAN;
        double diemKetQua = QuyDinh.tinhDiemKetQua(phanTramDat);

        // 3. Lấy số lần vi phạm từ hệ thống
        int soLanViPhamChamCong = DemViPham(nv, LoaiViPham.VI_PHAM_CHAM_CONG, ngayDauQuy, ngayTinhKPI);
        int soLanNghiKhongPhep = DemViPham(nv, LoaiViPham.NGHI_KHONG_PHEP, ngayDauQuy, ngayTinhKPI);
        int soLanOTKhongLam = DemViPham(nv, LoaiViPham.DANG_KY_OT_MA_KHONG_LAM, ngayDauQuy, ngayTinhKPI);
        int soLanAnToan = DemViPham(nv, LoaiViPham.VI_PHAM_AN_TOAN_VE_SINH, ngayDauQuy, ngayTinhKPI);
        int soLanTaiSan = DemViPham(nv, LoaiViPham.VI_PHAM_TAI_SAN_CONG_TY, ngayDauQuy, ngayTinhKPI);
        int soLanTratTu = DemViPham(nv, LoaiViPham.VI_PHAM_TRAT_TU_NOI_LAM_VIEC, ngayDauQuy, ngayTinhKPI);
        int soLanQuayRoi = DemViPham(nv, LoaiViPham.QUAY_ROI, ngayDauQuy, ngayTinhKPI);
        double soGioOT = TinhGioOTTrongQuy(nv, ngayDauQuy, ngayTinhKPI);
        double diemCong = QuyDinh.tinhDiemOT(soGioOT);
        if (soLanViPhamChamCong == 0) {
            diemCong = diemCong + QuyDinh.DIEM_KHONG_TRE;
        }
        if (diemCong > QuyDinh.DIEM_CONG_TOI_DA) {
            diemCong = QuyDinh.DIEM_CONG_TOI_DA;
        }
        double diemAnToan = QuyDinh.VI_PHAM_AN_TOAN;
        String tenPhong = DanhSachPhongBan.layTenPhongBan(nv.getMaPhongBan());
        if (tenPhong != null && (tenPhong.toLowerCase().contains("sản xuất") || tenPhong.toLowerCase().contains("logistics"))) {
            diemAnToan = QuyDinh.VI_PHAM_AN_TOAN_NHAN_DOI;
        }
        double diemTru = QuyDinh.tinhDiemTruDiTre(soLanViPhamChamCong)
                + soLanNghiKhongPhep * QuyDinh.NGHI_KHONG_PHEP
                + soLanOTKhongLam * QuyDinh.OT_KHONG_THUC_HIEN
                + soLanAnToan * diemAnToan
                + soLanTaiSan * QuyDinh.VI_PHAM_TAI_SAN;
        boolean biKyLuatNang = soLanQuayRoi > 0;

        double DiemKPI = QuyDinh.tinhTongDiemKPI(diemNen, diemKetQua, diemCong, diemTru, biKyLuatNang);

        System.out.println("Nền: " + diemNen + " | Kết quả: " + diemKetQua + " | Cộng: " + diemCong + " | Trừ: " + diemTru);
        System.out.println("Điểm KPI: " + DiemKPI + " - Xếp loại: " + QuyDinh.phanXepLoai(DiemKPI));

        DanhGiaKPI kpi = new DanhGiaKPI(DiemKPI, thang, nam, MaQuanLy, nv.getMaNV());
        nvFull.getDskpi().them(kpi);
        HomThu.GuiThongBao(MaQuanLy, nv.getMaNV(), date, time, "đã có đánh giá kpi có điểm số là " + DiemKPI + " tháng " + thang + " năm " + nam).setRequest(LoaiRequest.THONG_BAO);
        NhatKyHoatDong.them("Quản lý mã " + MaQuanLy + " đã đánh giá kpi " + DiemKPI + " lúc " + time + " ngày " + date + " cho nhân viên mã " + MaNV);
    }

    public static void DanhGiaKPINhanVienPhuTrach(Scanner sc, LocalDate date, LocalTime time, String MaNVDanhGia) {
        XuLyRequest.daHuyTacVu = false;
        ArrayList<NhanVien> ds = DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrachVaFullTime(MaNVDanhGia);
        if (ds.isEmpty()) {
            System.out.println("Danh sách nhân viên full-time cấp dưới trống");
            return;
        }
        for (NhanVien nv : ds) {
            DanhGiaKPI(sc, date, time, MaNVDanhGia, nv.getMaNV());
            if (XuLyRequest.daHuyTacVu) {
                return;
            }
        }
    }

    public static int DemViPham(NhanVien nv, LoaiViPham loai, LocalDate NgayDau, LocalDate NgayCuoi) {
        int dem = 0;
        for (KhenThuongKyLuat k : nv.getDsktkl().getKtdskl()) {
            if (k instanceof KyLuat) {
                KyLuat kl = (KyLuat) k;
                LocalDate ngay = kl.getNgayBanHanh();
                if (kl.getLoaiViPham() == loai && !ngay.isBefore(NgayDau) && !ngay.isAfter(NgayCuoi)) {
                    dem++;
                }
            }
        }
        return dem;
    }

    public static double TinhGioOTTrongQuy(NhanVien nv, LocalDate NgayDau, LocalDate NgayCuoi) {
        double tong = 0;
        for (BangChamCong b : nv.getDsbcc().getDsbcc()) {
            LocalDate ngay = LocalDate.of(b.getNam(), b.getThang(), 1);
            if (!ngay.isBefore(NgayDau) && !ngay.isAfter(NgayCuoi)) {
                tong = tong + b.getSoGioOT();
            }
        }
        BangChamCong hienTai = nv.getBcc();
        LocalDate ngayHienTai = LocalDate.of(hienTai.getNam(), hienTai.getThang(), 1);
        if (!ngayHienTai.isBefore(NgayDau) && !ngayHienTai.isAfter(NgayCuoi)) {
            tong = tong + hienTai.getSoGioOT();
        }
        return tong;
    }
}
