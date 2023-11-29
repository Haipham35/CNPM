package sample.dao;

import sample.model.TaiKhoanNhanKhau;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class TaiKhoanNhanKhauDAO triển khai interface DataAccessObject để thao tác với đối tượng TaiKhoanNhanKhau trong cơ sở dữ liệu.
 */
public class TaiKhoanNhanKhauDAO implements DataAccessObject<TaiKhoanNhanKhau, String> {
    private final Connection connection;

    /**
     * Khởi tạo một đối tượng TaiKhoanNhanKhauDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public TaiKhoanNhanKhauDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaiKhoanNhanKhau> getAll() {
        List<TaiKhoanNhanKhau> danhSachTaiKhoanNhanKhau = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM taikhoan");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TaiKhoanNhanKhau taikhoannhankhau = _get(resultSet);
                danhSachTaiKhoanNhanKhau.add(taikhoannhankhau);
            }
        } catch (SQLException e) {

        }
        return danhSachTaiKhoanNhanKhau;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaiKhoanNhanKhau> get(String soCCCD) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM taikhoan WHERE soCccd = ?");
            statement.setString(1, soCCCD);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TaiKhoanNhanKhau taikhoannhankhau = _get(resultSet);
                return Optional.of(taikhoannhankhau);
            }
        } catch (SQLException e) {

        }
        return Optional.empty();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull TaiKhoanNhanKhau taikhoannhankhau) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO taikhoan" +
                    "(soCCCD, tenTaiKhoan, matKhau) " +
                    "VALUES (?, ?, ?)");
            _setValuesForStatement(taikhoannhankhau, statement, 1);
            statement.executeUpdate();
            System.out.println("thong tin tai khoan moi da duoc luu");
        } catch (SQLException e) {

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NotNull TaiKhoanNhanKhau taikhoannhankhau) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE taikhoan SET" +
                    "tenTaiKhoan = ?, " +
                    "matKhau = ?, " +
                    "WHERE soCccd = ?");
            int parameterIndex = _setValuesForStatement(taikhoannhankhau, statement, 1);
            statement.setString(parameterIndex, taikhoannhankhau.getSoCCCD());
            statement.executeUpdate();
            System.out.println("thong tin tai khoan da duoc thay doi");
        } catch (SQLException e) {

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull TaiKhoanNhanKhau taikhoannhankhau) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM taikhoan WHERE soCCCD = ?");
            statement.setString(1, taikhoannhankhau.getSoCCCD());
            statement.executeUpdate();
            System.out.println("thong tin tai khoan da duoc xoa");
        } catch (SQLException e) {

        }
    }

    /**
     * Phương thức private để chuyển đổi dữ liệu từ ResultSet thành đối tượng TaiKhoanNhanKhau.
     *
     * @param resultSet ResultSet chứa dữ liệu từ cơ sở dữ liệu.
     * @return Đối tượng TaiKhoanNhanKhau được tạo từ dữ liệu ResultSet.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    private TaiKhoanNhanKhau _get(ResultSet resultSet) throws SQLException {
        String soCCCD = resultSet.getString("soCCCD");
        String tenTaiKhoan = resultSet.getString("tenTaiKhoan");
        String matKhau = resultSet.getString("matKhau");

        return new TaiKhoanNhanKhau(soCCCD, tenTaiKhoan, matKhau);
    }

    /**
     * Phương thức private để thiết lập giá trị cho PreparedStatement khi thêm hoặc cập nhật TaiKhoanNhanKhau.
     *
     * @param taikhoannhankhau  Đối tượng TaiKhoanNhanKhau cần được thêm hoặc cập nhật.
     * @param statement PreparedStatement đang được chuẩn bị.
     * @param index     Index bắt đầu để thiết lập giá trị trong PreparedStatement.
     * @return Index tiếp theo sẽ được sử dụng cho các giá trị khác nếu cần.
     * @throws SQLException Nếu có lỗi khi thiết lập giá trị trong PreparedStatement.
     */
    private int _setValuesForStatement(TaiKhoanNhanKhau taikhoannhankhau, PreparedStatement statement, int index) throws SQLException {
        statement.setString(index++, taikhoannhankhau.getSoCCCD());
        statement.setString(index++, taikhoannhankhau.getTentaikhoan());
        statement.setString(index++, taikhoannhankhau.getPass());

        return index;
    }


}
