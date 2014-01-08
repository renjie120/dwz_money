package money.org;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class OrgManagerImpl extends AbstractBusinessObjectManager implements
		OrgManager {

	private OrgDao orgDao = null;

	public OrgManagerImpl(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public Integer searchOrgNum(Map<OrgSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.orgDao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	public Collection<Org> searchOrg(Map<OrgSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<Org> eaList = new ArrayList<Org>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<OrgVO> voList = this.orgDao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (OrgVO po : voList) {
			eaList.add(new OrgImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<OrgSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( org) "
						: "select new OrgVO(org.orgId,org.orgName,org.orderCode,org2.orgName,org.orderId)  ").append("from OrgVO as org ,OrgVO as org2 ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<OrgSearchFields, Object> entry : criterias
					.entrySet()) {
				OrgSearchFields fd = entry.getKey();
				switch (fd) {
				case ORGID:
					sb.append(count == 0 ? " where" : " and").append(
							" org.orgId =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORGNAME:
					sb.append(count == 0 ? " where" : " and").append(
							" org.orgName like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORDERCODE:
					sb.append(count == 0 ? " where" : " and").append(
							" org.orderCode like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case PARENTORG:
					sb.append(count == 0 ? " where" : " and").append(
							" org.parentOrg like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORDERID:
					sb.append(count == 0 ? " where" : " and").append(
							" org.orderId like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}else{
			sb.append(count == 0 ? " where " : " and").append(
					"  org.parentOrg=org2.orgId ");
		}

		OrgOrderByFields orderBy = OrgOrderByFields.ORGID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = OrgOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case ORGID:
			sb.append(" order by org.orgId");
			break;
		case ORGID_DESC:
			sb.append(" order by org.orgId desc");
			break;
		case ORGNAME:
			sb.append(" order by org.orgName");
			break;
		case ORGNAME_DESC:
			sb.append(" order by org.orgName desc");
			break;
		case ORDERCODE:
			sb.append(" order by org.orderCode");
			break;
		case ORDERCODE_DESC:
			sb.append(" order by org.orderCode desc");
			break;
		case PARENTORG:
			sb.append(" order by org.parentOrg");
			break;
		case PARENTORG_DESC:
			sb.append(" order by org.parentOrg desc");
			break;
		case ORDERID:
			sb.append(" order by org.orderId");
			break;
		case ORDERID_DESC:
			sb.append(" order by org.orderId desc");
			break;

		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createOrg(Org org) throws ValidateFieldsException {
		OrgImpl orgImpl = (OrgImpl) org;
		this.orgDao.insert(orgImpl.getOrgVO());
	}

	public void removeOrg(String orgIds) {
		String[] idArr = orgIds.split(",");
		for (String s : idArr) {
			OrgVO vo = this.orgDao.findByPrimaryKey(Integer.parseInt(s));
			this.orgDao.delete(vo);
		}
	}

	public void updateOrg(Org org) throws ValidateFieldsException {
		OrgImpl orgImpl = (OrgImpl) org;

		OrgVO po = orgImpl.getOrgVO();
		this.orgDao.update(po);
	}

	public Org getOrg(Integer id) {
		Collection<OrgVO> orgs = this.orgDao.findRecordById(id);

		if (orgs == null || orgs.size() < 1)
			return null;

		OrgVO org = orgs.toArray(new OrgVO[orgs.size()])[0];

		return new OrgImpl(org);
	}

}
