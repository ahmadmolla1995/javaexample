package OnlineShop;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static Shop shop;
    private static Cart userCart;
    private static UserDataBase database;
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/online_shop";

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();

            shop = new Shop(statement);
            shop.init(statement);
            database = new UserDataBase(statement);
            userCart = new Cart(statement);

            while (true) {
                displayMenu();
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter option:");
                int choice = scanner.nextInt();

                if (choice == 1)
                    signUp(statement);
                else if (choice == 2)
                    signIn(statement);
                else {
                    System.out.print("user name:");
                    String userName = scanner.next();
                    ResultSet user = database.getUser(userName, statement);
                    if (!user.next() || !user.getBoolean("loggedin_status"))
                        System.out.println("You should login before any request!");

                    else if(choice == 3)
                        selectProductFromShop(user, statement);
                    else if(choice == 4)
                        removeItemFromCart(user, statement);
                    else if(choice == 5)
                        userCart.verifyUserPurchase(user.getInt("id"), statement);
                    else if(choice == 6)
                        userCart.displayUserCart(user.getInt("id"), statement);
                    else if (choice == 7)
                        shop.displayProducts(statement);
                    else if(choice == 8)
                        database.setLoggedInStatus(userName, false, statement);
                    else if (choice == 9)
                        database.displayUserInfo(userName, statement);
                    else if(choice == 10)
                        break;
                    else
                        System.out.println("Incorrect input option! Enter again.");
                }
            }
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
        } catch (SQLException e) {
            System.out.println("Your sql query is wrong!");
        } catch (NoSuchElementException e) {
            System.out.println("The program terminated!");
        }
    }

    public static void displayMenu() {
        System.out.print("1.SignUp  ");
        System.out.print("2.SignIn  ");
        System.out.print("3.SelectProduct  ");
        System.out.print("4.RemoveProductFromCart  ");
        System.out.print("5.VerifyPurchase  ");
        System.out.print("6.DisplayCart  ");
        System.out.print("7.DisplayStoreProducts  ");
        System.out.print("8.SignOut  ");
        System.out.print("9.DisplayUserInfo  ");
        System.out.print("10.Exit\n\n");
    }

    public static void signUp(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("user name:");
        String username = scanner.next();

        String sqlQuery = "select * from user where username = " + "\"" + username + "\"" + ";";
        ResultSet rs = statement.executeQuery(sqlQuery);

        if (rs.next())
            System.out.println("User Exists in DataBase!");
        else {
            System.out.print("password:");
            String password = scanner.next();
            System.out.print("first name:");
            String firstName = scanner.next();
            System.out.print("last name:");
            String lastName = scanner.next();
            System.out.print("email:");
            String email = scanner.next();
            System.out.print("phone number:");
            String phoneNumber = scanner.next();
            System.out.print("province:");
            String province = scanner.next();
            System.out.print("city:");
            String city = scanner.next();
            System.out.print("avenue:");
            String avenue = scanner.next();
            System.out.print("zip code:");
            String zipCode = scanner.next();

            String address = province + '-' + city + '-' + avenue + '-' + zipCode;
            database.insertUser(username, password, firstName, lastName, phoneNumber, email, address, statement);
        }
    }

    public static void signIn(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("user name:");
        String userName = scanner.next();
        System.out.print("password:");
        String password = scanner.next();

        ResultSet user = database.getUser(userName, statement);
        if(!user.next())
            System.out.println("User not found! Please try again.");
        else if (!user.getString("password").equals(password))
            System.out.println("Wrong password!");
        else
            database.setLoggedInStatus(userName, true, statement);
    }

    public static void selectProductFromShop(ResultSet user, Statement statement) throws SQLException {
        int userID = user.getInt("id");
        if(!database.canOrderMoreItem(userID, statement)) {
            System.out.println("User Cart is Full! You can't order more elements.");
            return;
        }

        shop.displayProducts(statement);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Product ID:");
        int productID = scanner.nextInt();

        ResultSet product = shop.getProductByID(productID, statement);
        if(!product.next()) {
            System.out.println("Product ID not found!");
            return;
        }

        String productName = product.getString("product_name");
        double productPrice = product.getDouble("price");
        shop.removeItem(productID, statement);
        userCart.addItem(userID, productID, productName, productPrice, statement);
    }

    public static void removeItemFromCart(ResultSet user, Statement statement) throws SQLException{
        int userID = user.getInt("id");

        userCart.displayUserCart(userID, statement);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Product ID:");
        int productID = scanner.nextInt();

        ResultSet product = userCart.getItem(userID, productID, statement);
        if (!product.next()){
            System.out.println("Item is not in your cart!");
            return;
        }

        String productName = product.getString("product_name");
        double price = product.getDouble("price");
        userCart.removeItem(productID, statement);
        shop.insertItem(productID, productName, price, statement);
    }
}
