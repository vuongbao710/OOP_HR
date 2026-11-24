package oop.entity.Service;

import java.util.ArrayList;
import java.util.Scanner;
import oop.entity.DanhSach.BangChamCong;
import oop.entity.DanhSach.DanhSachNhanVien;
import oop.entity.DanhSach.DanhSachPhongBan;
import static oop.entity.DanhSach.DanhSachPhongBan.timPhongBan;
import oop.entity.Enum.LoaiChucVu;
import oop.entity.Enum.LoaiTrangThaiChamCong;
import oop.entity.Object.ChiTietChamCong;
import oop.entity.Object.NhanVien;
import oop.entity.Object.NhanVienPartTime;
import oop.entity.Object.PhieuLuong;
import oop.entity.Object.PhongBan;

public class ThongKe {

    private static int DemMotPhongBan(String ma, String ten) {
        int soFullTime = 0;
        int soPartTime = 0;
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
            if (nv.isDaNghiViec()) {
                continue;
            }
            if (!ma.equalsIgnoreCase(nv.getMaPhongBan())) {
                continue;
            }
            if (nv instanceof NhanVienPartTime) {
                soPartTime++;
            } else {
                soFullTime++;
            }
        }
        int dem = soFullTime + soPartTime;
        if (dem > 0) {
            System.out.println(ma + " - " + ten + ": " + dem
                    + " nhân viên (full-time " + soFullTime + ", part-time " + soPartTime + ")");
        }
        return dem;
    }

    public static void DemTheoPhongBan() {
        System.out.println("========= Số nhân viên theo phòng ban =========");
        int daDem = 0;
        for (PhongBan pb : DanhSachPhongBan.getDspb()) {
            daDem = daDem + DemMotPhongBan(pb.getMaPhongBan(), pb.getTenPhongBan());
        }
        int tong = 0;
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
            if (!nv.isDaNghiViec()) {
                tong++;
            }
        }
        if (tong > daDem) {
            System.out.println("Không rõ (mã phòng ban chưa có trong phongban.txt): " + (tong - daDem) + " nhân viên");
        }
        System.out.println("Tổng cộng: " + tong + " nhân viên");
    }

    public static ArrayList<NhanVien> LocQuanLy(String maPhongBan) {
        ArrayList<NhanVien> ketQua = new ArrayList<>();
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
            if (nv.isDaNghiViec()) {
                continue;
            }
            if (nv.getChucVu() != LoaiChucVu.QUAN_LY) {
                continue;
            }
            if (!maPhongBan.isEmpty() && !maPhongBan.equalsIgnoreCase(DanhSachPhongBan.LayMaPhongBan(nv))) {
                continue;
            }
            ketQua.add(nv);
        }
        return ketQua;
    }

    public static void XemChamCongVaPhieuLuong(NhanVien nv) {
        System.out.println("========= " + nv.getHoTen() + " (" + nv.getMaNV() + ") - " + nv.getHinhThucDiLam() + " =========");

        BangChamCong bang = nv.getBcc();
        System.out.println("--- Chấm công kỳ " + bang.getThang() + "/" + bang.getNam()
                + " (từ " + bang.getNgayBatDauBang() + " đến " + bang.getNgayKetThucBang() + ") ---");
        System.out.println("Số ngày công: " + bang.getSoNgayCong() + " | Giờ OT: " + bang.getSoGioOT());
        int chuaCham = 0;
        for (ChiTietChamCong cc : bang.getBcc()) {

            if (cc.getTrangThai() == LoaiTrangThaiChamCong.KHONG_CHAM_CONG) {
                chuaCham++;
            } else {

                System.out.println(cc.getDate() + ": " + cc.getTrangThai());
            }
        }
        System.out.println("Số ngày chưa có dữ liệu chấm công: " + chuaCham);
        ArrayList<BangChamCong> cacKyCu = nv.getDsbcc().getDsbcc();
        if (!cacKyCu.isEmpty()) {
            System.out.println("--- Các kỳ đã chốt ---");
            for (BangChamCong b : cacKyCu) {
                System.out.println(b.getThang() + "/" + b.getNam() + " | ngày công " + b.getSoNgayCong() + " | giờ OT " + b.getSoGioOT());
            }
        }

        System.out.println("--- Phiếu lương ---");
        ArrayList<PhieuLuong> dsPhieu = nv.getDspl().getPl();
        if (dsPhieu.isEmpty()) {
            System.out.println("Chưa có phiếu lương");
        } else {
            for (PhieuLuong pl : dsPhieu) {
                System.out.println(pl);
            }
        }
    }

    public static void TongHopTheoPhongBan(String maPhongBan) {
        System.out.println("========= Phòng " + maPhongBan + " - " + DanhSachPhongBan.layTenPhongBan(maPhongBan) + " =========");
        int soNhanVien = 0;
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
            if (nv.isDaNghiViec()) {
                continue;
            }
            if (!maPhongBan.equalsIgnoreCase(DanhSachPhongBan.LayMaPhongBan(nv))) {
                continue;
            }
            soNhanVien++;
            ArrayList<PhieuLuong> dsPhieu = nv.getDspl().getPl();
            String thongTinPhieu;
            if (dsPhieu.isEmpty()) {
                thongTinPhieu = "chưa có phiếu lương";
            } else {
                PhieuLuong gannhat = dsPhieu.get(dsPhieu.size() - 1);
                thongTinPhieu = "phiếu gần nhất " + gannhat.getThang() + "/" + gannhat.getNam()
                        + ", thực nhận " + String.format("%,.0f", gannhat.getLuongThucTe()) + " VND";
            }
            System.out.println(nv.getMaNV() + " | " + nv.getHoTen() + " | " + nv.getHinhThucDiLam()
                    + " | ngày công " + nv.getBcc().getSoNgayCong() + " | OT " + nv.getBcc().getSoGioOT()
                    + " | " + dsPhieu.size() + " phiếu lương (" + thongTinPhieu + ")"); // in 1 dòng cho mỗi nhân viên
        }
        if (soNhanVien == 0) {
            System.out.println("Phòng ban này không có nhân viên");
        }
    }

    // ===== CÁC HÀM MENU =====
    private static void MenuLocQuanLy(Scanner sc) {
        System.out.println("Nhập mã phòng ban để lọc (bấm Enter để xem tất cả quản lý):");
        String ma = sc.nextLine().trim();
        ArrayList<NhanVien> dsQuanLy = LocQuanLy(ma);
        if (dsQuanLy.isEmpty()) {
            System.out.println("Không có quản lý phù hợp");
        } else {
            DanhSachNhanVien.xuat(dsQuanLy);
        }
    }

    private static void MenuXemMotNhanVien(Scanner sc) {
        System.out.println("Nhập mã nhân viên:");
        NhanVien nv = DanhSachNhanVien.timKiem(sc.nextLine().trim());
        if (nv == null) {
            System.out.println("Không tìm thấy nhân viên");
        } else {
            XemChamCongVaPhieuLuong(nv);

        }
    }

    private static void MenuTongHop(Scanner sc) {
    ArrayList<PhongBan> ds = DanhSachPhongBan.getDspb();
    for (int i = 0; i < ds.size(); i++) {
        System.out.println((i + 1) + ". " + ds.get(i).getMaPhongBan() + " - " + ds.get(i).getTenPhongBan());
    }
    int chon = KiemTraLoi.NhapSoNguyen(sc, "Chọn phòng ban (0 để quay lại):", 0, ds.size());
    if (chon == 0) {
        return;
    }
    TongHopTheoPhongBan(ds.get(chon - 1).getMaPhongBan());
}

    public static void ChayThongKe(Scanner sc) {
        int input = -1;
        while (input != 0) {
            System.out.println("\n===== THỐNG KÊ =====");
            System.out.println("0. Quay lại");
            System.out.println("1. Đếm nhân viên theo phòng ban");
            System.out.println("2. Lọc danh sách quản lý");
            System.out.println("3. Xem chấm công và phiếu lương của 1 nhân viên");
            System.out.println("4. Xem chấm công và phiếu lương tổng hợp theo phòng ban");
            input = KiemTraLoi.NhapSoNguyen(sc, "Chọn chức năng:", 0, 4);
            switch (input) {
                case 1:
                    DemTheoPhongBan();
                    break;
                case 2:
                    MenuLocQuanLy(sc);
                    break;
                case 3:
                    MenuXemMotNhanVien(sc);
                    break;
                case 4:
                    MenuTongHop(sc);
                    break;
                default:
                    break;

            }
        }
    }
}
