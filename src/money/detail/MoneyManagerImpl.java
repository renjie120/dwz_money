package money.detail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import brightmoon.jdbc.DataHandler;
import brightmoon.jdbc.DbTool;
import brightmoon.util.NewJsonUtil;

import common.base.AllSelect;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.util.DateTool;
import common.util.NPOIReader;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class MoneyManagerImpl extends AbstractBusinessObjectManager implements
		MoneyManager {

	private MoneyDao moneyDao = null;

	public MoneyManagerImpl(MoneyDao moneyDao) {
		this.moneyDao = moneyDao;
	}

	public Integer searchMoneyNum(Map<MoneySearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.moneyDao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	public Collection<Money> searchMoney(
			Map<MoneySearchFields, Object> criterias, String orderField,
			int startIndex, int count) {
		ArrayList<Money> eaList = new ArrayList<Money>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		// 直接根据hql语句进行查询.
		Collection<MoneyVO> voList = this.moneyDao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select1 = allSelect.getAllMoneyType();

		for (MoneyVO po : voList) {
			po.setMoneyTypeName(select1.getName(po.getMoneyType()));
			eaList.add(new MoneyImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<MoneySearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct money) "
						: "select distinct money ").append(
				"from MoneyVO as money ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<MoneySearchFields, Object> entry : criterias
					.entrySet()) {
				MoneySearchFields fd = entry.getKey();
				switch (fd) {
				case MONEY_SNO:
					sb.append(count == 0 ? " where" : " and").append(
							" money.moneySno=? ");
					argList.add(entry.getValue());
					count++;
					break;
				case MONEY_TIME:
					sb.append(count == 0 ? " where" : " and").append(
							" money.moneyTime=? ");
					argList.add(entry.getValue());
					count++;
					break;
				case MONEY:
					sb.append(count == 0 ? " where" : " and").append(
							" money.money=? ");
					argList.add(entry.getValue());
					count++;
					break;
				case MONEY_TYPE:
					sb.append(count == 0 ? " where" : " and").append(
							" money.moneyType=? ");
					argList.add(entry.getValue());
					count++;
					break;
				case MONEY_DESC:
					sb.append(count == 0 ? " where" : " and").append(
							" money.moneyDesc like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case SHOP_CARD:
					sb.append(count == 0 ? " where" : " and").append(
							" money.moneyCard =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case BOOK_TYPE:
					sb.append(count == 0 ? " where" : " and").append(
							" money.bookType=? ");
					argList.add(entry.getValue());
					count++;
					break;
				default:
					break;
				}
			}
		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}
		MoneyOrderByFields orderBy = MoneyOrderByFields.MONEY_TIME_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = MoneyOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case MONEY_TIME:
			sb.append(" order by money.moneyTime");
			break;
		case MONEY_TYPE:
			sb.append(" order by money.moneyType  ");
			break;
		case MONEY:
			sb.append(" order by money.money");
			break;
		case MONEY_SNO:
			sb.append(" order by money.moneySno  ");
			break;
		case MONEY_TIME_DESC:
			sb.append(" order by money.moneyTime desc");
			break;
		case MONEY_TYPE_DESC:
			sb.append(" order by money.moneyType desc");
			break;
		case MONEY_DESC:
			sb.append(" order by money.money desc");
			break;
		case MONEY_SNO_DESC:
			sb.append(" order by money.moneySno desc");
			break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createMoney(Money money) throws ValidateFieldsException {
		MoneyImpl moneyImpl = (MoneyImpl) money;
		this.moneyDao.insert(moneyImpl.getMoneyVO());
	}

	public void removeMoney(String moneyId) {
		String[] ids = moneyId.split(",");
		for (String s : ids) {
			MoneyVO vo = this.moneyDao.findByPrimaryKey(Integer.parseInt(s));
			this.moneyDao.delete(vo);
		}
	}

	public void updateMoney(Money money) throws ValidateFieldsException {
		MoneyImpl moneyImpl = (MoneyImpl) money;

		MoneyVO po = moneyImpl.getMoneyVO();
		this.moneyDao.update(po);
	}

	public Money getMoney(Integer id) {
		Collection<MoneyVO> moneys = this.moneyDao.findRecordById(id);

		if (moneys == null || moneys.size() < 1)
			return null;

		MoneyVO money = moneys.toArray(new MoneyVO[moneys.size()])[0];

		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select1 = allSelect.getAllMoneyType();

		money.setMoneyTypeName(select1.getName(money.getMoneyType()));
		return new MoneyImpl(money);
	}

	public void importFromExcel(File file) {
		NPOIReader excel = null;
		try {
			excel = new NPOIReader(file);
			int index = excel.getSheetNames().indexOf("Sheet0");
			String[][] contents = excel.read(index, true, true);
			for (int i = 1; i < contents.length; i++) {
				MoneyVO vo = new MoneyVO();
				String moneyTimeString = contents[i][0];
				String moneyString = contents[i][1];
				String moneyTypeString = contents[i][2];
				String moneyDescString = contents[i][3];
				vo.setMoneyTime(DateTool.getDate(moneyTimeString));
				vo.setMoney(Double.parseDouble(moneyString));
				vo.setMoneyType(moneyTypeString);
				vo.setMoneyDesc(moneyDescString);
				vo.setShopCard(-1);
				vo.setBookType("1");
				this.moneyDao.insert(vo);
				// this.moneyDao.callProcedure("{call
				// addMoneyDetail(?,?,?,?)}",new
				// Object[]{moneyTimeString,moneyString,moneyTypeString,moneyDescString});
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String addMoneySql = "insert into money_detail_t( money_time,money,money_type,"
			+ "money_desc,useful,code)" + " values(   ? ,?,?,?,1,? )";
	String queryMoneySql = "select money_sno,money_time,money,money_type,money_desc from money_detail_t where code=? limit ?,?" ;
	String queryMoneyCountSql = "select count(1) from money_detail_t where code=?  " ;
	String updateMoneySql = "update money_detail_t set code = ?  where code=?";

	public static void main(String[] args) {
		MoneySyn ss = (MoneySyn) JSONObject.toBean(JSONObject
				.fromObject("{arg1:1}"), MoneySyn.class);
		System.out.println(ss.getArg1());
	}

	public String syn(String method, String json, String data) {
		String result;
		try {
			DbTool db = new DbTool();
			MoneySyn arg = (MoneySyn) NewJsonUtil.jsonToJava(json,
					MoneySyn.class);
			if ("addMonyeFromPhone".equals(method)) {
				String[] moneys = data.split(";");
				String[] ss;
				List momeyVos = new ArrayList();
				for (String s : moneys) {
					s = s.replace("$", "");
					ss = s.split(",");
					momeyVos.clear();
					momeyVos.add(ss[0]);// 日期
					momeyVos.add(ss[1]);// 金额
					momeyVos.add(changeStr(ss[2]));// 描述
					momeyVos.add(changeStr(ss[3]));// 类型
					momeyVos.add(arg.getArg1());
					db.updateRecords(addMoneySql, momeyVos);
				}
				result = "成功添加数目:"+moneys.length+"!";
			} else if ("getAllNewMoneys".equals(method)) {
				List argument = new ArrayList();
				argument.add(arg.getArg1());
				argument.add(Integer.parseInt(""+arg.getArg2()));//起始行
				argument.add(Integer.parseInt(""+arg.getArg3()));//终止行
				final StringBuffer buf = new StringBuffer("[");
				db.queryList(queryMoneySql, argument, new DataHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						buf.append("{\"money_time\":\"" + rs.getString(2) + "\",");
						buf.append("\"money\":\"" + rs.getString(3) + "\",");
						buf.append("\"money_type\":\"" + rs.getString(4) + "\",");
						buf.append("\"money_desc\":\"" + rs.getString(5) + "\"},");
					}
				});
				if (buf.lastIndexOf(",") != -1)
					result = buf.deleteCharAt(buf.lastIndexOf(",")).append("]").toString();
				else
					result = "无记录!";
			} else if ("queryMoneyCount".equals(method)) {
				List argument = new ArrayList();
				argument.add(arg.getArg1());  
				int count = db.queryForInt(queryMoneyCountSql, argument);
				if (count>0)
					result = ""+count;
				else
					result = "0";
			}else if ("updateAllNewMoneys".equals(method)) {
				List argument = new ArrayList();
				argument.add(arg.getArg1());
				argument.add(arg.getArg2());
				if(db.updateRecords(updateMoneySql, argument)>0)
				 	result = "更新成功!";
				else
					result = "更新失败!";
			} else {
				result = "没有找到合适的处理方法!";
			}
		} catch (Exception e) {
			// PMS Auto-generated catch block
			e.printStackTrace();
			result = "处理失败，请重试!";
		}
		return result;
	}

	private String changeStr(String old) {
		try {
			System.out.println(old);
			System.out.println(new String(old.getBytes("GBK"), "UTF-8"));
			System.out.println(new String(old.getBytes("GBK"), "ISO_8859_1"));
			System.out.println(new String(old.getBytes("UTF-8"), "GBK"));
			System.out.println(new String(old.getBytes("UTF-8"), "ISO_8859_1"));
			System.out.println(new String(old.getBytes("GBK"), "ISO_8859_1"));
			return new String(old.getBytes("GBK"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return old;
		}
	}
}
