package com.nurse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapSession;
import com.nurse.model.Person;

public class PersonDao extends SqlMapClientDaoSupport implements IDao
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


    public List<Object> getByCond() throws SQLException
    {
        return null;
    }


    @SuppressWarnings("unchecked")
    public List<Person> getByCond(Person p) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Person> queryForList = getSqlMapClientTemplate().queryForList(
            "getpersonbycond", p);
        session.commitTransaction();

        session.endTransaction();

        session.close();
        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Person> getAllPerson() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Person> queryForList = getSqlMapClientTemplate().queryForList(
            "getpersons");
        session.commitTransaction();

        session.endTransaction();

        session.close();
        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPersonByCond(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientcfbycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPersonByUserID(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpersonsbyid", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    public void savePerson(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();

        getSqlMapClientTemplate().insert("inserperson", hm);

        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    public void updatePerson(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();

        getSqlMapClientTemplate().update("updateperson", hm);
        
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }
   
}
