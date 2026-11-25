package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import oop.entity.DanhSach.BangChamCong;
import oop.entity.DanhSach.DanhSachNhanVien;
import oop.entity.Object.DanhGiaKPI;
import oop.entity.Object.NhanVien;
import oop.entity.Object.NhanVienFullTime;
import oop.entity.Object.PhieuLuong;

public class XemThongTinCaNhan {

    public static void XemThongTinCaNhan(Scanner sc, NhanVien nv) {
        int input = -1;
        while (input != 0) {
            System.out.println("\n===== XEM THÔNG TIN CÁ NHÂN =====");
            System.out.println("0. Quay lại");
            System.out.println("1. Thông tin cá nhân");
            System.out.println("2. Bảng chấm công");
            System.out.println("3. Phiếu lương");
            System.out.println("4. KPI");
            input = KiemTraLoi.NhapSoNguyen(sc, "Chọn mục cần xem:", 0, 4);
            switch (input) {
                case 1:
                    System.out.println(nv);
                    break;
                case 2:
                    MenuBangChamCong(sc, nv);
                    break;
                case 3:
                    MenuPhieuLuong(sc, nv);
                    break;
                case 4:
                    MenuKPI(sc, nv);
                    break;
                default:
                    break;
            }
        }
    }

    // Hỏi cách xem: 1 = mới nhất, 2 = tìm theo mã, 0 = quay lại
    private static int ChonCachXem(Scanner sc) {
        System.out.println("1. Xem mới nhất\n2. Tìm kiếm theo mã");
        return KiemTraLoi.NhapSoNguyen(sc, "Chọn cách xem (0 để quay lại):", 0, 2);
    }

    private static String NhapMaCanTim(Scanner sc) {
        System.out.println("Nhập mã cần tìm:");
        return sc.nextLine().trim();
    }

    // --- Bảng chấm công ---
    private static void MenuBangChamCong(Scanner sc, NhanVien nv) {
        int cach = ChonCachXem(sc);
        if (cach == 1) {
            // bảng hiện tại chính là bảng mới nhất
            InBangChamCong(nv.getBcc());
        } else if (cach == 2) {
            String ma = NhapMaCanTim(sc);
            BangChamCong kq = null;
            if (!ma.isEmpty()) {
                if (nv.getBcc().getMaBangChamCong() != null && nv.getBcc().getMaBangChamCong().equalsIgnoreCase(ma)) {
                    kq = nv.getBcc();
                } else {
                    for (BangChamCong b : nv.getDsbcc().getDsbcc()) {
                        if (b.getMaBangChamCong() != null && b.getMaBangChamCong().equalsIgnoreCase(ma)) {
                            kq = b;
                            break;
                        }
                    }
                }
            }
            if (kq == null) {
                System.out.println("Không tìm thấy bảng chấm công có mã: " + ma);
            } else {
                InBangChamCong(kq);
            }
        }
    }

    private static void InBangChamCong(BangChamCong b) {
        if (b == null || b.getBcc() == null || b.getBcc().isEmpty()) {
            System.out.println("Chưa có dữ liệu bảng chấm công");
            return;
        }
        b.xuat();
    }

    // --- Phiếu lương ---
    private static void MenuPhieuLuong(Scanner sc, NhanVien nv) {
        int cach = ChonCachXem(sc);
        ArrayList<PhieuLuong> ds = nv.getDspl().getPl();
        if (cach == 1) {
            PhieuLuong moiNhat = null;
            for (PhieuLuong p : ds) {
                if (moiNhat == null || p.getNam() > moiNhat.getNam()
                        || (p.getNam() == moiNhat.getNam() && p.getThang() >= moiNhat.getThang())) {
                    moiNhat = p;
                }
            }
            if (moiNhat == null) {
                System.out.println("Chưa có phiếu lương nào");
            } else {
                System.out.println(moiNhat);
            }
        } else if (cach == 2) {
            String ma = NhapMaCanTim(sc);
            PhieuLuong kq = null;
            for (PhieuLuong p : ds) {
                if (!ma.isEmpty() && p.getMaPhieu() != null && p.getMaPhieu().equalsIgnoreCase(ma)) {
                    kq = p;
                    break;
                }
            }
            if (kq == null) {
                System.out.println("Không tìm thấy phiếu lương có mã: " + ma);
            } else {
                System.out.println(kq);
            }
        }
    }

    // --- KPI (chỉ nhân viên full time có đánh giá KPI) ---
    private static void MenuKPI(Scanner sc, NhanVien nv) {
        if (!(nv instanceof NhanVienFullTime)) {
            System.out.println("Chỉ nhân viên full time mới có đánh giá KPI");
            return;
        }
        ArrayList<DanhGiaKPI> ds = ((NhanVienFullTime) nv).getDskpi().getDskpi();
        int cach = ChonCachXem(sc);
        if (cach == 1) {
            DanhGiaKPI moiNhat = null;
            for (DanhGiaKPI k : ds) {
                if (moiNhat == null || k.getNam() > moiNhat.getNam()
                        || (k.getNam() == moiNhat.getNam() && k.getThang() >= moiNhat.getThang())) {
                    moiNhat = k;
                }
            }
            if (moiNhat == null) {
                System.out.println("Chưa có đánh giá KPI nào");
            } else {
                System.out.println(moiNhat);
            }
        } else if (cach == 2) {
            String ma = NhapMaCanTim(sc);
            DanhGiaKPI kq = null;
            for (DanhGiaKPI k : ds) {
                if (!ma.isEmpty() && k.getMaDanhGia() != null && k.getMaDanhGia().equalsIgnoreCase(ma)) {
                    kq = k;
                    break;
                }
            }
            if (kq == null) {
                System.out.println("Không tìm thấy đánh giá KPI có mã: " + ma);
            } else {
                System.out.println(kq);
            }
        }
    }

    public static void XemThongTinCapDuoi(Scanner sc, NhanVien nguoiXem) {
        ArrayList<NhanVien> capDuoi = DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrach(nguoiXem.getMaNV());
        if (capDuoi.isEmpty()) {
            System.out.println("Bạn chưa có nhân viên cấp dưới");
            return;
        }
        DanhSachNhanVien.xuat(capDuoi);
        System.out.println("Nhập mã nhân viên cấp dưới cần xem (để trống để quay lại):");
        String ma = sc.nextLine().trim();
        if (ma.isEmpty()) {
            return;
        }
        NhanVien duocXem = null;
        for (NhanVien n : capDuoi) {
            if (n.getMaNV() != null && n.getMaNV().equalsIgnoreCase(ma)) {
                duocXem = n;
                break;
            }
        }
        if (duocXem == null) {
            System.out.println("Mã " + ma + " không phải nhân viên cấp dưới của bạn");
            return;
        }
        System.out.println("\n>>> Nhân viên: " + duocXem.getHoTen() + " (" + duocXem.getMaNV() + ")");
        XemThongTinCaNhan(sc, duocXem);
    }

}
