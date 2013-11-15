create view money_detail_view as
select dt.MONEY_SNO AS MONEY_SNO,
       dt.MONEY_TIME AS MONEY_TIME,
       dt.MONEY AS MONEY,
       dt.MONEY_TYPE AS MONEY_TYPE,
       dt.MONEY_DESC AS MONEY_DESC,
       dt.SHOPCARD AS SHOPCARD,
       dt.USEFUL AS USEFUL,
       year(dt.MONEY_TIME) AS year,
       month(dt.MONEY_TIME) AS month,
       dt.BOOKTYPE AS BOOKTYPE,
       tp.TALLY_TYPE_DESC AS tallytype,
       tp2.TALLY_TYPE_SNO AS bigtypeSNO,
       tp2.TALLY_TYPE_DESC AS bigtype
  from ((money_detail_t dt join tally_type_t tp2) join tally_type_t tp)
 where ((tp.PARENT_CODE = tp2.TYPE_CODE) and (dt.MONEY_TYPE = tp.TYPE_CODE));

create view money_detail_bigtype_v as
select sum(t.MONEY) AS money,
       t.bigtypeSNO AS BIGtypeSNO,
       t.bigtype AS BIGtype
  from money_detail_view t
 group by t.bigtypeSNO, t.bigtype;

create view money_detail_bigtype_year_month_v as
select sum(t.MONEY) AS money,
       t.bigtypeSNO AS BIGtypeSNO,
       t.bigtype AS BIGtype,
       t.year AS YEAR,
       t.month AS MONTH
  from money_detail_view t
 group by t.year, t.month, t.bigtypeSNO, t.bigtype;

create view money_detail_bigtype_year_v as
select sum(t.MONEY) AS money,
       t.bigtypeSNO AS BIGtypeSNO,
       t.bigtype AS BIGtype,
       t.year AS YEAR
  from money_detail_view t
 group by t.year, t.bigtypeSNO, t.bigtype;

create view money_detail_type_v as
select sum(t.MONEY) AS money,
       t.MONEY_TYPE AS money_type,
       t.tallytype AS tallytype
  from money_detail_view t
 group by t.MONEY_TYPE, t.tallytype;

create view money_detail_type_year_v as
select sum(t.MONEY) AS money,
       t.MONEY_TYPE AS money_type,
       t.tallytype AS tallytype,
       t.year AS year
  from money_detail_view t
 group by t.year, t.MONEY_TYPE, t.tallytype;

create view money_detail_type_year_month_v as
select sum(t.MONEY) AS money,
       t.MONEY_TYPE AS money_type,
       t.tallytype AS tallytype,
       t.year AS year,
       t.month AS MONTH
  from money_detail_view t
 group by t.year, t.month, t.MONEY_TYPE, t.tallytype;

create view question_v as
select qt.id             AS id,
       qt.questionDesc   AS questionDesc,
       qt.questiondate   AS questiondate,
       qt.consoledate    AS consoledate,
       qt.answer         AS answer,
       qt.tag            AS tag,
       qt.sort           AS sort,
       qt.ORDERID        AS ORDERID,
       qt.status         AS status,
       pm.parametername  AS typename,
       pm2.parametername AS statusname
  from ((question_t qt join params pm) join params pm2)
 where ((qt.sort = pm.parameterid) and (pm.parametertype = 5) and
       (qt.status = pm2.parameterid) and (pm2.parametertype = 6)); 
       
create view money_detail_type_year_v as
select sum(MONEY) AS money,
       t.MONEY_TYPE AS money_type,
       t.tallytype AS tallytype,
       t.year AS year
  from money_detail_view t
 group by year, MONEY_TYPE, tallytype;
       
