package cn.com.bsoft.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.bsoft.base.dao.BaseDaoImpl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;

public class UserDaoImpl extends BaseDaoImpl implements IMessageDao
{

    protected SqlMapClient sqlMapClient;


    public SqlMapClient getSqlMapClient()
    {
        return sqlMapClient;
    }


    public void setSqlMapClient(SqlMapClient sqlMapClient)
    {
        this.sqlMapClient = sqlMapClient;
    }


    public void deleteMessage(HashMap<?, ?> message)
    {

    }


    public List<Map<?, ?>> getAllMessage()
    {
        return null;
    }


    public void insertMessage(HashMap<?, ?> message) throws SQLException
    {
        SqlMapSession session = null;

        session = this.sqlMapClient.openSession();

        session.startTransaction();

        this.sqlMapClient.insert("insertMessage", message);

        session.commitTransaction();

        session.endTransaction();
    }


    public void updateMessage(HashMap<?, ?> message)
    {

    }


    public List<Map<?, ?>> getMessageByCondition(HashMap<?, ?> message)
        throws SQLException
    {
        return null;
    }
}
