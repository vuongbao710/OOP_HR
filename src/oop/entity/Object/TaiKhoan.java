package oop.entity.Object;
import java.util.Scanner;
import oop.entity.DanhSach.DanhSachNhanVien;
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
    private int SoLanSaiMatKhau=5;

    public TaiKhoan(String TenDangNhap, String MatKhau, String Role, String MaNV) {
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.Role = LoaiVaiTro.NhanDien(Role);
        this.MaNV = MaNV;
        this.TrangThai = TrangThaiHoatDong.HOAT_DONG;
    }

    

    public TaiKhoan(String MaTaiKhoan, String TenDangNhap, String MatKhau, String Role, String MaNV) {
        this.MaTaiKhoan = MaTaiKhoan;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.Role = LoaiVaiTro.NhanDien(Role);
        this.MaNV = MaNV;
        this.TrangThai = TrangThaiHoatDong.HOAT_DONG;
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

    public int getSoLanSaiMatKhau() {
        return SoLanSaiMatKhau;
    }

    public void setSoLanSaiMatKhau(int SoLanSaiMatKhau) {
        this.SoLanSaiMatKhau = SoLanSaiMatKhau;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "MaTaiKhoan=" + MaTaiKhoan 
                + ", TenDangNhap=" + TenDangNhap + ", MatKhau=" 
                + MatKhau + ", Role=" + Role + ", MaNV=" + MaNV 
                + ", TrangThai=" + TrangThai + ", SoLanSaiMatKhau=" + SoLanSaiMatKhau + '}';
    }
    
    public boolean KiemTraMatKhau(String input){
        return input.equalsIgnoreCase(this.MatKhau);
    }
    public boolean NhapMatKhau(Scanner sc){
        while(this.SoLanSaiMatKhau>0){
            if(KiemTraMatKhau(sc.nextLine())){
                System.out.println("Đăng nhập thành công!");
                setSoLanSaiMatKhau(5);
                return true;
            }
            this.SoLanSaiMatKhau--;
            System.out.println("Sai mat khau, vui long nhap lai, con "+SoLanSaiMatKhau+" lan thu");
            
        }
        if(SoLanSaiMatKhau==0){
            System.out.println("Ban da het co hoi nhap mat khau, chung toi se khoa tai khoan nay");
            setTrangThai(TrangThaiHoatDong.BI_KHOA);
        }
        return false;
    }
    public void QuenMatKhau(){
        
    }
    public boolean KiemTraTrangThaiHoatDong(){
        if(getTrangThai()==TrangThaiHoatDong.HOAT_DONG){
            return true;
        }
        return false;
    }
    
    public NhanVien timKiemNhanVien(DanhSachNhanVien dsnv){
        for(NhanVien nv : dsnv.getDsnv()){
            if(nv.getMaNV().equalsIgnoreCase(this.MaNV)){
                return nv;
            }
        }
        return null;
    }
    
}