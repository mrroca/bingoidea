package com.nurse.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao
{
    public void update() throws SQLException;


    public void delete() throws SQLException;


    public void insert() throws SQLException;


    public List<Object> getAll() throws SQLException;


    public List<Object> getByCond() throws SQLException;

}
