package oop.entity.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.DanhSach.DanhSachNhanVien;
import oop.entity.Enum.*;
import oop.entity.Object.*;
import oop.entity.Object.Boss;
import oop.entity.Object.NhanVien;

public class KiemTraLoi {

    private static final int TUOI_TOI_THIEU = 15;
    private static final int TUOI_TOI_DA = 70;

    // ===================== CÁC HÀM KIỂM TRA (sai thì ném Exception) =====================

    public static void KiemTraDinhDangSDT(String SDT) throws Exception {
        if(SDT == null || SDT.trim().isEmpty()){
            throw new Exception("Số điện thoại không được để trống");
        }
        if(!SDT.matches("\\d+")){
            throw new Exception("Số điện thoại chỉ được chứa chữ số");
        }
        if(SDT.length() != 10){
            throw new Exception("Số điện thoại phải đủ 10 số (đang có " + SDT.length() + " số)");
        }
        if(SDT.charAt(0) != '0'){
            throw new Exception("Số điện thoại phải bắt đầu bằng số 0");
        }
    }

    public static void KiemTraCCCD(String CCCD) throws Exception {
        if(CCCD == null || CCCD.trim().isEmpty()){
            throw new Exception("CCCD không được để trống");
        }
        if(!CCCD.matches("\\d+")){
            throw new Exception("CCCD chỉ được chứa chữ số");
        }
        if(CCCD.length() != 12){
            throw new Exception("CCCD phải đủ 12 số (đang có " + CCCD.length() + " số)");
        }
    }

    // Kiểm tra CCCD chưa có nhân viên nào dùng
    public static void KiemTraCCCDTrung(String CCCD) throws Exception {
        for(NhanVien nv : DanhSachNhanVien.getDsnv()){
            if(nv.getCCCD() != null && nv.getCCCD().equals(CCCD)){
                throw new Exception("CCCD này đã thuộc về nhân viên " + nv.getMaNV());
            }
        }
    }

    public static void KiemTraHoTen(String HoTen) throws Exception {
        if(HoTen == null || HoTen.trim().isEmpty()){
            throw new Exception("Họ tên không được để trống");
        }
        // \p{L} là mọi chữ cái (kể cả có dấu tiếng Việt)
        if(!HoTen.trim().matches("\\p{L}+( \\p{L}+)*")){
            throw new Exception("Họ tên chỉ được chứa chữ cái và dấu cách");
        }
    }

    public static void KiemTraMaNV(String MaNV) throws Exception {
        if(MaNV == null || !MaNV.trim().matches("(?i)NV\\d{3}")){
            throw new Exception("Mã nhân viên phải có dạng NV + 3 chữ số (ví dụ NV001)");
        }
        if(DanhSachNhanVien.timKiem(MaNV.trim()) == null){
            throw new Exception("Không tìm thấy nhân viên mã " + MaNV);
        }
    }

    // Mã quản lý phụ trách: là BOSS hoặc mã của một nhân viên đã có
    public static void KiemTraMaQuanLy(String Ma) throws Exception {
        if(Ma == null || Ma.trim().isEmpty()){
            throw new Exception("Mã quản lý không được để trống");
        }
        if(Ma.trim().equalsIgnoreCase(Boss.MA)){
            return;
        }
        KiemTraMaNV(Ma);
    }

    public static void KiemTraMatKhau(String MatKhau) throws Exception {
        if(MatKhau == null || MatKhau.length() < 8){
            throw new Exception("Mật khẩu phải có ít nhất 8 ký tự");
        }
        if(MatKhau.contains(" ")){
            throw new Exception("Mật khẩu không được chứa dấu cách");
        }
    }

    // Đổi chuỗi thành ngày, sai định dạng hoặc ngày không có thật (30/02) thì ném lỗi
    public static LocalDate KiemTraNgay(String input, String pattern) throws Exception {
        if(input == null || input.trim().isEmpty()){
            throw new Exception("Ngày không được để trống");
        }
        try{
            // STRICT + uuuu để ngày không có thật (30/02) bị từ chối thay vì tự đổi thành 29/02
            DateTimeFormatter f = DateTimeFormatter.ofPattern(pattern.replace("yyyy", "uuuu")).withResolverStyle(ResolverStyle.STRICT);
            return LocalDate.parse(input.trim(), f);
        }catch(DateTimeParseException e){
            throw new Exception("Ngày không hợp lệ, phải đúng dạng " + pattern);
        }
    }

    public static LocalDate KiemTraNgaySinh(String input, String pattern) throws Exception {
        LocalDate ngaySinh = KiemTraNgay(input, pattern);
        LocalDate homNay = HeThong.date;
        if(ngaySinh.isAfter(homNay)){
            throw new Exception("Ngày sinh không được ở tương lai");
        }
        int tuoi = homNay.getYear() - ngaySinh.getYear();
        if(tuoi < TUOI_TOI_THIEU){
            throw new Exception("Chưa đủ " + TUOI_TOI_THIEU + " tuổi để làm việc");
        }
        if(tuoi > TUOI_TOI_DA){
            throw new Exception("Tuổi vượt quá " + TUOI_TOI_DA + ", vui lòng kiểm tra lại");
        }
        return ngaySinh;
    }

    public static int KiemTraSoNguyen(String input, int min, int max) throws Exception {
        int so;
        try{
            so = Integer.parseInt(input.trim());
        }catch(NumberFormatException e){
            throw new Exception("Đây không phải số nguyên");
        }
        if(so < min || so > max){
            throw new Exception("Vui lòng nhập số từ " + min + " đến " + max);
        }
        return so;
    }

    public static double KiemTraSoThuc(String input, double min) throws Exception {
        double so;
        try{
            so = Double.parseDouble(input.trim());
        }catch(NumberFormatException e){
            throw new Exception("Đây không phải số hợp lệ");
        }
        if(so < min){
            throw new Exception("Giá trị phải từ " + min + " trở lên");
        }
        return so;
    }

    // ===================== CÁC HÀM NHẬP (lặp đến khi đúng) =====================

    public static String NhapSDT(Scanner sc){
        while(true){
            System.out.println("Nhập số điện thoại");
            String SDT = sc.nextLine().trim();
            try{
                KiemTraDinhDangSDT(SDT);
                return SDT;
            }catch(Exception e){
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    public static String NhapCCCD(Scanner sc){
        while(true){
            System.out.println("Nhập CCCD");
            String CCCD = sc.nextLine().trim();
            try{
                KiemTraCCCD(CCCD);
                KiemTraCCCDTrung(CCCD);
                return CCCD;
            }catch(Exception e){
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    public static String NhapHoTen(Scanner sc){
        while(true){
            System.out.println("Nhập họ tên");
            String HoTen = sc.nextLine().trim();
            try{
                KiemTraHoTen(HoTen);
                return HoTen;
            }catch(Exception e){
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    public static LocalDate NhapNgaySinh(Scanner sc){
        while(true){
            System.out.println("Nhập ngày sinh (dd/MM/yyyy)");
            try{
                return KiemTraNgaySinh(sc.nextLine(), "dd/MM/yyyy");
            }catch(Exception e){
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    public static String NhapMaQuanLy(Scanner sc){
        while(true){
            System.out.println("Nhập mã quản lý phụ trách (BOSS hoặc mã nhân viên)");
            String Ma = sc.nextLine().trim();
            try{
                KiemTraMaQuanLy(Ma);
                return Ma.toUpperCase();
            }catch(Exception e){
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    // ===== Nhập liệu và kiểm tra dùng chung (chuyển từ lớp cũ) =====
// Đọc số nguyên trong [min, max], nhập sai thì hỏi lại (thay cho nextInt)
    public static int NhapSoNguyen(Scanner sc, String thongBao, int min, int max){
    while(true){
        System.out.println(thongBao);
        try{
            int v = Integer.parseInt(sc.nextLine().trim());
            if(v >= min && v <= max) return v;
        }catch(NumberFormatException e){ }
        System.out.println("Vui lòng nhập số từ " + min + " đến " + max);
    }
}

    public static LocalDate isValidDate(String dateStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate Date = LocalDate.parse(dateStr, formatter);
            return Date; // Hợp lệ
        } catch (DateTimeParseException e) {
            System.out.println("Khong hop le");
            return null; // Không hợp lệ hoặc sai định dạng
        }
    }

    public static LocalDate isValidDate(String dateStr, String pattern,LocalDate Moc) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate Date = LocalDate.parse(dateStr, formatter);
            if(Date.isBefore(Moc)){
                return null;
            }
            return Date;          // Hợp lệ
        } catch (DateTimeParseException e) {
            System.out.println("Khong hop le");
            return null; // Không hợp lệ hoặc sai định dạng
        }
    }

    public static boolean KiemTraIsFullTime(NhanVien nv){
        if(nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")){
            return true;
        }
        return false;
    }

    public static boolean HuyTacVu(Scanner sc){
        System.out.println("Chắc chắn chưa? Hay muốn hoàn tác, thoát khỏi quy trình\n Nhập 'thoát' để thoát hoặc nhập bất kỳ để tiếp tục");
        String input = sc.nextLine().trim();
        if(input.equalsIgnoreCase("thoát")){
            System.out.println("Đã ngắt chương trình");
        return true;
    }
    return false;
    }

    public static LocalDate NhapNgay(Scanner sc, String thongBao) {
    while (true) {
        System.out.println(thongBao + " (dd/MM/yyyy)");
        LocalDate d = isValidDate(sc.nextLine().trim(), "dd/MM/yyyy");
        if (d != null) return d;
        System.out.println("Ngày không hợp lệ, vui lòng nhập lại");
    }
}

// Đọc số thực >= 0, nhập sai thì hỏi lại
    public static double NhapSo(Scanner sc, String thongBao) {
    while (true) {
        System.out.println(thongBao);
        try {
            double v = Double.parseDouble(sc.nextLine().trim());
            if (v >= 0) return v;
        } catch (NumberFormatException e) { }
        System.out.println("Vui lòng nhập số hợp lệ (>= 0)");
    }
}

// Nhập ca làm, lặp đến khi hợp lệ với loại nhân viên
    public static String NhapCaLam(Scanner sc, boolean fullTime) {
    while (true) {
        System.out.println("Nhập ca làm");
        String ca = sc.nextLine().trim();
        boolean hopLe = fullTime ? LoaiCaLamFullTime.NhanDien(ca) != null
                                 : LoaiCaLamPartTime.NhanDien(ca) != null;
        if (hopLe) return ca;
        System.out.println("Ca làm không hợp lệ, vui lòng nhập lại");
    }
}

// Nhập mã vị trí, lặp đến khi tồn tại
    public static ViTri NhapMaViTri(Scanner sc) {
    while (true) {
        System.out.println("Nhập mã vị trí (ví dụ VT01)");
        ViTri vt = ViTri.timKiem(sc.nextLine().trim());
        if (vt != null) return vt;
        System.out.println("Mã vị trí không tồn tại, vui lòng nhập lại");
    }
}

    public static boolean KiemTraMaViTriTonTai(String MaViTri){
        if(MaViTri == null){
            return false;
        }
        for(ViTri vt : ViTri.values()){
            if(MaViTri.equalsIgnoreCase(vt.getMaViTri())){
                return true;
            }
        }
        return false;
        }

    public static String NhapMaNV(Scanner sc){
            String input = sc.nextLine();//////////////////////
            if(input==null){
                System.out.println("không hợp lệ");
                return null;
            }
            NhanVien nv = DanhSachNhanVien.timKiem(input);
            if(nv==null){
                System.out.println("Không tìm thấy nhân viên");
                return null;
            }
            return input;
        }
}
