import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\databases\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";


    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + " (" + COLUMN_NAME + " text," + COLUMN_PHONE + " integer," + COLUMN_EMAIL + " text)");

            insertContact(statement,"Tim", 653422, "tim@mail");
            insertContact(statement,"Joe", 422568, "joe@mail");
            insertContact(statement,"Jane", 912422, "jane@mail");
            insertContact(statement,"Fido", 912422, "dog@mail");

            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " + COLUMN_PHONE + "=554322 WHERE " + COLUMN_NAME + "='Jane'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + "='Joe'");

            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while (results.next()) {
                System.out.println(results.getString(COLUMN_NAME) + " " +
                        results.getInt(COLUMN_PHONE) + " " +
                        results.getString(COLUMN_EMAIL));
            }

            results.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something wrong" + e.getMessage());
            e.printStackTrace();
        }


    }

    public static void insertContact(Statement statement, String name, int phone, String email) {
        try {
            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") VALUES('"+name+"'" + ", " +phone+ ", " +"'"+email+"'"+ ")");
        } catch (SQLException e) {
            System.out.println("Something went wrong"+e.getMessage());
        }
    }
}
