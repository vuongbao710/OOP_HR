
package oop.entity.Service;

import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
public class TaoDuLieuMau {
    
    public static void TaoDanhSachNhanVien(DanhSachHopDong dsHopDong){
        int SoDong=0;
        try(BufferedReader read = new BufferedReader(new FileReader("NhanVien.txt"))){
            String line;
            while((line = read.readLine())!=null){
                String[] parts = line.split(",");
                if(parts.length != 10){
                    System.out.println("Dòng "+SoDong+" không đủ dữ liệu");
                    continue;
                }
                String MaNV = parts[0].trim();
                String CCCD = parts[1].trim();
                String HoTen = parts[2].trim();
                String SDT = parts[3].trim();
                String MaQuanLyPhuTrach = parts[4].trim();
                String MaPhong = parts[5].trim();
               LocalDate BirthDate = LocalDate.parse(parts[6].trim());
               String ChucVu = parts[7].trim();
               String MaViTri = parts[8].trim();
               if(!ThaoTac.KiemTraMaViTriTonTai(MaViTri)){
                   System.out.println("mã vị trí nhập vào không tồn tại ở dòng " +SoDong+1);
                   continue;
               }
               String CaLam = parts[9].trim();
               
               HopDong HD = dsHopDong.timKiemTheoMaNV(MaNV);
               if(HD.getLoaiHopDong().equals(LOAI_HOP_DONG.PART_TIME)){
                   NhanVien nv = new NhanVienPartTime(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, MaPhong, BirthDate, ChucVu, CaLam);
                   nv.setHopDong(HD);
                   DanhSachNhanVien.them(nv);
               }
               else{
                   NhanVien nv = new NhanVienFullTime(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, MaPhong, BirthDate, ChucVu, CaLam);
                   nv.setHopDong(HD);
                   DanhSachNhanVien.them(nv);
               }
               
               SoDong++;
            }
            
        }catch (IOException e){
            System.err.println("Lỗi phần dữ liệu mẫu danh sách nhân viên ở dòng "+ (SoDong+1) + e.getMessage());
        }
    }
    private static TaiKhoan TaoTaiKhoanHoiDongQuanTri(){
        String MaTK = "TK000";
        String TenDangNhap = MaTK;
        String MatKhau = "0918554647tuki@A";
        LoaiVaiTro Role = LoaiVaiTro.BOSS;
        TaiKhoan tk = new TaiKhoan(MaTK, TenDangNhap, MatKhau, Role);
        return tk;
    }
    
    public static void TaoDanhSachTaiKhoan(){
        int SoDong = 0;
        try(BufferedReader read = new BufferedReader(new FileReader("NhanVien.txt"))){
            String line;
            while((line = read.readLine())!=null){
                String[] parts = line.split(",");
                if(parts.length != 2){
                    System.out.println("Dòng "+SoDong+" không đủ hoặc thừa dữ liệu cho phép");
                    continue;
                }
                String MatKhau = parts[0].trim();
                String MaNV = parts[1].trim();
                NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
                String TenDangNhap = MaNV;
                String MaVitri = nv.getMaViTri();
                LoaiVaiTro role = ViTri.XacDinhQuyen(MaVitri);
                
                TaiKhoan tk = new TaiKhoan(TenDangNhap, MatKhau, role, MaNV);
                DanhSachTaiKhoan.them(tk);
            }
            
        }catch(IOException e){
            System.out.println("Lỗi tạo danh sách tài khoản ");
        }
        DanhSachTaiKhoan.them(TaoTaiKhoanHoiDongQuanTri());
        
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
    
    public static DanhSachHopDong TaoDanhSachHopDong(){
        DanhSachHopDong dsHopDong = new DanhSachHopDong(SoLuongTapDuLieu("HopDong.txt"));
        try(BufferedReader read = new BufferedReader(new FileReader("HopDong.txt"))){
            String line;
            int SoThuTuDong = 0;
            while((line = read.readLine())!=null){
                String[] temp = line.split(",");
                SoThuTuDong++;
                if(temp.length < 12){
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
        }catch(IOException e){
            System.err.println("Lỗi đọc file: " + e.getMessage());
        }
        return dsHopDong;
    }
    
    public static int SoLuongTapDuLieu(String file){
        int SoLuong=0;
        try(BufferedReader read = new BufferedReader(new FileReader("HopDong.txt"))){
            String line;
            
            while((line = read.readLine())!=null){
                SoLuong++;
            }
        
        }catch(IOException e){
            System.out.println("Lỗiiiii" + e.getMessage());
        }
        
        return SoLuong;
        
    }
    
    
}
