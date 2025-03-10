package mpp.lab03;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        var props = new Properties();

        try (var reader = new FileReader("db.config")) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var url = props.getProperty("jdbc.url");
        var connection = DriverManager.getConnection(url);

        var repo = new Repo(connection);

        repo.addCeai(new Ceai(0, "Mentă", "verde-violet"));
        repo.modifyCeai(new Ceai(2, "Mentă", "verde"));

        repo.getCeaiuriCuCuloriLungi().forEach(System.out::println);
    }
}