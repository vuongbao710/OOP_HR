
package oop.entity;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class DanhSachPhongBan {
    private ArrayList<PhongBan> dspb;

    public DanhSachPhongBan() {
        this.dspb = new ArrayList<>();
    }
    /*
    public void DocFile(){
        try(BufferedReader br = new BufferedReader(new FileReader("PhongBan.txt"))){
            String line;
            while((line=br.readLine())!=null){
                String[] temp = line.split(",");
                String MaPhong = temp[0].trim();
                String TenPhongBan = temp[1].trim();
                String MaTruongPhong = temp[2].trim();
                String TenTruongPhong = temp[3].trim();
                
                PhongBan pb = new PhongBan(MaPhong, TenPhongBan, MaTruongPhong, TenTruongPhong);
                this.dspb.add(pb);
            }
        } catch(IOException e){
            System.out.println("Loi truy xuat du lieu danh sach phong ban");
        }
        
    }
    */
    public void ThemPhongBan (PhongBan pb){
        this.dspb.add(pb);
    }
    
    public void TaoSanDanhSachPhongBan(){
        PhongBan HR = new PhongBan("P001","Phong Nhan Su","QL001","Nguyen Trung B");
        PhongBan CSKH = new PhongBan("P002","Phong CSKH","QL002","Nguyen Van C");
        ThemPhongBan(HR);
        ThemPhongBan(CSKH);
    }
    


    public ArrayList<PhongBan> getDspb() {
        return dspb;
    }
    
    
}
