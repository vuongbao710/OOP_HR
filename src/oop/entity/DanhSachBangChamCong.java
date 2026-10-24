
package oop.entity;
import java.util.ArrayList;
public class DanhSachBangChamCong {
    private ArrayList<BangChamCong> dsbcc;

    public DanhSachBangChamCong() {
        this.dsbcc = new ArrayList<>();
    }

    public ArrayList<BangChamCong> getDsbcc() {
        return dsbcc;
    }

    public void setDsbcc(ArrayList<BangChamCong> dsbcc) {
        this.dsbcc = dsbcc;
    }
    
}
