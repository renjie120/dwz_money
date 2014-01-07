

INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('完善我的资料功能',{d '2014-01-04'},{d '2014-01-04'},'',1,15,'admin',0,9,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('完善查询条件自动生成功能',{d '2014-01-04'},{d '2014-01-04'},'',1,15,'admin',0,8,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('完成图形功能的保存编辑功能',{d '2014-01-04'},null,'',2,14,'admin',0,4,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('完成图形报表查询条件功能',{d '2014-01-04'},null,'',2,14,'admin',0,7,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('完成手机端理财代码自动保存功能',{d '2014-01-04'},null,'',2,14,'admin',0,6,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('完成菜单功能',{d '2014-01-04'},{d '2014-01-05'},'',2,15,'admin',0,3,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('完成菜单权限控制功能',{d '2014-01-04'},null,'',3,14,'admin',0,5,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('添加系统动态代码功能，例如首页标题动态配置',{d '2014-01-04'},null,'',1,15,'admin',0,10,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('缩小依赖的jar包',{d '2014-01-04'},null,'',3,14,'admin',0,1,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('考虑使用简单的nutz包',{d '2014-01-04'},null,'',3,14,'admin',0,2,null);
INSERT INTO question_t (questionDesc,questionDate,consoleDate,answer,sort,status,submit,orderId,id,tag) VALUES ('自动生成代码里面的列表自动转换为汉字',{d '2014-01-05'},{d '2014-01-05'},'',1,15,'test',0,11,null);


INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (1,null,'业务系统','0',0,'','4','A');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (2,'a','GridTree2.0展示','1',0,'','5','A1');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (3,null,'基本信息列表','1',0,'','5','A2');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (4,null,'报表展示','1',0,'','5','A3');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (5,'b','普通表格树(推荐)','2',0,'/commonGridTreeIndex.jsp','6','A1_1');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (6,'c','配置说明','2',0,'/gridTree/myApi.html','6','A1_2');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (7,null,'API文档','2',0,'/gridTree/myMethod.html','6','A1_3');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (8,null,'菜单列表','3',0,'/money/menu!query.do','6','A2_1');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (9,null,'收支列表','3',0,'/money/newmoney!query.do','6','A2_2');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (10,null,'问题列表','3',0,'/money/question!query.do','6','A2_3');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (11,null,'计划列表','3',0,'/money/plan!query.do','6','A2_4');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (12,null,'收支相关报表','4',0,'/money/newmoney!report.do','6','A3_1');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (13,null,'问题相关报表','4',0,'/money/question!report.do','6','A3_2');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (14,null,'系统维护','0',0,'null','4','B');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (15,null,'参数类型','3',0,'/money/paramtype!query.do','6','A2_4');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (16,null,'参数列表','14',0,'/money/param!query.do','5','B1');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (17,null,'参数类型','14',0,'/money/paramtype!query.do','5','B2');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (18,null,'组织机构','14',0,'/money/org!query.do','5','B3');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (19,null,'日志列表','3',0,'/money/diary!query.do','5','A2_5');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (20,null,'缓存列表','14',0,'/money/cache!query.do','5','B4');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (21,null,'金额类型','14',0,'/money/moneyType!query.do','5','B5');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (22,null,'用户列表','14',0,'/money/myuser!query.do','5','B6');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (23,null,'演示系统','0',0,'null','4','C');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (24,'f','fusionChartFree文档','23',0,'http://docs.fusioncharts.com/free/','5','C1');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (25,'g','超级控制台','23',0,'/money/superconsole!init.do','5','C2');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (26,'d','DWZ演示','23',0,'/demoDwz/dwz-ria/index.html','5','C3');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (27,'e','DWZ官网','23',0,'http://j-ui.com','5','C4');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (28,null,'文件上传','23',0,'/upload/test!init.do','5','C5');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (29,null,'文件上传2','23',0,'/upload/test2!init.do','5','C6');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (30,null,'测试OGNL表达式','23',0,'/upload/test2!testOgnl.do','5','C7');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (31,null,'类型转换','23',0,'/typechangedemo/saveuser!initUser.do','5','C8');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (32,null,'类型转换2','23',0,'/typechangedemo/saveuser2!initUser.do','5','C9');
INSERT INTO menu_t (menuid,target,menuname,parentId,orderId,url,level,relId) VALUES (33,null,'权限维护','14',0,'http://www.baidu.com','5','CCC');
