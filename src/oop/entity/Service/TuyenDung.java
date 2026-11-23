package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

public class TuyenDung {
//=================================Tuyển dụng=====================================
    public static DotTuyenDung TaoDotTuyenDung(Scanner sc, String MaNguoiTuyen){
        
    if(KiemTraLoi.HuyTacVu(sc)){
        return null;
    }
        LocalDate NgayBatDau;
        LocalDate NgayKetThuc;
        ViTri vt;
        System.out.println("Nhập số lượng cần tuyển");
        int SoLuong = sc.nextInt();
        sc.nextLine();
        while(true){
        System.out.println("Nhập ngày bắt đầu tuyển dụng");
        NgayBatDau = KiemTraLoi.isValidDate(sc.nextLine(),"dd/MM/yyyy");
        if(NgayBatDau != null && !NgayBatDau.isBefore(LocalDate.now())){
            break;
        }
        }
        while(true){
        System.out.println("Nhập ngày kết thúc tuyển dụng");
        NgayKetThuc = KiemTraLoi.isValidDate(sc.nextLine(),"dd/MM/yyyy");
        if(NgayKetThuc != null && !NgayKetThuc.isBefore(LocalDate.now())){
            break;
        }
        }
        while(true){
        System.out.println("Nhập mã vị trí tuyển dụng");
        String MaViTri = sc.nextLine().trim();
        vt = ViTri.timKiem(MaViTri);
        if(vt!=null){
            break;
        }
        else{
            System.out.println("Không tìm thấy vị trí");
        }
        }
        DotTuyenDung dot = new DotTuyenDung(SoLuong,MaNguoiTuyen, NgayBatDau, NgayKetThuc, vt);
        return dot;
    }

    public static void DangTaiTuyenDung(Scanner sc, String MaQuanLy, String MaCapTren, LocalDate date, LocalTime time){
        DotTuyenDung dot = TaoDotTuyenDung(sc,MaQuanLy);
        if(dot==null){
            return;
        }
        
        System.out.println("Nhập lý do");
        String Message = sc.nextLine().trim();
        DanhSachDotTuyenDung.them(dot);
        HomThu.GuiThongBao(MaQuanLy, MaCapTren, date, time, Message).setRequest(LoaiRequest.DANG_TAI_DOT_TUYEN_DUNG);
        NhatKyHoatDong.them("Quản lý mã "+ MaQuanLy+ " gửi đề nghị tuyển dụng vào "+ time + " ngày " + date +
                "cho giám đốc mã " + MaCapTren);
    }

    public static UngVien TaoUngVien(Scanner sc){
    System.out.println("--- NHẬP THÔNG TIN ỨNG VIÊN ---");
    
    if(KiemTraLoi.HuyTacVu(sc)){
        return null;
    }
    
    String CCCD = KiemTraLoi.NhapCCCD(sc);
    String HoTen = KiemTraLoi.NhapHoTen(sc);
    String SDT = KiemTraLoi.NhapSDT(sc);
    LocalDate NgayThangNamSinh = KiemTraLoi.NhapNgaySinh(sc);
    
   // Nhập và kiểm tra Mã đợt tuyển dụng
    String MaDotTuyenDung = "";
    while (true) {
        System.out.print("Nhập mã đợt tuyển dụng: ");
        MaDotTuyenDung = sc.nextLine().trim();
        if (DanhSachDotTuyenDung.timKiem(MaDotTuyenDung) != null) {
            break;
        }
        System.out.println("Mã đợt tuyển dụng không tồn tại! Vui lòng nhập lại.");
    }
    
    String ViTriUngTuyen = "";
    while (true) {
        System.out.print("Nhập vị trí ứng tuyển: ");
        ViTriUngTuyen = sc.nextLine().trim();
        if (ViTri.timKiem(ViTriUngTuyen) != null) {
            break;
        }
        System.out.println("Vị trí tuyển dụng không tồn tại! Vui lòng nhập lại.");
    }
    
    String MaKhoaDaoTao = "";
    while (true) {
        System.out.print("Nhập mã khóa đào tạo: ");
        MaKhoaDaoTao = sc.nextLine().trim();
        if (DanhSachKhoaDaoTao.timKiem(MaKhoaDaoTao) != null) {
            break;
        }
        System.out.println("Mã khóa đào tạo không tồn tại! Vui lòng nhập lại.");
    }
    
    return new UngVien(CCCD, HoTen, SDT, NgayThangNamSinh, MaDotTuyenDung, ViTriUngTuyen, MaKhoaDaoTao);
    }

    public static void ThemUngVienVaoDanhSach(Scanner sc, String MaNguoiThem){
        System.out.println("Nhập số lượng ứng viên muốn thêm: ");
        int SoLuong = sc.nextInt();
        sc.nextLine();
        while(SoLuong!=0){
            UngVien uv = TaoUngVien(sc);
            if(uv==null){
                return;
            }
            DotTuyenDung dot = DanhSachDotTuyenDung.timKiem(uv.getMaDotTuyenDung());
            dot.themUngVien(uv);
            SoLuong--;
            NhatKyHoatDong.them("Nhân viên mã " + MaNguoiThem + " thêm ứng viên có mã " 
                    + uv.getMaUngVien() + " vào " + LocalDate.now() + " , " + LocalTime.now());
        }
    }

    public static KhoaDaoTao TaoKhoaDaoTao(Scanner sc, String MaNguoiTao, LocalDate date, LocalTime time) {
    if (KiemTraLoi.HuyTacVu(sc)) return null;

    System.out.println("Nhập tên khóa đào tạo");
    String ten = sc.nextLine().trim();

    ViTri vt;
    NhanVien quanLy;
    while (true) {
        System.out.println("Nhập mã vị trí tuyển dụng của khóa");
        vt = ViTri.timKiem(sc.nextLine().trim());
        if (vt == null) {
            System.out.println("Không tìm thấy vị trí");
            continue;
        }
        if (vt == ViTri.GIAM_DOC_DIEU_HANH) {
            System.out.println("Vị trí này không thuộc phòng ban nào, không thể mở khóa đào tạo");
            continue;
        }
        quanLy = DanhSachNhanVien.TimQuanLyTheoViTri(vt);
        if (quanLy == null) {
            System.out.println("Phòng ban " + vt.getPhongBan() + " chưa có quản lý, hãy chọn vị trí khác");
            continue;
        }
        break;
    }
    System.out.println("Người đào tạo: " + quanLy.getHoTen() + " (mã " + quanLy.getMaNV()
            + ", phòng ban " + quanLy.getMaPhongBan() + ")");

    LocalDate ngayBatDau;
    while (true) {
        ngayBatDau = KiemTraLoi.NhapNgay(sc, "Nhập ngày bắt đầu khóa");
        if (!ngayBatDau.isBefore(date)) break;
        System.out.println("Ngày bắt đầu không được trước hôm nay");
    }
    LocalDate ngayKetThuc;
    while (true) {
        ngayKetThuc = KiemTraLoi.NhapNgay(sc, "Nhập ngày kết thúc khóa");
        if (ngayKetThuc.isAfter(ngayBatDau)) break;
        System.out.println("Ngày kết thúc phải sau ngày bắt đầu");
    }

    KhoaDaoTao khoa = new KhoaDaoTao(ten, ngayBatDau, ngayKetThuc, vt, quanLy.getMaNV());
    DanhSachKhoaDaoTao.them(khoa);

    NhatKyHoatDong.them("Người dùng mã " + MaNguoiTao + " tạo khóa đào tạo " + khoa.getMaKhoaHoc()
            + " cho vị trí " + vt.getMaViTri() + ", người đào tạo là quản lý mã " + quanLy.getMaNV()
            + " vào " + time + " ngày " + date);
    System.out.println("Đã tạo khóa đào tạo mã " + khoa.getMaKhoaHoc());
    return khoa;
}

    public static void ChamDiemUngVien(Scanner sc, String MaNguoiCham, LocalDate date, LocalTime time){
        
    if(KiemTraLoi.HuyTacVu(sc)){
        return;
    }
        final int DIEM_DAT = 50;
        
        DotTuyenDung dot;
        while(true){
            System.out.println("Nhập mã đợt tuyển dụng");
            String MaDot = sc.nextLine().trim();
            dot = DanhSachDotTuyenDung.timKiem(MaDot);
            if(dot != null){
                break;
            }
            System.out.println("Không tìm thấy đợt tuyển dụng");
        }
        
        UngVien uv;
        while(true){
            System.out.println("Nhập mã ứng viên");
            String MaUngVien = sc.nextLine();
            uv = dot.timKiem(MaUngVien);
            if(uv != null){
                break;
            }
        }
        System.out.println("Nhập điểm số");
        int DiemSo = sc.nextInt();
        sc.nextLine();
        KetQuaDaoTao kq = uv.getKq();
        kq.setMaKetQua("KQ" + uv.getMaUngVien());
        kq.setMaUngVien(uv.getMaUngVien());
        kq.setDiemSo(DiemSo);
        if(uv.getKhoa() != null){
            kq.setMaKhoaHoc(uv.getKhoa().getMaKhoaHoc());
        }
        if(DiemSo >= DIEM_DAT){
            kq.setKetQua(LoaiKetQua.DAT);
        }
        else{
            kq.setKetQua(LoaiKetQua.KHONG_DAT);
        }
        
        System.out.println("Ứng viên " + uv.getHoTen() + " có kết quả: " + kq.getKetQua().getMoTa());
        NhatKyHoatDong.them("Nhân viên mã " + MaNguoiCham + " chấm " + DiemSo + " điểm cho ứng viên " + uv.getMaUngVien() 
                + " vào " + time + " ngày " + date + " kết quả " + kq.getKetQua().getMoTa());
    }

    private static NhanVien TaoNhanVien(String maNV, UngVien uv, String maQL,
                                    String caLam, boolean fullTime) {
    if (fullTime) {
        return new NhanVienFullTime(maNV, uv.getCCCD(), uv.getHoTen(), uv.getSDT(), maQL,
                uv.getNgayThangNamSinh(), caLam);
    }
    return new NhanVienPartTime(maNV, uv.getCCCD(), uv.getHoTen(), uv.getSDT(), maQL,
            uv.getNgayThangNamSinh(), caLam);
}

    public static NhanVien NhapNhanVien(Scanner sc) {
    String CCCD = KiemTraLoi.NhapCCCD(sc);
    String HoTen = KiemTraLoi.NhapHoTen(sc);
    String SDT = KiemTraLoi.NhapSDT(sc);
    LocalDate NgaySinh = KiemTraLoi.NhapNgaySinh(sc);
    String MaQuanLy = KiemTraLoi.NhapMaQuanLy(sc);
    ViTri vt = KiemTraLoi.NhapMaViTri(sc);
    String caLam = KiemTraLoi.NhapCaLam(sc, vt.isFullTime());

    System.out.println("--- KÝ HỢP ĐỒNG ---");
    HopDong hd = KyHopDong(sc, vt, HeThong.date);

    String maNV = DanhSachNhanVien.taoMa();
    NhanVien nv;
    if (vt.isFullTime()) {
        nv = new NhanVienFullTime(maNV, CCCD, HoTen, SDT, MaQuanLy, NgaySinh, caLam);
    } else {
        nv = new NhanVienPartTime(maNV, CCCD, HoTen, SDT, MaQuanLy, NgaySinh, caLam);
    }
    hd.setMaNV(maNV);
    hd.setMaHopDong("HD" + maNV);
    nv.setHopDong(hd);
    return nv;
}

    public static HopDong KyHopDong(Scanner sc, ViTri vt, LocalDate ngayKy) {
    LOAI_HOP_DONG loai;
    if (!vt.isFullTime()) {
        loai = LOAI_HOP_DONG.PART_TIME;
        System.out.println("Loại hợp đồng: " + loai.getMoTa());
    } else {
        while (true) {
            System.out.println("Chọn loại hợp đồng:\n1." + LOAI_HOP_DONG.CHINH_THUC_NGAN_HAN.getMoTa()
                    + "\n2." + LOAI_HOP_DONG.CHINH_THUC_DAI_HAN.getMoTa());
            String chon = sc.nextLine().trim();
            if (chon.equals("1")) { loai = LOAI_HOP_DONG.CHINH_THUC_NGAN_HAN; break; }
            if (chon.equals("2")) { loai = LOAI_HOP_DONG.CHINH_THUC_DAI_HAN; break; }
            System.out.println("Vui lòng nhập 1 hoặc 2");
        }
    }

    LocalDate ngayBatDau;
    while (true) {
        ngayBatDau = KiemTraLoi.NhapNgay(sc, "Nhập ngày bắt đầu hợp đồng");
        if (!ngayBatDau.isBefore(ngayKy)) break;
        System.out.println("Ngày bắt đầu không được trước ngày ký");
    }
    LocalDate ngayHetHan;
    while (true) {
        ngayHetHan = KiemTraLoi.NhapNgay(sc, "Nhập ngày hết hạn hợp đồng");
        if (ngayHetHan.isAfter(ngayBatDau)) break;
        System.out.println("Ngày hết hạn phải sau ngày bắt đầu");
    }

    double luongCoBan = 0, luongTheoGio = 0;
    if (loai == LOAI_HOP_DONG.PART_TIME) {
        luongTheoGio = KiemTraLoi.NhapSo(sc, "Nhập lương theo giờ");
    } else {
        luongCoBan = KiemTraLoi.NhapSo(sc, "Nhập lương cơ bản");
    }
    double phuCap = KiemTraLoi.NhapSo(sc, "Nhập phụ cấp");

    System.out.println("Nhập ghi chú (có thể để trống)");
    String ghiChu = sc.nextLine().trim();

    return new HopDong(null, null, ngayKy, ngayBatDau, ngayHetHan, vt.getMaViTri(),
            luongCoBan, luongTheoGio, phuCap, loai,
            TRANG_THAI_HOP_DONG.DANG_CO_HIEU_LUC, ghiChu);
}

    public static void TiepNhanUngVien(Scanner sc, String MaNguoiThucHien, LocalDate date, LocalTime time) {
    if (KiemTraLoi.HuyTacVu(sc)) return;
DotTuyenDung dot;
while (true) {
    System.out.println("Nhập mã đợt tuyển dụng");
    dot = DanhSachDotTuyenDung.timKiem(sc.nextLine().trim());
    if (dot != null) break;
    System.out.println("Không tìm thấy đợt tuyển dụng");
}
if (dot.getTrangThai() != TRANG_THAI_DOT.DANG_TUYEN_DUNG) {
    System.out.println("Đợt tuyển dụng này chưa được duyệt hoặc đã đóng, không thể tiếp nhận");
    return;
}

UngVien uv;
while (true) {
    System.out.println("Nhập mã ứng viên");
    uv = dot.timKiem(sc.nextLine().trim());
    if (uv != null) break;
}

if (uv.getKq() == null || uv.getKq().getKetQua() != LoaiKetQua.DAT) {
    System.out.println("Ứng viên chưa đạt hoặc chưa được chấm điểm, không thể tiếp nhận");
    return;
}
if (uv.isDaLaNhanVien()) {
    System.out.println("Ứng viên này đã được chuyển thành nhân viên rồi");
    return;
}
for (NhanVien n : DanhSachNhanVien.getDsnv()) {
    if (n.getCCCD() != null && n.getCCCD().equals(uv.getCCCD())) {
        System.out.println("CCCD này đã tồn tại trong danh sách nhân viên (mã " + n.getMaNV() + ")");
        return;
    }
}

NhanVien quanLy = DanhSachNhanVien.timKiem(dot.getMaNguoiTuyen());
if (quanLy == null) {
    System.out.println("Không tìm thấy quản lý phụ trách của đợt tuyển dụng này");
    return;
}
ViTri vt = dot.getViTri();
String caLam = KiemTraLoi.NhapCaLam(sc, vt.isFullTime());
    System.out.println("--- KÝ HỢP ĐỒNG CHO " + uv.getHoTen() + " ---");
    HopDong hd = KyHopDong(sc, vt, date);
    System.out.println(hd);
    while (true) {
        System.out.println("Xác nhận tiếp nhận và ký hợp đồng?\n0.Không\n1.Có");
        String c = sc.nextLine().trim();
        if (c.equals("0")) {
            System.out.println("Đã hủy, chưa tạo nhân viên");
            return;
        }
        if (c.equals("1")) break;
        System.out.println("Vui lòng nhập đúng định dạng");
    }
    String maNV = DanhSachNhanVien.taoMa();
    NhanVien nv = TaoNhanVien(maNV, uv, quanLy.getMaNV(), caLam, vt.isFullTime());

    hd.setMaNV(maNV);
    hd.setMaHopDong("HD" + maNV);
    nv.setHopDong(hd); 

    DanhSachNhanVien.them(nv);
    uv.setDaLaNhanVien(true);

    NhatKyHoatDong.them("Người dùng mã " + MaNguoiThucHien + " tiếp nhận ứng viên " + uv.getMaUngVien()
            + " thành nhân viên " + maNV + " và ký hợp đồng " + hd.getMaHopDong()
            + " vào " + time + " ngày " + date);
}
}
