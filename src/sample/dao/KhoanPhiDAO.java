package sample.dao;

import sample.model.KhoanPhi;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class KhoanPhiDAO triển khai interface DataAccessObject để thao tác với đối tượng KhoanPhi trong cơ sở dữ liệu.
 */
public class KhoanPhiDAO implements DataAccessObject<KhoanPhi, Integer> {
    private final Connection connection;
    private final String table_name;
    
    public enum TableType {
        KHOANPHI,
        KHOANPHICHUATHU
    }
    
    /**
     * Khởi tạo một đối tượng KhoanPhiDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     * @param tableType Loại bảng.
     */
    public KhoanPhiDAO(Connection connection, TableType tableType) {
        this.connection = connection;
        if (tableType == TableType.KHOANPHI) {
            table_name = "khoanphi";
        } else {
            table_name = "themkhoanphi";
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<KhoanPhi> getAll() {
        List<KhoanPhi> danhSachKhoanPhi = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM khoanphi");
            statement.setString(1, table_name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                KhoanPhi khoanphi = _get(resultSet);
                danhSachKhoanPhi.add(khoanphi);
            }
        } catch (SQLException e) {
        
        }
        return danhSachKhoanPhi;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<KhoanPhi> get(Integer idPhi) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM khoanphi WHERE idPhi = ?");
            statement.setString(1, table_name);
            statement.setInt(2, idPhi);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                KhoanPhi khoanphi = _get(resultSet);
                return Optional.of(khoanphi);
            }
        } catch (SQLException e) {
        
        }
        return Optional.empty();
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull KhoanPhi khoanphi) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO khoanphi" +
                    "(idPhi, kieuPhi, noiDungThuPhi, mucPhi, ngayTao, ngayKetThuc, tieuDeKhoanPhi " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, table_name);
            _setValuesForStatement(khoanphi, statement, 3);
            statement.executeUpdate();
        } catch (SQLException e) {
        
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NotNull KhoanPhi khoanphi) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE khoanphi SET" +
                    "kieuPhi = ?, " +
                    "noiDungPhi = ?, " +
                    "mucPhi= ?, " +
                    "ngayTao = ?, " +
                    "ngayKetThuc = ?, " +
                    "tieuDeKhoanPhi = ?, " +
                    "WHERE idPhi = ?");
            statement.setString(1, table_name);
            int parameterIndex = _setValuesForStatement(khoanphi, statement, 2);
            statement.setInt(parameterIndex, khoanphi.getIdPhi());
            statement.executeUpdate();
        } catch (SQLException e) {
        
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull KhoanPhi khoanphi) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ? WHERE idPhi = ?");
            statement.setString(1, table_name);
            statement.setInt(2, khoanphi.getIdPhi());
            statement.executeUpdate();
        } catch (SQLException e) {
        
        }
    }
    
    /**
     * Phương thức private để chuyển đổi dữ liệu từ ResultSet thành đối tượng KhoanPhi.
     *
     * @param resultSet ResultSet chứa dữ liệu từ cơ sở dữ liệu.
     * @return Đối tượng KhoanPhi được tạo từ dữ liệu ResultSet.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    private KhoanPhi _get(ResultSet resultSet) throws SQLException {
        KhoanPhi khoanphi = new KhoanPhi();
        khoanphi.setIdPhi(resultSet.getInt("idPhi"));
        khoanphi.setKieuphi(resultSet.getString("kieuPhi"));
        khoanphi.setNoidungphi(resultSet.getString("noiDungPhi"));
        khoanphi.setMucphi(resultSet.getInt("mucPhi"));
        khoanphi.setNgaytao(resultSet.getTimestamp("ngayTao").toLocalDateTime());
        khoanphi.setNgayketthuc(resultSet.getTimestamp("ngayKetThuc").toLocalDateTime());
        khoanphi.setTieudephi(resultSet.getString("tieuDeKhoanPhi"));
        return khoanphi;
    }
    
    /**
     * Phương thức private để thiết lập giá trị cho PreparedStatement khi thêm hoặc cập nhật KhoanPhi.
     *
     * @param khoanphi  Đối tượng KhoanPhi cần được thêm hoặc cập nhật.
     * @param statement PreparedStatement đang được chuẩn bị.
     * @param index     Index bắt đầu để thiết lập giá trị trong PreparedStatement.
     * @return Index tiếp theo sẽ được sử dụng cho các giá trị khác nếu cần.
     * @throws SQLException Nếu có lỗi khi thiết lập giá trị trong PreparedStatement.
     */
    private int _setValuesForStatement(KhoanPhi khoanphi, PreparedStatement statement, int index) throws SQLException {
        statement.setInt(index, khoanphi.getIdPhi());
        statement.setString(index + 1, khoanphi.getKieuphi());
        statement.setString(index + 2, khoanphi.getNoidungphi());
        statement.setInt(index + 3, khoanphi.getMucphi());
        statement.setTimestamp(index + 4, Timestamp.valueOf(khoanphi.getNgaytao()));
        statement.setTimestamp(index + 5, Timestamp.valueOf(khoanphi.getNgayketthuc()));
        statement.setString(index + 6, khoanphi.getTieudephi());
        return index + 7;
    }
}
