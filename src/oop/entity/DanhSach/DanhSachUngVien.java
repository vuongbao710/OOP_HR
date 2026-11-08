
package oop.entity.DanhSach;
import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.UngVien;

public class DanhSachUngVien implements DanhSach<UngVien>{
    private ArrayList<UngVien> dsuv;
    private static int dem = 0;

    public DanhSachUngVien() {
        this.dsuv = new ArrayList<>();
    }

    public ArrayList<UngVien> getDsuv() {
        return dsuv;
    }

    public void setDsuv(ArrayList<UngVien> dsuv) {
        this.dsuv = dsuv;
    }
    
    public void them(UngVien uv){
        this.dsuv.add(uv);
    }
    public void xoa(UngVien uv){
        this.dsuv.remove(uv);
    }
    public UngVien timKiem(String MaUngVien){
        for(UngVien uv : dsuv){
            if(uv.getMaUngVien().equals(MaUngVien)){
                return uv;
            }
        }
        return null;
    }
    public void xuat(){
        for(UngVien uv : dsuv){
            System.out.println(uv);
        }
    }
    public static String taoMa(){
        dem++;
        return String.format("UngVien%04d", dem);
    }
    
    
}
