
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
    
    public PhongBan TimTheoMa (String MaPhong){
        for(PhongBan pb : dspb){
            if(pb.getMaPhong().equals(MaPhong))return pb;
        }
        return null;
    }

    public ArrayList<PhongBan> getDspb() {
        return dspb;
    }
    
    
}
