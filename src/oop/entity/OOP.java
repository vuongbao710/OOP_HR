
package oop.entity;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Object.*;
import oop.entity.Service.TaoDuLieuMau;
import oop.entity.Service.ThaoTac;
import oop.entity.Enum.*;
public class OOP {

    public static void main(String[] args) {
        
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        
        
        DanhSachPhongBan dspb = TaoDuLieuMau.TaoDanhSachPhongBan();
        
        DanhSachHopDong dsHopDong = TaoDuLieuMau.TaoDanhSachHopDong();
        dsHopDong.xuat();
        
        TaoDuLieuMau.TaoDanhSachNhanVien(dsHopDong);
        TaoDuLieuMau.TaoDanhSachTaiKhoan();
        DanhSachNhanVien.xuat();
        Scanner sc = new Scanner(System.in);
        while(true){
        int choice=-1;
        TaiKhoan tk = ThaoTac.DangNhap(sc);
            
        while(choice!=0){
        NhanVien nv = tk.timKiemNhanVien();
        DanhSachThongBao dsThongBao = nv.getDsThongBao();
        String MaNV = nv.getMaNV();
        
        //ThaoTac.checkInbox(dsThongBao);
        
        //ThaoTac.ChamCong(LocalTime.now(),LocalDate.now(),nv);      reason
        //nv.getBcc().xuat();
        //nv.getDsktkl().xuat();
        
        /*
        ThaoTac.XinNghiPhep(LocalDate.now(), LocalTime.now(), nv, sc, dsnv);
        nv.getDsDon().xuat();*/
        
        ThaoTac.checkInbox(MaNV, sc, LocalDate.now(), LocalTime.now(), dsThongBao);
        System.out.println("Role hien tai: " + tk.getRole());
        ThaoTac.PhanQuyen(tk, sc);
        
        System.out.println("muốn đăng xuất chưa");
        choice = sc.nextInt();
        sc.nextLine();
            }
        }
    }
    
}
