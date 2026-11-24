package oop.entity.DanhSach;

import java.text.Collator;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import oop.entity.Enum.LoaiChucVu;
import oop.entity.Object.NhanVien;
import oop.entity.Object.TaiKhoan;
import oop.entity.Object.TaiKhoan.TrangThaiHoatDong;
import oop.entity.Object.ViTri;

public class DanhSachNhanVien {

    private static ArrayList<NhanVien> dsnv = new ArrayList<>();
    private static int dem = 0;
    private static final Collator collator = Collator.getInstance(Locale.forLanguageTag("vi"));

    public static void xuat() {
        System.out.println("========= Danh sach nhan vien ==========");
        for (NhanVien nv : dsnv) {
            if (nv.isDaNghiViec()) {
                continue;
            }
            System.out.println(nv);
        }
    }

    public static void xuat(ArrayList<NhanVien> ds) {
        System.out.println("========= Danh sach nhan vien ==========");
        for (NhanVien nv : ds) {
            if (nv.isDaNghiViec()) {
                continue;
            }
            System.out.println(nv);
        }
    }

    public static NhanVien timKiem(String MaNV) {
        for (NhanVien nv : dsnv) {
            if (nv.getMaNV().equalsIgnoreCase(MaNV)) {
                return nv;
            }
        }
        return null;
    }

    public static TaiKhoan timKiemTheoMaTK(String MaTK) {
        for (TaiKhoan tk : DanhSachTaiKhoan.getDstk()) {
            if (tk.getMaNV().equalsIgnoreCase(MaTK) && tk.getTrangThai() == TrangThaiHoatDong.HOAT_DONG) {
                return tk;
            }
        }
        return null;
    }

    public static void xoa(NhanVien nv) {

        dsnv.remove(nv);
    }

    public static void them(NhanVien nv) {
        if (nv.getMaNV() == null) {
            nv.setMaNV(taoMa());
        } else {
            try {
                int so = Integer.parseInt(nv.getMaNV().substring(2));
                if (so > dem) {
                    dem = so;
                }
            } catch (Exception e) {
            }
        }
        dsnv.add(nv);
        if (nv.getTaiKhoan() != null) {
            DanhSachTaiKhoan.them(nv.getTaiKhoan());
        }
    }

    public static String taoMa() {
        dem++;
        return String.format("NV%03d", dem);
    }

    public static ArrayList<NhanVien> getDsnv() {
        return dsnv;
    }

    public static void setDsnv(ArrayList<NhanVien> dsnv) {
        DanhSachNhanVien.dsnv = dsnv;
    }

    public static int getDem() {
        return dem;
    }

    public static void setDem(int dem) {
        DanhSachNhanVien.dem = dem;
    }

    public static ArrayList<NhanVien> timKiemTheoMaQuanLyPhuTrach(String MaQuanLyPhuTrach) {
        ArrayList<NhanVien> temp = new ArrayList<>();
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
            if (nv.getMaQuanLyPhuTrach().equalsIgnoreCase(MaQuanLyPhuTrach)) {
                temp.add(nv);
            }
        }
        return temp;
    }

    public static ArrayList<NhanVien> timKiemTheoMaQuanLyPhuTrachVaFullTime(String MaQuanLyPhuTrach) {
        ArrayList<NhanVien> temp = new ArrayList<>();
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {
            if (nv.getMaQuanLyPhuTrach().equalsIgnoreCase(MaQuanLyPhuTrach) && nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")) {
                temp.add(nv);
            }
        }
        return temp;
    }

    private static String layTen(NhanVien nv) {
        String HoTen = nv.getHoTen().trim();
        int viTri = HoTen.lastIndexOf(' ');
        return HoTen.substring(viTri + 1);
    }

    private static LocalDate layNgayBatDauHopDong(NhanVien nv) {
        if (nv.getHopDong() == null) {
            return null;
        }
        return nv.getHopDong().getNgayBatDauHopDong();
    }

    private static int soSanhMaNV(NhanVien a, NhanVien b) {
        return a.getMaNV().compareToIgnoreCase(b.getMaNV());
    }

    private static int soSanhHoTen(NhanVien a, NhanVien b) {
        int kq = collator.compare(layTen(a), layTen(b));
        if (kq != 0) {
            return kq;
        }
        kq = collator.compare(a.getHoTen(), b.getHoTen());
        if (kq != 0) {
            return kq;
        }
        return soSanhMaNV(a, b);
    }

    private static int soSanhMaPhongBan(NhanVien a, NhanVien b) {
        int kq = a.getMaPhongBan().compareToIgnoreCase(b.getMaPhongBan());
        if (kq != 0) {
            return kq;
        }
        return soSanhMaNV(a, b);
    }

    private static int soSanhThamNien(NhanVien a, NhanVien b) {
        LocalDate ngayA = layNgayBatDauHopDong(a);
        LocalDate ngayB = layNgayBatDauHopDong(b);

        if (ngayA == null && ngayB != null) {
            return 1;
        }
        if (ngayA != null && ngayB == null) {
            return -1;
        }
        if (ngayA != null && ngayB != null) {
            int kq = ngayA.compareTo(ngayB);
            if (kq != 0) {
                return kq;
            }
        }
        return soSanhMaNV(a, b);
    }

    public static void SortTheoHoTen() {
        ArrayList<NhanVien> temp = new ArrayList<>(dsnv);
        for (int i = 0; i < temp.size() - 1; i++) {
            for (int j = i + 1; j < temp.size(); j++) {
                NhanVien a = temp.get(i);
                NhanVien b = temp.get(j);
                if (soSanhHoTen(a, b) > 0) {
                    temp.set(i, b);
                    temp.set(j, a);
                }
            }
        }
        xuat(temp);
    }

    public static void SortTheoMaPhongBan() {
        ArrayList<NhanVien> temp = new ArrayList<>(dsnv);
        for (int i = 0; i < temp.size() - 1; i++) {
            for (int j = i + 1; j < temp.size(); j++) {
                NhanVien a = temp.get(i);
                NhanVien b = temp.get(j);
                if (soSanhMaPhongBan(a, b) > 0) {
                    temp.set(i, b);
                    temp.set(j, a);
                }
            }
        }
        xuat(temp);
    }

    public static void SortTheoThamNien() {
        ArrayList<NhanVien> temp = new ArrayList<>(dsnv);
        for (int i = 0; i < temp.size() - 1; i++) {
            for (int j = i + 1; j < temp.size(); j++) {
                NhanVien a = temp.get(i);
                NhanVien b = temp.get(j);
                if (soSanhThamNien(a, b) > 0) {
                    temp.set(i, b);
                    temp.set(j, a);
                }
            }
        }
        xuat(temp);
    }

    public static NhanVien TimQuanLyTheoViTri(ViTri vt) {
        for (NhanVien n : DanhSachNhanVien.getDsnv()) {
            if (n.getChucVu() == LoaiChucVu.QUAN_LY
                    && n.getMaPhongBan() != null
                    && n.getMaPhongBan().equalsIgnoreCase(vt.getPhongBan())) {
                return n;
            }
        }
        return null;
    }
    
    private static String ChuanHoa(String s) {                 
        if (s == null) {                                      
            return "";                                       
        }
        s = s.toLowerCase();                                  
        s = s.replace('đ', 'd');                             
        String phanTach = Normalizer.normalize(s, Normalizer.Form.NFD); 
        String ketQua = "";                                   
        for (int i = 0; i < phanTach.length(); i++) {         
            char c = phanTach.charAt(i);                      
            if (Character.getType(c) != Character.NON_SPACING_MARK) { 
                ketQua = ketQua + c;                          
            }
        }
        return ketQua.trim();                                 
    }

    private static ArrayList<NhanVien> TimTheoHoTen(String tuKhoa) {  
        ArrayList<NhanVien> ketQua = new ArrayList<>();       
        String khoa = ChuanHoa(tuKhoa);                       
        if (khoa.isEmpty()) {                                
            return ketQua;        }
        String[] cacTu = khoa.split(" ");                     
        for (NhanVien nv : DanhSachNhanVien.getDsnv()) {      
            if (nv.isDaNghiViec()) {                        
                continue;                                              
}
            String ten = ChuanHoa(nv.getHoTen());             
            boolean khop = true;                              
            for (int i = 0; i < cacTu.length; i++) {          
                if (!ten.contains(cacTu[i])) {                 
                    khop = false;                            
                    break;                                   
                }
            }
            if (khop) {                                       
                ketQua.add(nv);                              
            }
        }
        return ketQua;                                       
    }

        public static void ChayTimKiem(Scanner sc) {             
        while (true) {                                        
            System.out.println("Nhập họ tên cần tìm (không cần dấu, nhập 'thoát' để quay lại):"); 
            String nhap = sc.nextLine().trim();              
            if (nhap.equalsIgnoreCase("thoát")) {             
                return;                                       
            }
            if (nhap.isEmpty()) {                            
                System.out.println("Từ khóa không được để trống"); 
                continue;                                    
            }
            ArrayList<NhanVien> ketQua = TimTheoHoTen(nhap);  
            if (ketQua.isEmpty()) {                          
                System.out.println("Không tìm thấy nhân viên nào gần đúng với: " + nhap); 
            } else {                                        
                System.out.println("Tìm thấy " + ketQua.size() + " nhân viên:");
                DanhSachNhanVien.xuat(ketQua);             
            }
        }
    }

}
