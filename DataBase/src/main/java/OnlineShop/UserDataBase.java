package OnlineShop;

import java.sql.*;

public class UserDataBase {
    public UserDataBase(Statement statement) throws SQLException {
        String sqlQuery = "create table if not exists user(" +
                "id int primary key auto_increment," +
                "username varchar(40)," +
                "password varchar(40)," +
                "first_name varchar(40)," +
                "last_name varchar(40)," +
                "phone_number varchar(40)," +
                "email varchar(40)," +
                "address text," +
                "loggedin_status tinyint(1) default false);";
        statement.execute(sqlQuery);
    }

    public ResultSet getUser(String userName, Statement statement) throws SQLException {
        String sqlQuery = "select * from user where username = " + "\"" + userName + "\"" + ";";
        return statement.executeQuery(sqlQuery);
    }

    public void setLoggedInStatus(String userName, boolean status, Statement statement) throws SQLException {
        String sqlQuery = "update user set loggedin_status = " + status + " where username = " + "\"" + userName + "\";";
        statement.execute(sqlQuery);
    }

    public boolean canOrderMoreItem(int userID, Statement statement) throws SQLException {
        String sqlQuery = "select count(user_id) as \"num_of_orders\" from cart where user_id = " + userID + ";";
        ResultSet result = statement.executeQuery(sqlQuery);

        while(result.next())
            return result.getInt("num_of_orders") < 5;
        return false;
    }
    
    public void insertUser(String username, String password, String firstName, String lastName,
                           String phoneNumber, String email, String address, Statement statement) throws SQLException {
        String sqlQuery = "insert into user(" +
                "username, password, first_name, last_name, phone_number, email, address)" +
                "values(" +
                "\"" + username + "\", " +
                "\"" + password + "\", " +
                "\"" + firstName + "\", " +
                "\"" + lastName + "\", " +
                "\"" + phoneNumber + "\", " +
                "\"" + email + "\", " +
                "\"" + address + "\");";
        statement.execute(sqlQuery);
    }

    public void displayUserInfo(String userName, Statement statement) throws SQLException{
        String sqlQuery = "select * from user where username = " + "\"" + userName + "\"" + ";";
        ResultSet users = statement.executeQuery(sqlQuery);

        while(users.next()){
            System.out.printf("username: %s  ", users.getString("username"));
            System.out.printf("first_name: %s  ", users.getString("first_name"));
            System.out.printf("last_name: %s  ", users.getString("last_name"));
            System.out.printf("phone_number: %s  ", users.getString("phone_number"));
            System.out.printf("email: %s  ", users.getString("email"));
            System.out.printf("address: %s\n", users.getString("address"));
        }
    }
}
