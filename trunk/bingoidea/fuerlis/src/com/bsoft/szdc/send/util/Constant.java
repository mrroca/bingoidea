package com.bsoft.szdc.send.util;

public class Constant
{

    public static final String LOG4J_CONFIG = "config/log4j.xml";

    // 数据库描述文件路径
    // =====================================================================

    public static final String DBASE_CONFIG_MZJY = "config/tabcfg/clinic_mzjy.xml";

    // ====================================================================================

    // 数据采集脚本路径
    // ======================================================================
    // 附二院
    public static final String SQLSCRIPT_MZJY = "config/tabcfg/sqlscript/lis.prop";

    // ====================================================================================
    // 数据库连接 配置文件路径
    // ================================================================
    public static final String DBCONNECT_CONFIG = "config/dbconnect.xml";

    public static final String HOSPITAL_CONFIG = "config/hospitalcode.xml";
    // ===================================================================================

    public static final String QUARTZ_CONFIG = "config/quartz.properties";

    public static final String EXCEPTION_FILTER = "config/exception filter.xml";

    // 数据库类型
    // ==========================================================================

    public static final int DATABASE_TYPE_ORACLE = 1;

    public static final int DATABASE_TYPE_MYSQL = 2;

    public static final int DATABASE_TYPE_MSSQL = 3;

    // ===================================================================================

    // memcached服务端地址==================================================================

    public static final String SERVICE_CARD = "10.96.36.25:11211";

    public static final String MEMERCACHE_CFG = "config/memercache_cfg.prop";

    // ===================================================================================

    // report客户端
    // 配置文件=================================================================

    public static final String REPORT_CLIENT_CFG = "config/report.prop";

    // ===================================================================================

}
