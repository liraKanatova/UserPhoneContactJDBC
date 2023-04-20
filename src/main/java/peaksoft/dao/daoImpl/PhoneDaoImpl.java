package peaksoft.dao.daoImpl;

import peaksoft.config.Configuration;
import peaksoft.dao.PhoneDao;
import peaksoft.models.Phone;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDaoImpl implements PhoneDao {

    @Override
    public void createPhoneTable() {
       String sql = "create table if not exists phones(" +
               "id serial primary key," +
               "model varchar," +
               "brand varchar," +
               "price int," +
               "user_id int references users(id));";
       try(Connection connection = Configuration.connectionTODatabase();
       Statement statement = connection.createStatement()){
           statement.executeUpdate(sql);
           statement.close();
           System.out.println("phones table is created");
        }catch (SQLException e){
           System.out.println(e.getMessage());
       }
    }



    @Override
    public void savePhone(Phone phone) {
        String sql="insert into phones(model,brand,price,user_id)" +
                "values(?,?,?,?);";
        try(Connection connection = Configuration.connectionTODatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        preparedStatement.setString(1,phone.getModel());
        preparedStatement.setString(2,phone.getBrand());
        preparedStatement.setInt(3,phone.getPrice());
        preparedStatement.setLong(4,phone.getUserId());
        preparedStatement.executeUpdate();
            System.out.println(phone.getBrand()+" is saved");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void updatePhone(Long id, Phone phone) {
        String sql="update phones set brand = ?,model = ?,price = ?,user_id = ? where id = ?";
        try(Connection connection = Configuration.connectionTODatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,phone.getBrand());
            preparedStatement.setString(2,phone.getModel());
            preparedStatement.setInt(3,phone.getPrice());
            preparedStatement.setInt(4,phone.getUserId());
            preparedStatement.setLong(5,id);
             preparedStatement.executeUpdate();
                 System.out.println("successfully updated!!!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Phone> getAllUserPhone(Long userId) {
        List<Phone> phones = new ArrayList<>();
        String sql = "select * from phones where user_id = ?;";
        try(Connection connection = Configuration.connectionTODatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                phones.add(new Phone(resultSet.getString("model"),
                        resultSet.getString("brand"),
                        resultSet.getInt("price"),
                        resultSet.getInt("user_id")));
            }
            resultSet.close();
            return phones;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Phone> getAllUserSortedUserPhone(Long userId, String ascOrDesc) {
        List<Phone> phoneList = new ArrayList<>();
        String sort = null;
        if(ascOrDesc.equals("asc")){
            sort ="select * from phones p join users u on p.user_id=u.id where user_id = ? order by price asc";
        }else if (ascOrDesc.equals("desc")){
            sort = "select * from phones p join users u on p.user_id=u.id where user_id = ?order by price desc";
        }
        try(Connection connection = Configuration.connectionTODatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sort)){
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                Phone phone = new Phone(resultSet.getLong("id"),
               resultSet.getString("model"),
               resultSet.getString("brand"),
                resultSet.getInt("price"),
                resultSet.getInt("userId"));
                phoneList.add(phone);
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return phoneList;




    }

    @Override
    public Phone getYoungerUserPhone() {
Phone phone = new Phone();
        String sql = "select * from phones p " +
                "join users u on p.user_id=u.id " +
                "order by u.age asc limit 1;";
        try(Connection connection = Configuration.connectionTODatabase();
        Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                phone.setId(resultSet.getLong("id"));
                phone.setModel(resultSet.getString("model"));
                phone.setBrand(resultSet.getString("brand"));
                phone.setPrice(resultSet.getInt("price"));
                phone.setUserId(resultSet.getInt("user_id"));

            }
resultSet.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return phone;
    }

    @Override
    public Phone getPhoneById(Long id) {
        String sql = "select * from phones where id = ?;";
        try(Connection connection = Configuration.connectionTODatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Phone phone = new Phone();
            while (resultSet.next()){
                phone = new Phone();
                phone.setId(resultSet.getLong("id"));
                phone.setModel(resultSet.getString("model"));
                phone.setBrand(resultSet.getString("brand"));
                phone.setPrice(resultSet.getInt("price"));
                phone.setUserId(resultSet.getInt("user_id"));
            }
            resultSet.close();
            return phone;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deletePhoneById(Long id) {
      String sql = "delete from phones where id = ?;";
      try(Connection connection = Configuration.connectionTODatabase();
      PreparedStatement preparedStatement = connection.prepareStatement(sql)){
          preparedStatement.setLong(1,id);
          preparedStatement.executeUpdate();
          System.out.println("successfully deleted");
      }catch (SQLException e){
          System.out.println(e.getMessage());
      }

    }


    }

