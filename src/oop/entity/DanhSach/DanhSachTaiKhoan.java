
package oop.entity.DanhSach;

import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.TaiKhoan;

public class DanhSachTaiKhoan implements DanhSach<TaiKhoan> {
     private ArrayList<TaiKhoan> dstk;
    private static int dem = 0;

    public DanhSachTaiKhoan() {
        this.dstk = new ArrayList<>();
    }

    public ArrayList<TaiKhoan> getDstk() {
        return dstk;
    }

    public void setDstk(ArrayList<TaiKhoan> dstk) {
        this.dstk = dstk;
    }
    
    public void them(TaiKhoan tk){
        this.dstk.add(tk);
    }
    public void xoa(TaiKhoan tk){
        this.dstk.remove(tk);
    }
    public TaiKhoan timKiem(String MaTaiKhoan){
        for(TaiKhoan tk : dstk){
            if(tk.getMaTaiKhoan().equals(MaTaiKhoan)){
                return tk;
            }
        }
        return null;
    }
    public void xuat(){
        for(TaiKhoan tk : dstk){
            System.out.println(tk);
        }
    }
    public static String taoMa(){
        dem++;
        return String.format("TK%03d", dem);
    }
}
