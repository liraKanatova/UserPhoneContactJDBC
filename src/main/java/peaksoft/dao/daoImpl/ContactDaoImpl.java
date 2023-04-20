package peaksoft.dao.daoImpl;

import peaksoft.config.Configuration;
import peaksoft.dao.ContactDao;
import peaksoft.models.Contact;
import peaksoft.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    @Override
    public void createContactTable() {
        String sql = "create table if not exists contacts(" +
                "id serial primary key," +
                "contact_name varchar," +
                "phone_number int," +
                "phone_id int references phones(id));";
        try (Connection connection = Configuration.connectionTODatabase();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("users table is created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void saveContact(Contact contact) {
        String sql = "insert into contacts(contact_name,phone_number,phone_id)" +
                "values(?,?,?);";
        try (Connection connection = Configuration.connectionTODatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, contact.getContactName());
            preparedStatement.setInt(2, contact.getPhoneNumber());
            preparedStatement.setInt(3, contact.getPhoneId());
            preparedStatement.executeUpdate();
            System.out.println(contact.getContactName() + " is saved");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Contact getContactById(Long id) {
        Contact contact = null;
        String sql = "select * from contacts where id=?;";
        try (Connection connection = Configuration.connectionTODatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                contact = new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setContactName(resultSet.getString("contact_name"));

                contact.setPhoneId(resultSet.getInt("phone_id"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        String sql = "select * from contacts;";
        try (Connection connection = Configuration.connectionTODatabase();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                contactList.add(new Contact(resultSet.getString("contact_name"),
                        resultSet.getInt("phone_number"),
                        resultSet.getInt("phone_id")));
            }
            resultSet.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contactList;
    }

    @Override
    public List<Contact> getAllPhoneContacts(Long contactId) {
        List<Contact> contactList = new ArrayList<>();
        String sql = "SELECT c.contactId, c.contactName,p.phone " +
                "FROM contacts c " +
                "JOIN phones p ON p.id =c.phone_id  " +
                "WHERE c.id != ?";
        try (Connection connection = Configuration.connectionTODatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, contactId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setContactName(resultSet.getString("contact_name"));
                contact.setPhoneNumber(resultSet.getInt("phone_number"));

                contact.setPhoneId(resultSet.getInt("phone_id"));
                contactList.add(contact);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return contactList;
    }


    @Override
    public List<Contact> getAllUserContacts(Long userId) {
        String sql = "select c.* from users join phones p on p.user_id = p.id " +
                "join contacts c on p.id = c.phone_id ";
        List<Contact> contactList = new ArrayList<>();
        try (Connection connection = Configuration.connectionTODatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contactList.add(new Contact(
                        resultSet.getLong("id"),
                        resultSet.getString("contact_name"),
                        resultSet.getInt("phone_number"),
                        resultSet.getInt("phone_id")
                ));
                resultSet.close();

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contactList;
    }

    @Override
    public void getPhoneContactsCount(Long phoneId) {
        String sql = "select p.brand,count(c.id) from contacts c join phones p on p.id = c.phone_id where p.id = ? group by p.brand ;";
        try (Connection connection = Configuration.connectionTODatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, phoneId);
            ResultSet resultSet = preparedStatement.executeQuery();
            String brand = "";
            int count = 0;
            while (resultSet.next()) {
                brand = resultSet.getString(1);
                count = resultSet.getInt(2);
            }
            System.out.println(brand + " count " + count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void getUserContactsCount(Long userId) {
        String sql = "select u.first_name,count(c.id) from contacts c join users u on u.id=c.user_id where u.id = ? group by u.first_name;";
        try (Connection connection = Configuration.connectionTODatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            String firstName = "";
            while (resultSet.next()) {
                firstName = resultSet.getString(1);
                count = resultSet.getInt(2);
            }
            System.out.println(firstName + "" + count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateContactInfo(Long id, Contact contact) {
     String sql = "update contacts set contact_name = ?,phone_number = ?,phone_id = ? where id = ?;";
     try(Connection connection = Configuration.connectionTODatabase();
     PreparedStatement preparedStatement = connection.prepareStatement(sql)){
         preparedStatement.setString(1,contact.getContactName());
         preparedStatement.setInt(2,contact.getPhoneNumber());
         preparedStatement.setLong(3,contact.getPhoneId());
         preparedStatement.setLong(4,id);
         preparedStatement.executeUpdate();
         System.out.println("successfully updated");
     }catch (SQLException e){
         System.out.println(e.getMessage());
     }
    }

    @Override
    public void deleteAllPhoneContactsByPhoneId(Long phoneId) {
        String sql = "delete from contacts c using phones p where c.phone_id= ?;";
        try (Connection connection = Configuration.connectionTODatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1,phoneId);
        int row = preparedStatement.executeUpdate();
            System.out.println(row + " deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
