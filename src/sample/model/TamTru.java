package sample.model;

import java.time.LocalDateTime;

/**
 * Lớp TamTru đại diện cho thông tin về tạm trú của một nhân khẩu trong hệ thống.
 */
public class TamTru {
    private int idTamTru;
    private String soCccd;
    private LocalDateTime ngayBatDau, ngayKetThuc;
    private String liDo;
    
    /**
     * Khởi tạo một đối tượng TamTru mới với thông tin mặc định.
     */
    public TamTru() {
    }
    
    /**
     * Khởi tạo một đối tượng TamTru mới với thông tin cung cấp.
     *
     * @param idTamTru    ID của thông tin tạm trú.
     * @param soCccd      Số CCCD của nhân khẩu.
     * @param ngayBatDau  Ngày bắt đầu tạm trú.
     * @param ngayKetThuc Ngày kết thúc tạm trú.
     * @param liDo        Lý do tạm trú.
     */
    public TamTru(int idTamTru, String soCccd, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc, String liDo) {
        this.idTamTru = idTamTru;
        this.soCccd = soCccd;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.liDo = liDo;
    }
    
    /**
     * Lấy ID của thông tin tạm trú.
     *
     * @return ID của thông tin tạm trú.
     */
    public int getIdTamTru() {
        return idTamTru;
    }
    
    /**
     * Thiết lập ID mới cho thông tin tạm trú.
     *
     * @param idTamTru ID mới cho thông tin tạm trú.
     */
    public void setIdTamTru(int idTamTru) {
        this.idTamTru = idTamTru;
    }
    
    /**
     * Lấy số CCCD của nhân khẩu.
     *
     * @return Số CCCD của nhân khẩu.
     */
    public String getSoCccd() {
        return soCccd;
    }
    
    /**
     * Thiết lập số CCCD mới cho thông tin tạm trú.
     *
     * @param soCccd Số CCCD mới cho thông tin tạm trú.
     */
    public void setSoCccd(String soCccd) {
        this.soCccd = soCccd;
    }
    
    /**
     * Lấy ngày bắt đầu tạm trú.
     *
     * @return Ngày bắt đầu tạm trú.
     */
    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }
    
    /**
     * Thiết lập ngày bắt đầu mới cho thông tin tạm trú.
     *
     * @param ngayBatDau Ngày bắt đầu mới cho thông tin tạm trú.
     */
    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }
    
    /**
     * Lấy ngày kết thúc tạm trú.
     *
     * @return Ngày kết thúc tạm trú.
     */
    public LocalDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }
    
    /**
     * Thiết lập ngày kết thúc mới cho thông tin tạm trú.
     *
     * @param ngayKetThuc Ngày kết thúc mới cho thông tin tạm trú.
     */
    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
    
    /**
     * Lấy lý do tạm trú.
     *
     * @return Lý do tạm trú.
     */
    public String getLiDo() {
        return liDo;
    }
    
    /**
     * Thiết lập lý do mới cho thông tin tạm trú.
     *
     * @param liDo Lý do mới cho thông tin tạm trú.
     */
    public void setLiDo(String liDo) {
        this.liDo = liDo;
    }
}
