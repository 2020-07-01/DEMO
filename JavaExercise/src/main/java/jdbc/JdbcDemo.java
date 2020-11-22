package jdbc;

import java.sql.*;

/**
 * @ClassName : JdbcDemo
 * @Author : yq
 * @Date: 2020-11-16
 * @Description :
 */
public class JdbcDemo {

    public static void main(String[] args) {

        String url = "";
        String userName = "";
        String passWord = "";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,userName,passWord);

            /**
             * sql语句对象
             */
            Statement statement = connection.createStatement();

            statement.execute("");
            ResultSet resultSet = statement.executeQuery("");



            PreparedStatement preparedStatement = connection.prepareStatement("");

            Boolean p = preparedStatement.execute();

            ResultSet resultSet1 = statement.executeQuery("");

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }
}
