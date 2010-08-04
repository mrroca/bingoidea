package com.nurse.action;

import java.util.List;

import com.nurse.dao.UserDao;
import com.nurse.model.User;
import com.nurse.util.SystemInit;

public class UsersAction extends BaseAction
{
    private static final long serialVersionUID = 1L;

    public List<User> userlist;

    public User user;

    public String result;


    public List<User> getUserlist()
    {
        return userlist;
    }


    public void setUserlist(List<User> userlist)
    {
        this.userlist = userlist;
    }


    public User getUser()
    {
        return user;
    }


    public void setUser(User user)
    {
        this.user = user;
    }


    public String getAllUser() throws Exception
    {
        UserDao ud = (UserDao) SystemInit.createFactory().getBean("userdao");

        List<User> li = (List<User>) ud.getAllListObject();

        this.getHttpServletRequest().setAttribute("resultlist", li);

        return "allUser";
    }


    public String execute() throws Exception
    {
        UserDao ud = (UserDao) SystemInit.createFactory().getBean("userdao");

        ud.insert(this.user);

        return SUCCESS;
    }


    public void setResult(String result)
    {
        this.result = result;
    }


    public String getResult()
    {
        return result;
    }

}
