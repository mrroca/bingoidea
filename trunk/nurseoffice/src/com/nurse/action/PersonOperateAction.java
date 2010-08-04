package com.nurse.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.nurse.dao.PersonDao;
import com.nurse.model.Nurse;
import com.nurse.model.Person;
import com.nurse.util.SystemInit;

public class PersonOperateAction extends BaseAction
{

    private static final long serialVersionUID = 1L;
    public Person person;
    public String result;
    public Nurse nurse;


    public Nurse getNurse()
    {
        return nurse;
    }


    public void setNurse(Nurse nurse)
    {
        this.nurse = nurse;
    }


    public String getResult()
    {
        return result;
    }


    public void setResult(String result)
    {
        this.result = result;
    }


    public Person getPerson()
    {
        return person;
    }


    public void setPerson(Person person)
    {
        this.person = person;
    }


    public String execute() throws Exception
    {
        return SUCCESS;
    }


    public String getAllPerson() throws Exception
    {
        JSONArray jarry = new JSONArray();
        PersonDao pd = (PersonDao) SystemInit.createFactory().getBean(
            "persondao");
        List<?> li = (List<?>) pd.getAllPerson();
        for (int i = 0; i < li.size(); i++)
        {
            JSONObject jo = JSONObject.fromObject(li.get(i));
            jarry.add(jo);
        }
        System.out.println(jarry.toString());
        setResult(jarry.toString());
        return "success";
    }


    public String modifyPersons()
    {
        String oldnurse = this.nurse.getOldnurse();
        String newnurse = this.nurse.getNewnurse();
        String[] olds = oldnurse.split(",");
        String[] news = newnurse.split(",");
        System.out.println(olds.toString());
        System.out.println(news.toString());
        PersonDao pd = (PersonDao) SystemInit.createFactory().getBean(
            "persondao");
        for (int i = 0; i < olds.length; i++)
        {
            Map<String, String> hm = new HashMap<String, String>();
            hm.put("userid", olds[i]);
            hm.put("flag", "0");
            try
            {
                pd.updatePerson(hm);
                setResult("[{'info':'success'}]");
            }
            catch (SQLException e)
            {
                setResult("[{'info':'failure'}]");
            }
        }
        for (int i = 0; i < news.length; i++)
        {
            Map<String, String> hm = new HashMap<String, String>();
            hm.put("userid", news[i]);
            hm.put("flag", "1");
            List<?> li;
            try
            {
                li = (List<?>) pd.getPersonByUserID(hm);
                if (li.size() > 0)
                {
                    pd.updatePerson(hm);
                    setResult("[{'info':'success'}]");
                }
                else
                {
                    pd.savePerson(hm);
                    setResult("[{'info':'success'}]");
                }
            }
            catch (SQLException e)
            {
                setResult("[{'info':'failure'}]");
            }

        }
        return "success";
    }
}
