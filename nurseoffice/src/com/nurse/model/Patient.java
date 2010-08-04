package com.nurse.model;

public class Patient
{
    public String patientname;// 病人姓名
    public String sex;// 病人性别
    public String address;// 家庭住址
    public String phone;// 家庭电话
    public String cfsbnumber;// 处方识别号
    public String ypxh;// 药品序号
	public String ypmc;// 药品名称
    public String ypgg;// 药品规格
    public String ztbs;// 组套标识
	public String ypdw;// 药品单位
    public String ypjl;// 剂量
    public String jldw;// 剂量单位
    public String ypyf;// 频次--外配病人为药品用法
    public String pc;// 频次
	public String days;// 天数
    public String interval;//间隔
    public String gytj;// 用法
    public String date;// 日期
    public String optname;// 处理人
    public String optid;// 处理人代码
    public String total;// 处理总数
    public String currents;// 已处理或当前处理次数
    public String finish;// 是否已完结
    public String ybkh;
    public String remark;
    public String disu;
    public String guancha;
    public String chuli;
    public String jc;// 计次
    public String czmc;// 操作名称
    public String sanitationcontent;// 消毒操作事项
    public String sanitationcomplete;// 消毒完成情况
    public String lczd;// 临床诊断
    public String brnl;// 年龄
    public String brhy;// 婚姻情况
    public String description;// 描述
    public String treatmentid;// 伤口处理，抽血等唯一标识
    public String sanitationid;// 卫生消毒唯一标识
    public String id;
    public String flag;
    public String kind;//分类
	public String blfy;// 有无不良反应
	public String blfynr;// 不良反应内容
    public String appid; // 唯一编号
    public int nl; // 年龄
    public int hy; // 婚姻
    public String xx; // ======11======血型
    public String mzzd; // =====10=====门诊诊断
    public String gms; // ====9==有无过敏史
    public String gmwz; // 过敏物质18
    public float tw; // =====12=====体温
    public int mb; // =====13=======脉搏
    public int hx; // =====14====呼吸
    public int ssy; // =====15===收缩压
    public int szy; // ======16====舒张压
    public int brxz; // 病人性质17
    public int sbxh; // ms_survey表的自然ID
    public String cfid;//处方唯一编号
    public String gcjlid;//观察记录唯一标识
	public String wpygcjlid;//外配药观察记录唯一标识
	public String sflsry;//是否与类似症状人员接触
    public String sfyqry;// 是否与疫区人员接触
    
    public String getSflsry() {
		return sflsry;
	}
	public void setSflsry(String sflsry) {
		this.sflsry = sflsry;
	}
	public String getSfyqry() {
		return sfyqry;
	}
	public void setSfyqry(String sfyqry) {
		this.sfyqry = sfyqry;
	}
	public String getBlfynr() {
		return blfynr;
	}
	public void setBlfynr(String blfynr) {
		this.blfynr = blfynr;
	}
	public String getCfid() {
		return cfid;
	}
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	public String getGcjlid() {
		return gcjlid;
	}
	public void setGcjlid(String gcjlid) {
		this.gcjlid = gcjlid;
	}
	public String getWpygcjlid() {
		return wpygcjlid;
	}
	public void setWpygcjlid(String wpygcjlid) {
		this.wpygcjlid = wpygcjlid;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
    public String getPc() {
		return pc;
	}
	public void setPc(String pc) {
		this.pc = pc;
	}
    public String getZtbs() {
		return ztbs;
	}
	public void setZtbs(String ztbs) {
		this.ztbs = ztbs;
	}
    
    public String getYpxh() {
		return ypxh;
	}
	public void setYpxh(String ypxh) {
		this.ypxh = ypxh;
	}

    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public int getNl() {
        return nl;
    }
    public void setNl(int nl) {
        this.nl = nl;
    }
    public int getHy() {
        return hy;
    }
    public void setHy(String hy) {
        if(hy==null || "".equals(hy) ||"undefined".equals(hy)){
            this.hy = 0;
        }else{
            this.hy = Integer.parseInt(hy);
        }
    }
    
    public String getXx() {
        return xx;
    }
    public void setXx(String xx) {
        this.xx = xx;
    }
    public String getMzzd() {
        return mzzd;
    }
    public void setMzzd(String mzzd) {
        this.mzzd = mzzd;
    }
    public String getGms() {
        return gms;
    }
    public void setGms(String gms) {
        this.gms = gms;
    }
    public String getGmwz() {
        return gmwz;
    }
    public void setGmwz(String gmwz) {
        this.gmwz = gmwz;
    }
    public float getTw() {
        return tw;
    }
    public void setTw(float tw) {
        this.tw = tw;
    }
    public int getMb() {
        return mb;
    }
    public void setMb(String mb) {
        if(mb==null || "".equals(mb) ||"undefined".equals(mb)){
            this.mb = 0;
        }else{
            this.mb = Integer.parseInt(mb);
        }
    }
    public int getHx() {
        return hx;
    }
    public void setHx(String hx) {
        if(hx==null || "".equals(hx) ||"undefined".equals(hx)){
            this.hx = 0;
        }else{
            this.hx = Integer.parseInt(hx);
        }
    }
    public int getSsy() {
        return ssy;
    }
    public void setSsy(String ssy) {
        if(ssy==null || "".equals(ssy) ||"undefined".equals(ssy)){
            this.ssy = 0;
        }else{
            this.ssy = Integer.parseInt(ssy);
        }
    }
    public int getSzy() {
        return szy;
    }
    public void setSzy(String szy) {
        if(szy==null || "".equals(szy) ||"undefined".equals(szy)){
            this.szy = 0;
        }else{
            this.szy = Integer.parseInt(szy);
        }
    }
    public int getBrxz() {
        return brxz;
    }
    public void setBrxz(int brxz) {
        this.brxz = brxz;
    }
    public int getSbxh() {
        return sbxh;
    }
    public void setSbxh(String sbxh) {
        if(sbxh==null || "".equals(sbxh) ||"undefined".equals(sbxh)){
            this.sbxh = 0;
        }else{
            this.sbxh = Integer.parseInt(sbxh);
        }
    }
    public String getInterval()
    {
        return interval;
    }


    public void setInterval(String interval)
    {
        this.interval = interval;
    }

    public String getSanitationcomplete()
    {
        return sanitationcomplete;
    }


    public void setSanitationcomplete(String sanitationcomplete)
    {
        this.sanitationcomplete = sanitationcomplete;
    }


    public String getLczd()
    {
        return lczd;
    }


    public void setLczd(String lczd)
    {
        this.lczd = lczd;
    }


    public String getBrnl()
    {
        return brnl;
    }


    public void setBrnl(String brnl)
    {
        this.brnl = brnl;
    }


    public String getBrhy()
    {
        return brhy;
    }


    public void setBrhy(String brhy)
    {
        this.brhy = brhy;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


    public String getTreatmentid()
    {
        return treatmentid;
    }


    public void setTreatmentid(String treatmentid)
    {
        this.treatmentid = treatmentid;
    }


    public String getSanitationid()
    {
        return sanitationid;
    }


    public void setSanitationid(String sanitationid)
    {
        this.sanitationid = sanitationid;
    }


    public String getFlag()
    {
        return flag;
    }


    public void setFlag(String flag)
    {
        this.flag = flag;
    }


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public String getJc()
    {
        return jc;
    }


    public void setJc(String jc)
    {
        this.jc = jc;
    }


    public String getCzmc()
    {
        return czmc;
    }


    public void setCzmc(String czmc)
    {
        this.czmc = czmc;
    }


    public String getSanitationcontent()
    {
        return sanitationcontent;
    }


    public void setSanitationcontent(String sanitationcontent)
    {
        this.sanitationcontent = sanitationcontent;
    }


    public String getDisu()
    {
        return disu;
    }


    public void setDisu(String disu)
    {
        this.disu = disu;
    }


    public String getGuancha()
    {
        return guancha;
    }


    public void setGuancha(String guancha)
    {
        this.guancha = guancha;
    }


    public String getChuli()
    {
        return chuli;
    }


    public void setChuli(String chuli)
    {
        this.chuli = chuli;
    }


    public String getRemark()
    {
        return remark;
    }


    public void setRemark(String remark)
    {
        this.remark = remark;
    }


    public String getYbkh()
    {
        return ybkh;
    }


    public void setYbkh(String ybkh)
    {
        this.ybkh = ybkh;
    }


    public String getPatientname()
    {
        return patientname;
    }


    public void setPatientname(String patientname)
    {
        this.patientname = patientname;
    }


    public String getSex()
    {
        return sex;
    }


    public void setSex(String sex)
    {
        this.sex = sex;
    }


    public String getAddress()
    {
        return address;
    }


    public void setAddress(String address)
    {
        this.address = address;
    }


    public String getPhone()
    {
        return phone;
    }


    public void setPhone(String phone)
    {
        this.phone = phone;
    }


    public String getCfsbnumber()
    {
        return cfsbnumber;
    }


    public void setCfsbnumber(String cfsbnumber)
    {
        this.cfsbnumber = cfsbnumber;
    }


    public String getYpmc()
    {
        return ypmc;
    }


    public void setYpmc(String ypmc)
    {
        this.ypmc = ypmc;
    }


    public String getYpgg()
    {
        return ypgg;
    }


    public void setYpgg(String ypgg)
    {
        this.ypgg = ypgg;
    }


    public String getYpdw()
    {
        return ypdw;
    }


    public void setYpdw(String ypdw)
    {
        this.ypdw = ypdw;
    }


    public String getYpjl()
    {
        return ypjl;
    }


    public void setYpjl(String ypjl)
    {
        this.ypjl = ypjl;
    }


    public String getJldw()
    {
        return jldw;
    }


    public void setJldw(String jldw)
    {
        this.jldw = jldw;
    }


    public String getYpyf()
    {
        return ypyf;
    }


    public void setYpyf(String ypyf)
    {
        this.ypyf = ypyf;
    }


    public String getDays()
    {
        return days;
    }


    public void setDays(String days)
    {
        this.days = days;
    }


    public String getGytj()
    {
        return gytj;
    }


    public void setGytj(String gytj)
    {
        this.gytj = gytj;
    }


    public String getDate()
    {
        return date;
    }


    public void setDate(String date)
    {
        this.date = date;
    }


    public String getOptname()
    {
        return optname;
    }


    public void setOptname(String optname)
    {
        this.optname = optname;
    }


    public String getOptid()
    {
        return optid;
    }


    public void setOptid(String optid)
    {
        this.optid = optid;
    }


    public String getTotal()
    {
        return total;
    }


    public void setTotal(String total)
    {
        this.total = total;
    }


    public String getCurrents()
    {
        return currents;
    }


    public void setCurrents(String currents)
    {
        this.currents = currents;
    }


    public String getFinish()
    {
        return finish;
    }


    public void setFinish(String finish)
    {
        this.finish = finish;
    }


    public String getBlfy()
    {
        return blfy;
    }


    public void setBlfy(String blfy)
    {
        this.blfy = blfy;
    }
}
