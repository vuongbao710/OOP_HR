
package oop.entity.DanhSach;

import java.util.ArrayList;
import oop.entity.Object.KhoaDaoTao;

public class DanhSachKhoaDaoTao {
    private static ArrayList<KhoaDaoTao> dskdt = new ArrayList<>();
    private static int dem=0;


    public static ArrayList<KhoaDaoTao> getDskdt() {
        return dskdt;
    }

    public static void setDskdt(ArrayList<KhoaDaoTao> ds) {
        dskdt = ds;
    }
    
    public static void them(KhoaDaoTao kdt){
        dskdt.add(kdt);
    }
    public static void xoa(KhoaDaoTao kdt){
        dskdt.remove(kdt);
    }
    public static KhoaDaoTao timKiem(String MaKhoaDaoTao){
        for(KhoaDaoTao kdt : dskdt){
            if(kdt.getMaKhoaHoc().equals(MaKhoaDaoTao)){
                return kdt;
            }
        }
        return null;
    }
    public static void xuat(){
        if(dskdt==null){
            System.out.println("danh sách khoá đào tạo rỗng");
            return;
        }
        for(KhoaDaoTao kdt : dskdt){
            System.out.println(kdt);
        }
    }
    public static String taoMa(){
        dem++;
        return String.format("KhoaDaoTao%04d", dem);
    }
    
}
