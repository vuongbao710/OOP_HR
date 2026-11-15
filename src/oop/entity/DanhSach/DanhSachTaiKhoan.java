
package oop.entity.DanhSach;

import java.util.ArrayList;
import oop.entity.Object.TaiKhoan;

public class DanhSachTaiKhoan {
     private static ArrayList<TaiKhoan> dstk = new ArrayList<>();
    private static int dem = 0;

    public static ArrayList<TaiKhoan> getDstk() {
        return dstk;
    }

    public static void setDstk(ArrayList<TaiKhoan> dsTK) {
        dstk = dsTK;
    }
    
    public static void them(TaiKhoan tk){
        dstk.add(tk);
    }
    public static void xoa(TaiKhoan tk){
        dstk.remove(tk);
    }
    public static TaiKhoan timKiem(String MaTaiKhoan){
        for(TaiKhoan tk : dstk){
            if(tk.getMaTaiKhoan().equals(MaTaiKhoan)){
                return tk;
            }
        }
        return null;
    }
    public static void xuat(){
        for(TaiKhoan tk : dstk){
            System.out.println(tk);
        }
    }
    public static String taoMa(){
        dem++;
        return String.format("TK%03d", dem);
    }
}
