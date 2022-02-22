package peaksoft.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();

            String sql = "CREATE TABLE IF NOT EXISTS USERS" +
                    "  id      SERIAL    PRIMARY KEY " +
                    "  name     VARCHAR(250) DEFAULT NULL," +
                    "  lastname VARCHAR(250) DEFAULT NULL," +
                    "  age      TINYINT      DEFAULT NULL)";
            session.beginTransaction();
            session.createSQLQuery(sql);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("Delete From User");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Успешное удаление всех пользователей");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession() ;
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
            System.out.println(name + " " + "add to dataBase");
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete User where id = :id")
                    .setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println(id + " " + "User delete by id");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        userList = session.createQuery("from User").list();
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Cleaned users table");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

