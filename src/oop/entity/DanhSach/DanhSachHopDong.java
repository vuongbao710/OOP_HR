
package oop.entity.DanhSach;
import java.util.Arrays;
import oop.entity.Object.HopDong;

public class DanhSachHopDong {
    private static HopDong[] dsHopDong;
    private static int dem = 0;
    public DanhSachHopDong(int SoLuongNhanVien) {
        this.dsHopDong = new HopDong[SoLuongNhanVien ];
    }
    
    public static HopDong timKiemTheoMaNV(String MaNV){
        for(int i = 0; i< dsHopDong.length; i++){
            if(dsHopDong[i]!=null && dsHopDong[i].getMaNV().equalsIgnoreCase(MaNV)){
                return dsHopDong[i];
            }
        }
        return null;
    }
    
      public static HopDong timKiemTheoMaHD(String MaHD){
        for(int i = 0; i< dsHopDong.length; i++){
            if(dsHopDong[i]!=null && dsHopDong[i].getMaHopDong().equalsIgnoreCase(MaHD)){
                return dsHopDong[i];
            }
        }
        return null;
    }
    
    public static void them(HopDong hd){
        if(hd.getMaHopDong()==null){
            String MaHopDong = taoMa();
            hd.setMaHopDong(MaHopDong);
        }
        else{
            dem++;
        }
        if(dem>=dsHopDong.length){
            dsHopDong = Arrays.copyOf(dsHopDong, dsHopDong.length+1);
        }
        dsHopDong[dem-1] = hd;
    }
    
    public void xuat(){
        for(HopDong hd : dsHopDong){
            System.out.println(hd);
        }
    }
      
      
    public static String taoMa(){
        dem++;
        return String.format("HD%04d", dem);
    }

    public static HopDong[] getDsHopDong() {
        return dsHopDong;
    }

    public static void setDsHopDong(HopDong[] dsHopDong) {
        DanhSachHopDong.dsHopDong = dsHopDong;
    }

    public static int getDem() {
        return dem;
    }

    public static void setDem(int dem) {
        DanhSachHopDong.dem = dem;
    }
    
    
}
