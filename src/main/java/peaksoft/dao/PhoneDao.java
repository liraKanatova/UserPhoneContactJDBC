package peaksoft.dao;

import peaksoft.models.Phone;

import java.util.List;

public interface PhoneDao {
    void createPhoneTable();

    void savePhone(Phone phone);

    void updatePhone(Long id, Phone phone);

    List<Phone> getAllUserPhone(Long userId);

    List<Phone> getAllUserSortedUserPhone(Long userId, String ascOrDesc);

    Phone getYoungerUserPhone();

    Phone getPhoneById(Long id);

    void deletePhoneById(Long id);
}
