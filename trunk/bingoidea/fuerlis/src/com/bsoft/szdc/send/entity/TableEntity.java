package com.bsoft.szdc.send.entity;

import java.util.HashMap;
import java.util.List;

public class TableEntity
{

    private String mTableName = "";

    private String mPK = "";

    private String mFK = "";

    private String mValue = "";

    private String mRelated = "";

    private String mCondition = "";

    private List<?> mColName = null;

    private List<?> mFilterList = null;

    private List<?> mQueryCol = null;

    private HashMap<?, ?> mColType = null;

    private String mErrorTableName = "";

    private String mTOTabmeName = "";


    public TableEntity()
    {
    }


    /**
     * @return the mCondition
     */
    public final String getCondition()
    {
        if (mCondition.trim().length() == 0)
            return "";
        if (mCondition.indexOf("{pk}") != -1)
        {
            mCondition = mCondition.replaceAll("\\{pk}", mPK);
        }
        if (mCondition.indexOf("{val}") != -1)
        {
            mCondition = mCondition.replaceAll("\\{val}", mValue);
        }
        if (mCondition.indexOf("{fk}") != -1)
        {
            mCondition = mCondition.replaceAll("\\{fk}", mFK);
        }
        return mCondition;
    }


    /**
     * @param condition
     *            the mCondition to set
     */
    public final void setCondition(String condition)
    {
        mCondition = condition;
    }


    /**
     * @return the mErrorTableName
     */
    public final String getErrorTableName()
    {
        return mErrorTableName;
    }


    /**
     * @param errorTableName
     *            the mErrorTableName to set
     */
    public final void setErrorTableName(String errorTableName)
    {
        mErrorTableName = errorTableName;
    }


    /**
     * @return the mFilterList
     */
    public final List<?> getFilterList()
    {
        return mFilterList;
    }


    /**
     * @param filterList
     *            the mFilterList to set
     */
    public final void setFilterList(List<?> filterList)
    {
        mFilterList = filterList;
    }


    /**
     * @return the mFK
     */
    public final String getFK()
    {
        return mFK;
    }


    /**
     * @param mfk
     *            the mFK to set
     */
    public final void setFK(String mfk)
    {
        mFK = mfk;
    }


    /**
     * @return the mPK
     */
    public final String getPK()
    {
        return mPK;
    }


    /**
     * @param mpk
     *            the mPK to set
     */
    public final void setPK(String mpk)
    {
        mPK = mpk;
    }


    /**
     * @return the mRelated
     */
    public final String getRelated()
    {
        return mRelated;
    }


    /**
     * @param related
     *            the mRelated to set
     */
    public final void setRelated(String related)
    {
        mRelated = related;
    }


    /**
     * @return the mTableName
     */
    public final String getTableName()
    {
        return mTableName;
    }


    /**
     * @param tableName
     *            the mTableName to set
     */
    public final void setTableName(String tableName)
    {
        mTableName = tableName;
    }


    /**
     * @return the mTOTabmeName
     */
    public final String getTOTabmeName()
    {
        return mTOTabmeName;
    }


    /**
     * @param tabmeName
     *            the mTOTabmeName to set
     */
    public final void setTOTabmeName(String tabmeName)
    {
        mTOTabmeName = tabmeName;
    }


    /**
     * @return the mValue
     */
    public final String getValue()
    {
        return mValue;
    }


    /**
     * @param value
     *            the mValue to set
     */
    public final void setValue(String value)
    {
        mValue = value;
    }


    /**
     * @return the mColName
     */
    public final List<?> getColName()
    {
        return mColName;
    }


    /**
     * @param colName
     *            the mColName to set
     */
    public final void setColName(List<?> colName)
    {
        mColName = colName;
    }


    /**
     * @return the mColType
     */
    public final HashMap<?, ?> getColType()
    {
        return mColType;
    }


    /**
     * @param colType
     *            the mColType to set
     */
    public final void setColType(HashMap<?, ?> colType)
    {
        mColType = colType;
    }


    /**
     * @return the mQueryCol
     */
    public final List<?> getQueryCol()
    {
        return mQueryCol;
    }


    /**
     * @param queryCol
     *            the mQueryCol to set
     */
    public final void setQueryCol(List<?> queryCol)
    {
        mQueryCol = queryCol;
    }

}
