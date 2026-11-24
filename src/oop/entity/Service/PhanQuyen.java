package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import oop.entity.DanhSach.DanhSachNhanVien;
import oop.entity.DanhSach.DanhSachPhongBan;
import oop.entity.Enum.LoaiVaiTro;
import static oop.entity.Enum.LoaiVaiTro.DIRECTOR;
import static oop.entity.Enum.LoaiVaiTro.HR_ADMIN;
import oop.entity.Object.Boss;
import oop.entity.Object.NhanVien;
import oop.entity.Object.TaiKhoan;

public class PhanQuyen {
    public static void ChayTheoVaiTro(TaiKhoan tk, Scanner sc) {
        if (tk.getRole() == LoaiVaiTro.BOSS) {
            PhanQuyen.BOSS(sc);
            return;
        }
        NhanVien nv = tk.timKiemNhanVien();
        if (nv == null) {
            System.out.println("Không tìm thấy nhân viên ứng với tài khoản này");
            return;
        }
        switch (tk.getRole()) {
            case HR_ADMIN:
                PhanQuyen.ADMIN(sc, nv);
                break;
            case NHAN_VIEN:
                PhanQuyen.NHAN_VIEN(sc, nv);
                break;
            case QUAN_LY:
                PhanQuyen.QUAN_LY(sc, nv);
                break;
            case DIRECTOR:
                PhanQuyen.DIRECTOR(sc, nv);
                break;
            default:
                break;
        }
        if (!tk.KiemTraTrangThaiHoatDong()) {
            System.out.println("Tài khoản của bạn đã bị khoá, bạn đã bị đăng xuất");
        }
    }
    private static void InMenuChung(NhanVien nv){
        System.out.println("\n===== " + nv.getHoTen() + " (" + nv.getMaNV() + ") - " + HeThong.date + " =====");
        int chuaDoc = HomThu.DemSoLuongInbox(nv.getMaNV());
        if(chuaDoc > 0){
            System.out.println("Bạn có " + chuaDoc + " thông báo chưa đọc");
        }
        System.out.println("0. Đăng xuất");
        System.out.println("1. Chấm công");
        System.out.println("2. Gửi đơn xin nghỉ phép");
        System.out.println("3. Xem tình trạng bảng chấm công");
        System.out.println("4. Xem phiếu lương");
        System.out.println("5. Xem thông tin cá nhân");
        System.out.println("6. Đăng ký làm Over time");
        System.out.println("7. Xem hòm thư");
        System.out.println("8. Đổi mật khẩu");
        System.out.println("9. Xin nghỉ việc");
    }
    
    private static boolean ChucNangChung(int input, Scanner sc, NhanVien nv){
        switch(input){
            case 1:
                ChucNang.ChamCong(HeThong.time, HeThong.date, nv);
                return true;
            case 2:
                ChucNang.XinNghiPhep(HeThong.date, HeThong.time, nv, sc);
                return true;
            case 3:
                nv.getBcc().xuat();
                return true;
            case 4:
                nv.getDspl().xuat();  
                return true;
            case 5:
                System.out.println(nv);
                return true;
            case 6:
                ChucNang.DangKyLamOT(nv, HeThong.date, HeThong.time, sc);
                return true;
            case 7:
                HomThu.checkInbox(nv.getMaNV(), sc, HeThong.date, HeThong.time, nv.getDsThongBao());
                return true;
            case 8:
            nv.getTaiKhoan().DoiMatKhau(sc, false);
            return true;
            case 9:
                ChucNang.XinNghiViec(HeThong.date, HeThong.time, nv, sc);
                return true;
            default:
                return false;
        }
    }
    public static void NHAN_VIEN(Scanner sc, NhanVien nv){
        int input = -1;
        while(input != 0 && nv.getTaiKhoan().KiemTraTrangThaiHoatDong()){
        InMenuChung(nv);
        input = KiemTraLoi.NhapSoNguyen(sc, "Chọn chức năng:", 0, 9);
        ChucNangChung(input, sc, nv);
}
    }
    private static void MenuCapQuanLy(Scanner sc, NhanVien nv){
        int input = -1;
        while(input != 0 && nv.getTaiKhoan().KiemTraTrangThaiHoatDong()){
        InMenuChung(nv);
        System.out.println("10. Xử lý request");
        System.out.println("11. Nộp đợt tuyển dụng");
        System.out.println("12. Xem danh sách nhân viên cấp dưới");
        System.out.println("13. Thưởng sáng kiến");
        System.out.println("14. Kỷ luật nhân viên");
        input = KiemTraLoi.NhapSoNguyen(sc, "Chọn chức năng:", 0, 14);

            if(ChucNangChung(input, sc, nv)){
                continue;
            }
            switch(input){
                case 10:
                    HomThu.checkChuaGiaiQuyet(nv.getMaNV(), sc);
                    break;
                case 11:
                    TuyenDung.DangTaiTuyenDung(sc, nv.getMaNV(), nv.getMaQuanLyPhuTrach(), HeThong.date, HeThong.time);
                    break;
                case 12:
                    ArrayList<NhanVien> capDuoi = DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrach(nv.getMaNV());
                    if(capDuoi.isEmpty()){
                        System.out.println("Bạn chưa có nhân viên cấp dưới");
                    } else {
                        DanhSachNhanVien.xuat(capDuoi);
                    }
                    break;
                case 13:
                    DanhGiaKhenThuong.ThuongSangKien(sc, HeThong.date, HeThong.time, nv.getMaNV());
                    break;
                case 14:
                    NhanSu.KyLuatNhanVien(sc, HeThong.date, HeThong.time, nv.getMaNV());
                    break;
                default:
                    break; 
            }
        }
    }
    public static void QUAN_LY(Scanner sc, NhanVien nv){
        MenuCapQuanLy(sc, nv);
    }

    public static void DIRECTOR(Scanner sc, NhanVien nv){
        MenuCapQuanLy(sc, nv);
    }
    public static void ADMIN(Scanner sc, NhanVien nv){
        int input = -1;
        while(input != 0 && nv.getTaiKhoan().KiemTraTrangThaiHoatDong()){
        InMenuChung(nv);
        System.out.println("10. Xử lý request");
        System.out.println("11. Tạo khóa đào tạo");
        System.out.println("12. Thêm ứng viên vào đợt tuyển dụng");
        System.out.println("13. Tiếp nhận ứng viên (chuyển thành nhân viên và ký hợp đồng)");
        System.out.println("14. Xem danh sách nhân viên");
        System.out.println("15. Xem nhật ký hoạt động");
        System.out.println("16. Giả lập hệ thống (qua ngày)");
        System.out.println("17. Giả lập thời gian (đổi giờ hệ thống)");
        System.out.println("18. Sửa thông tin");
        System.out.println("19. Xóa (nhân viên / ứng viên / phòng ban)");
        System.out.println("20. Thêm nhân viên");
        System.out.println("21. Tìm kiếm gần đúng");
        System.out.println("22. Thống kê phòng ban");
        input = KiemTraLoi.NhapSoNguyen(sc, "Chọn chức năng:", 0, 22);
            if(ChucNangChung(input, sc, nv)){
                continue;
            }
            switch(input){
                case 10:
                    HomThu.checkChuaGiaiQuyet(nv.getMaNV(), sc);
                    break;
                case 11:
                    TuyenDung.TaoKhoaDaoTao(sc, nv.getMaNV(), HeThong.date, HeThong.time);
                    break;
                case 12:
                    TuyenDung.ThemUngVienVaoDanhSach(sc, nv.getMaNV());
                    break;
                case 13:
                    TuyenDung.TiepNhanUngVien(sc, nv.getMaNV(), HeThong.date, HeThong.time);
                    break;
                case 14:
                    XemDanhSachNhanVien(sc);
                    break;
                case 15:
                    NhatKyHoatDong.LuaChonCachXem(sc);
                    break;
                case 16:
                    GiaLapHeThong(sc);
                    break;
                case 17:
                    GiaLapThoiGian(sc);
                    break;
                case 18:
                    Sua.SuaThongTin(sc, HeThong.date, HeThong.time, nv.getMaNV());
                    break;
                case 19:
                    Xoa.XoaThongTin(sc, HeThong.date, HeThong.time, nv.getMaNV());
                    break;
                case 20:
                    NhanSu.ThemNhanVien(sc, HeThong.date, HeThong.time, nv.getMaNV());
                    break;
                case 21:
                    DanhSachNhanVien.ChayTimKiem(sc);
                    break;
                case 22:
                    ThongKe.ChayThongKe(sc);
                    break; 
                default:
                    break;
            }
        }
    }
    
    public static void BOSS(Scanner sc){
        int input = -1;
        while(input != 0){
            System.out.println("\n===== BOSS - " + HeThong.date + " =====");
            int chuaDoc = HomThu.DemSoLuongInbox(Boss.MA);
            if(chuaDoc > 0){
                System.out.println("Bạn có " + chuaDoc + " thông báo chưa đọc");
            }
            System.out.println("0. Đăng xuất");
            System.out.println("1. Xem hòm thư");
            System.out.println("2. Xử lý request");
            System.out.println("3. Xem danh sách nhân viên");
            System.out.println("4. Xem nhật ký hoạt động");
            System.out.println("5. Đổi mật khẩu");
            System.out.println("6. Kỷ luật nhân viên");
            input = KiemTraLoi.NhapSoNguyen(sc, "Chọn chức năng:", 0, 6);
            switch(input){
                case 1:
                    HomThu.checkInbox(Boss.MA, sc, HeThong.date, HeThong.time, Boss.getDsThongBao());
                    break;
                case 2:
                    HomThu.checkChuaGiaiQuyet(Boss.MA, sc);
                    break;
                case 3:
                    XemDanhSachNhanVien(sc);
                    break;
                case 4:
                    NhatKyHoatDong.LuaChonCachXem(sc);
                    break;
                case 5:
                    Boss.getTk().DoiMatKhau(sc, false);
                    break;
                case 6:
                NhanSu.KyLuatNhanVien(sc, HeThong.date, HeThong.time, Boss.MA);
                    break;
                default:
                    break;
            }
        }
    }
    private static void XemDanhSachNhanVien(Scanner sc){
        System.out.println("1. Theo thứ tự thêm vào\n2. Sắp xếp theo họ tên\n3. Sắp xếp theo phòng ban\n4. Sắp xếp theo thâm niên");
        int c = KiemTraLoi.NhapSoNguyen(sc, "Chọn cách xem:", 1, 4);
        if(c == 1) DanhSachNhanVien.xuat();
        else if(c == 2) DanhSachNhanVien.SortTheoHoTen();
        else if(c == 3) DanhSachNhanVien.SortTheoMaPhongBan();
        else DanhSachNhanVien.SortTheoThamNien();
    }
    
    private static void GiaLapHeThong(Scanner sc){
        System.out.println("Hôm nay là " + HeThong.date + ". Nhập ngày kết thúc (dd/MM/yyyy)");
        LocalDate ngayKetThuc;
        while(true){
            ngayKetThuc = KiemTraLoi.isValidDate(sc.nextLine(), "dd/MM/yyyy", HeThong.date);
            if(ngayKetThuc != null){
                break;
            }
            System.out.println("Ngày không hợp lệ hoặc trước hôm nay, nhập lại");
        }
        VanHanh.KiemTraVanHanhLuongNhanSu(HeThong.date, HeThong.time, "HETHONG", ngayKetThuc.minusDays(1));
        HeThong.date = ngayKetThuc;
    }
    
    private static void GiaLapThoiGian(Scanner sc){
    System.out.println("Giờ hệ thống hiện tại là " + HeThong.time.withNano(0) + ". Nhập giờ mới (HH:mm), nhập 'thoát' để hủy");
    while(true){
        String input = sc.nextLine().trim();
        if(input.equalsIgnoreCase("thoát")) return;
        try{
            HeThong.time = LocalTime.parse(input, DateTimeFormatter.ofPattern("H:mm"));
            System.out.println("Đã đổi giờ hệ thống thành " + HeThong.time + " ngày " + HeThong.date);
            return;
        }catch(DateTimeParseException e){
            System.out.println("Giờ không hợp lệ, nhập lại theo dạng HH:mm (ví dụ 08:30)");
        }
    }
}
}
