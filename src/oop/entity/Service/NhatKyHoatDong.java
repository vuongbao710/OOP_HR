
package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import oop.entity.DanhSach.DanhSachNhanVien;

public class NhatKyHoatDong {
    private static ArrayList<String> NhatKyHeThong = new ArrayList<>();
    
    public static void them(String s){
        NhatKyHeThong.add(s);
    }
    
    private static boolean kiemTraIsNull(){
        if(NhatKyHeThong.isEmpty()){
            return true;
        }
        return false;
    }
    
    private static void xuatMoiNhat(){
        if(kiemTraIsNull()){
            System.out.println("Nhật ký rỗng");
            return;
        }
        for(int i = NhatKyHeThong.size()-1 ; i>=0 ; i--){
            System.out.println(NhatKyHeThong.get(i));
        }
    }
    
    private static void xuatCuNhat(){
        if(kiemTraIsNull()){
            System.out.println("Nhật ký rỗng");
            return;
        }
        for(int i = 0 ; i<NhatKyHeThong.size(); i++){
            System.out.println(NhatKyHeThong.get(i));
        }
    }
    
    private static void xuatTheoMaNV (String MaNV){
        boolean flag = false;
        if(DanhSachNhanVien.timKiem(MaNV)==null){
            System.out.println("Không có nhân viên trong danh sách");
        }
        for(String s : NhatKyHeThong){
            if(s.contains(MaNV)){
                System.out.println(s);
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("Không tìm thấy mã nhân viên bạn muốn trên nhật ký");
        }
    }
    
    private static void xuatTheoTime (LocalTime time){
        String timKiem = time.toString();
        
        for(String s : NhatKyHeThong){
            if(s.contains(timKiem)){
                System.out.println(s);
                
            }
        }
        
    }
            
    private static void xuatTheoDate (LocalDate date){
        String timKiem = date.toString();
        for(String s : NhatKyHeThong){
            if(s.contains(timKiem)){
                System.out.println(s);
            }
        }
    }
    
    public static void LuaChonCachXem(Scanner sc){
        LocalDate dateTimKiem;
        int input=-1;
        while(input!=0){
        System.out.println("0. Thoát");
        System.out.println("1. Xem theo trình tự mới nhất");
        System.out.println("2. Xem theo trình tự cũ nhất ");
        System.out.println("3. Tìm kiếm theo ngày");
        System.out.println("4. Tìm kiếm theo mã nhân viên");
        input = sc.nextInt();
        sc.nextLine();
        switch (input){
            case 0:
                break;
            case 1:
                xuatMoiNhat();
                break;
            case 2:
                xuatCuNhat();
                break;
            case 3:
                while(true){
                dateTimKiem = KiemTraLoi.isValidDate(sc.nextLine(), "dd/MM/yyyy");
                if(dateTimKiem!=null){
                    break;
                }
                }
                xuatTheoDate(dateTimKiem);
            case 4:
                xuatTheoMaNV(sc.nextLine());
            default:
                System.out.println("Không nằm trong phạm vi");
                break;
                
        }
        }
    }
    
}
