package mpp.lab03;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repo {
    private Connection connection;

    public Repo(Connection connection) {
        this.connection = connection;
    }

    void addCeai(Ceai ceai) throws SQLException {
        try (var cmd = connection.prepareStatement("INSERT INTO ceaiuri (id, denumire, culoare) VALUES (NULL, ?, ?)")) {
            cmd.setString(1, ceai.denumire());
            cmd.setString(2, ceai.culoare());
            cmd.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    void removeCeai(int id) throws  SQLException {
        try (var cmd = connection.prepareStatement("DELETE FROM ceaiuri WHERE id = ?")) {
            cmd.setInt(1, id);
            cmd.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    void modifyCeai(Ceai ceai) throws SQLException {
        try (var cmd = connection.prepareStatement("UPDATE ceaiuri SET denumire = ?, culoare = ? WHERE id = ?")) {
            cmd.setString(1, ceai.denumire());
            cmd.setString(2, ceai.culoare());
            cmd.setInt(3, ceai.id());
            cmd.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    List<Ceai> getCeaiuriCuCuloriLungi() throws SQLException {
        try (var cmd = connection.prepareStatement("SELECT * FROM ceaiuri WHERE length(culoare) > 3");
             var rs = cmd.executeQuery()) {
            var result = new ArrayList<Ceai>();
            while (rs.next()) {
                var id = rs.getInt("id");
                var denumire = rs.getString("denumire");
                var culoare = rs.getString("culoare");
                result.add(new Ceai(id, denumire, culoare));
            }
            return result;
        } catch (SQLException e) {
            throw e;
        }
    }
}
