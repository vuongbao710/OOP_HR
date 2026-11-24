/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import oop.entity.DanhSach.DanhSachDotTuyenDung;
import oop.entity.DanhSach.DanhSachNhanVien;
import oop.entity.DanhSach.DanhSachPhongBan;
import oop.entity.Object.DotTuyenDung;
import oop.entity.Object.NhanVien;
import oop.entity.Object.PhongBan;
import oop.entity.Object.UngVien;
import oop.entity.Object.ViTri;
import static oop.entity.Service.NhanSu.XoaNhanVien;

public class Xoa {
    // ===================== ADMIN: XÓA =====================

public static void XoaThongTin(Scanner sc, LocalDate date, LocalTime time, String maNguoiThucHien) {
    int input = -1;
    while (input != 0) {
        System.out.println("\n--- XÓA ---");
        System.out.println("1. Nhân viên (chấm dứt hợp đồng)");
        System.out.println("2. Ứng viên");
        System.out.println("3. Phòng ban");
        System.out.println("0. Quay lại");
        input = KiemTraLoi.NhapSoNguyen(sc, "Chọn đối tượng cần xóa:", 0, 3);
        switch (input) {
            case 1:
                XoaNhanVien(sc, date, time, maNguoiThucHien);
                break;
            case 2:
                XoaUngVien(sc, date, time, maNguoiThucHien);
                break;
            case 3:
                XoaPhongBan(sc, date, time, maNguoiThucHien);
                break;
            default:
                break;
        }
    }
}

private static boolean XacNhanXoa(Scanner sc) {
    System.out.println("Nhập 'XOA' để xác nhận, nhập bất kỳ để hủy:");
    if (sc.nextLine().trim().equals("XOA")) {
        return true;
    }
    System.out.println("Đã hủy thao tác xóa");
    return false;
}

public static void XoaUngVien(Scanner sc, LocalDate date, LocalTime time, String maNguoiThucHien) {
    System.out.println("Nhập mã ứng viên cần xóa (nhập 'thoát' để hủy):");
    String ma = sc.nextLine().trim();
    if (ma.equalsIgnoreCase("thoát")) {
        return;
    }
    UngVien uv = DanhSachDotTuyenDung.timUngVien(ma);
    if (uv == null) {
        System.out.println("Không tìm thấy ứng viên");
        return;
    }
    if (uv.isDaLaNhanVien()) {
        System.out.println("Ứng viên này đã được chuyển thành nhân viên, không thể xóa");
        return;
    }
    System.out.println(uv);
    if (!XacNhanXoa(sc)) {
        return;
    }
    DotTuyenDung dot = DanhSachDotTuyenDung.timKiem(uv.getMaDotTuyenDung());
    if (dot == null) {
        System.out.println("Không tìm thấy đợt tuyển dụng của ứng viên");
        return;
    }
    dot.getDsUV().xoa(uv);
    NhatKyHoatDong.them("Người dùng mã " + maNguoiThucHien + " xóa ứng viên " + uv.getMaUngVien()
            + " khỏi đợt " + dot.getMaDotTuyenDung() + " vào " + time + " ngày " + date);
    System.out.println("Đã xóa ứng viên " + uv.getMaUngVien());
}

public static void XoaPhongBan(Scanner sc, LocalDate date, LocalTime time, String maNguoiThucHien) {
    DanhSachPhongBan.xuat();
    System.out.println("Nhập mã phòng ban cần xóa (nhập 'thoát' để hủy):");
    String ma = sc.nextLine().trim();
    if (ma.equalsIgnoreCase("thoát")) {
        return;
    }
    PhongBan pb = null;
    for (PhongBan p : DanhSachPhongBan.getDspb()) {
        if (p.getMaPhongBan().equalsIgnoreCase(ma)) {
            pb = p;
            break;
        }
    }
    if (pb == null) {
        System.out.println("Không tìm thấy phòng ban");
        return;
    }
    for (NhanVien n : DanhSachNhanVien.getDsnv()) {
        if (!n.isDaNghiViec() && pb.getMaPhongBan().equalsIgnoreCase(n.getMaPhongBan())) {
            System.out.println("Không thể xóa: phòng vẫn còn nhân viên đang làm việc (ví dụ "
                    + n.getMaNV() + " - " + n.getHoTen() + "). Hãy chuyển hoặc chấm dứt hợp đồng trước");
            return;
        }
    }
    for (ViTri vt : ViTri.values()) {
        if (vt.getPhongBan() != null && vt.getPhongBan().equalsIgnoreCase(pb.getMaPhongBan())) {
            System.out.println("Không thể xóa: vị trí " + vt.getMaViTri() + " (" + vt.getMoTa()
                    + ") vẫn thuộc phòng này");
            return;
        }
    }
    System.out.println(pb);
    if (!XacNhanXoa(sc)) {
        return;
    }
    DanhSachPhongBan.xoaPhongBan(pb.getMaPhongBan());
    NhatKyHoatDong.them("Người dùng mã " + maNguoiThucHien + " xóa phòng ban " + pb.getMaPhongBan()
            + " (" + pb.getTenPhongBan() + ") vào " + time + " ngày " + date);
    System.out.println("Đã xóa phòng ban " + pb.getMaPhongBan());
}
}
