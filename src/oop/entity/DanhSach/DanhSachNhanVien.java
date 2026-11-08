
package oop.entity.DanhSach;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.NhanVien;
import oop.entity.Object.NhanVienFullTime;
import oop.entity.Object.NhanVienPartTime;
import oop.entity.Object.PhongBan;

public class DanhSachNhanVien implements DanhSach<NhanVien>{
    ArrayList<NhanVien> dsnv;
    ArrayList<NhanVienFullTime> dsnv_full;
    ArrayList<NhanVienPartTime> dsnv_part;
    String[] LuuTru;
    private int dem=0;
   
    public DanhSachNhanVien(){
        this.dsnv=new ArrayList<>();
        this.dsnv_full=new ArrayList<>();
        this.dsnv_part=new ArrayList<>();
        this.LuuTru = new String[100];
    }/*
    public void DocFile(){
        
        try(BufferedReader br = new BufferedReader(new FileReader("NhanVien.txt"))){
            String line;
            while((line=br.readLine())!=null){
                String[] temp = line.split(",");
                String MaNV = temp[0].trim();
                String HoTen = temp[1].trim();
                String NgaySinh = temp[2].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate NgayThangNamSinh = LocalDate.parse(NgaySinh, formatter);
                String SDT = temp[3].trim();
                String MaPhongBan = temp[4].trim();
                String NgayLam = temp[5].trim();
                LocalDate NgayVaoLam = LocalDate.parse(NgayLam, formatter);
                String MaNhanVienPartTimePhuTrach = temp[6].trim();
                
                if(MaNV.startsWith("NV")){
                    NhanVienFullTime nv = new NhanVienFullTime(MaNV, HoTen, NgayThangNamSinh, SDT, MaPhongBan, NgayVaoLam, MaNhanVienPartTimePhuTrach);
                    this.dsnv_full.add(nv);
                    this.dsnv.add(nv);
                    
                    PhongBan pb = dspb.TimTheoMa(MaPhongBan);
                    if(pb!=null){
                        pb.ThemNhanVien(nv);
                    }
                    else if(pb==null){
                    System.out.println("Nhan vien "+MaNV+" khong thuoc ve phong nao");
                    }
                }
                else if(MaNV.startsWith("QL")){
                    NhanVienPartTime nv = new NhanVienPartTime(MaNV,HoTen, NgayThangNamSinh, SDT, MaPhongBan, NgayVaoLam); 
                    this.dsnv_part.add(nv);
                    this.dsnv.add(nv);
                    PhongBan pb = dspb.TimTheoMa(MaPhongBan);
                    
                    if(pb!=null){
                    pb.ThemNhanVien(nv);
                            
                    }
                    else if(pb==null){
                    System.out.println("Nhan vien "+MaNV+" khong thuoc ve phong nao");
                    }
                
                }
                
                
            }
        } catch (IOException e){
            System.out.println("Loi truy xuat du lieu danh sach nhan vien");
        }
    }
    /*
    public void ThemNhanVien(NhanVien nv){
        this.dsnv.add(nv);
    }
    
    public void ThemNhanVienFullTime(NhanVienFullTime nvcd){
        this.dsnv_full.add(nvcd);
    }
    public void ThemNhanVienPartTime(NhanVienPartTime ql){
        this.dsnv_part.add(ql);
    } */ 
     /*
    public void TongHopVaoDanhSach(NhanVien nv){
            this.dsnv.add(nv);
        
        if(nv.getChucVu().equals("NhanVienFullTime")){
            NhanVienFullTime nvcd = (NhanVienFullTime) nv;
            this.dsnv_full.add(nvcd);
        }
        else if(nv.getChucVu().equals("NhanVienPartTime")){
            NhanVienPartTime ql = (NhanVienPartTime) nv;
            this.dsnv_part.add(ql);
        }
        
        //=======================phan loai nhan vien theo phong ban
        boolean flag = false;
        for(PhongBan pb : dspb.getDspb()){
            if(nv.getMaPhongBan().equals(pb.getMaPhong())){
                pb.ThemNhanVien(nv);
                flag=true;
            }
        }
        if(flag==false){
            System.out.println("loi du lieu ma phong ban");
            return;
            
        }
    }
    */
    public void xuat(){
        System.out.println("========= Danh sach nhan vien ==========");
        for(NhanVien nv : dsnv){
            System.out.println(nv);
        }
        
    }
    
    
    
    public ArrayList<NhanVienFullTime> getDsnvcd(){
        return dsnv_full;
    }

    public ArrayList<NhanVien> getDsnv() {
        return dsnv;
    }

    public ArrayList<NhanVienPartTime> getDsql() {
        return dsnv_part;
    }

   
    
    
    public NhanVien timKiem (String MaNV){
        for(NhanVien nv : this.dsnv){
            if(nv.getMaNV().equalsIgnoreCase(MaNV)){
                return nv;
            }
        }
        return null;
    }
    
    public void xoa(NhanVien nv){
        
        this.dsnv.remove(nv);
    }
    public void them(NhanVien nv){
       
        this.dsnv.add(nv);
    }
            
   
    public String taoMa(){
        dem++;
        return String.format("NV%03d", dem);
    }
}
