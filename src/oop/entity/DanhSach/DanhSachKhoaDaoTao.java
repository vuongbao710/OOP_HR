
package oop.entity.DanhSach;

import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.KhoaDaoTao;

public class DanhSachKhoaDaoTao implements DanhSach<KhoaDaoTao>{
    private ArrayList<KhoaDaoTao> dskdt;
    private static int dem=0;

    public DanhSachKhoaDaoTao() {
        this.dskdt = new ArrayList<>();
    }

    public ArrayList<KhoaDaoTao> getDskdt() {
        return dskdt;
    }

    public void setDskdt(ArrayList<KhoaDaoTao> dskdt) {
        this.dskdt = dskdt;
    }
    
    public void them(KhoaDaoTao kdt){
        this.dskdt.add(kdt);
    }
    public void xoa(KhoaDaoTao kdt){
        this.dskdt.remove(kdt);
    }
    public KhoaDaoTao timKiem(String MaKhoaDaoTao){
        for(KhoaDaoTao kdt : dskdt){
            if(kdt.getMaKhoaHoc().equals(MaKhoaDaoTao)){
                return kdt;
            }
        }
        return null;
    }
    public void xuat(){
        for(KhoaDaoTao kdt : dskdt){
            System.out.println(kdt);
        }
    }
    public static String taoMa(){
        dem++;
        return String.format("KhoaDaoTao%04d", dem);
    }
    
}
