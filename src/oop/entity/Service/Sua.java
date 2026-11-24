
package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import oop.entity.DanhSach.DanhSachDotTuyenDung;
import oop.entity.DanhSach.DanhSachKhoaDaoTao;
import oop.entity.DanhSach.DanhSachNhanVien;
import oop.entity.DanhSach.DanhSachPhongBan;
import oop.entity.Enum.LoaiCaLamFullTime;
import oop.entity.Enum.LoaiCaLamPartTime;
import oop.entity.Enum.LoaiChucVu;
import oop.entity.Object.Boss;
import oop.entity.Object.DotTuyenDung;
import oop.entity.Object.KhoaDaoTao;
import oop.entity.Object.NhanVien;
import oop.entity.Object.NhanVienFullTime;
import oop.entity.Object.NhanVienPartTime;
import oop.entity.Object.PhongBan;
import oop.entity.Object.UngVien;
import oop.entity.Object.ViTri;

public class Sua {
    public static void SuaThongTin(Scanner sc, LocalDate date, LocalTime time, String maNguoiThucHien) {
    int input = -1;
    while (input != 0) {
        System.out.println("\n--- SỬA THÔNG TIN ---");
        System.out.println("1. Nhân viên");
        System.out.println("2. Ứng viên");
        System.out.println("3. Phòng ban");
        System.out.println("0. Quay lại");
        input = KiemTraLoi.NhapSoNguyen(sc, "Chọn đối tượng cần sửa:", 0, 3);
        switch (input) {
            case 1:
                SuaNhanVien(sc, date, time, maNguoiThucHien);
                break;
            case 2:
                SuaUngVien(sc, date, time, maNguoiThucHien);
                break;
            case 3:
                SuaPhongBan(sc, date, time, maNguoiThucHien);
                break;
            default:
                break;
        }
    }
}

private static void GhiNhanThayDoi(String doiTuong, String maDoiTuong, String thayDoi,
        String maNguoiThucHien, LocalDate date, LocalTime time) {
    System.out.println("Đã cập nhật " + thayDoi);
    NhatKyHoatDong.them("Người dùng mã " + maNguoiThucHien + " sửa " + thayDoi + " của " + doiTuong
            + " " + maDoiTuong + " vào " + time + " ngày " + date);
}
private static int CapBac(String ma) {
    if (ma.equalsIgnoreCase(Boss.MA)) {
        return 3;
    }
    NhanVien n = DanhSachNhanVien.timKiem(ma);
    if (n == null) {
        return -1;
    }
    switch (n.getChucVu()) {
        case GIAM_DOC:
            return 2;
        case QUAN_LY:
            return 1;
        default:
            return 0;
    }
}
private static String NhapMaQuanLyMoi(Scanner sc, NhanVien nv) {
    while (true) {
        String ma = KiemTraLoi.NhapMaQuanLy(sc);
        if (ma.equalsIgnoreCase(nv.getMaNV())) {
            System.out.println("Không thể tự làm quản lý của chính mình");
            continue;
        }
        if (!ma.equalsIgnoreCase(Boss.MA)) {
            NhanVien ql = DanhSachNhanVien.timKiem(ma);
            if (ql == null || ql.isDaNghiViec()) {
                System.out.println("Quản lý này đã nghỉ việc");
                continue;
            }
        }
        if (CapBac(ma) <= CapBac(nv.getMaNV())) {
            System.out.println("Quản lý phụ trách phải có chức vụ cao hơn nhân viên được sửa");
            continue;
        }
        return ma;
    }
}
public static void SuaNhanVien(Scanner sc, LocalDate date, LocalTime time, String maNguoiThucHien) {
    NhanVien nv = NhanSu.ChonNhanVienDeQuanLy(sc, "sửa thông tin");
    if (nv == null) {
        return;
    }
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    int input = -1;
    while (input != 0) {
        System.out.println("\n--- Sửa thông tin nhân viên " + nv.getMaNV() + " ---");
        System.out.println("1. Họ tên: " + nv.getHoTen());
        System.out.println("2. Số điện thoại: " + nv.getSDT());
        System.out.println("3. CCCD: " + nv.getCCCD());
        System.out.println("4. Ngày sinh: " + (nv.getBirthDate() == null ? "" : nv.getBirthDate().format(fmt)));
        System.out.println("5. Ca làm: " + nv.getCaLam());
        System.out.println("6. Quản lý phụ trách: " + nv.getMaQuanLyPhuTrach());
        System.out.println("7. Số ngày nghỉ phép năm: " + nv.getSoNgayNghiPhepNam());
        System.out.println("0. Hoàn tất");
        input = KiemTraLoi.NhapSoNguyen(sc, "Chọn thông tin cần sửa:", 0, 7);
        String thayDoi = null;
        String thongBaoChoQL = null;
        switch (input) {
            case 1: {
                String cu = nv.getHoTen();
                String moi = KiemTraLoi.NhapHoTen(sc);
                nv.setHoTen(moi);
                thayDoi = "họ tên từ " + cu + " thành " + moi;
                break;
            }
            case 2: {
                String cu = nv.getSDT();
                String moi = KiemTraLoi.NhapSDT(sc);
                nv.setSDT(moi);
                thayDoi = "số điện thoại từ " + cu + " thành " + moi;
                break;
            }
            case 3: {
                String moi = KiemTraLoi.NhapCCCD(sc, nv);
                nv.setCCCD(moi);
                thayDoi = "CCCD";
                break;
            }
            case 4: {
                LocalDate moi = KiemTraLoi.NhapNgaySinh(sc);
                String cu = nv.getBirthDate() == null ? "" : nv.getBirthDate().format(fmt);
                nv.setBirthDate(moi);
                thayDoi = "ngày sinh từ " + cu + " thành " + moi.format(fmt);
                break;
            }
            case 5: {
                boolean fullTime = KiemTraLoi.KiemTraIsFullTime(nv);
                String cu = nv.getCaLam();
                String moi = KiemTraLoi.NhapCaLam(sc, fullTime);
                nv.setCaLam(moi);
                if (nv instanceof NhanVienFullTime) {
                    ((NhanVienFullTime) nv).setCa(LoaiCaLamFullTime.NhanDien(moi));
                } else if (nv instanceof NhanVienPartTime) {
                    ((NhanVienPartTime) nv).setCa(LoaiCaLamPartTime.NhanDien(moi));
                }
                thayDoi = "ca làm từ " + cu + " thành " + moi;
                break;
            }
            case 6: {
                String cu = nv.getMaQuanLyPhuTrach();
                String moi = NhapMaQuanLyMoi(sc, nv);
                nv.setMaQuanLyPhuTrach(moi);
                thayDoi = "quản lý phụ trách từ " + cu + " thành " + moi;
                thongBaoChoQL = moi;
                break;
            }
            case 7: {
                int cu = nv.getSoNgayNghiPhepNam();
                int moi = KiemTraLoi.NhapSoNguyen(sc, "Nhập số ngày phép năm mới (0-30):", 0, 30);
                nv.setSoNgayNghiPhepNam(moi);
                thayDoi = "số ngày nghỉ phép năm từ " + cu + " thành " + moi;
                break;
            }
            default:
                break;
        }
        if (thayDoi != null) {
            GhiNhanThayDoi("nhân viên", nv.getMaNV(), thayDoi, maNguoiThucHien, date, time);
            HomThu.GuiThongBao("HETHONG", nv.getMaNV(), date, time,
                    "Admin vừa cập nhật thông tin cá nhân của bạn: " + thayDoi);
            if (thongBaoChoQL != null && !thongBaoChoQL.equalsIgnoreCase(Boss.MA)) {
                HomThu.GuiThongBao("HETHONG", thongBaoChoQL, date, time,
                        "Nhân viên " + nv.getMaNV() + " (" + nv.getHoTen() + ") đã được chuyển về dưới quyền quản lý của bạn");
            }
        }
    }
}

// ---------- Ứng viên ----------

private static String NhapCCCDUngVien(Scanner sc, UngVien uv) {
    while (true) {
        System.out.println("Nhập CCCD");
        String cccd = sc.nextLine().trim();
        try {
            KiemTraLoi.KiemTraCCCD(cccd);
            KiemTraLoi.KiemTraCCCDTrung(cccd); // trùng với nhân viên
            for (DotTuyenDung dot : DanhSachDotTuyenDung.getDsDot()) {
                for (UngVien khac : dot.getDsUV().getDsuv()) {
                    if (khac != uv && cccd.equals(khac.getCCCD())) {
                        throw new Exception("CCCD này đã thuộc về ứng viên " + khac.getMaUngVien());
                    }
                }
            }
            return cccd;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}

private static KhoaDaoTao NhapKhoaDaoTaoMoi(Scanner sc) {
    while (true) {
        System.out.println("Nhập mã khóa đào tạo mới (nhập 'thoát' để hủy):");
        String ma = sc.nextLine().trim();
        if (ma.equalsIgnoreCase("thoát")) {
            return null;
        }
        KhoaDaoTao khoa = DanhSachKhoaDaoTao.timKiem(ma);
        if (khoa != null) {
            return khoa;
        }
        System.out.println("Mã khóa đào tạo không tồn tại");
    }
}

public static void SuaUngVien(Scanner sc, LocalDate date, LocalTime time, String maNguoiThucHien) {
    System.out.println("Nhập mã ứng viên cần sửa (nhập 'thoát' để hủy):");
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
        System.out.println("Ứng viên này đã được chuyển thành nhân viên, hãy sửa ở mục Nhân viên");
        return;
    }
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    int input = -1;
    while (input != 0) {
        System.out.println("\n--- Sửa thông tin ứng viên " + uv.getMaUngVien() + " (đợt " + uv.getMaDotTuyenDung() + ") ---");
        System.out.println("1. Họ tên: " + uv.getHoTen());
        System.out.println("2. Số điện thoại: " + uv.getSDT());
        System.out.println("3. CCCD: " + uv.getCCCD());
        System.out.println("4. Ngày sinh: " + (uv.getNgayThangNamSinh() == null ? "" : uv.getNgayThangNamSinh().format(fmt)));
        System.out.println("5. Vị trí ứng tuyển: " + uv.getViTriUngTuyen());
        System.out.println("6. Khóa đào tạo: " + (uv.getKhoa() == null ? "chưa có" : uv.getKhoa().getMaKhoaHoc()));
        System.out.println("0. Hoàn tất");
        input = KiemTraLoi.NhapSoNguyen(sc, "Chọn thông tin cần sửa:", 0, 6);
        String thayDoi = null;
        switch (input) {
            case 1: {
                String cu = uv.getHoTen();
                String moi = KiemTraLoi.NhapHoTen(sc);
                uv.setHoTen(moi);
                thayDoi = "họ tên từ " + cu + " thành " + moi;
                break;
            }
            case 2: {
                String cu = uv.getSDT();
                String moi = KiemTraLoi.NhapSDT(sc);
                uv.setSDT(moi);
                thayDoi = "số điện thoại từ " + cu + " thành " + moi;
                break;
            }
            case 3: {
                uv.setCCCD(NhapCCCDUngVien(sc, uv));
                thayDoi = "CCCD";
                break;
            }
            case 4: {
                String cu = uv.getNgayThangNamSinh() == null ? "" : uv.getNgayThangNamSinh().format(fmt);
                LocalDate moi = KiemTraLoi.NhapNgaySinh(sc);
                uv.setNgayThangNamSinh(moi);
                thayDoi = "ngày sinh từ " + cu + " thành " + moi.format(fmt);
                break;
            }
            case 5: {
                String cu = uv.getViTriUngTuyen();
                ViTri vt = KiemTraLoi.NhapMaViTri(sc);
                if (uv.getDot() != null && uv.getDot().getViTri() != null
                        && uv.getDot().getViTri() != vt) {
                    System.out.println("Lưu ý: đợt " + uv.getMaDotTuyenDung() + " đang tuyển vị trí "
                            + uv.getDot().getViTri().getMaViTri());
                }
                uv.setViTriUngTuyen(vt.getMaViTri());
                thayDoi = "vị trí ứng tuyển từ " + cu + " thành " + vt.getMaViTri();
                break;
            }
            case 6: {
                String cu = uv.getKhoa() == null ? "chưa có" : uv.getKhoa().getMaKhoaHoc();
                KhoaDaoTao moi = NhapKhoaDaoTaoMoi(sc);
                if (moi != null) {
                    uv.setKhoa(moi);
                    thayDoi = "khóa đào tạo từ " + cu + " thành " + moi.getMaKhoaHoc();
                }
                break;
            }
            default:
                break;
        }
        if (thayDoi != null) {
            GhiNhanThayDoi("ứng viên", uv.getMaUngVien(), thayDoi, maNguoiThucHien, date, time);
        }
    }
}

// ---------- Phòng ban ----------

private static boolean TenPhongBanBiTrung(String ten, PhongBan boQua) {
    for (PhongBan pb : DanhSachPhongBan.getDspb()) {
        if (pb != boQua && pb.getTenPhongBan().equalsIgnoreCase(ten)) {
            return true;
        }
    }
    return false;
}

private static String NhapTenPhongBanMoi(Scanner sc, PhongBan pb) {
    while (true) {
        System.out.println("Nhập tên phòng ban mới (nhập 'thoát' để hủy):");
        String ten = sc.nextLine().trim();
        if (ten.equalsIgnoreCase("thoát")) {
            return null;
        }
        if (ten.isEmpty() || ten.length() > 50 || ten.contains(",")) {
            System.out.println("Tên không được để trống, tối đa 50 ký tự và không chứa dấu phẩy");
        } else if (TenPhongBanBiTrung(ten, pb)) {
            System.out.println("Tên phòng ban đã tồn tại");
        } else {
            return ten;
        }
    }
}

/** Trả về "" nếu bỏ trống trưởng phòng, null nếu hủy, hoặc mã nhân viên hợp lệ. */
private static String NhapTruongPhongMoi(Scanner sc, PhongBan pb) {
    while (true) {
        System.out.println("Nhập mã trưởng phòng mới (nhập '0' để bỏ trống, 'thoát' để hủy):");
        String ma = sc.nextLine().trim();
        if (ma.equalsIgnoreCase("thoát")) {
            return null;
        }
        if (ma.equals("0")) {
            return "";
        }
        NhanVien n = DanhSachNhanVien.timKiem(ma);
        if (n == null || n.isDaNghiViec()) {
            System.out.println("Không tìm thấy nhân viên (hoặc đã nghỉ việc)");
        } else if (n.getChucVu() != LoaiChucVu.QUAN_LY) {
            System.out.println("Trưởng phòng phải là nhân viên có chức vụ Quản lý");
        } else if (!pb.getMaPhongBan().equalsIgnoreCase(n.getMaPhongBan())) {
            System.out.println("Nhân viên này thuộc phòng " + n.getMaPhongBan() + ", không thuộc phòng " + pb.getMaPhongBan());
        } else {
            return n.getMaNV();
        }
    }
}

public static void SuaPhongBan(Scanner sc, LocalDate date, LocalTime time, String maNguoiThucHien) {
    DanhSachPhongBan.xuat();
    System.out.println("Nhập mã phòng ban cần sửa (nhập 'thoát' để hủy):");
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
    int input = -1;
    while (input != 0) {
        System.out.println("\n--- Sửa phòng ban " + pb.getMaPhongBan() + " ---");
        System.out.println("1. Tên phòng ban: " + pb.getTenPhongBan());
        System.out.println("2. Trưởng phòng: " + (pb.getMaTruongPhong() == null ? "chưa có" : pb.getMaTruongPhong()));
        System.out.println("0. Hoàn tất");
        input = KiemTraLoi.NhapSoNguyen(sc, "Chọn thông tin cần sửa:", 0, 2);
        String thayDoi = null;
        switch (input) {
            case 1: {
                String cu = pb.getTenPhongBan();
                String moi = NhapTenPhongBanMoi(sc, pb);
                if (moi != null) {
                    pb.setTenPhongBan(moi);
                    thayDoi = "tên phòng ban từ " + cu + " thành " + moi;
                }
                break;
            }
            case 2: {
                String cu = pb.getMaTruongPhong();
                String moi = NhapTruongPhongMoi(sc, pb);
                if (moi != null) {
                    String maMoi = moi.isEmpty() ? null : moi;
                    pb.setMaTruongPhong(maMoi);
                    thayDoi = "trưởng phòng từ " + (cu == null ? "chưa có" : cu)
                            + " thành " + (maMoi == null ? "chưa có" : maMoi);
                    if (maMoi != null) {
                        HomThu.GuiThongBao("HETHONG", maMoi, date, time,
                                "Bạn được Admin chỉ định làm trưởng phòng " + pb.getTenPhongBan());
                    }
                    if (cu != null && !cu.equalsIgnoreCase(maMoi) && DanhSachNhanVien.timKiem(cu) != null) {
                        HomThu.GuiThongBao("HETHONG", cu, date, time,
                                "Bạn không còn là trưởng phòng " + pb.getTenPhongBan());
                    }
                }
                break;
            }
            default:
                break;
        }
        if (thayDoi != null) {
            GhiNhanThayDoi("phòng ban", pb.getMaPhongBan(), thayDoi, maNguoiThucHien, date, time);
        }
    }
}
}
