
package oop.entity.DanhSach;
import java.util.ArrayList;
import oop.entity.Interface.DanhSach;

public class DanhSachBangChamCong implements DanhSach<BangChamCong>{
    private ArrayList<BangChamCong> dsbcc;
    private static int dem=0;

    public DanhSachBangChamCong() {
        this.dsbcc = new ArrayList<>();
    }

    public ArrayList<BangChamCong> getDsbcc() {
        return dsbcc;
    }

    public void setDsbcc(ArrayList<BangChamCong> dsbcc) {
        this.dsbcc = dsbcc;
    }
    public void them(BangChamCong bcc){
        this.dsbcc.add(bcc);
    }
    public void xoa(BangChamCong bcc){
        this.dsbcc.remove(bcc);
    }
    public BangChamCong timKiem(String MaBCC){
        for(BangChamCong bcc : dsbcc){
            if(bcc.getMaChamCong().equals(MaBCC)){
                return bcc;
            }
        }
        return null;
    }
    public void xuat(){
        for(BangChamCong bcc : dsbcc){
            System.out.println(bcc);
        }
    }
    public static String taoMa(){
        dem++;
        return String.format("BCC%03d", dem);
    }
}
