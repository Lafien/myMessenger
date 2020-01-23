package com.nefedov.project.DAO;

import com.nefedov.project.mapper.MessageMapper;
import com.nefedov.project.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class MessageDAO extends JdbcDaoSupport {

    @Autowired
    public MessageDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public List<Message> getMessagesInChat(String msgOwner, String msgTo){
        String sql = MessageMapper.GET_MESSAGE_IN_CHAT;
        Object[] params = new Object[]{msgOwner, msgTo, msgTo, msgOwner, msgOwner, msgTo, msgTo, msgOwner,
                msgOwner, msgTo, msgTo, msgOwner, msgOwner, msgTo, msgTo, msgOwner,};
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql,params,(resultSet, i) -> {
            String dateCreate = resultSet.getString("datecreate");
            String textMessage = resultSet.getString("textmessage");
            String messageFrom = resultSet.getString("msgowner");
            String messageTo = resultSet.getString("msgto");
            return new Message(dateCreate,textMessage,messageFrom ,messageTo);
        });
    }

    public void createMessage(String textMessage, String msgFrom, String msgTo) {
        String sql = MessageMapper.CREATE_MESSAGE;
        Object[] params = new Object[]{textMessage, msgFrom, msgTo};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println(rows + " message send");
    }




}
