package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

public class HomThu {

    public static ThongBao GuiYeuCau(String gui, String nhan, LocalDate date, LocalTime time,
            String msg, LoaiRequest loai, String maLienQuan) {
        boolean tonTai = nhan != null && (nhan.equalsIgnoreCase("BOSS") || DanhSachNhanVien.timKiem(nhan) != null);
        if (!tonTai) {
            System.out.println("Không tìm thấy người nhận request: " + nhan);
            return null;
        }
        ThongBao tb = GuiThongBao(gui, nhan, date, time, msg);
        tb.setMaLienQuan(maLienQuan);
        tb.setRequest(loai);
        return tb;
    }

    public static ThongBao GuiThongBao(String MaNguoiGui, String MaNguoiNhan, LocalDate date, LocalTime time, String Message) {
        if (MaNguoiNhan.equalsIgnoreCase("BOSS")) {
            ThongBao tb = new ThongBao(MaNguoiGui, date, time, Message);
            Boss.getDsThongBao().them(tb);
            return tb;
        }
        NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiNhan);
        if (nv == null) {
            return GuiThongBao("HETHONG", MaNguoiGui, date, time, "Không tìm thấy nhân viên bạn gửi thông báo");
        } else {
            ThongBao ThongBao = new ThongBao(MaNguoiGui, date, time, Message);
            nv.getDsThongBao().them(ThongBao);
            return ThongBao;
        }
    }

    public static ThongBao GuiThongBao(String MaNguoiGui, String MaNguoiNhan, LocalDate date, LocalTime time, String Message, LocalDate NgayDangKy) {
        if (MaNguoiNhan.equalsIgnoreCase("BOSS")) {
            ThongBao tb = new ThongBao(MaNguoiGui, date, time, Message);
            tb.setNgayDangKy(NgayDangKy);
            Boss.getDsThongBao().them(tb);
            return tb;
        }
        NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiNhan);
        if (nv == null) {
            return GuiThongBao("HETHONG", MaNguoiGui, date, time, "Không tìm thấy nhân viên bạn gửi thông báo");
        }
        ThongBao ThongBao = new ThongBao(MaNguoiGui, date, time, Message);
        ThongBao.setNgayDangKy(NgayDangKy);
        nv.getDsThongBao().them(ThongBao);
        return ThongBao;
    }

    public static void checkInbox(String MaNVCheckInbox, Scanner sc, LocalDate date, LocalTime time, DanhSachThongBao dsThongBao) {
        int soLuong = DemSoLuongInbox(MaNVCheckInbox);
        if (soLuong == 0) {
            return;
        }
        System.out.println("Hòm thư của bạn có " + soLuong + " thông báo");
        if (!CoDocKhong(sc)) {
            return;
        }

        for (ThongBao tb : new ArrayList<>(dsThongBao.getDsThongBao())) {
            if (tb.getTrangThai() != TrangThaiDocThongBao.CHUA_DOC) {
                continue;
            }
            System.out.println(tb);
            if (tb.getRequest() != LoaiRequest.THONG_BAO && CoGiaiQuyetKhong(sc)) {
                if (XuLyRequest.XuLy(MaNVCheckInbox, tb.getMaNguoiGui(), tb, sc, date, time)) {
                    tb.setTrangThai(TrangThaiDocThongBao.DA_GIAI_QUYET);
                    continue;
                }
            }
            tb.setTrangThai(TrangThaiDocThongBao.DA_DOC);
            System.out.println();
        }
    }

    public static DanhSachThongBao LayHomThu(String ma) {
        if (ma == null) {
            return null;
        }
        if (ma.equalsIgnoreCase(Boss.MA)) {
            return Boss.getDsThongBao();
        }
        NhanVien nv = DanhSachNhanVien.timKiem(ma);
        if (nv == null) {
            return null;
        }
        return nv.getDsThongBao();
    }

    public static int DemSoLuongInbox(String MaNVCheckInbox) {
        DanhSachThongBao hom = LayHomThu(MaNVCheckInbox);
        if (hom == null) {
            return 0;
        }
        int dem = 0;
        for (ThongBao tb : hom.getDsThongBao()) {
            if (tb.getTrangThai() == TrangThaiDocThongBao.CHUA_DOC) {
                dem++;
            }
        }
        return dem;
    }

    public static int DemSoLuongChuaGiaiQuyet(String MaNVCheckInbox) {
        return DanhSachRequestChuaGiaiQuyet(MaNVCheckInbox).size();
    }

    public static boolean CoDocKhong(Scanner sc) {
        while (true) {
            System.out.println("đọc thông báo bây giờ không?\n0.Không\n1.Có");
            int input = sc.nextInt();
            sc.nextLine();
            if (input == 1) {
                return true;
            } else if (input == 0) {
                return false;
            }
            System.out.println("Nhập sai định dạng, vui lòng nhập lại");
        }
    }

    public static boolean CoGiaiQuyetKhong(Scanner sc) {
        while (true) {
            System.out.println("giải quyết bây giờ không?\n0.Không\n1.Có");
            int input = sc.nextInt();
            sc.nextLine();
            if (input == 1) {
                return true;
            } else if (input == 0) {
                return false;
            }
            System.out.println("Nhập sai định dạng, vui lòng nhập lại");
        }
    }

    public static ArrayList<ThongBao> DanhSachRequestChuaGiaiQuyet(String MaNV) {
        ArrayList<ThongBao> temp = new ArrayList<>();
        DanhSachThongBao hom = LayHomThu(MaNV);
        if (hom == null) {
            return temp;
        }
        for (ThongBao tb : hom.getDsThongBao()) {
            if (tb.getRequest() != LoaiRequest.THONG_BAO
                    && (tb.getTrangThai() == TrangThaiDocThongBao.CHUA_DOC
                    || tb.getTrangThai() == TrangThaiDocThongBao.DA_DOC)) {
                temp.add(tb);
            }
        }
        return temp;
    }

    public static void checkChuaGiaiQuyet(String MaNVCheckInbox, Scanner sc) {
        ArrayList<ThongBao> temp = DanhSachRequestChuaGiaiQuyet(MaNVCheckInbox);
        if (temp.isEmpty()) {
            System.out.println("Hiện không còn request nào cần bạn xử lý");
            return;
        }
        while (!temp.isEmpty()) {
            System.out.println("Hòm thư của bạn có " + temp.size() + " request chưa giải quyết bao gồm");
            for (int i = 0; i < temp.size(); i++) {
                System.out.println((i + 1) + ". " + temp.get(i));
            }
            int input = KiemTraLoi.NhapSoNguyen(sc, "Bạn muốn giải quyết mục nào (0 để thoát)", 0, temp.size());
            if (input == 0) {
                return;
            }
            ThongBao tb = temp.get(input - 1);
            boolean daXong = XuLyRequest.XuLy(MaNVCheckInbox, tb.getMaNguoiGui(), tb, sc, HeThong.date, HeThong.time);
            if (daXong) {
                tb.setTrangThai(TrangThaiDocThongBao.DA_GIAI_QUYET);   // đánh dấu đã giải quyết
                temp.remove(input - 1);
            } else if (tb.getTrangThai() == TrangThaiDocThongBao.CHUA_DOC) {
                tb.setTrangThai(TrangThaiDocThongBao.DA_DOC);
            }
        }
        System.out.println("Đã giải quyết hết request");
    }
}
