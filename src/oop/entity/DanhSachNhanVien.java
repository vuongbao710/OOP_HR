
package oop.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;

public class DanhSachNhanVien {
    ArrayList<NhanVien> dsnv;
    ArrayList<NhanVienCapDuoi> dsnvcd;
    ArrayList<QuanLy> dsql;
    DanhSachPhongBan dspb;
    
    public DanhSachNhanVien(DanhSachPhongBan dspb){
        this.dsnv=new ArrayList<>();
        this.dsnvcd=new ArrayList<>();
        this.dsql=new ArrayList<>();
        this.dspb=dspb;
    }
    /*
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
                String MaQuanLyPhuTrach = temp[6].trim();
                
                if(MaNV.startsWith("NV")){
                    NhanVienCapDuoi nv = new NhanVienCapDuoi(MaNV, HoTen, NgayThangNamSinh, SDT, MaPhongBan, NgayVaoLam, MaQuanLyPhuTrach);
                    this.dsnvcd.add(nv);
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
                    QuanLy nv = new QuanLy(MaNV,HoTen, NgayThangNamSinh, SDT, MaPhongBan, NgayVaoLam); 
                    this.dsql.add(nv);
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
    */
    /*
    public void ThemNhanVien(NhanVien nv){
        this.dsnv.add(nv);
    }
    
    public void ThemNhanVienCapDuoi(NhanVienCapDuoi nvcd){
        this.dsnvcd.add(nvcd);
    }
    public void ThemQuanLy(QuanLy ql){
        this.dsql.add(ql);
    } */       
    public boolean KiemTraTonTai (String MaNV){
        for(NhanVien nv : this.dsnv){
            if(nv.getMaNV().equalsIgnoreCase(MaNV)){
                return true;
            }
        }
        return false;
    }
     
    public void TongHopVaoDanhSach(NhanVien nv){
        if(KiemTraTonTai(nv.getMaNV())==false){
            this.dsnv.add(nv);
        }
        else if(KiemTraTonTai(nv.getMaNV())==true){
            System.out.println("NV " + nv.getMaNV() + " da ton tai trong danh sach");
            return;
        }
        
        if(nv.getChucVu().equals("NhanVienCapDuoi")){
            NhanVienCapDuoi nvcd = (NhanVienCapDuoi) nv;
            this.dsnvcd.add(nvcd);
        }
        else if(nv.getChucVu().equals("QuanLy")){
            QuanLy ql = (QuanLy) nv;
            this.dsql.add(ql);
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
    
    public void XuatDanhSach(){
        System.out.println("========= Danh sach nhan vien ==========");
        for(NhanVien nv : dsnv){
            System.out.println(nv);
        }
        System.out.println("========= Danh sach nhan vien cap duoi ==========");
        for(NhanVienCapDuoi nvcd : dsnvcd){
            System.out.println(nvcd);
        }
        System.out.println("========= Danh sach quan ly ==========");
        for(QuanLy ql : dsql){
            System.out.println(ql);
        }
        System.out.println("========= Danh sach phong ban ==========");
        for(PhongBan pb : dspb.getDspb()){
            System.out.println(pb);
        }
        System.out.println("========= Danh sach nhan vien theo phong ban ==========");
        for(PhongBan pb : dspb.getDspb()){
            System.out.println("=== "+ pb.getTenPhongBan()+ " ===");
            for(NhanVien nv : pb.getDsnvpb()){
                System.out.println(nv);
            }
        }
        
    }
    
    public void TaoSanDanhSach(){
        NhanVien nv1 = new NhanVienCapDuoi("NV001","Nguyen Van A","0373731913","P001",LocalDate.of(2000, 9, 30),LocalDate.of(2026, 9, 30),"QL001");
        NhanVien nv2 = new QuanLy("Ql001","Nguyen Trung B","0373731914","P001",LocalDate.of(2002, 6, 30),LocalDate.of(2026, 9, 30));
        NhanVien nv3 = new QuanLy("QL002","Nguyen Van C","0373731915","P002",LocalDate.of(2001, 8, 30),LocalDate.of(2026, 9, 30));
        TongHopVaoDanhSach(nv1);
        TongHopVaoDanhSach(nv2);
        TongHopVaoDanhSach(nv3);
        TongHopVaoDanhSach(nv1);
        
    }
    
    
    
    public ArrayList<NhanVienCapDuoi> getDsnvcd(){
        return dsnvcd;
    }

    public ArrayList<NhanVien> getDsnv() {
        return dsnv;
    }

    public ArrayList<QuanLy> getDsql() {
        return dsql;
    }

    public DanhSachPhongBan getDspb() {
        return dspb;
    }
    
    public void GanNhanVienVoiQuanLy(){
        for(NhanVienCapDuoi nvcd : dsnvcd){
            for(QuanLy ql : dsql){
                if(nvcd.getMaQuanLyPhuTrach().equals(ql.getMaNV())){
                    ql.ThemNVCD(nvcd);
                }
            }
        }
    }
    
}
