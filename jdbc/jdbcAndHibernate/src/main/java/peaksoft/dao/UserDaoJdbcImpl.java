package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String SQL = "create table if not exists users ("+
                "id serial ,"+
                "name varchar (225),"+
                "Lastname varchar (225),"+
                "age integer not null," +
                "primary key (id))";
        try( Connection connection = Util.util()) {

            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.executeUpdate();
            System.out.println("Creat table");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String dropSql = "drop table if exists users";
        try (Connection connection = Util.util()) {
            PreparedStatement statement = connection.prepareStatement(dropSql);
            statement.executeUpdate();
            System.out.println("Drop table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    public void saveUser(String name, String lastName, byte age) {
       try(Connection connection = Util.util()){
         PreparedStatement psmt =
                 connection.prepareStatement("insert into users( name,  lastName, age) values (?,?,?);");
           psmt.setString(1,name);
           psmt.setString(2,lastName);
           psmt.setByte(3,age);
            psmt.executeUpdate();
           System.out.println(name + " " + "add to dataBase");
       }catch (SQLException e){
           System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String removeUserSql = "delete from users where id = ? ";
        try (Connection connection = Util.util()) {
            PreparedStatement statement = connection.prepareStatement(removeUserSql);
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println(id + " " + "User delete by id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
        public List<User> getAllUsers () {
            String getAllSql = "select * from users";
            List<User> userList = new ArrayList<>();
            try (Connection connection = Util.util();
                 PreparedStatement psmt =
                         connection.prepareStatement(getAllSql);
                 ResultSet resultSet = psmt.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    System.out.println(userList);

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return userList;
        }

        public void cleanUsersTable () {
            String cleanSql = "truncate users";
            try (Connection connection = Util.util()) {
                PreparedStatement statement = connection.prepareStatement(cleanSql);
                statement.executeUpdate();
                System.out.println("Cleaned users table");
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }


        }
    }