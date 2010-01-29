--自检异常指标
drop table sqxtdata.dbo.ZJ_ZJSX
select * into sqxtdata.dbo.ZJ_ZJSX from (
	select
		mh_cardlist.pid as PID,
		mh_cardlist.pidtype as PIDTYPE,
		ZJ_ZJSX.JLXH,
		ZJ_ZJSX.ZJRQ,
		ZJ_ZJSX.TSXX,
		ZJ_ZJSX.TSLX,
		ZJ_ZJSX.CKBZ,
		ZJ_ZJSX.GLZJ,
		ZJ_ZJSX.JKKH,
		ZJ_ZJSX.CZJD,
		ZJ_ZJSX.CZTD,
		ZJ_ZJSX.GXJD,
		ZJ_ZJSX.GXTD
	from 
		ZJ_ZJSX,
		(select mh_cardlist.grbm,cardid as pid,cardtype as pidtype from mh_cardlist right join (select distinct(grbm),max(cardid) as PID from mh_cardlist group by grbm) as aa on mh_cardlist.cardid = aa.pid) as mh_cardlist 
	where 
		ZJ_ZJSX.grbm = mh_cardlist.grbm and ZJ_ZJSX.ZJRQ>convert(char(10),getdate()-2,120)
)AS ZJ_ZJSX

--自检人体成分分析
drop table sqxtdata.dbo.ZJ_ZFFX
select * into sqxtdata.dbo.ZJ_ZFFX from (
	select
		mh_cardlist.pid as PID,
		mh_cardlist.pidtype as PIDTYPE,
		ZJ_ZFFX.JLXH,
		ZJ_ZFFX.ZJRQ,
		ZJ_ZFFX.SYS_PK,
		ZJ_ZFFX.MACHINE_MODEL,
		ZJ_ZFFX.BODY_TYPE,
		ZJ_ZFFX.GENDER,
		ZJ_ZFFX.AGE,
		ZJ_ZFFX.HEIGHT,
		ZJ_ZFFX.WEIGHT,
		ZJ_ZFFX.BMI,
		ZJ_ZFFX.BMR,
		ZJ_ZFFX.FAT,
		ZJ_ZFFX.FAT_MASS,
		ZJ_ZFFX.FFM,
		ZJ_ZFFX.TBW,
		ZJ_ZFFX.DES_FAT,
		ZJ_ZFFX.DES_FAT_MASS,
		ZJ_ZFFX.IMP_BODY,
		ZJ_ZFFX.IMP_RLEG,
		ZJ_ZFFX.IMP_LLEG,
		ZJ_ZFFX.IMP_RARM,
		ZJ_ZFFX.IMP_LARM,
		ZJ_ZFFX.ANALY_RLEG_FAT,
		ZJ_ZFFX.ANALY_RLEG_FAT_MASS,
		ZJ_ZFFX.ANALY_RLEG_FFM,
		ZJ_ZFFX.ANALY_RLEG_PRED_MUSCLE_MASS,
		ZJ_ZFFX.ANALY_LLEG_FAT,
		ZJ_ZFFX.ANALY_LLEG_FAT_MASS,
		ZJ_ZFFX.ANALY_LLEG_FFM,
		ZJ_ZFFX.ANALY_LLEG_PRED_MUSCLE_MASS,
		ZJ_ZFFX.ANALY_LARM_FAT,
		ZJ_ZFFX.ANALY_LARM_FAT_MASS,
		ZJ_ZFFX.ANALY_LARM_FFM,
		ZJ_ZFFX.ANALY_LARM_PRED_MUSCLE_MASS,
		ZJ_ZFFX.ANALY_RARM_FAT,
		ZJ_ZFFX.ANALY_RARM_FAT_MASS,
		ZJ_ZFFX.ANALY_RARM_FFM,
		ZJ_ZFFX.ANALY_RARM_PRED_MUSCLE_MASS,
		ZJ_ZFFX.ANALY_TRUNK_FAT,
		ZJ_ZFFX.ANALY_TRUNK_FAT_MASS,
		ZJ_ZFFX.ANALY_TRUNK_FFM,
		ZJ_ZFFX.ANALY_TRUNK_PRED_MUSCLE_MASS,
		ZJ_ZFFX.SYS_STRING1,
		ZJ_ZFFX.SYS_STRING2,
		ZJ_ZFFX.SYS_STRING3,
		ZJ_ZFFX.SYS_STRING4,
		ZJ_ZFFX.SYS_STRING5,
		ZJ_ZFFX.REMARKS,
		ZJ_ZFFX.SYS_CD,
		ZJ_ZFFX.SYS_UD,
		ZJ_ZFFX.SATISFY_YN,
		ZJ_ZFFX.JKKH,
		ZJ_ZFFX.CZJD,
		ZJ_ZFFX.NZFAT,
		ZJ_ZFFX.JRL,
		ZJ_ZFFX.GZ,
		ZJ_ZFFX.JY
	from 
		ZJ_ZFFX,
		(select mh_cardlist.grbm,cardid as pid,cardtype as pidtype from mh_cardlist right join (select distinct(grbm),max(cardid) as PID from mh_cardlist group by grbm) as aa on mh_cardlist.cardid = aa.pid) as mh_cardlist 
	where 
		ZJ_ZFFX.grbm = mh_cardlist.grbm and ZJ_ZFFX.ZJRQ>convert(char(10),getdate()-2,120)
)AS ZJ_ZFFX

--血压测量结果
drop table sqxtdata.dbo.ZJ_XYCL
select * into sqxtdata.dbo.ZJ_XYCL from (
	select
		mh_cardlist.pid as PID,
		mh_cardlist.pidtype as PIDTYPE,
		ZJ_XYCL.JLXH,
		ZJ_XYCL.ZJRQ,
		ZJ_XYCL.SSY,
		ZJ_XYCL.SZY,
		ZJ_XYCL.PJY,
		ZJ_XYCL.MBS,
		ZJ_XYCL.XYJL,
		ZJ_XYCL.JKKH,
		ZJ_XYCL.CZJD,
		ZJ_XYCL.JY
	from 
		ZJ_XYCL,
		(select mh_cardlist.grbm,cardid as pid,cardtype as pidtype from mh_cardlist right join (select distinct(grbm),max(cardid) as PID from mh_cardlist group by grbm) as aa on mh_cardlist.cardid = aa.pid) as mh_cardlist 
	where 
		ZJ_XYCL.grbm = mh_cardlist.grbm and ZJ_XYCL.ZJRQ>convert(char(10),getdate()-2,120)
)AS ZJ_XYCL

--血糖结果
drop table sqxtdata.dbo.ZJ_XTY
select * into sqxtdata.dbo.ZJ_XTY from (
	select
		mh_cardlist.pid as PID,
		mh_cardlist.pidtype as PIDTYPE,
		ZJ_XTY.JLXH,
		ZJ_XTY.ZJRQ,
		ZJ_XTY.CLJG,
		ZJ_XTY.CZGH,
		ZJ_XTY.CLZT,
		ZJ_XTY.BY1,
		ZJ_XTY.BY2,
		ZJ_XTY.JL,
		ZJ_XTY.CZJD,
		ZJ_XTY.XT,
		ZJ_XTY.XTJL,
		ZJ_XTY.JKKH,
		ZJ_XTY.JY
	from 
		ZJ_XTY,
		(select mh_cardlist.grbm,cardid as pid,cardtype as pidtype from mh_cardlist right join (select distinct(grbm),max(cardid) as PID from mh_cardlist group by grbm) as aa on mh_cardlist.cardid = aa.pid) as mh_cardlist 
	where 
		ZJ_XTY.grbm = mh_cardlist.grbm and ZJ_XTY.ZJRQ>convert(char(10),getdate()-2,120)
)AS ZJ_XTY

--身高体重
drop table sqxtdata.dbo.ZJ_SGTZ
select * into sqxtdata.dbo.ZJ_SGTZ from (
	select
		mh_cardlist.pid as PID,
		mh_cardlist.pidtype as PIDTYPE,
		ZJ_SGTZ.JLXH,
		ZJ_SGTZ.ZJRQ,
		ZJ_SGTZ.HEIGHT,
		ZJ_SGTZ.WEIGHT,
		ZJ_SGTZ.BMI,
		ZJ_SGTZ.TXJL,
		ZJ_SGTZ.JKKH,
		ZJ_SGTZ.CZJD,
		ZJ_SGTZ.JY
	from 
		ZJ_SGTZ,
		(select mh_cardlist.grbm,cardid as pid,cardtype as pidtype from mh_cardlist right join (select distinct(grbm),max(cardid) as PID from mh_cardlist group by grbm) as aa on mh_cardlist.cardid = aa.pid) as mh_cardlist 
	where 
		ZJ_SGTZ.grbm = mh_cardlist.grbm and ZJ_SGTZ.ZJRQ>convert(char(10),getdate()-2,120)
)AS ZJ_SGTZ

--骨密度
drop table sqxtdata.dbo.ZJ_GMD
select * into sqxtdata.dbo.ZJ_GMD from (
	select
		mh_cardlist.pid as PID,
		mh_cardlist.pidtype as PIDTYPE,
		ZJ_GMD.JLXH,
		ZJ_GMD.ZJRQ,
		ZJ_GMD.XB,
		ZJ_GMD.CSNY,
		ZJ_GMD.CLBW,
		ZJ_GMD.SOSZ,
		ZJ_GMD.TZ,
		ZJ_GMD.ZZ,
		ZJ_GMD.GMDJL,
		ZJ_GMD.CZJD,
		ZJ_GMD.JY
	from 
		ZJ_GMD,
		(select mh_cardlist.grbm,cardid as pid,cardtype as pidtype from mh_cardlist right join (select distinct(grbm),max(cardid) as PID from mh_cardlist group by grbm) as aa on mh_cardlist.cardid = aa.pid) as mh_cardlist 
	where 
		ZJ_GMD.grbm = mh_cardlist.grbm and ZJ_GMD.ZJRQ>convert(char(10),getdate()-2,120)
)AS ZJ_GMD

--肺功能
drop table sqxtdata.dbo.ZJ_FGN
select * into sqxtdata.dbo.ZJ_FGN from (
	select
		mh_cardlist.pid as PID,
		mh_cardlist.pidtype as PIDTYPE,
		ZJ_FGN.JLXH,
		ZJ_FGN.ZJRQ,
		ZJ_FGN.VC,
		ZJ_FGN.VCPR,
		ZJ_FGN.ERV,
		ZJ_FGN.IRV,
		ZJ_FGN.FVC,
		ZJ_FGN.FVCPR,
		ZJ_FGN.MVV,
		ZJ_FGN.MVVPR,
		ZJ_FGN.TV,
		ZJ_FGN.FGNJL,
		ZJ_FGN.JKKH,
		ZJ_FGN.CZJD,
		ZJ_FGN.JY
	from 
		ZJ_FGN,
		(select mh_cardlist.grbm,cardid as pid,cardtype as pidtype from mh_cardlist right join (select distinct(grbm),max(cardid) as PID from mh_cardlist group by grbm) as aa on mh_cardlist.cardid = aa.pid) as mh_cardlist 
	where 
		ZJ_FGN.grbm = mh_cardlist.grbm and ZJ_FGN.ZJRQ>convert(char(10),getdate()-2,120)
)AS ZJ_FGN
