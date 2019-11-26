package OnlineShop;

import java.sql.*;

public class Shop {
    public Shop(Statement statement) throws SQLException {
        String sqlQuery = "create table if not exists shop(id int primary key, product_name varchar(40), price double);";
        statement.execute(sqlQuery);
    }

    public void init(Statement statement) throws SQLException {
        String sqlQuery = "select * from shop;";
        ResultSet rs = statement.executeQuery(sqlQuery);

        if(!rs.next()) {
            statement.execute("insert into shop(id, product_name, price) values(1, \"book1\", 2000)");
            statement.execute("insert into shop(id, product_name, price) values(2, \"tv1\", 3000)");
            statement.execute("insert into shop(id, product_name, price) values(3, \"magazine1\", 4000)");
            statement.execute("insert into shop(id, product_name, price) values(4, \"magazine2\", 5000)");
            statement.execute("insert into shop(id, product_name, price) values(5, \"radio1\", 6000)");
            statement.execute("insert into shop(id, product_name, price) values(6, \"sneaker1\", 7000)");
            statement.execute("insert into shop(id, product_name, price) values(7, \"dressedshoe1\", 8000)");
            statement.execute("insert into shop(id, product_name, price) values(8, \"book2\", 9000)");
            statement.execute("insert into shop(id, product_name, price) values(9, \"radio2\", 10000)");
            statement.execute("insert into shop(id, product_name, price) values(10, \"tv2\", 11000)");
            statement.execute("insert into shop(id, product_name, price) values(11, \"tv3\", 12000)");
            statement.execute("insert into shop(id, product_name, price) values(12, \"sneaker2\", 13000)");
            statement.execute("insert into shop(id, product_name, price) values(13, \"dressedshoe2\", 14000)");
            statement.execute("insert into shop(id, product_name, price) values(14, \"sneaker3\", 14500)");
            statement.execute("insert into shop(id, product_name, price) values(15, \"book3\", 10800)");
            statement.execute("insert into shop(id, product_name, price) values(16, \"radio3\", 10400)");
            statement.execute("insert into shop(id, product_name, price) values(17, \"dressedshoe3\", 10200)");
            statement.execute("insert into shop(id, product_name, price) values(18, \"sneaker4\", 13000)");
            statement.execute("insert into shop(id, product_name, price) values(19, \"tv4\", 13800)");
            statement.execute("insert into shop(id, product_name, price) values(20, \"book4\", 11300)");
        }
    }

    public void displayProducts(Statement statement) throws SQLException{
        String sqlQuery = "select * from shop;";
        ResultSet products = statement.executeQuery(sqlQuery);

        while (products.next()) {
            System.out.printf("ID:%d  ", products.getInt("id"));
            System.out.printf("Name:%s  ", products.getString("product_name"));
            System.out.printf("Price:%s\n", products.getDouble("price"));
        }
    }

    public ResultSet getProductByID(int productID, Statement statement) throws SQLException {
        String sqlQuery = "select * from shop where id = " + productID + ";";
        return statement.executeQuery(sqlQuery);
    }

    public void insertItem(int productID, String productName, double price, Statement statement) throws SQLException{
        String sqlQuery = "insert into shop(id, product_name, price)" +
                        " values (" + productID + ", " + "\"" + productName + "\"" + ", " + price + ");";

        statement.execute(sqlQuery);
    }

    public void removeItem(int productID, Statement statement) throws SQLException {
        String sqlQuery = "delete from shop where id = " + productID + ";";
        statement.execute(sqlQuery);
    }
}
