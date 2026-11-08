
package oop.entity.DanhSach;
import oop.entity.Object.DanhGiaKPI;
import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
public class DanhSachDanhGiaKPI implements DanhSach<DanhGiaKPI>{
    private ArrayList<DanhGiaKPI> dskpi;
    private static int dem=0;

    public DanhSachDanhGiaKPI() {
        this.dskpi = new ArrayList<>();
    }

    public ArrayList<DanhGiaKPI> getDskpi() {
        return dskpi;
    }

    public void setDskpi(ArrayList<DanhGiaKPI> dskpi) {
        this.dskpi = dskpi;
    }
    
     public void them(DanhGiaKPI kpi){
        this.dskpi.add(kpi);
    }
    public DanhGiaKPI timKiem(String MaDanhGiaKPI){
        for(DanhGiaKPI kpi : dskpi){
            if(kpi.getMaDanhGia().equals(MaDanhGiaKPI)){
                return kpi;
            }
        }
        return null;
    }
    public void xoa(DanhGiaKPI kpi){
        this.dskpi.remove(kpi);
    }
    public void xuat(){
        for(DanhGiaKPI kpi : dskpi){
            System.out.println(kpi);
        }
    }
    public static String taoMa(){
        dem++;
        return String.format("KPI%03d", dem);
    }
    
}
