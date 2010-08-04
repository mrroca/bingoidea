package com.nurse.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapSession;
import com.nurse.model.User;

public class UserDao extends SqlMapClientDaoSupport implements IBaseDao
{

    public void update(HashMap<?, ?> hm) throws SQLException
    {

    }


    public void update(Object o) throws SQLException
    {

    }


    public void delete(HashMap<?, ?> hm) throws SQLException
    {

    }


    public void delete(Object o) throws SQLException
    {

    }


    public void insert(HashMap<?, ?> hm) throws SQLException
    {

    }


    public void insert(Object o) throws SQLException
    {
        SqlMapSession session = null;

        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();

        getSqlMapClientTemplate().insert("addUser", (User) o);

        session.commitTransaction();

        session.endTransaction();

        session.close();

    }


    public List<Map<?, ?>> getAllList() throws SQLException
    {
        return null;
    }


    @SuppressWarnings("unchecked")
    public List<User> getAllListObject() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<User> queryForList = getSqlMapClientTemplate().queryForList(
            "getAllUser");
        session.commitTransaction();

        session.endTransaction();

        session.close();

        return queryForList;
    }


    public List<Map<?, ?>> getByConditionList(HashMap<?, ?> hm)
        throws SQLException
    {
        return null;
    }


    public List<Object> getByConditionListObject(HashMap<?, ?> hm)
        throws SQLException
    {
        return null;
    }

}
