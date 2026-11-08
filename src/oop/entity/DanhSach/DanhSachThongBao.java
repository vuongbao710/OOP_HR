
package oop.entity.DanhSach;

import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.ThongBao;

public class DanhSachThongBao implements DanhSach<ThongBao>{
    private ArrayList<ThongBao> dsThongBao;
    private int dem=0;
    public DanhSachThongBao() {
        this.dsThongBao = new ArrayList<>();
    }

    public DanhSachThongBao(ArrayList<ThongBao> dsThongBao) {
        this.dsThongBao = dsThongBao;
    }
        public void them(ThongBao ThongBao){
        this.dsThongBao.add(ThongBao);
    }
    public void xoa(ThongBao ThongBao){
        this.dsThongBao.remove(ThongBao);
    }
    public ThongBao timKiem(String MaThongBao){
        for(ThongBao ThongBao : dsThongBao){
            if(ThongBao.getMaThongBao().equals(MaThongBao)){
                return ThongBao;
            }
        }
        return null;
    }
    public void xuat(){
        for(ThongBao ThongBao : dsThongBao){
            System.out.println(ThongBao);
        }
    }
    public String taoMa(){
        dem++;
        return String.format("THONGBAO%03d", dem);
    }

    public ArrayList<ThongBao> getDsThongBao() {
        return dsThongBao;
    }

    public void setDsThongBao(ArrayList<ThongBao> dsThongBao) {
        this.dsThongBao = dsThongBao;
    }

    public int getDem() {
        return dem;
    }

    public void setDem(int dem) {
        this.dem = dem;
    }
    
}
