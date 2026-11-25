package oop.entity.Service;

import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Object.*;

public class TraCuu {

    // ============ MENU CHÍNH ============
    public static void ChayTraCuu(Scanner sc) {
        int input = -1;
        while (input != 0) {
            System.out.println("\n===== XEM VÀ TÌM KIẾM THEO MÃ =====");
            System.out.println("0. Quay lại");
            System.out.println("1. Nhân viên");
            System.out.println("2. Ứng viên");
            System.out.println("3. Đợt tuyển dụng");
            System.out.println("4. Khóa đào tạo");
            System.out.println("5. Tài khoản");
            System.out.println("6. Phòng ban");
            input = KiemTraLoi.NhapSoNguyen(sc, "Chọn đối tượng:", 0, 6);
            if (input == 0) {
                return;
            }
            System.out.println("1. Xem tất cả\n2. Tìm theo mã");
            int cach = KiemTraLoi.NhapSoNguyen(sc, "Chọn cách xem (0 để quay lại):", 0, 2);
            if (cach == 0) {
                continue;
            }
            if (cach == 1) {
                XemTatCa(input);
            } else {
                System.out.println("Nhập mã cần tìm:");
                TimTheoMa(input, sc.nextLine().trim());
            }
        }
    }

    private static void XemTatCa(int loai) {
        switch (loai) {
            case 1:
                XemTatCaNhanVien();
                break;
            case 2:
                XemTatCaUngVien();
                break;
            case 3:
                if (DanhSachDotTuyenDung.getDsDot().isEmpty()) {
                    System.out.println("Chưa có đợt tuyển dụng nào");
                } else {
                    DanhSachDotTuyenDung.xuat();
                }
                break;
            case 4:
                if (DanhSachKhoaDaoTao.getDskdt().isEmpty()) {
                    System.out.println("Chưa có khóa đào tạo nào");
                } else {
                    DanhSachKhoaDaoTao.xuat();
                }
                break;
            case 5:
                if (DanhSachTaiKhoan.getDstk().isEmpty()) {
                    System.out.println("Chưa có tài khoản nào");
                } else {
                    DanhSachTaiKhoan.xuat();
                }
                break;
            case 6:
                if (DanhSachPhongBan.getDspb().isEmpty()) {
                    System.out.println("Chưa có phòng ban nào");
                } else {
                    DanhSachPhongBan.xuat();
                }
                break;
            default:
                break;
        }
    }

    private static void TimTheoMa(int loai, String ma) {
        if (ma.isEmpty()) {
            System.out.println("Mã không được để trống");
            return;
        }
        Object kq = null;
        switch (loai) {
            case 1:
                kq = TimNhanVien(ma);
                break;
            case 2:
                kq = DanhSachDotTuyenDung.timUngVien(ma);
                break;
            case 3:
                kq = DanhSachDotTuyenDung.timKiem(ma);
                break;
            case 4:
                kq = TimKhoaDaoTao(ma);
                break;
            case 5:
                kq = TimTaiKhoan(ma);
                break;
            case 6:
                kq = TimPhongBan(ma);
                break;
            default:
                break;
        }
        if (kq == null) {
            System.out.println("Không tìm thấy đối tượng có mã: " + ma);
            return;
        }
        if (kq instanceof UngVien) {
            System.out.println(MoTaUngVien((UngVien) kq));
        } else if (kq instanceof NhanVien && ((NhanVien) kq).isDaNghiViec()) {
            System.out.println(kq + "\n(Nhân viên này đã nghỉ việc)");
        } else {
            System.out.println(kq);
        }
    }

    // ============ NHÂN VIÊN ============
    private static void XemTatCaNhanVien() {
        if (DanhSachNhanVien.getDsnv().isEmpty()) {
            System.out.println("Chưa có nhân viên nào");
            return;
        }
        DanhSachNhanVien.xuat();   // chỉ in nhân viên chưa nghỉ việc
    }

    private static NhanVien TimNhanVien(String ma) {
        return DanhSachNhanVien.timKiem(ma);   // đã so sánh không phân biệt hoa thường
    }

    // ============ ỨNG VIÊN ============
    // toString của UngVien in lồng cả đợt và khóa nên in gọn lại
    private static String MoTaUngVien(UngVien uv) {
        return "UngVien{MaUngVien=" + uv.getMaUngVien() + ", HoTen=" + uv.getHoTen()
                + ", CCCD=" + uv.getCCCD() + ", SDT=" + uv.getSDT()
                + ", NgaySinh=" + uv.getNgayThangNamSinh()
                + ", MaDotTuyenDung=" + uv.getMaDotTuyenDung()
                + ", ViTriUngTuyen=" + uv.getViTriUngTuyen()
                + (uv.isDaLaNhanVien() ? ", đã là nhân viên" : "") + "}";
    }

    private static void XemTatCaUngVien() {
        int dem = 0;
        for (DotTuyenDung dot : DanhSachDotTuyenDung.getDsDot()) {
            for (UngVien uv : dot.getDsUV().getDsuv()) {
                System.out.println(MoTaUngVien(uv));
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("Chưa có ứng viên nào");
        }
    }

    // ============ KHÓA ĐÀO TẠO ============
    private static KhoaDaoTao TimKhoaDaoTao(String ma) {
        for (KhoaDaoTao k : DanhSachKhoaDaoTao.getDskdt()) {
            if (k.getMaKhoaHoc() != null && k.getMaKhoaHoc().equalsIgnoreCase(ma)) {
                return k;
            }
        }
        return null;
    }

    // ============ TÀI KHOẢN ============
    // Tìm theo mã tài khoản (khóa chính), không phải mã nhân viên
    private static TaiKhoan TimTaiKhoan(String ma) {
        for (TaiKhoan tk : DanhSachTaiKhoan.getDstk()) {
            if (tk.getMaTaiKhoan() != null && tk.getMaTaiKhoan().equalsIgnoreCase(ma)) {
                return tk;
            }
        }
        return null;
    }

    // ============ PHÒNG BAN ============
    private static PhongBan TimPhongBan(String ma) {
        for (PhongBan pb : DanhSachPhongBan.getDspb()) {
            if (pb.getMaPhongBan() != null && pb.getMaPhongBan().equalsIgnoreCase(ma)) {
                return pb;
            }
        }
        return null;
    }
}