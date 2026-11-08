
package oop.entity.DanhSach;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.DonXinPhep;

public class DanhSachDonXinPhep implements DanhSach<DonXinPhep> {
    private ArrayList<DonXinPhep> dsDon;
    private int dem=0;

    public DanhSachDonXinPhep() {
        this.dsDon = new ArrayList<>();
    }

    public DanhSachDonXinPhep(ArrayList<DonXinPhep> dsDon) {
        this.dsDon = dsDon;
    }
    
        public void them(DonXinPhep Don){
        this.dsDon.add(Don);
    }
    public void xoa(DonXinPhep Don){
        this.dsDon.remove(Don);
    }
    public DonXinPhep timKiem(String MaDonXinPhep){
        for(DonXinPhep Don : dsDon){
            if(Don.getMaDon().equals(MaDonXinPhep)){
                return Don;
            }
        }
        return null;
    }
    
    public DonXinPhep timKiem(LocalDate date, LocalTime time){
        for(DonXinPhep Don : dsDon){
            if(Don.getNgayNopDon().equals(date) && Don.getThoiGianNopDon().equals(time)){
                return Don;
            }
        }
        return null;
    }
    
    public void xuat(){
        for(DonXinPhep Don : dsDon){
            System.out.println(Don);
        }
    }
    public String taoMa(){
        dem++;
        return String.format("DON%03d", dem);
    }
    
}
