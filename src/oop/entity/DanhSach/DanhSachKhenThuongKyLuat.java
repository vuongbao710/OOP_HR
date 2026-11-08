
package oop.entity.DanhSach;
import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.KhenThuong;
import oop.entity.Object.KhenThuongKyLuat;
import oop.entity.Object.KyLuat;

public class DanhSachKhenThuongKyLuat implements DanhSach<KhenThuongKyLuat>{
    private ArrayList<KhenThuongKyLuat> dsktkl;
    private ArrayList<KhenThuong> dskt;
    private ArrayList<KyLuat> dskl;
    private int dem=0;

    public DanhSachKhenThuongKyLuat() {
        this.dsktkl=new ArrayList<>();
        this.dskt=new ArrayList<>();
        this.dskl=new ArrayList<>();
    }
    public ArrayList<KhenThuongKyLuat> getKtdskl() {
        return dsktkl;
    }

    public void setKtdskl(ArrayList<KhenThuongKyLuat> dsktkl) {
        this.dsktkl = dsktkl;
    }

    public ArrayList<KhenThuong> getKt() {
        return dskt;
    }

    public void setKt(ArrayList<KhenThuong> dskt) {
        this.dskt = dskt;
    }

    public ArrayList<KyLuat> getKl() {
        return dskl;
    }

    public void setKl(ArrayList<KyLuat> dskl) {
        this.dskl = dskl;
    }
    
    public void them(KhenThuongKyLuat k){
        if(k.getLoaiQuyetDinh().equals("Khen Thuong")){
            KhenThuong kt = (KhenThuong) k;
            this.dskt.add(kt);
        }
        else{
            KyLuat kl = (KyLuat) k;
            this.dskl.add(kl);
        }
        this.dsktkl.add(k);
    }
    
    public KhenThuongKyLuat timKiem(String MaQD){
        for(KhenThuongKyLuat k : dsktkl){
            if(k.getMaQD().equals(MaQD)){
                return k;
            }
        }
        return null;
    }
    public void xoa(KhenThuongKyLuat k){
        this.dsktkl.remove(k);
    }
    public void xuat(){
        for(KhenThuongKyLuat k : dsktkl){
            System.out.println(k);
        }
    }
    public String taoMa(){
        dem++;
        return String.format("QD%03d", dem);
    }
}
