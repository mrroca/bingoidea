package com.nurse.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapSession;
import com.nurse.model.CFVO;

public class CFDao extends SqlMapClientDaoSupport implements IDao
{

    public void update() throws SQLException
    {

    }


    public void delete() throws SQLException
    {

    }


    public void insert() throws SQLException
    {

    }


    public List<Object> getAll() throws SQLException
    {
        return null;
    }


    @SuppressWarnings("unchecked")
    public List<CFVO> getAllCF() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<CFVO> queryForList = getSqlMapClientTemplate().queryForList(
            "getallcf");
        session.commitTransaction();

        session.endTransaction();

        session.close();
        
        return queryForList;
    }


    public List<Object> getByCond() throws SQLException
    {
        return null;
    }

}
