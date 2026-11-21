
package oop.entity.DanhSach;
import java.time.LocalDate;
import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.KhenThuongKyLuat;
import oop.entity.Object.NhanVien;

public class DanhSachKhenThuongKyLuat implements DanhSach<KhenThuongKyLuat>{
    private ArrayList<KhenThuongKyLuat> dsktkl;
    private int dem=0;

    public DanhSachKhenThuongKyLuat() {
        this.dsktkl=new ArrayList<>();
    }
    public ArrayList<KhenThuongKyLuat> getKtdskl() {
        return dsktkl;
    }

    public void setKtdskl(ArrayList<KhenThuongKyLuat> dsktkl) {
        this.dsktkl = dsktkl;
    }

    
    public void them(KhenThuongKyLuat k){
        if(k.getMaQD()==null)
        k.setMaQD(taoMa());
        this.dsktkl.add(k);
    }
    
    public KhenThuongKyLuat timKiem(String MaQD){
        for(KhenThuongKyLuat k : dsktkl){
            if(k.getMaQD().equals(MaQD)){
                return k;
            }
        }
        return null;
    }
    public void xoa(KhenThuongKyLuat k){
        this.dsktkl.remove(k);
    }
    public void xuat(){
        for(KhenThuongKyLuat k : dsktkl){
            System.out.println(k);
        }
    }
    private String taoMa(){
        dem++;
        return String.format("QD%03d", dem);
    }
    
    public KhenThuongKyLuat timKiem(LocalDate date){
        for(KhenThuongKyLuat k : dsktkl){
            if(k.getNgayBanHanh().equals(date)){
                return k;
            }
        }
        return null;
    }
    
    public double getTienTacDong(LocalDate NgayBatDau, LocalDate NgayKetThuc, NhanVien nv){
        double Tien=0;
        for(LocalDate date = NgayBatDau ; !date.isAfter(NgayKetThuc) ; date = date.plusDays(1)){
            KhenThuongKyLuat k = timKiem(date);
            if(k!=null){
            Tien = Tien + k.tinhTacDongLuong(nv);}
            else{
            }
        }
        return Tien;
    }
}
