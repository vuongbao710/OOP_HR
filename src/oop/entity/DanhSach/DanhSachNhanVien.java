
package oop.entity.DanhSach;
import java.text.Collator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import oop.entity.Enum.LoaiChucVu;
import oop.entity.Object.NhanVien;
import oop.entity.Object.TaiKhoan;
import oop.entity.Object.TaiKhoan.TrangThaiHoatDong;
import oop.entity.Object.ViTri;

public class DanhSachNhanVien{
    private static ArrayList <NhanVien>dsnv = new ArrayList<>();
    private static int dem=0;
   private static final Collator collator = Collator.getInstance(Locale.forLanguageTag("vi"));
    public static void xuat(){
        System.out.println("========= Danh sach nhan vien ==========");
        for(NhanVien nv : dsnv){
            System.out.println(nv);
        }
        
    }
    
    public static void xuat(ArrayList<NhanVien> ds){
        System.out.println("========= Danh sach nhan vien ==========");
        for(NhanVien nv : ds){
            System.out.println(nv);
        }
        
    }
    
    
    public static NhanVien timKiem (String MaNV){
        for(NhanVien nv : dsnv){
            if(nv.getMaNV().equalsIgnoreCase(MaNV)){
                return nv;
            }
        }
        return null;
    }
    
    public static TaiKhoan timKiemTheoMaTK(String MaTK){
        for(TaiKhoan tk : DanhSachTaiKhoan.getDstk()){
            if(tk.getMaNV().equalsIgnoreCase(MaTK) && tk.getTrangThai() == TrangThaiHoatDong.HOAT_DONG){
                return tk;
            }
        }
        return null;
    }
    
    public static void xoa(NhanVien nv){
        
        dsnv.remove(nv);
    }
    public static void them(NhanVien nv){
        if(nv.getMaNV()==null){
        nv.setMaNV(taoMa());}
        else {
        try{
            int so = Integer.parseInt(nv.getMaNV().substring(2));
            if(so > dem) dem = so;
        }catch(Exception e){ }
    }
        dsnv.add(nv);
        if(nv.getTaiKhoan() != null){
        DanhSachTaiKhoan.them(nv.getTaiKhoan());
    }
    }
            
   
    public static String taoMa(){
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
    
    public static ArrayList<NhanVien> timKiemTheoMaQuanLyPhuTrach(String MaQuanLyPhuTrach){
        ArrayList<NhanVien> temp = new ArrayList<>();
        for(NhanVien nv : DanhSachNhanVien.getDsnv()){
            if(nv.getMaQuanLyPhuTrach().equalsIgnoreCase(MaQuanLyPhuTrach)){
                temp.add(nv);
            }
        }
        return temp;
    }
    
        public static ArrayList<NhanVien> timKiemTheoMaQuanLyPhuTrachVaFullTime(String MaQuanLyPhuTrach){
        ArrayList<NhanVien> temp = new ArrayList<>();
        for(NhanVien nv : DanhSachNhanVien.getDsnv()){
            if(nv.getMaQuanLyPhuTrach().equalsIgnoreCase(MaQuanLyPhuTrach) && nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")){
                temp.add(nv);
            }
        }
        return temp;
    }
        
        
        
        private static String layTen(NhanVien nv){
        String HoTen = nv.getHoTen().trim();
        int viTri = HoTen.lastIndexOf(' ');
        return HoTen.substring(viTri + 1);
    }

    // Lấy ngày bắt đầu hợp đồng, chưa có hợp đồng thì trả về null
    private static LocalDate layNgayBatDauHopDong(NhanVien nv){
        if(nv.getHopDong() == null){
            return null;
        }
        return nv.getHopDong().getNgayBatDauHopDong();
    }

    // So sánh Mã NV, dùng làm tiêu chí phụ khi trùng
    private static int soSanhMaNV(NhanVien a, NhanVien b){
        return a.getMaNV().compareToIgnoreCase(b.getMaNV());
    }

    // Trả về > 0 nếu a phải đứng sau b
    private static int soSanhHoTen(NhanVien a, NhanVien b){
        // Xếp theo tên (từ cuối) trước
        int kq = collator.compare(layTen(a), layTen(b));
        if(kq != 0){
            return kq;
        }
        // Trùng tên thì xếp theo họ tên đầy đủ
        kq = collator.compare(a.getHoTen(), b.getHoTen());
        if(kq != 0){
            return kq;
        }
        // Vẫn trùng thì xếp theo Mã NV
        return soSanhMaNV(a, b);
    }

    private static int soSanhMaPhongBan(NhanVien a, NhanVien b){
        int kq = a.getMaPhongBan().compareToIgnoreCase(b.getMaPhongBan());
        if(kq != 0){
            return kq;
        }
        return soSanhMaNV(a, b);
    }

    // Thâm niên cao nhất (ngày bắt đầu hợp đồng sớm nhất) đứng trước
    private static int soSanhThamNien(NhanVien a, NhanVien b){
        LocalDate ngayA = layNgayBatDauHopDong(a);
        LocalDate ngayB = layNgayBatDauHopDong(b);

        // Nhân viên chưa có hợp đồng thì xếp cuối
        if(ngayA == null && ngayB != null){
            return 1;
        }
        if(ngayA != null && ngayB == null){
            return -1;
        }
        if(ngayA != null && ngayB != null){
            int kq = ngayA.compareTo(ngayB);
            if(kq != 0){
                return kq;
            }
        }
        return soSanhMaNV(a, b);
    }

    // Sort trên bản sao rồi in ra, không làm thay đổi dsnv
    public static void SortTheoHoTen(){
        ArrayList<NhanVien> temp = new ArrayList<>(dsnv);
        for(int i = 0; i < temp.size() - 1; i++){
            for(int j = i + 1; j < temp.size(); j++){
                NhanVien a = temp.get(i);
                NhanVien b = temp.get(j);
                if(soSanhHoTen(a, b) > 0){
                    temp.set(i, b);
                    temp.set(j, a);
                }
            }
        }
        xuat(temp);
    }

    public static void SortTheoMaPhongBan(){
        ArrayList<NhanVien> temp = new ArrayList<>(dsnv);
        for(int i = 0; i < temp.size() - 1; i++){
            for(int j = i + 1; j < temp.size(); j++){
                NhanVien a = temp.get(i);
                NhanVien b = temp.get(j);
                if(soSanhMaPhongBan(a, b) > 0){
                    temp.set(i, b);
                    temp.set(j, a);
                }
            }
        }
        xuat(temp);
    }

    public static void SortTheoThamNien(){
        ArrayList<NhanVien> temp = new ArrayList<>(dsnv);
        for(int i = 0; i < temp.size() - 1; i++){
            for(int j = i + 1; j < temp.size(); j++){
                NhanVien a = temp.get(i);
                NhanVien b = temp.get(j);
                if(soSanhThamNien(a, b) > 0){
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
    
}
    

