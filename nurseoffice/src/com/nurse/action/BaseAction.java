package com.nurse.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware,
    ServletRequestAware, ServletResponseAware
{

    private static final long serialVersionUID = 1L;
    private Map<String, Object> sessionMap;
    private HttpServletRequest request;
    private HttpServletResponse response;


    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }


    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }


    public void setSession(Map<String, Object> map)
    {
        this.sessionMap = map;
    }


    public HttpServletRequest getHttpServletRequest()
    {
        return request;
    }


    public HttpServletResponse getHttpServletResponse()
    {
        return response;
    }


    public Map<String, Object> getSession()
    {
        return sessionMap;
    }
}
