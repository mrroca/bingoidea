package cn.com.bsoft.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMessageDao
{

    public void updateMessage(HashMap<?, ?> message) throws SQLException;


    public void deleteMessage(HashMap<?, ?> message) throws SQLException;


    public void insertMessage(HashMap<?, ?> message) throws SQLException;


    public List<Map<?, ?>> getAllMessage() throws SQLException;


    public List<Map<?, ?>> getMessageByCondition(HashMap<?, ?> message)
        throws SQLException;
}
