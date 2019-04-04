package com.dao;

import com.daoInterface.UserDaoInterface;
import com.manager.Manager;
import com.model.Admin;

import java.util.List;

public class AdminUserDao implements UserDaoInterface<Admin, Integer> {

    public Admin findById(Integer id) {
        Manager.beginTransaction();
        Admin admin = Manager.getSession().find(Admin.class, id);
        Manager.commitTransaction();
        return admin;
    }

    public Admin findByLoginAndPassword(String login, String password) {
        Manager.beginTransaction();
        Admin admin = (Admin) Manager.getSession().createQuery("from Admin where" +
                " login = "+ login +" and password =" + password +"");
        Manager.commitTransaction();
        return admin;
    }

    public void delete(Admin entity) {
        Manager.beginTransaction();
        if (!Manager.getSession().contains(entity))
            Manager.getSession().merge(entity);
        Manager.getSession().delete(entity);
        Manager.commitTransaction();
    }

    @SuppressWarnings("unchecked")
    public List<Admin> findAll() {
        Manager.beginTransaction();
        List<Admin> admins = (List<Admin>) Manager.getSession().createQuery("from Admin").list();
        Manager.commitTransaction();
        return admins;
    }

    public void deleteAll() {
        Manager.beginTransaction();
        for (Admin admin : findAll()) {
            if(!Manager.getSession().contains(admin))
                Manager.getSession().merge(admin);
            Manager.getSession().delete(admin);
        }
        Manager.commitTransaction();
    }

    public void saveOrUpdate(Admin entity) {
        Manager.beginTransaction();
        Manager.getSession().saveOrUpdate(entity);
        Manager.commitTransaction();
    }
}
