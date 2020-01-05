package com.nefedov.DAO;

import com.nefedov.mapper.UserMapper;
import com.nefedov.model.CreateUser;
import com.nefedov.model.UserInfo;
import com.nefedov.model.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

    @Autowired
    public UserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<UserInfo> findInfoAboutUser(String login){
        String sql = UserMapper.FIND_INFO_ABOUT_USER;
        Object[] params = new Object[] {login, login, login};
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql,params, (resultSet, i) -> {
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("firstname");
            String secondName = resultSet.getString("secondname");
           return new UserInfo(username, firstName, secondName);
        });
    }



    public List<UserSecurity> userForSecurity(String login) {
        String sql = UserMapper.USER_FOR_SPRING_SECURITY;
        Object[] params = new Object[]{login, login, login};
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, (resultSet, i) -> {
            String username = resultSet.getString("login");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");
            return new UserSecurity(username,role,password);
        });
    }

    public void createUser(String username, String password, String role) {
        String sql = UserMapper.CREATE_USER;
        Object[] params = new Object[]{username, password, role};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println(rows + " row(s) updated.");
    }



}
