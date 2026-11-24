package oop.entity.DanhSach;

import java.util.ArrayList;
import oop.entity.Object.DotTuyenDung;
import oop.entity.Object.UngVien;

public class DanhSachDotTuyenDung {
    private static ArrayList<DotTuyenDung> dsDot = new ArrayList<>();
    private static int dem = 0;

    public static void them(DotTuyenDung dot){
        dot.setMaDotTuyenDung(taoMa());
        dsDot.add(dot);
    }

    public static void xoa(String MaDot){
        DotTuyenDung dot = timKiem(MaDot);
        if(dot == null){
            System.out.println("không tìm thấy đợt tuyển dụng bạn cần xoá");
            return;
        }
        dsDot.remove(dot);
        System.out.println("Xoá thành công");
    }

    public static DotTuyenDung timKiem(String MaDot){
        if(MaDot == null) return null;
        for(DotTuyenDung dot : dsDot){
            if(dot.getMaDotTuyenDung().equalsIgnoreCase(MaDot.trim())){
                return dot;
            }
        }
        return null;
    }

    public static ArrayList<DotTuyenDung> getDsDot(){ return dsDot; }

    public static void xuat(){
        if(dsDot.isEmpty()){
            System.out.println("danh sách đợt rỗng");
            return;
        }
        for(DotTuyenDung dot : dsDot){
            System.out.println(dot);
        }
    }

    public static String taoMa(){
        dem++; 
        return String.format("DOT%03d", dem);
    }
    
    public static UngVien timUngVien(String maUngVien){
    if(maUngVien == null) return null;
    for(DotTuyenDung dot : dsDot){
        for(UngVien uv : dot.getDsUV().getDsuv()){
            if(uv.getMaUngVien() != null && uv.getMaUngVien().equalsIgnoreCase(maUngVien.trim())){
                return uv;
            }
        }
    }
    return null;
}
}