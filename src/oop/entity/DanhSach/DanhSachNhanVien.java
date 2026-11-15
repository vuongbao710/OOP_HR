
package oop.entity.DanhSach;
import java.util.ArrayList;
import oop.entity.Object.NhanVien;

public class DanhSachNhanVien{
    private static ArrayList <NhanVien>dsnv = new ArrayList<>();
    private static int dem=0;
   
    public static void xuat(){
        System.out.println("========= Danh sach nhan vien ==========");
        for(NhanVien nv : dsnv){
            System.out.println(nv);
        }
        
    }
    
    
    public static NhanVien timKiem (String MaNV){
        for(NhanVien nv : dsnv){
            if(nv.getMaNV().equalsIgnoreCase(MaNV)){
                return nv;
            }
        }
        return null;
    }
    
    public static void xoa(NhanVien nv){
        
        dsnv.remove(nv);
    }
    public static void them(NhanVien nv){
        dsnv.add(nv);
    }
            
   
    public static String taoMa(){
        dem++;
        return String.format("NV%03d", dem);
    }

    public static ArrayList<NhanVien> getDsnv() {
        return dsnv;
    }

    public static void setDsnv(ArrayList<NhanVien> dsnv) {
        DanhSachNhanVien.dsnv = dsnv;
    }

    public static int getDem() {
        return dem;
    }

    public static void setDem(int dem) {
        DanhSachNhanVien.dem = dem;
    }
    
    public static ArrayList<NhanVien> timKiemTheoMaQuanLyPhuTrach(String MaQuanLyPhuTrach){
        ArrayList<NhanVien> temp = new ArrayList<>();
        for(NhanVien nv : DanhSachNhanVien.getDsnv()){
            if(nv.getMaQuanLyPhuTrach().equalsIgnoreCase(MaQuanLyPhuTrach)){
                temp.add(nv);
            }
        }
        return temp;
    }
    
        public static ArrayList<NhanVien> timKiemTheoMaQuanLyPhuTrachVaFullTime(String MaQuanLyPhuTrach){
        ArrayList<NhanVien> temp = new ArrayList<>();
        for(NhanVien nv : DanhSachNhanVien.getDsnv()){
            if(nv.getMaQuanLyPhuTrach().equalsIgnoreCase(MaQuanLyPhuTrach) && nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")){
                temp.add(nv);
            }
        }
        return temp;
    }
    
}
