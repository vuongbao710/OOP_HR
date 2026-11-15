
package oop.entity.DanhSach;

import oop.entity.Object.DotTuyenDung;

public class DanhSachDotTuyenDung {
    private static DotTuyenDung[] dsDot = new DotTuyenDung[10];
    private static int dem = 0;
    
    public static void them(DotTuyenDung dot){
        dot.setMaDotTuyenDung(taoMa());
        dsDot[dem]=dot;
        dem++;
    }
    public static void xoa(String MaDot){
        boolean flag = false;
        for(int i = 0 ; i<dem ; i++){
            if(dsDot[i].getMaDotTuyenDung().equalsIgnoreCase(MaDot)){
                for(int j = i; j<dem; j++){
                    dsDot[j]=dsDot[j+1];
                }
                dsDot[dem-1] = null;
                dem--;
                flag = true;
                break;
            }
        }
        if(flag==false){
            System.out.println("không tìm thấy đợt tuyển dụng bạn cần xoá");
        }
        else{
            System.out.println("Xoá thành công");
        }
    }
    
    public static DotTuyenDung timKiem(String MaDot){
        for(DotTuyenDung dot : dsDot){
            if(dot.getMaDotTuyenDung().equalsIgnoreCase(MaDot)){
                return dot;
            }
        }
        return null;
    }
    
    public static void xuat(){
        if(dsDot == null){
            System.out.println("danh sách đợt rỗng");
            return;
        }
        for(DotTuyenDung dot : dsDot){
            System.out.println(dot);
        }
    }
    public static String taoMa(){
        return String.format("DOT%03d", dem);
    }
}
