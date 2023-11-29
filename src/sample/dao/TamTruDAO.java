package sample.dao;

import sample.model.TamTru;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class TamTruDAO triển khai interface DataAccessObject để thao tác với đối tượng TamTru trong cơ sở dữ liệu.
 */
public class TamTruDAO implements DataAccessObject<TamTru, Integer> {
    private final Connection connection;
    
    /**
     * Khởi tạo một đối tượng TamTruDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public TamTruDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TamTru> getAll() {
        List<TamTru> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM tamvang");
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idTamTru = rs.getInt("idTamTru");
                String soCccd = rs.getString("soCccd");
                LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime();
                LocalDateTime ngayKetThuc = rs.getTimestamp("ngayKetThuc").toLocalDateTime();
                String liDo = rs.getString("liDo");
                
                TamTru tamvang = new TamTru(idTamTru, soCccd, ngayBatDau, ngayKetThuc, liDo);
                list.add(tamvang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Error while retrieving data from the database.");
        }
        
        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull TamTru t) {
        try {
            String sql = "INSERT INTO tamvang (soCccd, ngayBatDau, ngayKetThuc, liDo) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            
            st.setString(1, t.getSoCccd());
            st.setTimestamp(2, Timestamp.valueOf(t.getNgayBatDau()));
            st.setTimestamp(3, Timestamp.valueOf(t.getNgayKetThuc()));
            st.setString(4, t.getLiDo());
            
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Error while saving data to the database.");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NotNull TamTru t) {
        try {
            String sql = "UPDATE tamvang SET soCccd = ?, ngayBatDau = ?, ngayKetThuc = ?, liDo = ? WHERE idTamTru = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            
            st.setString(1, t.getSoCccd());
            st.setTimestamp(2, Timestamp.valueOf(t.getNgayBatDau()));
            st.setTimestamp(3, Timestamp.valueOf(t.getNgayKetThuc()));
            st.setString(4, t.getLiDo());
            st.setInt(5, t.getIdTamTru());
            
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Error while updating data in the database.");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull TamTru t) {
        try {
            String sql = "DELETE FROM tamvang WHERE idTamTru = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            
            st.setInt(1, t.getIdTamTru());
            
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Error while deleting data from the database.");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TamTru> get(Integer id) {
        try {
            String sql = "SELECT * FROM tamvang WHERE idTamTru = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int idTamTru = rs.getInt("idTamTru");
                String soCccd = rs.getString("soCccd");
                LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime();
                LocalDateTime ngayKetThuc = rs.getTimestamp("ngayKetThuc").toLocalDateTime();
                String liDo = rs.getString("liDo");
                
                TamTru tamvang = new TamTru(idTamTru, soCccd, ngayBatDau, ngayKetThuc, liDo);
                return Optional.of(tamvang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Error while retrieving data from the database.");
        }
        
        return Optional.empty();
    }
}
