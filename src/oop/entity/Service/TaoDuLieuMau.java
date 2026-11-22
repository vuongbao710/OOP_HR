
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
    int SoDong = 0;
    try(BufferedReader read = new BufferedReader(new FileReader("NhanVien.txt"))){
        String line;
        while((line = read.readLine()) != null){
            SoDong++;
            if(line.trim().isEmpty()){
                continue;
            }
            String[] parts = line.split(",");
            if(parts.length != 8){
                System.out.println("Dòng " + SoDong + " không đủ dữ liệu");
                continue;
            }
            try{
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

                if(!KiemTraLoi.KiemTraMaViTriTonTai(MaViTri)){
                    throw new Exception("Mã vị trí " + MaViTri + " không tồn tại");
                }
                HopDong HD = DanhSachHopDong.timKiemTheoMaNV(MaNV);
                if(HD == null){
                    throw new Exception("Nhân viên " + MaNV + " chưa có hợp đồng");
                }

                NhanVien nv;
                if(HD.getLoaiHopDong().equals(LOAI_HOP_DONG.PART_TIME)){
                    nv = new NhanVienPartTime(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, BirthDate, CaLam);
                }
                else{
                    nv = new NhanVienFullTime(MaNV, CCCD, HoTen, SDT, MaQuanLyPhuTrach, BirthDate, CaLam);
                }
                nv.setHopDong(HD);
                DanhSachNhanVien.them(nv);
            }catch(Exception e){
                System.out.println("Dòng " + SoDong + " bị lỗi: " + e.getMessage());
            }
        }
    }catch (IOException e){
        System.err.println("Lỗi đọc file NhanVien.txt ở dòng " + (SoDong + 1) + ": " + e.getMessage());
    }
}
    public static void TaoTaiKhoanBoss(){
    TaiKhoan tk = new TaiKhoan("TK000", "TK000", "0918554647tuki@A", LoaiVaiTro.BOSS);
    tk.setMaNV(Boss.MA);
    Boss.setTk(tk);
    DanhSachTaiKhoan.them(tk);
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
