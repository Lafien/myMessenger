package com.nefedov.project.DAO;

import com.nefedov.project.mapper.RoleMapper;
import com.nefedov.project.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class RoleDAO extends JdbcDaoSupport {

    @Autowired
    public RoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public List<Role> getRoleDAO(){
        String sql = RoleMapper.GET_ROLE;
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql,(resultSet, i) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("role");
           return new Role(id, name);
        });
    }





}
