
package oop.entity.Object;
import oop.entity.DanhSach.BangChamCong;
import oop.entity.DanhSach.DanhSachBangChamCong;
import oop.entity.DanhSach.DanhSachKhenThuongKyLuat;
import oop.entity.DanhSach.DanhSachPhieuLuong;
import java.time.LocalDate;
import oop.entity.DanhSach.DanhSachDonXinPhep;
import oop.entity.DanhSach.DanhSachThongBao;
import oop.entity.DanhSach.DanhSachViPham;
import oop.entity.Enum.LOAI_HOP_DONG;
import oop.entity.Enum.LoaiChucVu;
import oop.entity.Enum.LoaiVaiTro;
import oop.entity.Enum.TRANG_THAI_HOP_DONG;
import oop.entity.Service.HeThong;

public abstract class NhanVien {


    private String MaNV;
    private String CCCD;
    private String HoTen;
    private String SDT;
    private String MaPhongBan;
    private String MaQuanLyPhuTrach;
    private LocalDate BirthDate;
    private LocalDate NgayVaoLam;
    private LoaiChucVu ChucVu;
    private String MaViTri;
    private String CaLam;
    private BangChamCong bcc;
    private DanhSachBangChamCong dsbcc;
    private DanhSachKhenThuongKyLuat dsktkl;
    private DanhSachPhieuLuong dspl;
    private DanhSachDonXinPhep dsDon;
    private DanhSachThongBao dsThongBao;
    private DanhSachViPham dsViPham;
    private TaiKhoan TaiKhoan;
    
    
    private LocalDate NgayXetNangLuong;
    private LocalDate NgayTraLuongDinhKy;
    private String HinhThucDiLam;
    private HopDong HopDong; /////
    private double LuongCoSo;
    private double PhuCap;
    private int SoNgayNghiPhepNam =12;
    private static final double TY_LE_BAO_HIEM = 0.105;
    private static final double GIAM_TRU_BAN_THAN = 11000000;
    
    

    public NhanVien() {
        this.bcc = new BangChamCong();
        this.dsbcc = new DanhSachBangChamCong();
        this.dsktkl = new DanhSachKhenThuongKyLuat();
        this.dspl = new DanhSachPhieuLuong();
        this.dsDon = new DanhSachDonXinPhep();
        this.dsThongBao = new DanhSachThongBao();
        this.dsViPham = new DanhSachViPham();
    }
    

    public NhanVien(String MaNV,String CCCD, String HoTen, String SDT,String MaQuanLyPhuTrach
            , LocalDate BirthDate, String CaLam) {
        this.MaNV = MaNV;
        this.CCCD=CCCD;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.MaQuanLyPhuTrach=MaQuanLyPhuTrach;
        this.BirthDate = BirthDate;
        this.CaLam=CaLam;
        this.dsbcc = new DanhSachBangChamCong();
        this.dsktkl = new DanhSachKhenThuongKyLuat();
        this.dspl = new DanhSachPhieuLuong();
        this.dsThongBao = new DanhSachThongBao();
        this.dsViPham = new DanhSachViPham();
        setNgayTraLuongDinhKy(HeThong.date);  //MocThoiGianBatDau
        
        LocalDate KyTruoc = NgayTraLuongDinhKy.minusMonths(1);
        this.bcc = new BangChamCong(MaNV, KyTruoc.getMonthValue(), KyTruoc.getYear());
        this.HopDong = new HopDong();
        this.TaiKhoan = new TaiKhoan(MaNV, SDT,LoaiVaiTro.NHAN_VIEN, MaNV);
        this.dsDon = new DanhSachDonXinPhep();

    }
    
    public NhanVien(String CCCD, String HoTen, String SDT,String MaQuanLyPhuTrach
            , LocalDate BirthDate, String CaLam) {
        this.CCCD=CCCD;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.MaQuanLyPhuTrach=MaQuanLyPhuTrach;
        this.BirthDate = BirthDate;
        this.CaLam=CaLam;
        this.dsbcc = new DanhSachBangChamCong();
        this.dsktkl = new DanhSachKhenThuongKyLuat();
        this.dspl = new DanhSachPhieuLuong();
        this.dsThongBao = new DanhSachThongBao();
        this.dsViPham = new DanhSachViPham();
        setNgayTraLuongDinhKy(HeThong.date);  //MocThoiGianBatDau
        
        LocalDate KyTruoc = NgayTraLuongDinhKy.minusMonths(1);
        this.bcc = new BangChamCong(MaNV, KyTruoc.getMonthValue(), KyTruoc.getYear());
        this.HopDong = new HopDong();
        this.TaiKhoan = new TaiKhoan(MaNV, SDT,LoaiVaiTro.NHAN_VIEN, MaNV);
        this.dsDon = new DanhSachDonXinPhep();

    }
    
    
    
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
        if(this.TaiKhoan != null){
        this.TaiKhoan.setMaNV(MaNV);
        this.TaiKhoan.setTenDangNhap(MaNV);
        }
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
    
    public abstract double TinhLuongMotPhut();
    
    public abstract double TinhLuongMotGio();
    
     public double TinhTienLamOT(){
        double LuongTangCaMotGio = TinhLuongMotPhut()*60*1.5;
        return LuongTangCaMotGio*bcc.getSoGioLamOT();
    }
     public double TinhHeSoKPI(){
        return 1.0;
    }
     
     public double TinhThue(double LuongChiuThue){
        double ThueTNCN;
        double Moc1=5000000;double Thue1=0.05;
        double Moc2=10000000;double Thue2=0.1;
        double Moc3=18000000;double Thue3=0.15;
        double Moc4=32000000;double Thue4=0.2;
        double Moc5=52000000;double Thue5=0.25;
        double Moc6=80000000;double Thue6=0.3;
        double Thue7=0.35;
            if(LuongChiuThue<Moc1){
                return LuongChiuThue*Thue1;
            }
            else{
            ThueTNCN=Moc1*Thue1;
                if(LuongChiuThue<Moc2){
                    return ThueTNCN+(LuongChiuThue-Moc1)*Thue2;
                    }
                    else{
                    ThueTNCN = ThueTNCN + (Moc2-Moc1)*Thue2;
                        if(LuongChiuThue<Moc3){
                        return ThueTNCN+(LuongChiuThue-Moc2)*Thue3;
                        }
                        
                        else{
                    ThueTNCN = ThueTNCN + (Moc3-Moc2)*Thue3;
                            if(LuongChiuThue<Moc4){
                            return ThueTNCN+(LuongChiuThue-Moc3)*Thue4;
                                }
                    else{
                    ThueTNCN = ThueTNCN + (Moc4-Moc3)*Thue4;
                    if(LuongChiuThue<Moc5){
                            return ThueTNCN+(LuongChiuThue-Moc4)*Thue5;
                    }
                    
                    else{
                    ThueTNCN = ThueTNCN + (Moc5-Moc4)*Thue5;
                        if(LuongChiuThue<Moc6){
                        return ThueTNCN+(LuongChiuThue-Moc5)*Thue6;
                    }
                    
                    else{
                        ThueTNCN=ThueTNCN + (Moc6-Moc5)*Thue6;
                        return ThueTNCN+(LuongChiuThue - 80000000)*Thue7;
                    }
                    }
                        }
                    }
            }
        }
        
    
    }
     
    protected double TinhLuongThucNhan(double luongTheoNgayCong){
        double luongSauKPI = luongTheoNgayCong * TinhHeSoKPI();  
        double tienOT = TinhTienLamOT();
        double khenPhat = dsktkl.getTienTacDong(bcc.getNgayBatDauBang(), bcc.getNgayKetThucBang(), this);

        double tongThuNhap = luongSauKPI + tienOT + PhuCap + khenPhat;
        double baoHiem = TY_LE_BAO_HIEM * luongTheoNgayCong;     

   
        double thuNhapChiuThue = tongThuNhap - baoHiem - GIAM_TRU_BAN_THAN;
        if(thuNhapChiuThue < 0){       
        thuNhapChiuThue = 0;
    }
        double thue = TinhThue(thuNhapChiuThue);

        double luongThucNhan = tongThuNhap - baoHiem - thue;
        if(luongThucNhan < 0){        
        luongThucNhan = 0;
    }
        return luongThucNhan;
}

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

    public String getMaViTri() {
        return MaViTri;
    }

    private void setMaViTri(String MaViTri) {
        this.MaViTri = MaViTri;
    }

    

    public LocalDate getNgayVaoLam() {
        return NgayVaoLam;
    }

    private void setNgayVaoLam(LocalDate NgayVaoLam) {
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

    public void setNgayTraLuongDinhKy(LocalDate dateNow) {
        this.NgayTraLuongDinhKy = LocalDate.of(dateNow.getYear() , dateNow.getMonthValue() , 6);  ///hết ngày 5 mới xét
        if(!NgayTraLuongDinhKy.isAfter(dateNow)){
            this.NgayTraLuongDinhKy = this.NgayTraLuongDinhKy.plusMonths(1);
        }
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

    private void setChucVu(LoaiChucVu ChucVu) {
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

    private void setTaiKhoan(TaiKhoan TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getHinhThucDiLam() {
        return HinhThucDiLam;
    }

    public void setHinhThucDiLam(String HinhThucDiLam) {
        this.HinhThucDiLam = HinhThucDiLam;
    }


    public LocalDate getNgayXetNangLuong() {
        return NgayXetNangLuong;
    }

    public void setNgayXetNangLuong(LocalDate NgayXetNangLuong) {
        this.NgayXetNangLuong = NgayXetNangLuong;
    }

    public HopDong getHopDong() {
        return HopDong;
    }

    public void setHopDong(HopDong HopDong) {
        this.HopDong = HopDong;
        if(this.NgayVaoLam == null) setNgayVaoLam(HopDong.getNgayBatDauHopDong());
        if(this.NgayXetNangLuong == null) setNgayXetNangLuong(this.NgayVaoLam.plusYears(1));
        setLuongCoSo(HopDong.getMucLuongCoSo());
        if(HopDong.getLoaiHopDong().equals(LOAI_HOP_DONG.PART_TIME)){
            setHinhThucDiLam("Part-time");
        }
        else{
            setHinhThucDiLam("Full-time");
        }
        setNgayVaoLam(HopDong.getNgayBatDauHopDong());
        setNgayXetNangLuong(this.NgayVaoLam.plusYears(1));
        setPhuCap(HopDong.getPhuCap());
        setMaViTri(HopDong.getLoaiViTri().getMaViTri());
        setChucVu(ViTri.XacDinhChucVu(MaViTri));
        if(this.TaiKhoan != null){
        this.TaiKhoan.setRole(ViTri.XacDinhQuyen(MaViTri));
        }
        setMaPhongBan(HopDong.getLoaiViTri().getPhongBan()); 
    }

    public double getLuongCoSo() {
        return LuongCoSo;
    }

    private void setLuongCoSo(double LuongCoSo) {
        this.LuongCoSo = LuongCoSo;
    }

    public double getPhuCap() {
        return PhuCap;
    }

    private void setPhuCap(double PhuCap) {
        this.PhuCap = PhuCap;
    }

    public DanhSachViPham getDsViPham() {
        return dsViPham;
    }

    public void setDsViPham(DanhSachViPham dsViPham) {
        this.dsViPham = dsViPham;
    }
    
    

    @Override
    public String toString() {
        return "NhanVien{" + "MaNV=" + MaNV + ", CCCD=" + CCCD + ", HoTen=" + HoTen + ", SDT=" 
                + SDT + ", MaPhongBan=" + MaPhongBan + ", MaQuanLyPhuTrach=" + MaQuanLyPhuTrach 
                + ", BirthDate=" + BirthDate + ", NgayVaoLam=" + NgayVaoLam + ", ChucVu=" + ChucVu 
                + ", CaLam=" + CaLam + ", NgayXetNangLuong=" + NgayXetNangLuong + ", NgayTraLuongDinhKy=" +
                NgayTraLuongDinhKy + ", HinhThucDiLam=" + HinhThucDiLam + ", HopDong=" + HopDong + ", LuongCoSo=" 
                + LuongCoSo + '}';
    }

    public int getSoNgayNghiPhepNam() {
        return SoNgayNghiPhepNam;
    }

    public void setSoNgayNghiPhepNam(int SoNgayNghiPhepNam) {
        this.SoNgayNghiPhepNam = SoNgayNghiPhepNam;
    }
    

    public boolean ToiNgayTongKetDinhKy(LocalDate NgayKiemTra){
        if(NgayKiemTra.isEqual(NgayTraLuongDinhKy)){
            return true;
        }
        return false;
    }
    
public boolean ToiNgayXetNangLuong(LocalDate NgayKiemTra){
    return this.NgayXetNangLuong != null && NgayKiemTra.isEqual(this.NgayXetNangLuong);
}
    
    
   public void ThemChiTietChamCong(ChiTietChamCong cc){
        this.bcc.them(cc);
    }
   
   public void ThemBangChamCong(){
       this.dsbcc.them(bcc);
   }
   
   public PhieuLuong TaoPhieuLuong(int thang, int nam){
       double LuongThucTe = TinhLuongThucTe();
       double TienLamOT = TinhTienLamOT();
       double TienKhenPhat = dsktkl.getTienTacDong(bcc.getNgayBatDauBang(), bcc.getNgayKetThucBang(), this);
       PhieuLuong pl = new PhieuLuong(LuongThucTe,LuongCoSo,PhuCap,TienLamOT,TienKhenPhat, thang, nam, this.MaNV);
       return pl;
   }
   
   public void ThemPhieuLuong(PhieuLuong pl){
       this.dspl.them(pl);
   }
   public void ThemKhenThuongKyLuat(KhenThuongKyLuat ktkl){
       this.dsktkl.them(ktkl);
   }
    
    public void ResetQuyTrinhChamCong(LocalDate NgayKiemTra){
    ThemBangChamCong(); 
    LocalDate KyTruoc = NgayKiemTra.minusMonths(1);
    PhieuLuong pl = TaoPhieuLuong(KyTruoc.getMonthValue(), KyTruoc.getYear());
    pl.setMaPhieu(dspl.taoMa());
    ThemPhieuLuong(pl);
    this.bcc = new BangChamCong(MaNV, NgayKiemTra.getMonthValue(), NgayKiemTra.getYear());

    this.NgayTraLuongDinhKy = NgayTraLuongDinhKy.plusMonths(1);
    }
    
    public boolean isDaNghiViec(){
    return HopDong != null && HopDong.getTrangThaiHopDong() == TRANG_THAI_HOP_DONG.DA_HUY;
}
 
}
