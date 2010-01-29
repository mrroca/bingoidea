package cn.com.bsoft.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapSession;

public class DoctorSchedulerDaoImpl extends SqlMapClientDaoSupport implements
    IMessageDao
{
    // private SqlMapClientTemplate sqlMapClientTemplate;
    //
    //
    // public void setSqlMapClientTemplate(
    // SqlMapClientTemplate sqlMapClientTemplate)
    // {
    // this.sqlMapClientTemplate = sqlMapClientTemplate;
    // }

    public synchronized void deleteMessage(HashMap<?, ?> message)
        throws SQLException
    {

    }


    public synchronized List<Map<?, ?>> getAllMessage() throws SQLException
    {
        return null;
    }


    public synchronized List<Map<?, ?>> getMessageByCondition(
        HashMap<?, ?> message) throws SQLException
    {
        return null;
    }


    public synchronized void insertMessage(HashMap<?, ?> message)
        throws SQLException
    {

        SqlMapSession session = null;

        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();

        getSqlMapClientTemplate().insert("insertDoctorScheduler", message);

        session.commitTransaction();

        session.endTransaction();

        session.close();
    }


    public synchronized void updateMessage(HashMap<?, ?> message)
        throws SQLException
    {

    }

}
