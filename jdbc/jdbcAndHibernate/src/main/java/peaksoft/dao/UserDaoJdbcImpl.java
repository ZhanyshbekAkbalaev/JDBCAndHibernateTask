package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    Connection connection;

    public  UserDaoJdbcImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        String creat = """
                create table if not exists users(
                id bigserial primary key,
                name varchar(50)not null,
                last_name varchar(50) not null,
                age smallint not null);""";
        try (Statement statement = connection.createStatement()){
            statement.execute(creat);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {
        String query = """
                drop table users;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            if (name == null || lastName == null || age == 0){
               throw  new NullPointerException("null || 0") ;
            }
        }catch (RuntimeException e){
            throw new RuntimeException("null");
        }
        String query = """
                insert into users(name,last_name , age)
                values (?,?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void   removeUserById(long id) {
        String sql  = """
               delete from users where id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = """
                select name ,last_name,age from users;
                """;
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString(1));
                user.setLastName(resultSet.getString(2));
                user.setAge(resultSet.getByte(3));
                users.add(user);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void   cleanUsersTable() {
        String sql = """
                drop table users;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}