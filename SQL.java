package hack;
import java.sql.*;
public class SQL {
    //Установка соединения
    static double inputdata[][] = new double[4][3];
    private Connection connect() {

        String url = "jdbc:sqlite:C:/sqlite/Test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }
    public  void createNewTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Client (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	surname text,\n"
                + "	phone text\n"
                + ");";
        try  {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insert(String name, int id, String surname, String phone) {
        String sql = "INSERT INTO Client (id,name, surname, phone) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setString(4, phone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void selectAll(){
        String sql = "SELECT id, name, surname, phone FROM Client";
        int numrows = 0;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                for (int i=0;i<3;i++){
                    System.out.println(rs.getInt("id") +  "\t" +
                            rs.getString("name") + "\t" +
                            rs.getString("surname") +"\t" +
                            rs.getString("phone"));
                }
                numrows++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public double getData(int i, int j)
    {
        return inputdata[i][j];
    }
}
