package com.nurse.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.nurse.model.User;

public class UserService
{
    private static Map<String, User> userMap = new HashMap<String, User>();


    public void deleteUser(String userName)
    {
        userMap.remove(userName);
    }


    public void addUser(User user)
    {
        userMap.put(user.getUserName(), user);
    }


    public User getUser(String userName)
    {
        return (User) userMap.get(userName);
    }


    public List<User> getUserList()
    {
        List<User> userList = new LinkedList<User>();
        Iterator<String> userNameIt = userMap.keySet().iterator();
        while (userNameIt.hasNext())
        {
            String userName = (String) userNameIt.next();
            userList.add(userMap.get(userName));
        }
        return userList;
    }

}
