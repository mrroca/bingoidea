package com.nurse.action;

import java.util.LinkedList;
import java.util.List;

import com.nurse.model.User;
import com.nurse.service.UserService;

public class UserAction extends BaseAction
{

    private static final long serialVersionUID = 1L;

    User user = new User();
    List<User> userList = new LinkedList<User>();

    UserService userService = new UserService();


    public String execute()
    {
        userList = userService.getUserList();
        return SUCCESS;
    }


    public String createOrEditUser()
    {
        if (getHttpServletRequest().getParameter("editUserName") != null)
        {
            String editUserName = getHttpServletRequest().getParameter(
                "editUserName");
            user = userService.getUser(editUserName);
        }
        return SUCCESS;
    }


    public void validateSubmitUser()
    {
        if (user.getPassword().length() < 6)
        {
            this
                .addFieldError("user.password", getText("User.Password.Length"));
        }

    }


    public String submitUser()
    {
        userService.addUser(user);
        return "userList";
    }


    public String deleteUser()
    {
        System.out.println("+++++:" + userList.size());
        for (int i = 0; i < userList.size(); i++)
        {
            System.out.println("user name:" + (userList.get(i)).getUserName());
            userService.deleteUser((userList.get(i)).getUserName());
        }
        return "userList";
    }


    /*
     * Getter and Setter for attributes
     */
    public User getUser()
    {
        return user;
    }


    public void setUser(User user)
    {
        this.user = user;
    }


    public List<User> getUserList()
    {
        return userList;
    }


    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

}
