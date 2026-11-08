
package oop.entity.Service;

import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class TaoDuLieuMau {
    public static DanhSachNhanVien TaoDanhSachNhanVien(){
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        
        try(BufferedReader read = new BufferedReader(new FileReader("NhanVien.txt"))){
            String line;
            while((line = read.readLine())!=null){
                String[] parts = line.split(",");
                String MaNV = parts[0];
                String CCCD = parts[1];
                String HoTen = parts[2];
                String SDT = parts[3];
                String MaQuanLyPhuTrach = parts[4];
                String MaPhong = parts[5];
               LocalDate BirthDate = LocalDate.parse(parts[6]);
               LocalDate NgayVaoLam = LocalDate.parse(parts[7]);
               String ChucVu = parts[8];
               String HinhThucDiLam = parts[9];
               String CaLam = parts[10];
               
               NhanVien nv = new NhanVien(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, MaPhong, BirthDate, NgayVaoLam, ChucVu, HinhThucDiLam)
            }
            
        }catch (IOException e){
            
        }
        
        
        return dsnv;
    }
    
    public static DanhSachTaiKhoan TaoDanhSachTaiKhoan(DanhSachNhanVien dsnv){
        DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
        for(NhanVien nv : dsnv.getDsnv()){
            String TenDangNhap = nv.getMaNV();
            String MatKhau = nv.getSDT();
            String MaTK = DanhSachTaiKhoan.taoMa();
            String VaiTro = nv.XacDinhVaiTro();
            TaiKhoan tk = new TaiKhoan(MaTK,TenDangNhap,MatKhau,VaiTro,TenDangNhap);
            dstk.them(tk);
        }
        return dstk;
    }
    public static DanhSachPhongBan TaoDanhSachPhongBan(){
        DanhSachPhongBan dspb = new DanhSachPhongBan();
        for(LoaiPhongBan pb : LoaiPhongBan.values()){
            String MaPhong = DanhSachPhongBan.taoMa();
            String TenPhong = pb.getMoTa();
            String MaTruongPhong = null;
            PhongBan p = new PhongBan(MaPhong,TenPhong,MaTruongPhong);
            
            dspb.them(p);
        }
        return dspb;
    }
    
    
}
