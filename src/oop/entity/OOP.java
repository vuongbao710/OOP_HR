package oop.entity;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Object.*;
import oop.entity.Service.TaoDuLieuMau;
import oop.entity.Enum.*;
import oop.entity.Service.HeThong;
import oop.entity.Service.HomThu;
import oop.entity.Service.NhanSu;
import oop.entity.Service.PhanQuyen;

public class OOP {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        TaoDuLieuMau.TaoDanhSachPhongBan();
        DanhSachHopDong dsHopDong = TaoDuLieuMau.TaoDanhSachHopDong();
        TaoDuLieuMau.TaoDanhSachNhanVien(dsHopDong);
        TaoDuLieuMau.TaoNhanVienNgauNhien(10, HeThong.date);
        for (NhanVien n : DanhSachNhanVien.getDsnv()) {      // <-- thêm
            NhanSu.GanNhanVienChoQuanLyMoi(n);
        }
        TaoDuLieuMau.TaoTaiKhoanBoss();
        TaoDuLieuMau.TaoKPINgauNhien(HeThong.date, 4);
        TaoDuLieuMau.TaoDanhSachNgayLe(HeThong.date.getYear());
        TaoDuLieuMau.TaoDanhSachNgayLe(HeThong.date.getYear() + 1);

        Scanner sc = new Scanner(System.in);
        while (true) {
            TaiKhoan tk = TaiKhoan.DangNhap(sc);
            if (tk == null) {
                System.out.println("Đã tắt chương trình");
                break;
            }
            String ma = (tk.getRole() == LoaiVaiTro.BOSS) ? Boss.MA : tk.getMaNV();
            HomThu.checkInbox(ma, sc, HeThong.date, HeThong.time, HomThu.LayHomThu(ma));

            PhanQuyen.ChayTheoVaiTro(tk, sc);
            System.out.println("Đã đăng xuất");
        }
    }

}
