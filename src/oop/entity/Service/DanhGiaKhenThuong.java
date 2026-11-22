package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

// Đánh giá và khen thưởng: thưởng sáng kiến, đánh giá KPI
public class DanhGiaKhenThuong {
//quản lý
    
    public static void ThuongSangKien(Scanner sc, LocalDate date, LocalTime time, String MaQuanLy){
        if(KiemTraLoi.HuyTacVu(sc)) return;
        NhanVien nv;
        while(true){
        System.out.println("Nhân viên bạn muốn thưởng sáng kiến có mã nhân viên là :");
        nv = DanhSachNhanVien.timKiem(sc.nextLine().trim());
        if(nv != null) break;
        System.out.println("Không tìm thấy nhân viên tương ứng");
    }
        double tien;
        while(true){
        tien = KiemTraLoi.NhapSo(sc, "Số tiền thưởng sáng kiến là ?\n Tối thiểu 100.000vnd - Tối đa 500.000vnd");
        if(tien >= 100000 && tien <= 500000) break;
        System.out.println("Số tiền phải trong khoảng 100.000 đến 500.000 vnd");
    }
        nv.getDsktkl().them(new KhenThuong(LoaiKhenThuong.THUONG_SANG_KIEN, date, tien, MaQuanLy, nv.getMaNV()));
        HomThu.GuiThongBao(MaQuanLy, nv.getMaNV(), date, time, "thưởng sáng kiến " + tien + " vnd");
        NhatKyHoatDong.them("Quản lý mã " + MaQuanLy + " thưởng sáng kiến có giá trị " + tien + " vào " + time
            + " ngày " + date + " cho nhân viên mã " + nv.getMaNV());
}

    public static void DanhGiaKPI(Scanner sc, LocalDate date, LocalTime time, String MaQuanLy, String MaNV){
        
    if(KiemTraLoi.HuyTacVu(sc)){
        return;
    }
            NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
                    while(true){
                        System.out.println("Đánh giá điểm KPI của nhân viên " + nv.getMaNV()+" là? ");
                        int DiemKPI = sc.nextInt();
                        sc.nextLine();
                    if(DiemKPI>=0 && DiemKPI<=100){
                    LocalDate ngayTinhKPI = NgayNghiepVu.ngayKetThucQuy(date.minusDays(3));
                    int thang = ngayTinhKPI.getMonthValue();
                    int nam = ngayTinhKPI.getYear();
                    DanhGiaKPI kpi = new DanhGiaKPI(DiemKPI, thang , nam, MaQuanLy, nv.getMaNV());
                    NhanVienFullTime nvFull = (NhanVienFullTime) nv;
                    
                    nvFull.getDskpi().them(kpi);
                    HomThu.GuiThongBao(MaQuanLy, nv.getMaNV(), date, time, "đã có đánh giá kpi có điểm số là " + DiemKPI +" tháng "+ thang + " nam " +nam).setRequest(LoaiRequest.THONG_BAO);
                    NhatKyHoatDong.them("Quản lý mã "+ MaQuanLy + "đã có đánh giá kpi có điểm số là " + DiemKPI + time + " ngày " + date +
                "cho nhân viên mã " + MaNV);
                    break; 
                    }
                    System.out.println("Điểm KPI phải dưới 100 trên 0 ");
                    }
                
             
    }

    public static void DanhGiaKPINhanVienPhuTrach(Scanner sc, LocalDate date, LocalTime time, String MaNVDanhGia){
    ArrayList<NhanVien> ds = DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrachVaFullTime(MaNVDanhGia);
    if(ds.isEmpty()){ // code cũ kiểm tra == null nên không bao giờ chạy
        System.out.println("Danh sách nhân viên full-time cấp dưới trống");
        return;
    }
    for(NhanVien nv : ds){
        DanhGiaKPI(sc, date, time, MaNVDanhGia, nv.getMaNV());
        if(XuLyRequest.daHuyTacVu) return;
    }
}
}
