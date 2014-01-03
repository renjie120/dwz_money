slee

INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (1,1,'简单',1,'0',1);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (2,1,'中级',2,'0',2);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (3,1,'复杂',3,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (4,2,'一级菜单',1,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (5,2,'二级菜单',2,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (6,2,'三级菜单',3,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (7,3,'计划中',1,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (8,3,'执行中',2,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (9,3,'执行完毕',3,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (10,3,'废弃',4,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (11,4,'紧急',1,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (12,4,'重要',2,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (13,4,'无足轻重',3,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (14,6,'待解决',1,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (15,6,'已经解决',2,'0',0);
INSERT INTO params (parameterID,parameterType,parameterName,paramvalue,usevalue,orderId) VALUES (16,6,'解决不了',3,'0',0);


INSERT INTO parameter_type (parameter_type_Id,parameter_type_name,orderId,code) VALUES (1,'问题类别',1,'questionSort');
INSERT INTO parameter_type (parameter_type_Id,parameter_type_name,orderId,code) VALUES (2,'菜单级别',2,'menulevel');
INSERT INTO parameter_type (parameter_type_Id,parameter_type_name,orderId,code) VALUES (3,'计划状态',3,'planstatus');
INSERT INTO parameter_type (parameter_type_Id,parameter_type_name,orderId,code) VALUES (4,'计划类型',4,'plantype');
INSERT INTO parameter_type (parameter_type_Id,parameter_type_name,orderId,code) VALUES (5,'菜单目的',5,'menutarget');
INSERT INTO parameter_type (parameter_type_Id,parameter_type_name,orderId,code) VALUES (6,'问题状态',6,'questionStatus');
