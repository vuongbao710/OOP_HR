
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
