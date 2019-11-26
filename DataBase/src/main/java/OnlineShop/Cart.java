package OnlineShop;

import java.sql.*;

public class Cart {
    public Cart(Statement statement) throws SQLException {
        String sqlQuery = "create table if not exists cart(" +
                "id int primary key auto_increment," +
                "user_id int, foreign key(user_id) references user(id)," +
                "product_id int unique ," +
                "product_name varchar(40)," +
                "price double);";
        statement.execute(sqlQuery);
    }

    public void addItem(int userID, int productID, String productName, double price, Statement statement) throws SQLException {
        String sqlQuery = "insert into cart (user_id, product_id, product_name, price) " +
                "values (" + userID + ", " + productID + ", \"" + productName + "\"" + ", " + price + ");";

        statement.execute(sqlQuery);
    }

    public ResultSet getItem(int userID, int productID, Statement statement) throws SQLException {
        String sqlQuery = "select * from cart where user_id = " + userID + " AND " + "product_id = " + productID + ";";
        return statement.executeQuery(sqlQuery);
    }

    public void removeItem(int productID, Statement statement) throws SQLException {
        String sqlQuery = "delete from cart where product_id = " + productID + ";";
        statement.execute(sqlQuery);
    }

    public void verifyUserPurchase(int userID, Statement statement) throws SQLException {
        String sqlQuery = "select sum(price) as \"total_cost\" from cart where user_id = " + userID + ";";
        ResultSet result = statement.executeQuery(sqlQuery);

        while (result.next()) {
            int price = result.getInt("total_cost");
            System.out.printf("Total Purchase Cost: %d\n\n", price);
        }

        sqlQuery = "delete from cart where user_id = " + userID + ";";
        statement.execute(sqlQuery);
    }

    public void displayUserCart(int userID, Statement statement) throws SQLException {
        String sqlQuery = "select * from cart usercart where user_id = " + userID + ";";

        System.out.println("ProductID  Name  Price\n=============================");
        ResultSet rs = statement.executeQuery(sqlQuery);
        while (rs.next()) {
            System.out.printf("%d\t", rs.getInt("product_id"));
            System.out.printf("%s\t", rs.getString("product_name"));
            System.out.printf("%s\n", rs.getDouble("price"));
        }
    }
}
