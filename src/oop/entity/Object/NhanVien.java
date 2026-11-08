
package oop.entity.Object;
import oop.entity.DanhSach.BangChamCong;
import oop.entity.DanhSach.DanhSachBangChamCong;
import oop.entity.DanhSach.DanhSachKhenThuongKyLuat;
import oop.entity.DanhSach.DanhSachPhieuLuong;
import java.time.LocalDate;
import oop.entity.DanhSach.DanhSachDonXinPhep;
import oop.entity.DanhSach.DanhSachThongBao;
import oop.entity.Enum.LoaiChucVu;
import oop.entity.Enum.LoaiVaiTro;

public abstract class NhanVien {


    protected String MaNV;
    protected String CCCD;
    protected String HoTen;
    protected String SDT;
    protected String MaPhongBan;
    protected String MaQuanLyPhuTrach;
    protected LocalDate BirthDate;
    protected LocalDate NgayVaoLam;
    protected LoaiChucVu ChucVu;
    protected String CaLam;
    protected BangChamCong bcc;
    protected DanhSachBangChamCong dsbcc;
    protected DanhSachKhenThuongKyLuat dsktkl;
    protected DanhSachPhieuLuong dspl;
    protected DanhSachDonXinPhep dsDon;
    protected DanhSachThongBao dsThongBao;
    protected TaiKhoan TaiKhoan;
    
    protected LocalDate NgayXetNangLuong;
    protected LocalDate NgayTraLuongDinhKy;
    protected String HinhThucDiLam;
    
    protected int SoLanViPhamChamCong = 0;
    protected int SoLanViPhamNghiKhongPhep = 0;
    protected int SoLanViPhamQuyDinh = 0;

    public NhanVien() {
        this.bcc = new BangChamCong();
        this.dsbcc = new DanhSachBangChamCong();
        this.dsktkl = new DanhSachKhenThuongKyLuat();
        this.dspl = new DanhSachPhieuLuong();
        this.dsDon = new DanhSachDonXinPhep();
        this.dsThongBao = new DanhSachThongBao();
    }
    

    public NhanVien(String MaNV,String CCCD, String HoTen, String SDT,String MaQuanLyPhuTrach, String MaPhongBan, LocalDate BirthDate, LocalDate NgayVaoLam, String ChucVu, String HinhThucDiLam, String CaLam) {
        this.MaNV = MaNV;
        this.CCCD=CCCD;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.MaQuanLyPhuTrach=MaQuanLyPhuTrach;
        this.MaPhongBan = MaPhongBan;
        this.BirthDate = BirthDate;
        this.NgayVaoLam = NgayVaoLam;
        this.ChucVu=LoaiChucVu.NhanDien(ChucVu);
        this.CaLam=CaLam;
        this.bcc = new BangChamCong(MaNV, NgayTraLuongDinhKy.getMonthValue(), NgayTraLuongDinhKy.getYear());
        this.dsbcc = new DanhSachBangChamCong();
        this.dsktkl = new DanhSachKhenThuongKyLuat();
        this.dspl = new DanhSachPhieuLuong();
        
        NgayTraLuongDinhKy = LocalDate.of(NgayVaoLam.getYear(), NgayVaoLam.getMonthValue(), 5);
        if(NgayTraLuongDinhKy.isBefore(NgayVaoLam)){
            NgayTraLuongDinhKy.plusMonths(1);
        }
        this.NgayXetNangLuong = NgayVaoLam.plusYears(1);
        
        this.HinhThucDiLam=HinhThucDiLam;
    }
    
    
    
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    
    public abstract double TinhLuongThucTe();

    public String getMaQuanLyPhuTrach() {
        return MaQuanLyPhuTrach;
    }

    public void setMaQuanLyPhuTrach(String MaQuanLyPhuTrach) {
        this.MaQuanLyPhuTrach = MaQuanLyPhuTrach;
    }


    public String getMaPhongBan() {
        return MaPhongBan;
    }

    public void setMaPhongBan(String MaPhongBan) {
        this.MaPhongBan = MaPhongBan;
    }

    public LocalDate getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(LocalDate BirthDate) {
        this.BirthDate = BirthDate;
    }

    public LocalDate getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(LocalDate NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    public BangChamCong getBcc() {
        return bcc;
    }

    public void setBcc(BangChamCong bcc) {
        this.bcc = bcc;
    }

    public DanhSachBangChamCong getDsbcc() {
        return dsbcc;
    }

    public void setDsbcc(DanhSachBangChamCong dsbcc) {
        this.dsbcc = dsbcc;
    }

    public DanhSachKhenThuongKyLuat getDsktkl() {
        return dsktkl;
    }

    public void setDsktkl(DanhSachKhenThuongKyLuat dsktkl) {
        this.dsktkl = dsktkl;
    }

    public DanhSachPhieuLuong getDspl() {
        return dspl;
    }

    public void setDspl(DanhSachPhieuLuong dspl) {
        this.dspl = dspl;
    }

    public DanhSachDonXinPhep getDsDon() {
        return dsDon;
    }

    public void setDsDon(DanhSachDonXinPhep dsDon) {
        this.dsDon = dsDon;
    }

    public DanhSachThongBao getDsThongBao() {
        return dsThongBao;
    }

    public void setDsThongBao(DanhSachThongBao dsThongBao) {
        this.dsThongBao = dsThongBao;
    }

    public LocalDate getNgayTraLuongDinhKy() {
        return NgayTraLuongDinhKy;
    }

    public void setNgayTraLuongDinhKy(LocalDate NgayTraLuongDinhKy) {
        this.NgayTraLuongDinhKy = NgayTraLuongDinhKy;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public LoaiChucVu getChucVu() {
        return ChucVu;
    }

    public void setChucVu(LoaiChucVu ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getCaLam() {
        return CaLam;
    }

    public void setCaLam(String CaLam) {
        this.CaLam = CaLam;
    }
    
    public TaiKhoan getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(TaiKhoan TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getHinhThucDiLam() {
        return HinhThucDiLam;
    }

    public void setHinhThucDiLam(String HinhThucDiLam) {
        this.HinhThucDiLam = HinhThucDiLam;
    }

    public int getSoLanViPhamChamCong() {
        return SoLanViPhamChamCong;
    }

    public void setSoLanViPhamChamCong(int SoLanViPhamChamCong) {
        this.SoLanViPhamChamCong = SoLanViPhamChamCong;
    }

    public int getSoLanViPhamNghiKhongPhep() {
        return SoLanViPhamNghiKhongPhep;
    }

    public void setSoLanViPhamNghiKhongPhep(int SoLanViPhamNghiKhongPhep) {
        this.SoLanViPhamNghiKhongPhep = SoLanViPhamNghiKhongPhep;
    }

    public int getSoLanViPhamQuyDinh() {
        return SoLanViPhamQuyDinh;
    }

    public void setSoLanViPhamQuyDinh(int SoLanViPhamQuyDinh) {
        this.SoLanViPhamQuyDinh = SoLanViPhamQuyDinh;
    }
    
    

    @Override
    public String toString() {
        return "NhanVien{" + "MaNV=" 
                + MaNV + ", CCCD=" + CCCD + ", HoTen=" 
                + HoTen + ", SDT=" + SDT + ", MaPhongBan=" 
                + MaPhongBan + ", MaQuanLyPhuTrach=" + MaQuanLyPhuTrach 
                + ", BirthDate=" + BirthDate + ", NgayVaoLam=" + NgayVaoLam 
                + ", ChucVu=" + ChucVu + ", dsktkl=" + dsktkl + ", HinhThucDiLam=" + HinhThucDiLam + '}';
    }
    
    


    public boolean ToiNgayTongKetDinhKy(LocalDate NgayKiemTra){
        if(NgayKiemTra.isEqual(NgayTraLuongDinhKy)){
            return true;
        }
        return false;
    }
    
   public void ThemChiTietChamCong(ChiTietChamCong cc){
        this.bcc.them(cc);
    }
   
   public void ThemBangChamCong(){
       this.dsbcc.them(bcc);
   }
   
   public PhieuLuong TaoPhieuLuong(int thang, int nam){
       double LuongThucTe = TinhLuongThucTe();
       PhieuLuong pl = new PhieuLuong(LuongThucTe, thang, nam, this.MaNV);
       return pl;
   }
   
   public void ThemPhieuLuong(PhieuLuong pl){
       this.dspl.them(pl);
   }
   public void ThemKhenThuongKyLuat(KhenThuongKyLuat ktkl){
       this.dsktkl.them(ktkl);
   }
    
    public void VanHanh(LocalDate NgayKiemTra, ChiTietChamCong cc){
        ThemChiTietChamCong(cc);
        if(ToiNgayTongKetDinhKy(NgayKiemTra)==false){
            return;
        }
        else{
            ThemBangChamCong();
            PhieuLuong pl = TaoPhieuLuong( NgayKiemTra.getMonthValue() , NgayKiemTra.getYear());
            ThemPhieuLuong(pl);
            this.bcc = new BangChamCong(MaNV, NgayKiemTra.getMonthValue(), NgayKiemTra.getYear());
            NgayTraLuongDinhKy.plusMonths(1);
        }
    }
    
    
    public String XacDinhVaiTro(){
        for(LoaiVaiTro vaitro : LoaiVaiTro.values()){
        if(this.ChucVu.getMoTa().equalsIgnoreCase(vaitro.getMoTa())){
            return vaitro.getMoTa();
        }
        if(this.ChucVu.getMoTa().equalsIgnoreCase("GiamDoc") || this.ChucVu.getMoTa().equalsIgnoreCase("NhanSu")){
            return vaitro.ADMIN.getMoTa();
        }
        if(this.ChucVu.getMoTa().equalsIgnoreCase("ThoiVu")){
            return vaitro.NHAN_VIEN.getMoTa();
        }
        
    }
    return null;
}
    
    
    
}
