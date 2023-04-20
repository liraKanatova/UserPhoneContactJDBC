package peaksoft.dao;

import peaksoft.models.Contact;

import java.util.List;

public interface ContactDao {
    void createContactTable();

    void saveContact(Contact contact);

    Contact getContactById(Long id);

    List<Contact> getAllContacts();

    List<Contact> getAllPhoneContacts(Long contactId);

    List<Contact> getAllUserContacts(Long userId);

    void getPhoneContactsCount(Long phoneId);

    void getUserContactsCount(Long userId);

    void updateContactInfo(Long id, Contact contact);

    void deleteAllPhoneContactsByPhoneId(Long phoneId);
}
