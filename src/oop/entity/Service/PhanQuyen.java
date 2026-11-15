
package oop.entity.Service;

public class PhanQuyen {
    
    public static void NHAN_VIEN(){
        System.out.println("1. Chấm công");
        System.out.println("2. Gửi đơn xin nghỉ phép");
        System.out.println("3. Xem tình trạng bảng chấm công");
        System.out.println("4. Xem phiếu lương");
        System.out.println("5. Xem thông tin cá nhân");
        System.out.println("6. Đăng ký làm Over time");
        
    }
    
    public static void QUAN_LY(){
        NHAN_VIEN();
        System.out.println("7. Duyệt đơn xin phép");
        System.out.println("8. Duyệt đăng ký đơn OT");
        System.out.println("9. Nộp đợt tuyển dụng");
        System.out.println("10. Xem danh sách nhân viên cấp dưới");
        System.out.println("11. Thưởng sáng kiến");
        System.out.println("12. Phạt nhân viên");
    }
    
    public static void ADMIN(){
        QUAN_LY();
    }
}
