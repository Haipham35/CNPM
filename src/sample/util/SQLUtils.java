package sample.util;

import sample.SqlConnection;
import sample.model.CCCD;
import sample.model.NhanKhau;
import sample.model.ThongTinNhanKhau;
import sample.model.ThongTinNhanKhau;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Helper cung cấp các phương thức hỗ trợ cho các class khác trong package dao
 */
public class SQLUtils {
    /**
     * @param resultSet ResultSet chứa dữ liệu cần trích xuất.
     * @return Thông tin của nhân khẩu
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    public static ThongTinNhanKhau get(ResultSet resultSet) throws SQLException {
        String soCccd = resultSet.getString("soCccd");
        LocalDateTime ngayCap = resultSet.getTimestamp("ngayCap").toLocalDateTime();
        String noiCap = resultSet.getString("noiCap");
        
        int idHoKhau = resultSet.getInt("idHoKhau");
        String hoTen = resultSet.getString("hoTen");
        String biDanh = resultSet.getString("biDanh");
        LocalDateTime ngaySinh = resultSet.getTimestamp("ngaySinh").toLocalDateTime();
        String noiSinh = resultSet.getString("noiSinh");
        String nguyenQuan = resultSet.getString("nguyenQuan");
        String danToc = resultSet.getString("danToc");
        String tonGiao = resultSet.getString("tonGiao");
        String ngheNghiep = resultSet.getString("ngheNghiep");
        String noiLamViec = resultSet.getString("noiLamViec");
        LocalDateTime ngayDKTT = resultSet.getTimestamp("ngayDKTT").toLocalDateTime();
        String diaChiCu = resultSet.getString("diaChiCu");
        String quanHe = resultSet.getString("quanHe");
        
        CCCD cccd = new CCCD(soCccd, ngayCap, noiCap);
        
        return new ThongTinNhanKhau(cccd, idHoKhau, hoTen, biDanh, ngaySinh,
                noiSinh, nguyenQuan, danToc, tonGiao, ngheNghiep, noiLamViec, ngayDKTT,
                diaChiCu, quanHe);
    }
    
    /**
     * @param thongTinNhanKhau Thông tin nhân khẩu để đưa vào truy vấn.
     * @param statement        PreparedStatement đang được chuẩn bị.
     * @param index            Index bắt đầu để thiết lập giá trị trong PreparedStatement.
     * @return Index tiếp theo sẽ được sử dụng cho các giá trị khác nếu cần.
     * @throws SQLException Nếu có lỗi khi thiết lập giá trị trong PreparedStatement.
     */
    public static int setValuesForStatement(ThongTinNhanKhau thongTinNhanKhau, PreparedStatement statement, int index) throws SQLException {
        statement.setTimestamp(index++, Timestamp.valueOf(thongTinNhanKhau.getCccd().getNgayCap()));
        statement.setString(index++, thongTinNhanKhau.getCccd().getNoiCap());
        
        statement.setInt(index++, thongTinNhanKhau.getIdHoKhau());
        statement.setString(index++, thongTinNhanKhau.getHoTen());
        statement.setString(index++, thongTinNhanKhau.getBiDanh());
        statement.setTimestamp(index++, Timestamp.valueOf(thongTinNhanKhau.getNgaySinh()));
        statement.setString(index++, thongTinNhanKhau.getNoiSinh());
        statement.setString(index++, thongTinNhanKhau.getNguyenQuan());
        statement.setString(index++, thongTinNhanKhau.getDanToc());
        statement.setString(index++, thongTinNhanKhau.getTonGiao());
        statement.setString(index++, thongTinNhanKhau.getNgheNghiep());
        statement.setString(index++, thongTinNhanKhau.getNoiLamViec());
        statement.setTimestamp(index++, Timestamp.valueOf(thongTinNhanKhau.getNgayDKTT()));
        statement.setString(index++, thongTinNhanKhau.getDiaChiCu());
        statement.setString(index++, thongTinNhanKhau.getQuanHe());
        
        return index;
    }
    
    public static List<NhanKhau> getNhanKhauFromHoKhau(int idHoKhau) throws SQLException {
        Connection connection = SqlConnection.connect();
        List<NhanKhau> danhSachNhanKhau = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM nhankhau WHERE idHoKhau = ?");
        statement.setInt(1, idHoKhau);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            NhanKhau nhanKhau = new NhanKhau(get(resultSet));
            danhSachNhanKhau.add(nhanKhau);
        }
        
        SqlConnection.close(connection);
        return danhSachNhanKhau;
    }
}
