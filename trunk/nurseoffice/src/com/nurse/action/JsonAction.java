package com.nurse.action;

import java.util.List;

import com.nurse.dao.UserDao;
import com.nurse.model.User;
import com.nurse.util.SystemInit;

import net.sf.json.JSONObject;

public class JsonAction extends BaseAction
{

    private static final long serialVersionUID = 1L;

    public List<User> userlist;


    public List<User> getUserlist()
    {
        return userlist;
    }


    public void setUserlist(List<User> userlist)
    {
        this.userlist = userlist;
    }

    public User user;
    // ���ڼ�¼���ؽ��
    public String result;


    // ʡ����Ӧ��get��set����

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
        System.out.println(this.getHttpServletRequest().getSession()
            .getAttribute("userid"));
        UserDao ud = (UserDao) SystemInit.createFactory().getBean("userdao");

        List<User> li = (List<User>) ud.getAllListObject();
        // JSONObject jo = JSONObject.fromObject(li);
        this.getHttpServletRequest().setAttribute("resultlist", li);
        String json = "{root:[";
        for (Object obj : li)
        {
            User user = (User) obj;
            json = json + "{\"userName\":\"" + user.getUserName()
                    + "\",\"password\":\"" + user.getPassword() + "\"},";
        }
        json = json.substring(0, json.length() - 1) + "]}";
        System.out.println(json.toString());
        // System.out.println(jo.toString());
        // System.out.println(jo.toString());
        setResult(json.toString());
        return "allUser";
    }


    public String execute() throws Exception
    {
        // ��Ҫ���ص�userʵ��������json����
        // System.out.println(user.getUserName()+"---------"+user.getPassword()+"----");
        // Map<String, String> map = new HashMap<String, String>();
        // map.put("userName", user.getUserName());
        // map.put("password",user.getPassword());
        // System.out.println(map.toString());
        UserDao ud = (UserDao) SystemInit.createFactory().getBean("userdao");

        ud.insert(this.user);

        JSONObject jo = JSONObject.fromObject(this.user);
        // {"name":"���","age":23}
        System.out.println(jo);

        // ����json�����toString����ת��Ϊ�ַ���Ȼ��ֵ��result
        setResult(jo.toString());

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
