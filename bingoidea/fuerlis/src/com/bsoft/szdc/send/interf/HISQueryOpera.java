package com.bsoft.szdc.send.interf;

import java.sql.Connection;

public interface HISQueryOpera extends QueryOper
{

    public String queryClinicguid(String sql, Connection conn);


    public void setLogicidCache(String key, String value);

}
