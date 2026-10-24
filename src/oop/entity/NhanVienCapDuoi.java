
package oop.entity;

import java.time.LocalDate;

public class NhanVienCapDuoi extends NhanVien {
    private String MaQuanLyPhuTrach;
    private BangChamCong bcc;
    private DanhSachBangChamCong dsbcc;
    private static LocalDate NgayReset;
    

    public NhanVienCapDuoi() {
        this.MaQuanLyPhuTrach = null;
    }
    
    public NhanVienCapDuoi(String MaNV, String HoTen, String SDT, String MaPhongBan,LocalDate BirthDate,LocalDate NgayVaoLam, String MaQuanLyPhuTrach) {
        super.setMaNV(MaNV);
        super.setHoTen(HoTen);
        super.setSDT(SDT);
        super.setMaPhongBan(MaPhongBan);
        this.MaQuanLyPhuTrach=MaQuanLyPhuTrach;
        super.setBirthDate(BirthDate);
        super.setNgayVaoLam(NgayVaoLam);
        super.setChucVu("NhanVienCapDuoi");
        super.setLuongCoBan(5000000);
        this.bcc=new BangChamCong(MaNV,MaQuanLyPhuTrach);
        this.dsbcc = new DanhSachBangChamCong();
        NgayReset = LocalDate.of(NgayVaoLam.getYear(), NgayVaoLam.getMonthValue(), 5);
        if(NgayReset.isBefore(NgayVaoLam)){
            NgayReset.plusMonths(1);
        }
    }
    
    public long TinhLuongCoBan(){
        return 5000000;
    }

    public String getMaQuanLyPhuTrach() {
        return MaQuanLyPhuTrach;
    }

    public void setMaQuanLyPhuTrach(String MaQuanLyPhuTrach) {
        this.MaQuanLyPhuTrach = MaQuanLyPhuTrach;
    }

    public DanhSachBangChamCong getDsbcc() {
        return dsbcc;
    }

    public void setDsbcc(DanhSachBangChamCong dsbcc) {
        this.dsbcc = dsbcc;
    }

    

    //phan he 2
    public void ThemBangChamCong(ChiTietChamCong cc){
        this.bcc.ThemChiTietChamCong(cc);
        if(cc.getDate().isEqual(NgayReset)){
            this.dsbcc.getDsbcc().add(bcc);
            this.bcc = new BangChamCong(MaNV, MaQuanLyPhuTrach);
        }
    }
    
    
    
    public String toString(){
        return "Ma nhan vien: " + MaNV + "| Ho ten :" + HoTen + "| SDT :" + SDT + "| Ma phong ban :" + MaPhongBan + "| Ma quan ly phu trach :" + MaQuanLyPhuTrach;
    } 
    
    
    
    public BangChamCong getBcc() {
        return bcc;
    }

    public void setBcc(BangChamCong bcc) {
        this.bcc = bcc;
    }
    
}
