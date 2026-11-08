
package oop.entity.DanhSach;
import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.PhieuLuong;

public class DanhSachPhieuLuong implements DanhSach<PhieuLuong>{
    private ArrayList<PhieuLuong> dspl;
    private static int dem=0;

    public DanhSachPhieuLuong() {
        this.dspl = new ArrayList<>();
    }

    public ArrayList<PhieuLuong> getPl() {
        return dspl;
    }

    public void setPl(ArrayList<PhieuLuong> pl) {
        this.dspl = dspl;
    }
    public void them(PhieuLuong pl){
        this.dspl.add(pl);
    }
    public PhieuLuong timKiem(String MaPhieuLuong){
        for(PhieuLuong pl : dspl){
            if(pl.getMaPhieu().equals(MaPhieuLuong)){
                return pl;
            }
        }
        return null;
    }
    public void xoa(PhieuLuong pl){
        this.dspl.remove(pl);
    }
    public void xuat(){
        for(PhieuLuong pl : dspl){
            System.out.println(pl);
        }
    }
    public static String taoMa(){
        dem++;
        return String.format("PL%03d", dem);
    }
    
}
