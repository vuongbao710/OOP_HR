
package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import oop.entity.Object.NhanVien;

public class PhanQuyen {
    
    public static void NHAN_VIEN(Scanner sc, NhanVien nv){
        int input=-1;
        while(input!=0){
        System.out.println("0. Đăng xuất");
        System.out.println("1. Chấm công");
        System.out.println("2. Gửi đơn xin nghỉ phép");
        System.out.println("3. Xem tình trạng bảng chấm công");
        System.out.println("4. Xem phiếu lương");
        System.out.println("5. Xem thông tin cá nhân");
        System.out.println("6. Đăng ký làm Over time");
        input = sc.nextInt();
        sc.nextLine();
        switch(input){
            case 0:
                break;
            case 1:
                ThaoTac.ChamCong(HeThong.time, HeThong.date, nv);
                break;
            case 2:
                ThaoTac.XinNghiPhep(HeThong.date, HeThong.time, nv, sc);
                break;
            case 3:
                nv.getBcc().xuat();
                break;
                
            default:
                System.out.println("Nằm ngoài phạm vi");
                break;
                
        }
        }
    }
    
    public static void QUAN_LY(Scanner sc, NhanVien nv){
        int input=-1;
        while(input!=0){
        System.out.println("0. Đăng xuất");
        System.out.println("1. Chấm công");
        System.out.println("2. Gửi đơn xin nghỉ phép");
        System.out.println("3. Xem tình trạng bảng chấm công");
        System.out.println("4. Xem phiếu lương");
        System.out.println("5. Xem thông tin cá nhân");
        System.out.println("6. Đăng ký làm Over time");
        System.out.println("7. Xử lý request");
        System.out.println("8. Nộp đợt tuyển dụng");
        System.out.println("9. Xem danh sách nhân viên cấp dưới");
        System.out.println("10. Thưởng sáng kiến");
        input = sc.nextInt();
        
        switch(input){
            case 0:
                break;
            case 1:
                ThaoTac.ChamCong(HeThong.time, HeThong.date, nv);
                break;
            case 2:
                ThaoTac.XinNghiPhep(HeThong.date, HeThong.time, nv, sc);
                break;
            case 3:
                nv.getBcc().xuat();/////////////
                break;
            case 4:
                nv.getDspl();//////////////// viết thêm trong thao tác
                break;
            case 5:
                System.out.println(nv);
                break;
            case 6:
                ThaoTac.DangKyLamOT(nv, HeThong.date, HeThong.time, sc);
                break;
            case 7:
                ThaoTac.checkChuaGiaiQuyet(nv.getMaNV(), sc);
                break;
            case 8:
                ThaoTac.DangTaiTuyenDung(sc, nv.getMaNV(), nv.getMaQuanLyPhuTrach(), HeThong.date, HeThong.time);////////
                break;
            case 9:
                
            case 10:
                ThaoTac.ThuongSangKien(sc, HeThong.date, HeThong.time, nv.getMaNV());
                break;
            default:
                System.out.println("Nằm ngoài phạm vi");
                break;
                
        }
        }
    }
    
    public static void ADMIN(Scanner sc, String MaADMIN){
        int input =-1;
        LocalDate NgayKetThuc;
        while(input != 0){
        System.out.println(HeThong.date + " " + HeThong.time);
        System.out.println("0. Đăng xuất");
        System.out.println("1. Giả lập hệ thống");
        System.out.println("2. Duyệt request");
        System.out.println("3. Xem nhật ký hoạt động");
        input = sc.nextInt();
        sc.nextLine();
        switch(input){
            case 0:
                break;
            case 1: {
                System.out.println("Chọn ngày kết thúc");
                while(true){
                NgayKetThuc = ThaoTac.isValidDate(sc.nextLine(), "dd/MM/yyyy", HeThong.date);
                if(NgayKetThuc!=null){
                    break;
                }
                }
                ThaoTac.KiemTraVanHanhLuongNhanSu(HeThong.date, HeThong.time, "HETHONG", NgayKetThuc);
                HeThong.date = NgayKetThuc;
                break;
            }
            case 2: {
                ThaoTac.checkChuaGiaiQuyet(MaADMIN, sc);
                break;
            }
            case 3: {
                NhatKyHoatDong.LuaChonCachXem(sc);
                break;
            }
            default:
                System.out.println("Nằm ngoài phạm vi");
                break;
                
        }
        }
    }
}
