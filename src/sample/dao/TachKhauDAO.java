package sample.dao;

import sample.model.TachKhau;
import sample.model.key.TachKhauKey;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Class TachKhauDao triển khai interface DataAccessObject để thao tác với đối tượng TachKhau trong cơ sở dữ liệu.
 */

public class TachKhauDAO implements DataAccessObject<TachKhau, TachKhauKey> {

    private final Connection connection;

    /**
     * Khởi tạo một đối tượng TachKhauDao với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */

    public TachKhauDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * * {@inheritDoc}
     */
    @Override
    public List<TachKhau> getAll() {
        List<TachKhau> danhSachTachKhau = new ArrayList<>();
        String sql = "SELECT * FROM tachkhau";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                List<String> danhSachNhanKhau = parseDanhSachNhanKhau(resultSet.getString("danhSachNhanKhau"));

                TachKhau tachKhau = new TachKhau(
                        resultSet.getString("soCccdChuHoMoi"),
                        resultSet.getInt("idHoKhau"),
                        danhSachNhanKhau,
                        resultSet.getString("trangThai"));
                danhSachTachKhau.add(tachKhau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachTachKhau;
    }

    /**
     * * {@inheritDoc}
     */
    @Override
    public Optional<TachKhau> get(TachKhauKey key) {
        String sql = "SELECT * FROM tachkhau WHERE soCccdChuHoMoi = ? AND idHoKhau = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, key.getSoCccdChuHoMoi());
            statement.setInt(2, key.getIdHoKhau());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                List<String> danhSachNhanKhau = parseDanhSachNhanKhau(resultSet.getString("danhSachNhanKhau"));

                TachKhau tachKhau = new TachKhau(
                        resultSet.getString("soCccdChuHoMoi"),
                        resultSet.getInt("idHoKhau"),
                        danhSachNhanKhau,
                        resultSet.getString("trangThai"));
                return Optional.of(tachKhau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * * {@inheritDoc}
     */
    @Override
    public void save(@NotNull TachKhau tachKhau) {
        String sql = "INSERT INTO tachkhau (soCccdChuHoMoi, idHoKhau, danhSachNhanKhau, trangThai) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tachKhau.getSoCccdChuHoMoi());
            statement.setInt(2, tachKhau.getIdHoKhau());
            statement.setString(3, String.join(",", tachKhau.getDanhSachNhanKhau()));
            statement.setString(4, tachKhau.getTrangThai());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * * {@inheritDoc}
     */
    @Override
    public void update(@NotNull TachKhau tachKhau) {
        String sql = "UPDATE tachkhau SET danhSachNhanKhau = ?, trangThai = ? WHERE soCccdChuHoMoi = ? AND idHoKhau = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.join(",", tachKhau.getDanhSachNhanKhau()));
            statement.setString(2, tachKhau.getTrangThai());
            statement.setString(3, tachKhau.getSoCccdChuHoMoi());
            statement.setInt(4, tachKhau.getIdHoKhau());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull TachKhau tachKhau) {
        String sql = "DELETE FROM tachkhau WHERE soCccdChuHoMoi = ? AND idHoKhau = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tachKhau.getSoCccdChuHoMoi());
            statement.setInt(2, tachKhau.getIdHoKhau());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức private để chuyển đổi dữ liệu từ String thành danh sách chứa số CCCD của các thành viên trong hộ khẩu.
     *
     * @param  data chứa dữ liệu từ cơ sở dữ liệu là một xâu chứa các số CCCD liên tiếp, ngăn cách nhau bởi dấu ','
     * (Không dùng dấu cách).
     *
     * @return Danh sách số CCCD của nhân khẩu từ data dưới dạng List
     */
    private List<String> parseDanhSachNhanKhau(String data) {
        return new ArrayList<>(Arrays.asList(data.split(",")));
    }
}

