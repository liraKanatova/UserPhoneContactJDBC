package peaksoft.dao.daoImpl;

import peaksoft.config.Configuration;
import peaksoft.dao.UserDao;
import peaksoft.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {


    @Override
    public void createUserTable() {
        String sql="create table if not exists users(" +
                "id serial primary key," +
                "full_name varchar," +
                "age int);";
        try(Connection connection = Configuration.connectionTODatabase();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("users table is created");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void saveUser(User user) {
        String sql="insert into users(" +
                "full_name,age)" +
                "values(?,?)";
        try(Connection connection = Configuration.connectionTODatabase()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getFullName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.executeUpdate();
            System.out.println(user.getFullName()+" is saved");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        String sql ="select * from users where id=?;";
        try (Connection connection = Configuration.connectionTODatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setAge(resultSet.getInt("age"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from users";
        List<User>getAll = new ArrayList<>();
        try(Connection connection = Configuration.connectionTODatabase();
        Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
    getAll.add(new User(
            resultSet.getLong("id"),
            resultSet.getString("fullName"),
            resultSet.getInt("age")));

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return getAll;
    }

    @Override
    public void updateUserInfo(Long id, User user) {
        String sql ="update users set" +
                " full_name = ?," +
                "age = ? " +
                "where id = ?;";
        try(Connection connection = Configuration.connectionTODatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,user.getFullName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.setLong(3,id);
            preparedStatement.executeUpdate();
            System.out.println("User with id:" +id+ "successfully updated!!!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void cleanUserTable() {
        String sql="delete from users;";
        try (Connection connection = Configuration.connectionTODatabase();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("successfully cleaned");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void dropUserTable() {
        String sql ="drop table  users if exists;";
        try(Connection connection = Configuration.connectionTODatabase();
        Statement statement = connection.createStatement()){
            statement .executeUpdate(sql);
            System.out.println("users is table deleted...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteById(Long id) {
String sql= "delete from users where id=?;";
try(Connection connection = Configuration.connectionTODatabase();
PreparedStatement preparedStatement = connection.prepareStatement(sql)){
preparedStatement.setLong(1,id);
preparedStatement.executeUpdate();
    System.out.println("successfully deleted");
}catch (SQLException e){
    System.out.println(e.getMessage());
}
    }

    @Override
    public List<User> getAllSortedUsers(String ascOrDeck) {
        List<User> sortUsers = new ArrayList<>();
        String sort = null;
        if(ascOrDeck.equals("asc")){
            sort = "select * from users order by full_name;";
        }else if(ascOrDeck.equals("desc")){
            sort = "select * from users order by full_name desc;";
        }
        try(Connection connection = Configuration.connectionTODatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sort)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
               user.setId(resultSet.getLong("id"));
              user.setFullName(resultSet.getString("full_name"));
                user.setAge(resultSet.getInt("age"));
                sortUsers.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return sortUsers;
    }
}