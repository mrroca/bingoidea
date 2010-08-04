package com.nurse.action;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.nurse.dao.PatientDao;
import com.nurse.model.Patient;
import com.nurse.util.SystemInit;

public class PatientInfoAction extends BaseAction
{

    private static final long serialVersionUID = 1L;
    public Patient patient;
    public String result;
    public String basicinfo;// 病人基本信息
    public String cfinfo;// 处方信息
    public String lczdinfo;// 临床诊断信息


    public String getCfinfo()
    {
        return cfinfo;
    }


    public void setCfinfo(String cfinfo)
    {
        this.cfinfo = cfinfo;
    }


    public String getLczdinfo()
    {
        return lczdinfo;
    }


    public void setLczdinfo(String lczdinfo)
    {
        this.lczdinfo = lczdinfo;
    }


    public String getBasicinfo()
    {
        return basicinfo;
    }


    public void setBasicinfo(String basicinfo)
    {
        this.basicinfo = basicinfo;
    }


    public Patient getPatient()
    {
        return patient;
    }


    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }


    public String getResult()
    {
        return result;
    }


    public void setResult(String result)
    {
        this.result = result;
    }


    public String getAllTprbp() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List li = patientDao.getAllTprbpInfo();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String saveTPRBPinfo() 
    {   long ms_survey_sbxh=0;
        long sbxh=0;
        long jzxh=0;
        long appid=0;
        long sqbh=0;
        long mzhm=0;
        long ksdm=0;
        String mzhm2="";
        JSONObject temp=new JSONObject();
        temp.put("message", "保存成功！");
        PatientDao patientDao = (PatientDao) SystemInit.createFactory().getBean("patientDao");
        
        try {
            Map map=new HashMap();
            map.put("ygdm", patient.getOptname());
            ksdm=patientDao.getKsdm(map); 
            sqbh=patientDao.getSqbh();
            
            
            
            Map map1=new HashMap();
            Map map2=new HashMap();
            Map map3=new HashMap();
        
            map1.put("ksdm", ksdm);
            map1.put("brxz", patient.getBrxz());
            map1.put("ysdm", patient.getOptname());
            
            
            
            map2.put("mzhm", patient.getYbkh());
            map2.put("brxm", patient.getPatientname());
            map2.put("brxz", patient.getBrxz());
            map2.put("brxb", Long.parseLong(patient.getSex()));
            long year=1900;
            Calendar now=Calendar.getInstance();
            long nl=patient.getNl();
            year=now.get(Calendar.YEAR)-nl;
            map2.put("csny", year+"-1-1");
            
            map2.put("hyzk", patient.getHy());
            int xxdm=0;
            if(patient.getXx()==null || "".equals(patient.getXx())){
                xxdm=0;
            }else{
                xxdm=Integer.parseInt(patient.getXx());
            }
            map2.put("xxdm", xxdm);
            map2.put("hkdz", patient.getAddress());
            map2.put("jtdh", patient.getPhone());
            
            
            map3.put("mzhm", patient.getYbkh());
            map3.put("gmbj", patient.getGms());
            map3.put("gmwz", patient.getGmwz());
            map3.put("xxdm", xxdm);
            map3.put("mzzd", patient.getMzzd());
            map3.put("tw", patient.getTw());
            map3.put("mb", patient.getMb());
            map3.put("hx", patient.getHx());
            map3.put("ssy", patient.getSsy());
            map3.put("szy", patient.getSzy());
            map3.put("clr", patient.getOptname());
            map3.put("sflsry", patient.getSflsry());
            map3.put("sfyqry", patient.getSfyqry());
            
             if(patient.getYbkh()==null || "".equals(patient.getYbkh())){
                 Map countMap=new HashMap();
                    countMap.put("type", "ms_ghmx");
                    if(patientDao.getCount(countMap)==0){
                        patientDao.insertStartNum(countMap);
                        sbxh=1;
                    }else{
                        sbxh=patientDao.getBh(countMap);
                        sbxh=sbxh+1;
                        countMap.put("typeid", "ms_ghmx1");
                        countMap.put("value", sbxh);
                        patientDao.updateCount(countMap);
                    }
                    
                    Map countMap1=new HashMap();
                    countMap1.put("type", "ms_brda");
                    if(patientDao.getCount(countMap1)==0){
                        patientDao.insertStartNum(countMap1);
                        appid=1;
                    }else{
                        appid=patientDao.getBh(countMap1);
                        appid=appid+1;
                        countMap1.put("typeid", "ms_brda1");
                        countMap1.put("value", appid);
                        patientDao.updateCount(countMap1);
                    }
                    
                    Map countMap2=new HashMap();
                    countMap2.put("type", "jzxh");
                    if(patientDao.getCount(countMap2)==0){
                        patientDao.insertStartNum(countMap2);
                        jzxh=1;
                    }else{
                        jzxh=patientDao.getBh(countMap2);
                        jzxh=jzxh+1;
                        countMap2.put("typeid", "jzxh1");
                        countMap2.put("value", jzxh);
                        patientDao.updateCount(countMap2);
                    }
                    
                    Map countMap3=new HashMap();
                    countMap3.put("type", "mzhm");
                    if(patientDao.getCount(countMap3)==0){
                        patientDao.insertStartNum(countMap3);
                        mzhm=1;
                    }else{
                        mzhm=patientDao.getBh(countMap3);
                        mzhm=mzhm+1;
                        countMap3.put("typeid", "mzhm1");
                        countMap3.put("value", mzhm);
                        patientDao.updateCount(countMap3);
                    }
                    Map countMap4=new HashMap();
                    countMap4.put("type", "ms_survey");
                    if(patientDao.getCount(countMap4)==0){
                        patientDao.insertStartNum(countMap4);
                        ms_survey_sbxh=1;
                    }else{
                        ms_survey_sbxh=patientDao.getBh(countMap4);
                        ms_survey_sbxh=ms_survey_sbxh+1;
                        countMap4.put("typeid", "ms_survey1");
                        countMap4.put("value", ms_survey_sbxh);
                        patientDao.updateCount(countMap4);
                    }
                    map1.put("sbxh", sbxh);
                    map1.put("appid", appid);
                    map1.put("jzxh", jzxh);
                    
                    map2.put("appid", appid);
                    
                    map3.put("appid", appid);
                    map3.put("sbxh", ms_survey_sbxh);
                    map3.put("jzxh", jzxh);
                 String temp1=String.valueOf(mzhm);
                 if(temp1.length()<8){
                     for(int i=0;i<8-temp1.length();i++){
                         temp1="0"+temp1;
                     }
                 }
                 mzhm2=sqbh+temp1;
                 map2.put("mzhm", mzhm2);
                 map3.put("mzhm", mzhm2);
                 patient.setYbkh(mzhm2);
                 patientDao.insertGhmxBrdaSurvey(map1, map2, map3);
            }else{
                if(patient.getYbkh().length()<15){
                    map1.put("sbxh", patient.getSbxh());
                    map1.put("appid", Long.parseLong(patient.getAppid()));
                    
                    
                    map2.put("appid", Long.parseLong(patient.getAppid()));
                    
                    map3.put("appid", Long.parseLong(patient.getAppid()));
                    map3.put("sbxh", patient.getSbxh());
                    
                    patientDao.updateGhmxBrdaSurvey(map1, map2, map3);
                    
                }else{
                	System.out.println("===========================================1============================================");
                    Map countMap2=new HashMap();
                    countMap2.put("type", "jzxh");
                    if(patientDao.getCount(countMap2)==0){
                        patientDao.insertStartNum(countMap2);
                        jzxh=1;
                    }else{
                        jzxh=patientDao.getBh(countMap2);
                        jzxh=jzxh+1;
                        countMap2.put("typeid", "jzxh1");
                        countMap2.put("value", jzxh);
                        patientDao.updateCount(countMap2);
                    }
                    map3.put("jzxh", jzxh);
                    map3.put("appid", patient.getAppid());
                    
                    if(patient.getSbxh()==0){
                    	System.out.println("===========================================2============================================");
                    	 Map countMap4=new HashMap();
                         countMap4.put("type", "ms_survey");
                         if(patientDao.getCount(countMap4)==0){
                             patientDao.insertStartNum(countMap4);
                             ms_survey_sbxh=1;
                         }else{
                             ms_survey_sbxh=patientDao.getBh(countMap4);
                             ms_survey_sbxh=ms_survey_sbxh+1;
                             countMap4.put("typeid", "ms_survey1");
                             countMap4.put("value", ms_survey_sbxh);
                             patientDao.updateCount(countMap4);
                         }
                         map3.put("sbxh", ms_survey_sbxh);
                         patientDao.insertSurvey_yb(map3);
                    }else{
                    	map3.put("appid", Long.parseLong(patient.getAppid()));
                        map3.put("sbxh", patient.getSbxh());
                    	System.out.println("===========================================3============================================");
                    	System.out.println(map3.toString());
                    	patientDao.updateSurvey_yb(map3);
                    }
                    
                }
            }
            
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            temp.put("message", "保存失败！");
        }
        
        temp.put("mzhm", patient.getYbkh());
        temp.put("appid", patient.getAppid());
        temp.put("sbxh", patient.getSbxh());
        setResult(temp.toString());
        return "success";
    }
    public String getTprbpInfo() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        Map map = new HashMap();
        map.put("appid", patient.getAppid());
        map.put("sbxh", patient.getSbxh());
        List<?> li = (List<?>) patientDao.getTprbpInfo(map);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getPatientAll() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getPatientAllCFInfo();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getWclPatientAll() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getPatientWCLAllCFInfo();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getYclPatientAll() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getPatientYCLAllCFInfo();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getPersonInfo() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getPerson();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getPatientBasicByCond() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ybkh", this.patient.getYbkh());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getPatientBasicByCard(hm);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    @SuppressWarnings("unchecked")
    public String getPatientName() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        System.out.println("-------------"
                + this.getHttpServletRequest().getParameter("q"));
        hm.put("xmpy", this.getHttpServletRequest().getParameter("q")
            .toString());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getPatientName(hm);
        String temp = "";
        for (int i = 0; i < li.size(); i++)
        {
            Map<String, String> map = (Map<String, String>) li.get(i);
            temp += "{ name: \"" + map.get("brxm") + "\", to: \""
                    + map.get("xmpy") + "\" },";
        }
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        System.out.println(jarry.toString());
        setResult(jarry.toString());
        temp = temp.substring(0, temp.length() - 1);
        return "success";
    }

    public String getDrugInfoByPYDM() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        System.out.println("-------------" + this.getHttpServletRequest().getParameter("q"));
        hm.put("pydm", this.getHttpServletRequest().getParameter("q").toString());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory().getBean("patientDao");
        List<?> li = (List<?>) patientDao.getDrugInfoByPYDM(hm);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        System.out.println(jarry.toString());
        setResult(jarry.toString());
        return "success";
    }
    
    public String getPatientListByName() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("brxm", this.patient.getPatientname());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getPatientListByName(hm);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        System.out.println(jarry.toString());
        setResult(jarry.toString());
        return "success";
    }


    public String getPatientListByCard() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ybkh", this.patient.getYbkh());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getPatientListByCardNum(hm);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        System.out.println(jarry.toString());
        setResult(jarry.toString());
        return "success";
    }


    public String getPatientCurrents() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfsb", this.patient.getCfsbnumber());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getPatientCurrents(hm);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        System.out.println(jarry.toString());
        setResult(jarry.toString());
        return "success";
    }

    public String getWPPatientCurrents() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfsb", this.patient.getCfsbnumber());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getWPPatientCurrents(hm);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        System.out.println(jarry.toString());
        setResult(jarry.toString());
        return "success";
    }
    
    public String getTodayYYVisitService() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getTodayVisitService();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        System.out.println(jarry.toString());
        setResult(jarry.toString());
        return "success";
    }

    public String getZFPatientAll() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getZFPatientAllCFInfo();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getPatientInfo_CF() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ybkh", this.patient.getYbkh());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getCFByCond(hm);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        if (li.size() > 0)
        {
            setResult(jarry.toString());
        }
        else
        {
            setResult("[{'info':'no card'}]");
        }

        return "success";
    }


    public String getClinicPatient() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getClinicPatientList();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getPatientInfoCFMX() throws Exception
    {
    	JSONArray jarry1 = new JSONArray();
        JSONArray jarry2 = new JSONArray();
        JSONArray jarry3 = new JSONArray();
        JSONArray jarry4 = new JSONArray();
        JSONArray jarry5 = new JSONArray();
        System.out.println(this.patient.getCfsbnumber());
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfsb", this.patient.getCfsbnumber());
        hm.put("gcjlid", this.patient.getGcjlid());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li1 = (List<?>) patientDao.getCFMXByCond(hm);
        List<?> li2 = (List<?>) patientDao.getPatientBasicInfoByCond(hm);
        List<?> li3 = (List<?>) patientDao.getPatientZdInfoByCond(hm);
        List<?> li4 = (List<?>) patientDao.getPerson();
        //List<?> li5 = (List<?>) patientDao.getPatientGcjlByCond(hm);
        for (int i = 0; i < li1.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li1.get(i));
            jarry1.add(jo);
        }
        for (int i = 0; i < li2.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li2.get(i));
            jarry2.add(jo);
        }
        for (int i = 0; i < li3.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li3.get(i));
            jarry3.add(jo);
        }
        for (int i = 0; i < li4.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li4.get(i));
            jarry4.add(jo);
        }
//        for (int i = 0; i < li5.size(); i++)
//        {
//            JSONObject jo = JSONObject.fromObject(li5.get(i));
//            jarry5.add(jo);
//        }
        setResult(jarry2.toString() + "|-|-|" + jarry1.toString() + "|-|-|"
                + jarry3.toString() + "|-|-|" + jarry4.toString());
        System.out.println(jarry2.toString() + "|-|-|" + jarry1.toString()
                + "|-|-|" + jarry3.toString() + "|-|-|" + jarry4.toString());
        return "success";
    }

    public String getClinicPatientInfo() throws Exception
    {
        JSONArray jarry2 = new JSONArray();
        JSONArray jarry3 = new JSONArray();
        JSONArray jarry5 = new JSONArray();
        System.out.println(this.patient.getCfsbnumber());
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfsb", this.patient.getCfsbnumber());
        hm.put("gcjlid", this.patient.getGcjlid());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li2 = (List<?>) patientDao.getPatientBasicInfoByCond(hm);
        List<?> li3 = (List<?>) patientDao.getPatientZdInfoByCond(hm);
        List<?> li5 = (List<?>) patientDao.getPatientGcjlByCond(hm);
        for (int i = 0; i < li2.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li2.get(i));
            jarry2.add(jo);
        }
        for (int i = 0; i < li3.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li3.get(i));
            jarry3.add(jo);
        }
        for (int i = 0; i < li5.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li5.get(i));
            jarry5.add(jo);
        }
        setResult(jarry2.toString() + "|-|-|" + jarry3.toString() + "|-|-|" + jarry5.toString());
        System.out.println(jarry2.toString() + "|-|-|" + jarry3.toString() +"|-|-|" + jarry5.toString());
        return "success";
    }
    
    public String saveClinicWpyService()
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfid", this.patient.getCfid());
        hm.put("blfy", this.patient.getBlfy());
        hm.put("blfynr", this.patient.getBlfynr());
        hm.put("chuli", this.patient.getChuli());
        hm.put("optid", this.patient.getOptid());
        hm.put("id", this.patient.getId());
        System.out.println(hm.toString());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");

        try
        {
            patientDao.saveClinicWpyService(hm);
            // patientDao.saveNurseJob(hm);
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (SQLException e)
        {
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        return "success";
    }
    
    public String getClinicPatientWpy() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getClinicPatientWpyList();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }
    
    public String getPatientWpyInfoCFMX() throws Exception
    {
        JSONArray jarry1 = new JSONArray();
        JSONArray jarry2 = new JSONArray();
        System.out.println(this.patient.getCfid());
//        System.out.println("getWpygcjlid "+this.patient.getWpygcjlid());
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfid", this.patient.getCfid());
        hm.put("wpygcjlid", this.patient.getWpygcjlid());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li1 = (List<?>) patientDao.getPatientWpyBasicInfoByCond(hm);
        List<?> li2 = (List<?>) patientDao.getPatientWpygcjlByCond(hm);
        for (int i = 0; i < li1.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li1.get(i));
            jarry1.add(jo);
        }
        for (int i = 0; i < li2.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li2.get(i));
            jarry2.add(jo);
        }
        setResult(jarry1.toString() + "|-|-|" + jarry2.toString());
        System.out.println(jarry1.toString() + "|-|-|" + jarry2.toString());
        return "success";
    }
    
    public String getPrintInfo() throws Exception
    {
        JSONArray jarry1 = new JSONArray();
        JSONArray jarry2 = new JSONArray();
        JSONArray jarry3 = new JSONArray();
        JSONArray jarry4 = new JSONArray();
        System.out.println(this.patient.getCfsbnumber());
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfsb", this.patient.getCfsbnumber());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li1 = (List<?>) patientDao.getCFMXByCond(hm);
        List<?> li2 = (List<?>) patientDao.getPatientBasicInfoByCond(hm);
        List<?> li3 = (List<?>) patientDao.getPatientZdInfoByCond(hm);
        List<?> li4 = (List<?>) patientDao.getPerson();
        for (int i = 0; i < li1.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li1.get(i));
            jarry1.add(jo);
        }
        for (int i = 0; i < li2.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li2.get(i));
            jarry2.add(jo);
        }
        for (int i = 0; i < li3.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li3.get(i));
            jarry3.add(jo);
        }
        for (int i = 0; i < li4.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li4.get(i));
            jarry4.add(jo);
        }
        setResult(jarry2.toString() + "|-|-|" + jarry1.toString() + "|-|-|"
                + jarry3.toString() + "|-|-|" + jarry4.toString());
        System.out.println(jarry2.toString() + "|-|-|" + jarry1.toString()
                + "|-|-|" + jarry3.toString() + "|-|-|" + jarry4.toString());
        return "success";
    }

    public String getWPPrintInfo() throws Exception
    {
        JSONArray jarry1 = new JSONArray();
        JSONArray jarry2 = new JSONArray();
        System.out.println(this.patient.getCfsbnumber());
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfsb", this.patient.getCfsbnumber());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li1 = (List<?>) patientDao.getWPCFMXByCond(hm);
        List<?> li2 = (List<?>) patientDao.getWPPatientBasicInfoByCond(hm);
        for (int i = 0; i < li1.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li1.get(i));
            jarry1.add(jo);
        }
        for (int i = 0; i < li2.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li2.get(i));
            jarry2.add(jo);
        }
        setResult(jarry2.toString() + "|-|-|" + jarry1.toString());
        System.out.println(jarry2.toString() + "|-|-|" + jarry1.toString());
        return "success";
    }

    public String savePatientinfo()
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfsb", this.patient.getCfsbnumber());
        hm.put("userid", this.patient.getOptid());
        hm.put("total", this.patient.getTotal());

        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");

        String currents = this.patient.getCurrents();

        if (!"".equals(currents) && null != currents && currents.length() != 0)
        {
            int cur = Integer.parseInt(currents);
            cur = cur + 1;
            hm.put("currents", cur + "");
            try
            {
                patientDao.savePatient(hm);
                patientDao.saveNurseJob(hm);
                Map<String, String> temp = new HashMap<String, String>();
                temp.put("results", "success");
                JSONObject jo = JSONObject.fromObject(temp);
                jarry.add(jo);
                setResult(jarry.toString());
            }
            catch (SQLException e)
            {
                Map<String, String> temp = new HashMap<String, String>();
                temp.put("results", "failure");
                JSONObject jo = JSONObject.fromObject(temp);
                jarry.add(jo);
                setResult(jarry.toString());
                e.printStackTrace();
            }
        }
        else
        {
            hm.put("currents", "1");
            try
            {
                patientDao.savePatient(hm);
                patientDao.saveNurseJob(hm);
                Map<String, String> temp = new HashMap<String, String>();
                temp.put("results", "success");
                JSONObject jo = JSONObject.fromObject(temp);
                jarry.add(jo);
                setResult(jarry.toString());
            }
            catch (SQLException e)
            {
                Map<String, String> temp = new HashMap<String, String>();
                temp.put("results", "failure");
                JSONObject jo = JSONObject.fromObject(temp);
                jarry.add(jo);
                setResult(jarry.toString());
                e.printStackTrace();
            }
        }

        return "success";
    }

    public String saveWPPatientinfo()
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfsb", this.patient.getCfsbnumber());
        hm.put("userid", this.patient.getOptid());
        hm.put("total", this.patient.getTotal());

        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");

        String currents = this.patient.getCurrents();

        if (!"".equals(currents) && null != currents && currents.length() != 0)
        {
            int cur = Integer.parseInt(currents);
            cur = cur + 1;
            hm.put("currents", cur + "");
            try
            {
                patientDao.saveWPPatient(hm);
                patientDao.saveWPNurseJob(hm);
                Map<String, String> temp = new HashMap<String, String>();
                temp.put("results", "success");
                JSONObject jo = JSONObject.fromObject(temp);
                jarry.add(jo);
                setResult(jarry.toString());
            }
            catch (SQLException e)
            {
                Map<String, String> temp = new HashMap<String, String>();
                temp.put("results", "failure");
                JSONObject jo = JSONObject.fromObject(temp);
                jarry.add(jo);
                setResult(jarry.toString());
                e.printStackTrace();
            }
        }
        else
        {
            hm.put("currents", "1");
            try
            {
                patientDao.saveWPPatient(hm);
                patientDao.saveWPNurseJob(hm);
                Map<String, String> temp = new HashMap<String, String>();
                temp.put("results", "success");
                JSONObject jo = JSONObject.fromObject(temp);
                jarry.add(jo);
                setResult(jarry.toString());
            }
            catch (SQLException e)
            {
                Map<String, String> temp = new HashMap<String, String>();
                temp.put("results", "failure");
                JSONObject jo = JSONObject.fromObject(temp);
                jarry.add(jo);
                setResult(jarry.toString());
                e.printStackTrace();
            }
        }

        return "success";
    }
    
    public String getAllVisitService() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getAllVisitService();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getVisitServiceByCond() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> temp = new HashMap<String, String>();
        temp.put("id", patient.getId());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getAllVisitServiceByCond(temp);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getVisitServiceYYByCond() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> temp = new HashMap<String, String>();
        temp.put("yysj", patient.getDate());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getVisitServiceYYByCond(temp);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String getVisitServiceYYByID() throws Exception
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> temp = new HashMap<String, String>();
        temp.put("id", this.patient.getId());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = (List<?>) patientDao.getVisitServiceYYByID(temp);
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String saveVisitService()
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ybkh", this.patient.getYbkh());
        hm.put("patientname", this.patient.getPatientname());
        hm.put("sex", this.patient.getSex());
        hm.put("address", this.patient.getAddress());
        hm.put("phone", this.patient.getPhone());
        hm.put("content", this.patient.getRemark());
        hm.put("optid", this.patient.getOptid());
        hm.put("id", this.patient.getId());
        hm.put("flag", this.patient.getFlag());
        hm.put("kind", this.patient.getKind());
        System.out.println(hm.toString());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");

        try
        {
            patientDao.saveVisitService(hm);
            // patientDao.saveNurseJob(hm);
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (SQLException e)
        {
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        return "success";
    }


    
    public String saveVisitYYService()
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ybkh", this.patient.getYbkh());
        hm.put("patientname", this.patient.getPatientname());
        hm.put("sex", this.patient.getSex());
        hm.put("address", this.patient.getAddress());
        hm.put("phone", this.patient.getPhone());
        hm.put("content", this.patient.getRemark());
        hm.put("optid", this.patient.getOptid());
        hm.put("id", this.patient.getId());
        hm.put("kind", this.patient.getKind());
        System.out.println(hm.toString());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");

        try
        {
            patientDao.saveVisitYYService(hm);
            // patientDao.saveNurseJob(hm);
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (SQLException e)
        {
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        return "success";
    }


    public String saveClinicService()
    {
    	JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("cfsb", this.patient.getCfsbnumber());
        // hm.put("disu", this.patient.getDisu());
        // hm.put("guancha", this.patient.getGuancha());
        hm.put("blfy", this.patient.getBlfy());
        hm.put("blfynr", this.patient.getBlfynr());
        hm.put("chuli", this.patient.getChuli());
        hm.put("optid", this.patient.getOptid());
        hm.put("id", this.patient.getId());
        System.out.println(hm.toString());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");

        try
        {
            patientDao.saveClinicService(hm);
            // patientDao.saveNurseJob(hm);
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (SQLException e)
        {
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        return "success";
    }

    public String saveWpPatientInfo()
    {
    	String ztbs = "";
    	String ypmc = "";
    	String ypgg = "";
    	String ypdw = "";
    	String ypjl = "";
    	String jldw = "";
    	String pc = "";
    	String days = "";
    	String ypyf = "";
    	String kfrq = "";
    	String ypxh = "";
    	String brxm = "";
    	String brxb = "";
    	String lxdh = "";
    	String jtdz = "";
    	String lczd = "";
    	
    	ztbs = getPatient().getZtbs();
    	ypmc = getPatient().getYpmc();
    	ypgg = getPatient().getYpgg();
    	ypdw = getPatient().getYpdw();
    	ypjl = getPatient().getYpjl();
    	jldw = getPatient().getJldw();
    	pc = getPatient().getPc();
    	days = getPatient().getDays();
    	ypyf = getPatient().getYpyf();
    	kfrq = getPatient().getDate();
    	ypxh = getPatient().getYpxh();
    	brxm = getPatient().getPatientname();
    	brxb = getPatient().getSex();
    	lxdh = getPatient().getPhone();
    	jtdz = getPatient().getAddress();
    	lczd = getPatient().getLczd();
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ztbs", ztbs);
        hm.put("ypmc", ypmc);
        hm.put("ypgg", ypgg);
        hm.put("ypdw", ypdw);
        hm.put("ypjl", ypjl);
        hm.put("jldw", jldw);
        hm.put("pc", pc);
        hm.put("days", days);
        hm.put("ypyf", ypyf);
        hm.put("kfrq", kfrq);
        hm.put("ypxh", ypxh);
        hm.put("brxm", brxm);
        hm.put("brxb", brxb);
        hm.put("lxdh", lxdh);
        hm.put("jtdz", jtdz);
        hm.put("lczd", lczd);
        System.out.println(hm.toString());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory().getBean("patientDao");

        try
        {
            patientDao.saveWpPatientInfo(hm);
            // patientDao.saveNurseJob(hm);
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (SQLException e)
        {
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        return "success";
    }


    public String saveYYVisitService()
    {// ----------yuyue
        JSONArray jarry = new JSONArray();

        //int days = Integer.parseInt(this.patient.getDays());
        //int interval = Integer.parseInt(this.patient.getInterval());
        String yysj = this.patient.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");

        try
        {
            date = sdf.parse(yysj);
            Long time = date.getTime();
            //for (int i = 0; i < days; i++)
            //{
                String dates = sdf.format(new Date(time));
                System.out.println(dates);
                Map<String, String> hm = new HashMap<String, String>();
                hm.put("brxm", this.patient.getPatientname());
                hm.put("brxb", this.patient.getSex());
                hm.put("lxdh", this.patient.getPhone());
                hm.put("yysj", dates);
                hm.put("jtdz", this.patient.getAddress());
                hm.put("remark", this.patient.getRemark());
                hm.put("ybkh", this.patient.getYbkh());
                hm.put("kind", this.patient.getKind());
                System.out.println(hm.toString());
                patientDao.saveYYService(hm);
                //time = time + interval * 24 * 60 * 60 * 1000;
            //}

            // patientDao.saveNurseJob(hm);
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (SQLException e)
        {
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "success";
    }


    public String getTreatmentAll() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = patientDao.getTreatmentInfo();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }

    public String getWpPatientAllList() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = patientDao.getWpPatientList();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }
    
    public String getWpPatientInfoByID() throws Exception
    {
        JSONArray jarry = new JSONArray();
        JSONArray jarry2 = new JSONArray();
        JSONArray jarry3 = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("id", getPatient().getId());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = patientDao.getWpPatientInfoByID(hm);
        List<?> li2 = patientDao.getWpPatientCFMX(hm);
        List<?> li3 = (List<?>) patientDao.getPerson();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        for (int i = 0; i < li2.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li2.get(i));
            jarry2.add(jo);
        }
        for (int i = 0; i < li3.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li3.get(i));
            jarry3.add(jo);
        }
        setResult(jarry.toString() + "|-|-|" + jarry2.toString()+ "|-|-|" + jarry3.toString());
        return "success";
    }

    public String getSanitationAll() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List<?> li = patientDao.getSanitationInfo();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        setResult(jarry.toString());
        return "success";
    }


    public String saveTreatment()
    {
        // System.out.println("++++++++++++");
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ybkh", this.patient.getYbkh());
        hm.put("patientname", this.patient.getPatientname());
        hm.put("brnl", this.patient.getBrnl());
        hm.put("brhy", this.patient.getBrhy());
        hm.put("gms", this.patient.getGms());
        hm.put("xx", this.patient.getXx());
        hm.put("mzzd", this.patient.getMzzd());
        hm.put("description", this.patient.getDescription());
        hm.put("sex", this.patient.getSex());
        hm.put("address", this.patient.getAddress());
        hm.put("phone", this.patient.getPhone());
        hm.put("optid", this.patient.getOptid());
        hm.put("jc", this.patient.getJc());
        hm.put("czmc", this.patient.getCzmc());
        System.out.println(hm.toString());
        PatientDao patientdao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        try
        {
            patientdao.saveTreatment(hm);
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (Exception e)
        {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        return "success";
    }


    public String updateTreatment()
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ybkh", this.patient.getYbkh());
        hm.put("patientname", this.patient.getPatientname());
        hm.put("brnl", this.patient.getBrnl());
        hm.put("brhy", this.patient.getBrhy());
        hm.put("gms", this.patient.getGms());
        hm.put("xx", this.patient.getXx());
        hm.put("mzzd", this.patient.getMzzd());
        hm.put("description", this.patient.getDescription());
        hm.put("sex", this.patient.getSex());
        hm.put("address", this.patient.getAddress());
        hm.put("phone", this.patient.getPhone());
        hm.put("optid", this.patient.getOptid());
        hm.put("jc", this.patient.getJc());
        hm.put("czmc", this.patient.getCzmc());
        hm.put("treatmentid", this.patient.getTreatmentid());
        System.out.println(hm.toString());
        PatientDao patientdao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        try
        {
            patientdao.updateTreatment(hm);
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (Exception e)
        {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        return "success";
    }


    public String saveSanitation()
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        // hm.put("ybkh", this.patient.getYbkh());
        // hm.put("patientname", this.patient.getPatientname());
        // hm.put("sex", this.patient.getSex());
        // hm.put("address", this.patient.getAddress());
        // hm.put("phone", this.patient.getPhone());
        hm.put("optid", this.patient.getOptid());
        hm.put("sanitationcontent", this.patient.getSanitationcontent());
        hm.put("sanitationcomplete", this.patient.getSanitationcomplete());
        System.out.println(hm.toString());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        try
        {
            patientDao.saveSanitation(hm);
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (Exception e)
        {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        return "success";
    }


    public String updateSanitation()
    {
        JSONArray jarry = new JSONArray();
        Map<String, String> hm = new HashMap<String, String>();
        // hm.put("ybkh", this.patient.getYbkh());
        // hm.put("patientname", this.patient.getPatientname());
        // hm.put("sex", this.patient.getSex());
        // hm.put("address", this.patient.getAddress());
        // hm.put("phone", this.patient.getPhone());
        hm.put("optid", this.patient.getOptid());
        hm.put("sanitationcontent", this.patient.getSanitationcontent());
        hm.put("sanitationcomplete", this.patient.getSanitationcomplete());
        hm.put("sanitationid", this.patient.getSanitationid());
        System.out.println(hm.toString());
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        try
        {
            patientDao.updateSanitation(hm);
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("results", "success");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
        }
        catch (Exception e)
        {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("results", "failure");
            JSONObject jo = JSONObject.fromObject(temp);
            jarry.add(jo);
            setResult(jarry.toString());
            e.printStackTrace();
        }
        return "success";
    }
    public String getAllObserved() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        List li =  patientDao.selectAllObserved();
        List ypList;
        for (int i = 0; i < li.size(); i++)
        {	
        	Map map=new HashMap();
        	map.put("cfsb",Integer.parseInt(((Map)li.get(i)).get("cfsb").toString()) );
        	ypList=patientDao.getAllYp(map);
        	String ypmc="";
        	for(int j=0;j<ypList.size();j++){
        		ypmc+=((Map)ypList.get(j)).get("ypmc").toString()+"；";
        	}
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jo.put("ypmc", ypmc);
            jarry.add(jo);
            
        }
        setResult(jarry.toString());
//        System.out.println("===========result================="+getResult());
        return "success";
    }
    public String getAllObservedBetween() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PatientDao patientDao = (PatientDao) SystemInit.createFactory()
            .getBean("patientDao");
        HttpServletRequest request = ServletActionContext. getRequest();
        String kssj=request.getParameter("qssj")+" 00:00";
        String jssj=request.getParameter("jssj")+" 23:59";
        Map map2 =new HashMap();
        map2.put("qssj", kssj);
        map2.put("jssj", jssj);
        System.out.println(map2.toString());
        List li =  patientDao.selectAllObservedBetween(map2);
        List ypList;
        for (int i = 0; i < li.size(); i++)
        {	
        	Map map=new HashMap();
        	map.put("cfsb",Integer.parseInt(((Map)li.get(i)).get("cfsb").toString()) );
        	ypList=patientDao.getAllYp(map);
        	String ypmc="";
        	for(int j=0;j<ypList.size();j++){
        		ypmc+=((Map)ypList.get(j)).get("ypmc").toString()+"；";
        	}
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jo.put("ypmc", ypmc);
            jarry.add(jo);
            
        }
        setResult(jarry.toString());
//        System.out.println("===========result================="+getResult());
        return "success";
    }

}
