package com.nurse.action;

import com.nurse.model.CFVO;

public class CFListAction  extends BaseAction
{

    private static final long serialVersionUID = 1L;
    
    public CFVO cfvo ;//����vo,����ҳ����ʾ
    public String result;//���ڴ��Ҫ����ҳ�������,��struts.xml��ķ���ֵ��Ӧ

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
