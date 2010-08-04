IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='MS_TREATMENT')
DROP TABLE MS_TREATMENT
CREATE TABLE [dbo].[MS_TREATMENT](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[YBKH] [varchar](25) COLLATE Chinese_PRC_CI_AS NULL,
	[NAME] [varchar](100) COLLATE Chinese_PRC_CI_AS NULL,
	[SEX] [varchar](2) COLLATE Chinese_PRC_CI_AS NULL,
	[ADDRESS] [varchar](100) COLLATE Chinese_PRC_CI_AS NULL,
	[PHONE] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[USERID] [varchar](30) COLLATE Chinese_PRC_CI_AS NULL,
	[MARKDATE] [datetime] NULL,
	[JC] [varchar](16) COLLATE Chinese_PRC_CI_AS NULL,
	[CZMC] [varchar](30) COLLATE Chinese_PRC_CI_AS NULL,
	[BRNL] [varchar](5) COLLATE Chinese_PRC_CI_AS NULL,
	[BRHY] [varchar](10) COLLATE Chinese_PRC_CI_AS NULL,
	[GMS] [varchar](500) COLLATE Chinese_PRC_CI_AS NULL,
	[XX] [varchar](5) COLLATE Chinese_PRC_CI_AS NULL,
	[MZZD] [varchar](500) COLLATE Chinese_PRC_CI_AS NULL,
	[DESCRIPTION] [varchar](1000) COLLATE Chinese_PRC_CI_AS NULL,
 CONSTRAINT [PK_MS_TREATMENT] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]


IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='MS_SANITATION')
DROP TABLE MS_SANITATION
CREATE TABLE [dbo].[MS_SANITATION](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[USERID] [varchar](30) COLLATE Chinese_PRC_CI_AS NULL,
	[MARKDATE] [datetime] NULL,
	[SANITATIONCONTENT] [varchar](5000) COLLATE Chinese_PRC_CI_AS NULL,
	[SANITATIONCOMPLETE] [varchar](500) COLLATE Chinese_PRC_CI_AS NULL,
 CONSTRAINT [PK_MS_SANITATION] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]



IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='CF_CFJOB')
DROP TABLE CF_CFJOB
CREATE TABLE [dbo].[CF_CFJOB](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CFSB] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[TOTAL] [varchar](10) COLLATE Chinese_PRC_CI_AS NULL,
	[CURRENTS] [nchar](10) COLLATE Chinese_PRC_CI_AS NULL,
	[MARKDATE] [datetime] NULL,
 CONSTRAINT [PK_CF_CFJOB] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]



IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='MZ_WPBRXX')
DROP TABLE MZ_WPBRXX
CREATE TABLE [dbo].[MZ_WPBRXX](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[YPMC] [varchar](100) COLLATE Chinese_PRC_CI_AS NULL,
	[YPGG] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[YPDW] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[YPJL] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[JLDW] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[PC] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[YPYF] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[LRRQ] [datetime] NULL,
	[WPBRID] [int] NULL,
	[YPXH] [varchar](30) COLLATE Chinese_PRC_CI_AS NULL,
	[KFRQ] [datetime] NULL,
	[ZTBS] [varchar](3) COLLATE Chinese_PRC_CI_AS NULL,
	[DAYS] [varchar](3) COLLATE Chinese_PRC_CI_AS NULL,
 CONSTRAINT [PK_MZ_WPBRXX] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]



IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='MZ_WPBR')
DROP TABLE MZ_WPBR
CREATE TABLE [dbo].[MZ_WPBR](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[BRXM] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[BRXB] [varchar](1) COLLATE Chinese_PRC_CI_AS NULL,
	[LXDH] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[JTDZ] [varchar](100) COLLATE Chinese_PRC_CI_AS NULL,
	[LCZD] [varchar](100) COLLATE Chinese_PRC_CI_AS NULL,
	[LRRQ] [datetime] NULL,
 CONSTRAINT [PK_MZ_WPBR] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]



IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='GY_NURSEGROUP')
DROP TABLE GY_NURSEGROUP
CREATE TABLE [dbo].[GY_NURSEGROUP](
	[USERID] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[VALIDFLAG] [varchar](1) COLLATE Chinese_PRC_CI_AS NULL,
	[ID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_GY_NURSEGROUP] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]




IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='MZ_GCJL')
DROP TABLE MZ_GCJL
CREATE TABLE [dbo].[MZ_GCJL](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CFSB] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[USERID] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[DISU] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[GUANCHA] [varchar](2000) COLLATE Chinese_PRC_CI_AS NULL,
	[CHULI] [varchar](2000) COLLATE Chinese_PRC_CI_AS NULL,
	[LRRQ] [datetime] NULL,
	[BLFY] [varchar](1) COLLATE Chinese_PRC_CI_AS NULL,
	[BLFYNR] [varchar](200) COLLATE Chinese_PRC_CI_AS NULL,
 CONSTRAINT [PK_MZ_GCJL] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]




IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='VISITSERVICE')
DROP TABLE VISITSERVICE
CREATE TABLE [dbo].[VISITSERVICE](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[YBKH] [varchar](25) COLLATE Chinese_PRC_CI_AS NULL,
	[NAME] [varchar](100) COLLATE Chinese_PRC_CI_AS NULL,
	[SEX] [varchar](1) COLLATE Chinese_PRC_CI_AS NULL,
	[ADDRESS] [varchar](100) COLLATE Chinese_PRC_CI_AS NULL,
	[PHONE] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[CONTENT] [varchar](5000) COLLATE Chinese_PRC_CI_AS NULL,
	[USERID] [varchar](30) COLLATE Chinese_PRC_CI_AS NULL,
	[MARKDATE] [datetime] NULL,
	[KIND] [varchar](2) COLLATE Chinese_PRC_CI_AS NULL,
 CONSTRAINT [PK_VISITSERVICE] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]




IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='YK_TYPK_WPY')
DROP TABLE YK_TYPK_WPY
CREATE TABLE [dbo].[YK_TYPK_WPY](
	[YPXH] [numeric](6, 0) NULL,
	[PYDM] [varchar](8) COLLATE Chinese_PRC_CI_AS NULL,
	[WBDM] [varchar](8) COLLATE Chinese_PRC_CI_AS NULL,
	[YPMC] [varchar](40) COLLATE Chinese_PRC_CI_AS NULL,
	[YPGG] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[YPDW] [varchar](4) COLLATE Chinese_PRC_CI_AS NULL,
	[YPJL] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[JLDW] [varchar](8) COLLATE Chinese_PRC_CI_AS NULL
) ON [PRIMARY]



IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='MZ_SMFWYY')
DROP TABLE MZ_SMFWYY
CREATE TABLE [dbo].[MZ_SMFWYY](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[BRXM] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[BRXB] [varchar](1) COLLATE Chinese_PRC_CI_AS NULL,
	[LXDH] [varchar](30) COLLATE Chinese_PRC_CI_AS NULL,
	[YYSJ] [datetime] NULL,
	[JTDZ] [varchar](100) COLLATE Chinese_PRC_CI_AS NULL,
	[REMARK] [varchar](500) COLLATE Chinese_PRC_CI_AS NULL,
	[YBKH] [varchar](30) COLLATE Chinese_PRC_CI_AS NULL,
	[LRRQ] [datetime] NULL,
	[FLAG] [varchar](1) COLLATE Chinese_PRC_CI_AS NULL,
	[KIND] [varchar](2) COLLATE Chinese_PRC_CI_AS NULL,
 CONSTRAINT [PK_MZ_SMFWYY] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]




IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='MZ_WPGCJL')
DROP TABLE MZ_WPGCJL
CREATE TABLE [dbo].[MZ_WPGCJL](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CFID] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[USERID] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[DISU] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[GUANCHA] [varchar](2000) COLLATE Chinese_PRC_CI_AS NULL,
	[CHULI] [varchar](2000) COLLATE Chinese_PRC_CI_AS NULL,
	[LRRQ] [datetime] NULL,
	[BLFY] [varchar](1) COLLATE Chinese_PRC_CI_AS NULL,
	[BLFYNR] [varchar](200) COLLATE Chinese_PRC_CI_AS NULL,
 CONSTRAINT [PK_MZ_WPGCJL] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]




IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='CF_WPCFJOB')
DROP TABLE CF_WPCFJOB
CREATE TABLE [dbo].[CF_WPCFJOB](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CFID] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[TOTAL] [varchar](10) COLLATE Chinese_PRC_CI_AS NULL,
	[CURRENTS] [nchar](10) COLLATE Chinese_PRC_CI_AS NULL,
	[MARKDATE] [datetime] NULL,
 CONSTRAINT [PK_CF_WPCFJOB] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]



IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='GY_WPNURSEJOB')
DROP TABLE GY_WPNURSEJOB
CREATE TABLE [dbo].[GY_WPNURSEJOB](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[USERID] [varchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[CFID] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[NUMBER] [varchar](10) COLLATE Chinese_PRC_CI_AS NULL,
	[JLRQ] [datetime] NULL,
 CONSTRAINT [PK_GY_WPNURSEJOB] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
) ON [PRIMARY]
) ON [PRIMARY]




IF  EXISTS (SELECT * FROM SYSOBJECTS WHERE NAME='MS_SURVEY')
DROP TABLE MS_SURVEY
CREATE TABLE [dbo].[MS_SURVEY](
	[SBXH] [numeric](18, 0) NOT NULL,
	[ID] [numeric](18, 0) NOT NULL,
	[MZHM] [varchar](30) COLLATE Chinese_PRC_CI_AS NOT NULL,
	[JZXH] [numeric](18, 0) NULL,
	[GMBJ] [varchar](2) COLLATE Chinese_PRC_CI_AS NULL,
	[GMWZ] [varchar](20) COLLATE Chinese_PRC_CI_AS NULL,
	[XXDM] [numeric](4, 0) NULL,
	[MZZD] [varchar](500) COLLATE Chinese_PRC_CI_AS NULL,
	[TW] [numeric](4, 1) NULL,
	[MB] [numeric](4, 0) NULL,
	[HX] [numeric](4, 0) NULL,
	[SSY] [numeric](3, 0) NULL,
	[SZY] [numeric](3, 0) NULL,
	[JLSJ] [datetime] NULL,
	[CLR] [varchar](30) COLLATE Chinese_PRC_CI_AS NULL,
	[SFLSRY] [varchar](1) COLLATE Chinese_PRC_CI_AS NULL CONSTRAINT [DF_MS_SURVEY_SFLSRY]  DEFAULT (''),
	[SFYQRY] [varchar](1) COLLATE Chinese_PRC_CI_AS NULL CONSTRAINT [DF_MS_SURVEY_SFYQRY]  DEFAULT (''),
 CONSTRAINT [PK_MS_SURVEY] PRIMARY KEY CLUSTERED 
(
	[SBXH] ASC
) ON [PRIMARY]
) ON [PRIMARY]