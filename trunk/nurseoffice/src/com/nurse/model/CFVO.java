package com.nurse.model;

public class CFVO
{
    public String cfsb;
    public String cfhm;// 处方号码
    public String brxm;// 病人姓名
    public String brxb;// 病人性别
    public String ksmc;// 就诊科室
    public String ysxm;// 就诊医生
    public String kfrq;// 开方日期


    public String getCfsb()
    {
        return cfsb;
    }


    public void setCfsb(String cfsb)
    {
        this.cfsb = cfsb;
    }


    public String getCfhm()
    {
        return cfhm;
    }


    public void setCfhm(String cfhm)
    {
        this.cfhm = cfhm;
    }


    public String getBrxm()
    {
        return brxm;
    }


    public void setBrxm(String xm)
    {
        this.brxm = xm;
    }


    public String getBrxb()
    {
        return brxb;
    }


    public void setBrxb(String xb)
    {
        this.brxb = xb;
    }


    public String getKsmc()
    {
        return ksmc;
    }


    public void setKsmc(String ks)
    {
        this.ksmc = ks;
    }


    public String getYsxm()
    {
        return ysxm;
    }


    public void setYsxm(String ys)
    {
        this.ysxm = ys;
    }


    public String getKfrq()
    {
        return kfrq;
    }


    public void setKfrq(String kfrq)
    {
        this.kfrq = kfrq;
    }
}
