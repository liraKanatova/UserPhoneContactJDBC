package peaksoft.service.serviceImpl;

import peaksoft.dao.PhoneDao;
import peaksoft.dao.daoImpl.PhoneDaoImpl;
import peaksoft.models.Phone;
import peaksoft.service.PhoneService;

import java.util.List;

public class PhoneServiceImpl implements PhoneService {
    PhoneDao phoneDao = new PhoneDaoImpl();

    @Override
    public void createPhoneTable() {
        phoneDao.createPhoneTable();
    }

    @Override
    public void savePhone(Phone phone) {
        phoneDao.savePhone(phone);

    }

    @Override
    public void updatePhone(Long id, Phone phone) {
        phoneDao.updatePhone(id, phone);

    }

    @Override
    public List<Phone> getAllUserPhone(Long userId) {
        return phoneDao.getAllUserPhone(userId);
    }

    @Override
    public List<Phone> getAllUserSortedUserPhone(Long userId, String ascOrDesc) {
        return phoneDao.getAllUserSortedUserPhone(userId, ascOrDesc);
    }

    @Override
    public Phone getYoungerUserPhone() {
        return phoneDao.getYoungerUserPhone();
    }

    @Override
    public Phone getPhoneById(Long id) {
        return phoneDao.getPhoneById(id);
    }

    @Override
    public void deletePhoneById(Long id) {
        phoneDao.deletePhoneById(id);
    }
}
