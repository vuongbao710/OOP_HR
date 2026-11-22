package oop.entity.Object;

import java.util.Scanner;
import oop.entity.DanhSach.DanhSachNhanVien;
import oop.entity.DanhSach.DanhSachTaiKhoan;
import oop.entity.Enum.LoaiVaiTro;

public class TaiKhoan {

    public enum TrangThaiHoatDong{
        BI_KHOA,
        HOAT_DONG;
    }

    private String MaTaiKhoan;
    private String TenDangNhap;
    private String MatKhau;
    private LoaiVaiTro Role;
    private String MaNV;
    private TrangThaiHoatDong TrangThai;
    private boolean CanDoiMatKhau;
    public TaiKhoan(String TenDangNhap, String MatKhau, LoaiVaiTro Role, String MaNV) {
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.Role = Role;
        this.MaNV = MaNV;
        this.TrangThai = TrangThaiHoatDong.HOAT_DONG;
        this.CanDoiMatKhau = true;
    }

    public TaiKhoan(String MaTaiKhoan, String TenDangNhap, String MatKhau, LoaiVaiTro Role) {
        this.MaTaiKhoan = MaTaiKhoan;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.Role = Role;
        this.TrangThai = TrangThaiHoatDong.HOAT_DONG;
        this.CanDoiMatKhau = false;
    }

    public String getMaTaiKhoan() {
        return MaTaiKhoan;
    }

    public void setMaTaiKhoan(String MaTaiKhoan) {
        this.MaTaiKhoan = MaTaiKhoan;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public LoaiVaiTro getRole() {
        return Role;
    }

    public void setRole(LoaiVaiTro Role) {
        this.Role = Role;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public TrangThaiHoatDong getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(TrangThaiHoatDong TrangThai) {
        this.TrangThai = TrangThai;
    }

    public boolean isCanDoiMatKhau() {
        return CanDoiMatKhau;
    }

    public void setCanDoiMatKhau(boolean CanDoiMatKhau) {
        this.CanDoiMatKhau = CanDoiMatKhau;
    }
     @Override
    public String toString() {
        return "TaiKhoan{" + "MaTaiKhoan=" + MaTaiKhoan + ", TenDangNhap=" + TenDangNhap
                + ", Role=" + Role + ", MaNV=" + MaNV + ", TrangThai=" + TrangThai + '}';
    }
    
    public boolean KiemTraMatKhau(String input){
        return input != null && input.equals(this.MatKhau);
    }

    public boolean KiemTraTrangThaiHoatDong(){
        return TrangThai == TrangThaiHoatDong.HOAT_DONG;
    }
    
     public boolean DoiMatKhau(Scanner sc, boolean batBuoc){
        if(batBuoc){
            System.out.println("Đây là lần đăng nhập đầu tiên, bạn phải đổi mật khẩu mới");
        } else {
            System.out.println("Nhập mật khẩu hiện tại (nhập 'thoát' để hủy)");
            String cu = sc.nextLine();
            if(cu.equalsIgnoreCase("thoát")){
                return false;
            }
            if(!KiemTraMatKhau(cu)){
                System.out.println("Mật khẩu hiện tại không đúng");
                return false;
            }
        }
        while(true){
            System.out.println("Nhập mật khẩu mới (ít nhất 8 ký tự)" + (batBuoc ? "" : ", 'thoát' để hủy"));
            String moi = sc.nextLine();
            if(!batBuoc && moi.equalsIgnoreCase("thoát")){
                return false;
            }
            if(moi.length() < 8){
                System.out.println("Mật khẩu phải có ít nhất 8 ký tự");
                continue;
            }
            if(moi.equals(this.MatKhau)){
                System.out.println("Mật khẩu mới không được trùng mật khẩu cũ");
                continue;
            }
            System.out.println("Nhập lại mật khẩu mới");
            if(!moi.equals(sc.nextLine())){
                System.out.println("Hai lần nhập không khớp");
                continue;
            }
            this.MatKhau = moi;
            this.CanDoiMatKhau = false;
            System.out.println("Đổi mật khẩu thành công");
            return true;
        }
    }
     public NhanVien timKiemNhanVien(){
        for(NhanVien nv : DanhSachNhanVien.getDsnv()){
            if(nv.getMaNV().equalsIgnoreCase(this.MaNV)){
                return nv;
            }
        }
        return null;
    }
     
     public static TaiKhoan DangNhap(Scanner sc){
    while(true){
        System.out.println("\nNhập tên đăng nhập (nhập 'thoát' để tắt chương trình)");
        String ten = sc.nextLine().trim();
        if(ten.equalsIgnoreCase("thoát")){
            return null;
        }
        TaiKhoan tk = null;
        for(TaiKhoan t : DanhSachTaiKhoan.getDstk()){
            if(t.getTenDangNhap().equalsIgnoreCase(ten)){
                tk = t;
                break;
            }
        }
        if(tk == null){
            System.out.println("Không tìm thấy tài khoản tương ứng");
            continue;
        }
        if(!tk.KiemTraTrangThaiHoatDong()){
            System.out.println("Tài khoản của bạn đã bị khoá, không thể đăng nhập");
            continue;
        }
        System.out.println("Nhập mật khẩu");
        if(!tk.KiemTraMatKhau(sc.nextLine())){
            System.out.println("Sai mật khẩu");
            continue;
        }
        System.out.println("Đăng nhập thành công!");
        if(tk.isCanDoiMatKhau()){
            tk.DoiMatKhau(sc, true);
        }
        return tk;
    }
}
     

}