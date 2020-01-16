package com.nefedov.project.DAO;

import com.nefedov.project.mapper.UserMapper;
import com.nefedov.project.model.Role;
import com.nefedov.project.model.UserInfo;
import com.nefedov.project.model.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

    @Autowired
    public UserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public UserInfo findInfoAboutUser(String login){
        String sql = UserMapper.FIND_INFO_ABOUT_USER;
        Object[] params = new Object[] {login, login, login};
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, params, (resultSet, i) -> {
                String username = resultSet.getString("username");
                String surname = resultSet.getString("surname");
                String firstname = resultSet.getString("firstname");
                return new UserInfo(username, surname, firstname);
            });
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }



    public UserSecurity userForSecurity(String login) {
        String sql = UserMapper.USER_FOR_SPRING_SECURITY;
        Object[] params = new Object[]{login, login, login};
        try {
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, params, (resultSet, i) -> {
            String username = resultSet.getString("login");
            List<Role> role = new ArrayList<>();
            Role role1 = new Role(resultSet.getString("role"));
            role.add(role1);
            String password = resultSet.getString("password");
            return new UserSecurity(username,password,role);
        });
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public void createUser(String username, String password, String role) {
        String sql = UserMapper.CREATE_USER;
        Object[] params = new Object[]{username, password, role};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println(rows + " row(s) updated.");
    }


    public List<UserSecurity> getAllUserSecurity(){
        String sql = UserMapper.FIND_ALL_USER;
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql,(resultSet, i) -> {
            String username = resultSet.getString("login");
            List<Role> role = new ArrayList<>();
            Role role1 = new Role(resultSet.getString("role"));
            role.add(role1);
            String password = resultSet.getString("password");
            return new UserSecurity(username,password,role);
        });
    }

    public List<UserInfo> getFriendContact(String username){
        String sql = UserMapper.GET_FRIENDS_CONTACT;
        Object[] params = new Object[]{username, username, username};
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql,params,(resultSet, i) -> {
            String username1 = resultSet.getString("username");
            String surname = resultSet.getString("surname");
            String firstname = resultSet.getString("firstname");
            return new UserInfo(username1,surname,firstname);
        });
    }


    public void addContact(String username, String usernameContact) {
        String sql = UserMapper.ADD_CONTACT;
        Object[] params = new Object[]{username, usernameContact};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println("Contact added");
    }


    public List<UserInfo> getChats(String username){
        String sql = UserMapper.GET_CHATS;
        Object[] params = new Object[]{username, username, username, username};
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql,params,(resultSet, i) -> {
            String username1 = resultSet.getString("username");
            String surname = resultSet.getString("surname");
            String firstname = resultSet.getString("firstname");
            return new UserInfo(username1,surname,firstname);
        });
    }

    public void changeSurname(String surname, String username) {
        String sql = UserMapper.CHANGE_SURNAME;
        Object[] params = new Object[]{surname, username};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println("Surname was changed");
    }

    public void changeFirstname(String firstname, String username) {
        String sql = UserMapper.CHANGE_FIRSTNAME;
        Object[] params = new Object[]{firstname, username};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println("Firstname was changed");
    }


    public void addSurname(String surname, String username) {
        String sql = UserMapper.ADD_SURNAME;
        Object[] params = new Object[]{username, surname};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println("Surname was added");
    }

    public void addFirstname(String firstname, String username) {
        String sql = UserMapper.ADD_FIRSTNAME;
        Object[] params = new Object[]{username, firstname};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println("Firstname was added");
    }



}
