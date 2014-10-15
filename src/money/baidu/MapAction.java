package money.baidu;

import org.springframework.jdbc.core.JdbcTemplate;

import common.base.SpringContextUtil;

import util.MolImportUtil;
import chemaxon.struc.Molecule;
import chemaxon.util.ConnectionHandler;
import dwz.present.BaseAction;

public class MapAction extends BaseAction {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 化学公式导入字符串.
	 */
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String initMap() {
		return "detail";
	}

	public String initSumMap() {
		return "sum";
	}

	public String initMarvinjs() {
		return "marvinjs";
	}

	public String saveSource() {
		Molecule molecule = MolImportUtil.importMolFromStringAsQuery(source);
		ConnectionHandler handler = (ConnectionHandler) SpringContextUtil
				.getBean("chemaxonHandler");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
				.getBean("jdbcTemplate");
		String huaxue = molecule.getFormula();
		try {
			handler.connectToDatabase();
			MolImportUtil.databaseImportFromMolObject(molecule, handler,
					"myfirst");

			jdbcTemplate.update(
					"update chem_detail set chem_struct = ? where id=?",
					new Object[] { huaxue, id });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String initMarvinjs2() {
		request.setAttribute("id", id);
		return "marvinjs2";
	}
}
