import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    private DatabaseManager dbManager;

    public UserManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean addCredentials(String url, String username, String password) {
        String sql = "INSERT INTO password_manager (url, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, url);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public void printCredentialsbyUrl(String url) throws SQLException {
        String sql = "Select username, password FROM password_manager WHERE url = ?";
        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, url);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println("Username: " + username + ", Password: " + password);
            }
            if (!found) {
                System.out.println("No credentials found for URL: " + url);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}