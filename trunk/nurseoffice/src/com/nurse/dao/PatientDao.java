package com.nurse.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapSession;
import com.nurse.model.Patient;

public class PatientDao extends SqlMapClientDaoSupport implements IDao
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
    public List<Patient> getByCond(Patient p) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Patient> queryForList = getSqlMapClientTemplate().queryForList(
            "getpatientbycond", p);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getCFByCond(Map<String, String> hm)
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
    public List<Map<String, String>> getCFMXByCond(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientcfmxbycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getWPCFMXByCond(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getwppatientcfmxbycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPrint(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("printpatientsydbycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientBasicInfoByCond(
        Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientbasicinfobycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getWPPatientBasicInfoByCond(
        Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getwppatientbasicinfobycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientZdInfoByCond(
        Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientzdinfobycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }

    @SuppressWarnings("unchecked")
	public List<Map<String, String>> getPatientGcjlByCond(
            Map<String, String> hm) throws SQLException
        {
            SqlMapSession session = null;
            if (null == session)
            {
                session = getSqlMapClientTemplate().getSqlMapClient().openSession();
            }
            session.startTransaction();
            List<Map<String, String>> queryForList = getSqlMapClientTemplate()
                .queryForList("getpatientgcjlcond", hm);
            session.commitTransaction();
            session.endTransaction();
            session.close();

            return queryForList;
        }
    
    public void saveClinicWpyService(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().update("updatemzwpgcjl", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getClinicPatientWpyList() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("clinicservicepatientwpylist");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientWpyBasicInfoByCond(
        Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientwpybasicinfobycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
	public List<Map<String, String>> getPatientWpygcjlByCond(
            Map<String, String> hm) throws SQLException
        {
            SqlMapSession session = null;
            if (null == session)
            {
                session = getSqlMapClientTemplate().getSqlMapClient().openSession();
            }
            session.startTransaction();
            List<Map<String, String>> queryForList = getSqlMapClientTemplate()
                .queryForList("getpatientwpygcjlcond", hm);
            session.commitTransaction();
            session.endTransaction();
            session.close();

            return queryForList;
        }

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getVisitServiceYYByCond(
        Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = null;
        if (null != hm.get("yysj") && hm.get("yysj").trim().length() != 0)
        {
            queryForList = getSqlMapClientTemplate().queryForList(
                "getvisityybycond", hm);
        }
        else
        {
            queryForList = getSqlMapClientTemplate().queryForList("getvisityy");
        }
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getVisitServiceYYByID(
        Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = null;

        queryForList = getSqlMapClientTemplate().queryForList("getvisityybyid",
            hm);

        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPerson() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getperson");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientBasicByCard(
        Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientbasicinfobycard", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    public void savePatient(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        if ("1".equals(hm.get("currents")))
        {
            getSqlMapClientTemplate().insert("insertinfo", hm);
            getSqlMapClientTemplate().insert("synmzgcjl", hm);
        }
        else
        {
            getSqlMapClientTemplate().update("updateinfo", hm);
            getSqlMapClientTemplate().insert("synmzgcjl", hm);
        }
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }

    public void saveWpPatientInfo(Map<String, String> hm) throws SQLException
    {
    	String[] ztbs = hm.get("ztbs").split("\\|-\\|-\\|");
    	String[] ypmc = hm.get("ypmc").split("\\|-\\|-\\|");
    	String[] ypgg = hm.get("ypgg").split("\\|-\\|-\\|");
    	String[] ypdw = hm.get("ypdw").split("\\|-\\|-\\|");
    	String[] ypjl = hm.get("ypjl").split("\\|-\\|-\\|");
    	String[] jldw = hm.get("jldw").split("\\|-\\|-\\|");
    	String[] pc = hm.get("pc").split("\\|-\\|-\\|");
    	String[] days = hm.get("days").split("\\|-\\|-\\|");
    	String[] ypyf = hm.get("ypyf").split("\\|-\\|-\\|");
    	String[] kfrq = hm.get("kfrq").split("\\|-\\|-\\|");
    	String[] ypxh = hm.get("ypxh").split("\\|-\\|-\\|");
    	
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("insertintowpbr", hm);
        
        String patientinfo = hm.get("brxm")+"----"+hm.get("brxb")+"----"+hm.get("lxdh")+"----"+hm.get("jtdz")+"----"+hm.get("lczd");
        System.out.println(patientinfo);
        
        for(int i = 0 ; i < ypmc.length ; i++){
        	Map<String, String> hashmap = new HashMap<String, String>();
        	if(!ztbs[i].equals("@")){
        		hashmap.put("ztbs", ztbs[i]);
        	}else{
        		hashmap.put("ztbs", "");
        	}
        	System.out.println(ypmc[i]);
        	if(!ypmc[i].equals("@")){
        		hashmap.put("ypmc", ypmc[i]);
        	}else{
        		hashmap.put("ypmc", "");
        	}
        	if(!ypgg[i].equals("@")){
        		hashmap.put("ypgg", ypgg[i]);
        	}else{
        		hashmap.put("ypgg", "");
        	}
        	if(!ypdw[i].equals("@")){
        		hashmap.put("ypdw", ypdw[i]);
        	}else{
        		hashmap.put("ypdw", "");
        	}
        	if(!ypjl[i].equals("@")){
        		hashmap.put("ypjl", ypjl[i]);
        	}else{
        		hashmap.put("ypjl", "");
        	}
        	if(!jldw[i].equals("@")){
        		hashmap.put("jldw", jldw[i]);
        	}else{
        		hashmap.put("jldw", "");
        	}
        	if(!pc[i].equals("@")){
        		hashmap.put("pc", pc[i]);
        	}else{
        		hashmap.put("pc", "");
        	}
        	if(!days[i].equals("@")){
        		hashmap.put("days", days[i]);
        	}else{
        		hashmap.put("days", "");
        	}
        	if(!ypyf[i].equals("@")){
        		hashmap.put("ypyf", ypyf[i]);
        	}else{
        		hashmap.put("ypyf", "");
        	}
        	if(!kfrq[i].equals("@")){
        		hashmap.put("kfrq", kfrq[i]);
        	}else{
        		hashmap.put("kfrq", "");
        	}
        	if(!ypxh[i].equals("@")){
        		hashmap.put("ypxh", ypxh[i]);
        	}else{
        		hashmap.put("ypxh", "");
        	}
        	System.out.println(hm.toString());
        	getSqlMapClientTemplate().insert("insertintowpbrxx", hashmap);
        }
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }

    public void saveNurseJob(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("insertnursejob", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }
    
    public void saveWPNurseJob(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("insertwpnursejob", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }
    
    public void saveWPPatient(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        if ("1".equals(hm.get("currents")))
        {
            getSqlMapClientTemplate().insert("insertwpinfo", hm);
            getSqlMapClientTemplate().insert("synwpmzgcjl", hm);
        }
        else
        {
            getSqlMapClientTemplate().update("updatewpinfo", hm);
            getSqlMapClientTemplate().insert("synwpmzgcjl", hm);
        }
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientAllCFInfo() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientcfall");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getWpPatientList() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getwppatientbasicinfolist");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientWCLAllCFInfo() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientwclcfall");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientYCLAllCFInfo() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientyclcfall");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getZFPatientAllCFInfo()
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getzfpatientcfall");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientCurrents(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientcurrents", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getWPPatientCurrents(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getwppatientcurrents", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientName(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientname", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getDrugInfoByPYDM(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getdruginfobypydm", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getWpPatientCFMX(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getwppatientxxbycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getWpPatientInfoByID(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getwppatientbasicinfobyid", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientListByName(Map<String, String> hm)
        throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientbyname", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPatientListByCardNum(
        Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getpatientbycardnum", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getClinicPatientList() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("clinicservicepatientlist");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    public void saveVisitService(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        if (hm.get("flag").equals("1"))
        {
            getSqlMapClientTemplate().update("updatevisitservice", hm);
        }
        else
        {
            getSqlMapClientTemplate().insert("visittotable", hm);
        }

        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    public void saveVisitYYService(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();

        getSqlMapClientTemplate().insert("yyvisittotable", hm);
        getSqlMapClientTemplate().update("updateyyvisitservice", hm);
        
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    public void saveClinicService(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().update("updatemzgcjl", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    public void saveYYService(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("yyservicetotable", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getAllVisitService() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getvisitservice");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getTodayVisitService() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getvisityybytoday");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getAllVisitServiceByCond(
        Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getvisitservicebycond", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }


    public void insertTprbp(Map<String, String> map) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("insertTPRBPInfo", map);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    //
    public void updateTprbp(Map<String, String> map) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().update("updateTPRBPInfo", map);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    public long getMaxId() throws SQLException
    {
        long msid;
        List<?> list = null;
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        list = getSqlMapClientTemplate().queryForList("getMaxId");
        if ("null"
            .equals(((HashMap<?, ?>) list.get(0)).get("appid").toString()))
        {
            msid = 0;
        }
        else
        {
            msid = Long.parseLong(((HashMap<?, ?>) list.get(0)).get("appid")
                .toString());
        }
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return msid;
    }

    @SuppressWarnings({ "rawtypes" })
    public List getAllTprbpInfo() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List queryForList = getSqlMapClientTemplate().queryForList(
            "getAllTprbpInfo");
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return queryForList;
    }

    @SuppressWarnings({  "rawtypes" })
    public List getTprbpInfo(Map map) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List queryForList = getSqlMapClientTemplate().queryForList(
            "getTprbpInfo", map);
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getTreatmentInfo() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getTreatmentAll");
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return queryForList;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getSanitationInfo() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List<Map<String, String>> queryForList = getSqlMapClientTemplate()
            .queryForList("getSanitationAll");
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return queryForList;
    }


    public void saveTreatment(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("insertTreatment", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    public void updateTreatment(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().update("updateTreatment", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    public void saveSanitation(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("insertSanitation", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }


    public void updateSanitation(Map<String, String> hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().update("updateSanitation", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }

    @SuppressWarnings({ "rawtypes" })
    public long getSqbh() throws SQLException
    {
        int sqbh = 0;
        List list = null;
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        list = getSqlMapClientTemplate().queryForList("getSqbh");
        sqbh = Integer.parseInt(((HashMap) list.get(0)).get("sqbh").toString());
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return sqbh;
    }

    @SuppressWarnings({"rawtypes" })
    public int getCount(Map map) throws SQLException
    {
        String type = (String) map.get("type");
        int count = 0;
        List list = null;
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        list = getSqlMapClientTemplate().queryForList(type, map);
        count = Integer.parseInt(((HashMap) list.get(0)).get(type).toString());
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return count;
    }

    @SuppressWarnings({"rawtypes" })
    public void updateCount(Map map) throws SQLException
    {
        String typeid = (String) map.get("typeid");
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().update(typeid, map);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }

    @SuppressWarnings({"rawtypes" })
    public void insertStartNum(Map hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("insertStartNum", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }

    @SuppressWarnings({"rawtypes" })
    public void insertGhmxBrdaSurvey(Map map1,Map map2,Map map3) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("insertGhmx", map1);
        getSqlMapClientTemplate().insert("insertBrda", map2);
        getSqlMapClientTemplate().insert("insertSurvey", map3);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }
    @SuppressWarnings({"rawtypes" })
    public void updateGhmxBrdaSurvey(Map map1,Map map2,Map map3) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().update("updateGhmx", map1);
        getSqlMapClientTemplate().update("updateBrda", map2);
        getSqlMapClientTemplate().update("updateSurvey", map3);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }

    @SuppressWarnings({"rawtypes" })
    public void updateSurvey_yb(Map hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().update("updateSurvey_yb", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }

    @SuppressWarnings({"rawtypes" })
    public long getBh(Map map) throws SQLException
    {
        long bh = 0;
        List list = null;
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        list = getSqlMapClientTemplate().queryForList("getBh", map);
        bh = Long.parseLong(((HashMap) list.get(0)).get("appid").toString());
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return bh;
    }

    @SuppressWarnings({"rawtypes" })
    public long getKsdm(Map map) throws SQLException
    {
        int ksdm = 0;
        List list = null;
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        list = getSqlMapClientTemplate().queryForList("getKsdm", map);
        ksdm = Integer.parseInt(((HashMap) list.get(0)).get("ksdm").toString());
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return ksdm;
    }

    @SuppressWarnings({"rawtypes" })
    public void insertSurvey_yb(Map hm) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        getSqlMapClientTemplate().insert("insertSurvey", hm);
        session.commitTransaction();
        session.endTransaction();
        session.close();
    }
    @SuppressWarnings({"rawtypes" })
    public List getAllYp(Map map) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List queryForList = getSqlMapClientTemplate()
            .queryForList("getAllYp",map);
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    @SuppressWarnings({"rawtypes" })
    public List selectAllObserved() throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List queryForList = getSqlMapClientTemplate()
            .queryForList("getAllObserved");
        session.commitTransaction();
        session.endTransaction();
        session.close();

        return queryForList;
    }
    @SuppressWarnings({"rawtypes" })
    public List selectAllObservedBetween(Map map) throws SQLException
    {
        SqlMapSession session = null;
        if (null == session)
        {
            session = getSqlMapClientTemplate().getSqlMapClient().openSession();
        }
        session.startTransaction();
        List queryForList = getSqlMapClientTemplate()
            .queryForList("getAllObservedBetween",map);
        session.commitTransaction();
        session.endTransaction();
        session.close();
        return queryForList;
    }
}
