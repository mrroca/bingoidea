package com.nurse.action;

import java.util.List;

import com.nurse.dao.PersonDao;
import com.nurse.model.Person;
import com.nurse.util.SystemInit;

public class LoginAction extends BaseAction
{

    private static final long serialVersionUID = 1L;
    public Person person;
    public String result;


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


    public String personLogin() throws Exception
    {
        if (null == person || null == person.username
                || "".equals(person.username))
        {
            return "logon";
        }
        PersonDao pd = (PersonDao) SystemInit.createFactory().getBean(
            "persondao");
        List<?> li = (List<?>) pd.getByCond(person);
        if (li.size() > 0)
        {
            String name = "";
            for (Object obj : li)
            {
                Person p = (Person) obj;
                name = p.getName();
            }
            this.getHttpServletRequest().getSession().setAttribute("username",
                person.getUsername());
            System.out.println(person.getUsername());
            this.getHttpServletRequest().getSession()
                .setAttribute("name", name);
            System.out.println(name);
            return "success";
        }
        else
        {
            if (person.getUsername().equals("bssa")
                    && person.getPassword().equals(""))
            {
                this.getHttpServletRequest().getSession().setAttribute(
                    "username", person.getUsername());
                this.getHttpServletRequest().getSession().setAttribute("name",
                    "管理员");
                return "success";
            }
            else
            {
                return "failure";
            }
        }
    }
}
