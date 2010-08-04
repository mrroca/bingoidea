package com.nurse.action;

import com.nurse.model.CFVO;

public class CFListAction  extends BaseAction
{

    private static final long serialVersionUID = 1L;
    
    public CFVO cfvo ;//处方vo,用于页面显示
    public String result;//用于存放要传到页面的数据,跟struts.xml里的返回值对应

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public CFVO getCfvo()
    {
        return cfvo;
    }

    public void setCfvo(CFVO cfvo)
    {
        this.cfvo = cfvo;
    }
    
    public String getCFlist(){
        return "success";
    }
    

}
