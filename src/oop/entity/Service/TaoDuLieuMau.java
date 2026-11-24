package oop.entity.Service;

import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class TaoDuLieuMau {

    private static Random random = new Random();

    private static String[] HO = {"Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Phan", "Vũ", "Võ", "Đặng", "Bùi", "Đỗ"};
    private static String[] TEN_DEM = {"Văn", "Thị", "Minh", "Quốc", "Thanh", "Ngọc", "Hoàng", "Gia"};
    private static String[] TEN = {"An", "Bình", "Cường", "Dũng", "Hà", "Hải", "Hùng", "Khoa", "Lan", "Linh", "Mai", "Nam", "Phúc", "Quân", "Trang", "Tuấn", "Vy"};
    private static String[] CA_LAM = {"Sáng", "Chiều", "Tối"};

// Vị trí quản lý / giám đốc đã có người đang làm thì không được thêm nữa
    private static boolean ViTriQuanLyDaCoNguoi(ViTri vt) {
        if (ViTri.XacDinhChucVu(vt.getMaViTri()) == LoaiChucVu.NHAN_VIEN) {
            return false;
        }
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
            if (!nv.isDaNghiViec() && vt.getMaViTri().equalsIgnoreCase(nv.getMaViTri())) {
                return true;
            }
        }
        return false;
    }

// Tìm mã giám đốc đang làm, không có thì trả về mã BOSS
    private static String TimMaGiamDoc() {
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
            if (!nv.isDaNghiViec() && nv.getChucVu() == LoaiChucVu.GIAM_DOC) {
                return nv.getMaNV();
            }
        }
        return Boss.MA;
    }

// Xác định quản lý phụ trách giống NhanSu.ThemNhanVien
    private static String TimMaQuanLyPhuTrach(ViTri vt) {
        LoaiChucVu chucVu = ViTri.XacDinhChucVu(vt.getMaViTri());
        if (chucVu == LoaiChucVu.GIAM_DOC) {
            return Boss.MA;
        }
        if (chucVu == LoaiChucVu.QUAN_LY) {
            return TimMaGiamDoc();
        }
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
            if (!nv.isDaNghiViec() && nv.getChucVu() == LoaiChucVu.QUAN_LY
                    && vt.getPhongBan().equalsIgnoreCase(nv.getMaPhongBan())) {
                return nv.getMaNV();
            }
        }
        return TimMaGiamDoc();   // phòng chưa có quản lý thì giám đốc phụ trách
    }

    private static String TaoCCCDMoi() {
        while (true) {
            String CCCD = "079";
            for (int i = 0; i < 9; i++) {
                CCCD = CCCD + random.nextInt(10);
            }
            boolean trung = false;
            for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
                if (CCCD.equals(nv.getCCCD())) {
                    trung = true;
                }
            }
            if (!trung) {
                return CCCD;
            }
        }
    }

    private static String TaoSDTMoi() {
        String SDT = "09";
        for (int i = 0; i < 8; i++) {
            SDT = SDT + random.nextInt(10);
        }
        return SDT;
    }

// Tạo hợp đồng ngẫu nhiên phù hợp với vị trí
    private static HopDong TaoHopDongNgauNhien(ViTri vt, LocalDate date) {
        LoaiChucVu chucVu = ViTri.XacDinhChucVu(vt.getMaViTri());
        LOAI_HOP_DONG loai;
        LocalDate ngayHetHan;
        double luongCoBan = 0;
        double luongTheoGio = 0;
        double phuCap = 0;

        if (!vt.isFullTime()) {
            loai = LOAI_HOP_DONG.PART_TIME;
            ngayHetHan = date.plusMonths(6);
            luongTheoGio = (random.nextInt(4) + 5) * 5000;       // 25.000 đến 40.000
        } else if (chucVu == LoaiChucVu.GIAM_DOC) {
            loai = LOAI_HOP_DONG.CHINH_THUC_DAI_HAN;
            ngayHetHan = date.plusYears(3);
            luongCoBan = 35000000;
            phuCap = 3000000;
        } else if (chucVu == LoaiChucVu.QUAN_LY) {
            loai = LOAI_HOP_DONG.CHINH_THUC_DAI_HAN;
            ngayHetHan = date.plusYears(3);
            luongCoBan = 22000000;
            phuCap = 2000000;
        } else {
            if (random.nextBoolean()) {
                loai = LOAI_HOP_DONG.CHINH_THUC_DAI_HAN;
                ngayHetHan = date.plusYears(3);
            } else {
                loai = LOAI_HOP_DONG.CHINH_THUC_NGAN_HAN;
                ngayHetHan = date.plusYears(1);
            }
            luongCoBan = (random.nextInt(8) + 8) * 1000000;      // 8 đến 15 triệu
            phuCap = 1000000;
        }

        // MaHD và MaNV để null, sẽ gán sau khi có mã nhân viên
        return new HopDong(null, null, date, date, ngayHetHan, vt.getMaViTri(),
                luongCoBan, luongTheoGio, phuCap, loai,
                TRANG_THAI_HOP_DONG.DANG_CO_HIEU_LUC, "Hop dong tao ngau nhien");
    }

// Tạo thêm soLuong nhân viên ngẫu nhiên (gọi sau khi đã load dữ liệu mẫu)
    public static void TaoNhanVienNgauNhien(int soLuong, LocalDate date) {
        for (int i = 0; i < soLuong; i++) {

            // 1. Random vị trí, bỏ qua vị trí quản lý đã có người
            ViTri vt;
            do {
                vt = ViTri.values()[random.nextInt(ViTri.values().length)];
            } while (ViTriQuanLyDaCoNguoi(vt));

            // 2. Random thông tin cá nhân
            String HoTen = HO[random.nextInt(HO.length)] + " "
                    + TEN_DEM[random.nextInt(TEN_DEM.length)] + " "
                    + TEN[random.nextInt(TEN.length)];
            String CCCD = TaoCCCDMoi();
            String SDT = TaoSDTMoi();
            int tuoi = 20 + random.nextInt(26);                  // 20 đến 45 tuổi
            LocalDate ngaySinh = LocalDate.of(date.getYear() - tuoi, random.nextInt(12) + 1, random.nextInt(28) + 1);

            // 3. Quản lý phụ trách và ca làm
            String maQuanLy = TimMaQuanLyPhuTrach(vt);
            String caLam = "Sáng";
            if (ViTri.XacDinhChucVu(vt.getMaViTri()) == LoaiChucVu.NHAN_VIEN) {
                caLam = CA_LAM[random.nextInt(CA_LAM.length)];
            }

            // 4. Tạo nhân viên và hợp đồng
            String maNV = DanhSachNhanVien.taoMa();
            NhanVien nv;
            if (vt.isFullTime()) {
                nv = new NhanVienFullTime(maNV, CCCD, HoTen, SDT, maQuanLy, ngaySinh, caLam);
            } else {
                nv = new NhanVienPartTime(maNV, CCCD, HoTen, SDT, maQuanLy, ngaySinh, caLam);
            }
            HopDong hd = TaoHopDongNgauNhien(vt, date);
            hd.setMaNV(maNV);
            hd.setMaHopDong("HD" + maNV);
            nv.setHopDong(hd);

            DanhSachHopDong.them(hd);
            DanhSachNhanVien.them(nv);
        }
        System.out.println("Đã tạo thêm " + soLuong + " nhân viên ngẫu nhiên");
    }

    public static void TaoDanhSachNhanVien(DanhSachHopDong dsHopDong) {
        int SoDong = 0;
        try (BufferedReader read = new BufferedReader(new FileReader("NhanVien.txt"))) {
            String line;
            while ((line = read.readLine()) != null) {
                SoDong++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 8) {
                    System.out.println("Dòng " + SoDong + " không đủ dữ liệu");
                    continue;
                }
                try {
                    String MaNV = parts[0].trim();
                    String CCCD = parts[1].trim();
                    String HoTen = parts[2].trim();
                    String SDT = parts[3].trim();
                    String MaQuanLyPhuTrach = parts[4].trim();
                    String MaViTri = parts[6].trim();
                    String CaLam = parts[7].trim();

                    KiemTraLoi.KiemTraCCCD(CCCD);
                    KiemTraLoi.KiemTraHoTen(HoTen);
                    KiemTraLoi.KiemTraDinhDangSDT(SDT);
                    LocalDate BirthDate = KiemTraLoi.KiemTraNgay(parts[5], "yyyy-MM-dd");

                    if (!KiemTraLoi.KiemTraMaViTriTonTai(MaViTri)) {
                        throw new Exception("Mã vị trí " + MaViTri + " không tồn tại");
                    }
                    HopDong HD = DanhSachHopDong.timKiemTheoMaNV(MaNV);
                    if (HD == null) {
                        throw new Exception("Nhân viên " + MaNV + " chưa có hợp đồng");
                    }

                    NhanVien nv;
                    if (HD.getLoaiHopDong().equals(LOAI_HOP_DONG.PART_TIME)) {
                        nv = new NhanVienPartTime(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, BirthDate, CaLam);
                    } else {
                        nv = new NhanVienFullTime(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, BirthDate, CaLam);
                    }
                    nv.setHopDong(HD);
                    DanhSachNhanVien.them(nv);
                } catch (Exception e) {
                    System.out.println("Dòng " + SoDong + " bị lỗi: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file NhanVien.txt ở dòng " + (SoDong + 1) + ": " + e.getMessage());
        }
    }

    public static void TaoTaiKhoanBoss() {
        TaiKhoan tk = new TaiKhoan("TK000", "TK000", "0918554647tuki@A", LoaiVaiTro.BOSS);
        tk.setMaNV(Boss.MA);
        Boss.setTk(tk);
        DanhSachTaiKhoan.them(tk);
    }

    public static void TaoDanhSachPhongBan() {
        int SoDong = 0;
        try (BufferedReader read = new BufferedReader(new FileReader("PhongBan.txt"))) {
            String line;
            while ((line = read.readLine()) != null) {
                SoDong++;
                if (SoDong == 1 && line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length < 2 || parts.length > 3) {
                    System.out.println("Dòng " + SoDong + " của phongban.txt không đúng định dạng MaPhongBan,TenPhongBan[,MaTruongPhong]");
                    continue;
                }
                String MaPhongBan = parts[0].trim();
                String TenPhongBan = parts[1].trim();
                String MaTruongPhong = (parts.length == 3 && !parts[2].trim().isEmpty()) ? parts[2].trim() : null;
                if (MaPhongBan.isEmpty() || TenPhongBan.isEmpty()) {
                    System.out.println("Dòng " + SoDong + " của phongban.txt thiếu mã hoặc tên phòng ban");
                    continue;
                }
                if (DanhSachPhongBan.timPhongBan(MaPhongBan) != null) {
                    System.out.println("Dòng " + SoDong + " của phongban.txt bị trùng mã phòng ban " + MaPhongBan);
                    continue;
                }
                DanhSachPhongBan.them(new PhongBan(MaPhongBan, TenPhongBan, MaTruongPhong));
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file phongban.txt ở dòng " + (SoDong + 1) + ": " + e.getMessage());
        }
    }

    public static DanhSachHopDong TaoDanhSachHopDong() {
        DanhSachHopDong dsHopDong = new DanhSachHopDong(SoLuongTapDuLieu("HopDong.txt"));
        try (BufferedReader read = new BufferedReader(new FileReader("HopDong.txt"))) {
            String line;
            int SoThuTuDong = 0;
            while ((line = read.readLine()) != null) {
                String[] temp = line.split(",");
                SoThuTuDong++;
                if (temp.length < 12) {
                    System.err.println("Cảnh báo: Dòng " + SoThuTuDong + " bị thiếu tham số (có " + temp.length + "/" + 12 + "): " + line);
                    continue;
                }
                String MaHopDong = temp[0].trim();
                String MaNV = temp[1].trim();
                LocalDate NgayKy = LocalDate.parse(temp[2].trim());
                LocalDate NgayBatDauHopDong = LocalDate.parse(temp[3].trim());
                LocalDate NgayHetHan = LocalDate.parse(temp[4].trim());
                String ViTriLamViec = temp[5].trim();
                double LuongCoBan = Double.parseDouble(temp[6].trim());
                double LuongTheoGio = Double.parseDouble(temp[7].trim());
                double PhuCap = Double.parseDouble(temp[8].trim());
                LOAI_HOP_DONG LoaiHopDong = LOAI_HOP_DONG.valueOf(temp[9].trim());
                TRANG_THAI_HOP_DONG TrangThaiHopDong = TRANG_THAI_HOP_DONG.valueOf(temp[10].trim());
                String GhiChu = temp[11].trim();

                HopDong HD = new HopDong(MaHopDong, MaNV, NgayKy, NgayBatDauHopDong, NgayHetHan, ViTriLamViec,
                        LuongCoBan, LuongTheoGio, PhuCap, LoaiHopDong, TrangThaiHopDong, GhiChu);
                dsHopDong.them(HD);

            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file: " + e.getMessage());
        }
        return dsHopDong;
    }

    public static int SoLuongTapDuLieu(String file) {
        int SoLuong = 0;
        try (BufferedReader read = new BufferedReader(new FileReader("HopDong.txt"))) {
            String line;

            while ((line = read.readLine()) != null) {
                SoLuong++;
            }

        } catch (IOException e) {
            System.out.println("Lỗiiiii" + e.getMessage());
        }

        return SoLuong;

    }

}
