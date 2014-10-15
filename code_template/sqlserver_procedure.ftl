/************插入培训信息的存储过程***********************/
CREATE PROCEDURE [dbo].[CRM_peixun_Insert] (@px_people varchar(50), @px_class varchar(50), @px_time varchar(10), @px_address varchar(500), @px_money_station varchar(500), @px_charge varchar(100), @crm_id int, @flag int OUTPUT, @msg varchar(80) OUTPUT) AS
INSERT INTO CRM_peixun (px_people,px_class,px_time,px_address,px_money_station,px_charge,crm_id)
VALUES (@px_people,
        @px_class,
        @px_time,
        @px_address,
        @px_money_station,
        @px_charge,
        @crm_id)
SET @flag = 1
SET @msg = 'OK!' ;