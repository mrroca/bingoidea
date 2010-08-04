package com.nurse.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IBaseDao
{
    public void update(HashMap<?, ?> hm) throws SQLException;


    public void update(Object o) throws SQLException;


    public void delete(HashMap<?, ?> hm) throws SQLException;


    public void delete(Object o) throws SQLException;


    public void insert(HashMap<?, ?> hm) throws SQLException;


    public void insert(Object o) throws SQLException;


    public List<Map<?, ?>> getAllList() throws SQLException;


    public List<Map<?, ?>> getByConditionList(HashMap<?, ?> hm)
        throws SQLException;

}
