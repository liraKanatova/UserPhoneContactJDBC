package peaksoft;


import peaksoft.config.Configuration;
import peaksoft.dao.daoImpl.ContactDaoImpl;
import peaksoft.models.Contact;
import peaksoft.models.Phone;
import peaksoft.models.User;
import peaksoft.service.ContactService;
import peaksoft.service.PhoneService;
import peaksoft.service.UserService;
import peaksoft.service.serviceImpl.ContactServiceImpl;
import peaksoft.service.serviceImpl.PhoneServiceImpl;
import peaksoft.service.serviceImpl.UserServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        UserService userService = new UserServiceImpl();
        PhoneServiceImpl phoneService = new PhoneServiceImpl();
        ContactService contactService = new ContactServiceImpl();

    //  System.out.println(Configuration.connectionTODatabase());
    //  userService.createUserTable();
       //   userService.saveUser(new User(4L,"Meerim Saskaraeva",39));
   //     System.out.println(userService.getUserById(2L));
//        System.out.println(userService.getAllUsers());

     //   userService.updateUserInfo(1L,new User("Madina Halikova",16));
    //    userService.deleteById(3L);
//        userService.dropTable();
     //   String ascOrDesc = new Scanner(System.in).next();
     //   System.out.println(userService.getAllSortedUsers(ascOrDesc));


       // phoneService.createPhoneTable();
       // phoneService.savePhone(new Phone("Samsung A10S","Samsung",91000,5L));
     //   phoneService.updatePhone(1L,new Phone("Redmi","Xiaomi",10500,1));
//        System.out.println(phoneService.getAllUserPhone(2L));

//        System.out.println(phoneService.getYoungerUserPhone());
//        System.out.println(phoneService.getPhoneById(4L));
//        phoneService.deletePhoneById(5L);
//        System.out.println(phoneService.getAllUserSortedUserPhone(2L, "asc"));?
//        System.out.println(contactDao.getAllUserContacts(1L));
//contactService.createContactTable();
//        contactService.saveContact(new Contact("Ilim",500102030,2));
//        contactService.saveContact(new Contact("Meerim",500112030,2));
//        contactService.saveContact(new Contact("Nuriza",500102230,2));
//        contactService.getPhoneContactsCount(1L);
//        contactService.updateContactInfo(11L,new Contact("Saltanat",500111222,1));
       // contactService.deleteAllPhoneContactsByPhoneId(2L);
    }

}
