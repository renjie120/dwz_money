package dwz.business.inv.impl;

import java.util.ArrayList;
import java.util.Collection;

import dwz.business.inv.Category;
import dwz.business.inv.CategoryManager;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.persistence.beans.InvCategory;
import dwz.persistence.daos.InvCategoryDao;

public class CategoryManagerImpl extends AbstractBusinessObjectManager
		implements CategoryManager {

	private InvCategoryDao invCategoryDao;

	public CategoryManagerImpl(InvCategoryDao invCategoryDao) {
		this.invCategoryDao = invCategoryDao;
	}

	public Collection<Category> getCategorys() {
		ArrayList<Category> bos = new ArrayList<Category>();
		Collection<InvCategory> pos = invCategoryDao.findAllInvCategory();

		if (pos != null && pos.size() > 0) {
			for (InvCategory po : pos) {
				bos.add(new CategoryImpl(po));
			}
		}

		return bos;

	}

	public Category newCategory() {

		return new CategoryImpl();
	}

	public String createCategory(Category category)
			throws ValidateFieldsException {
		String ret = null;

		if (category == null || category.getName() == null) {
			log.debug("category is null.");
			throw new ValidateFieldsException("category is null.");
		}
		invCategoryDao.insert(((CategoryImpl) category).getPersistentObject());

		return ret;
	}

	public void deleteCategory(int id) {
		InvCategory po = invCategoryDao.findByPrimaryKey(id);
		if (po != null) {
			invCategoryDao.delete(po);
		}
	}

	public Category getCategory(int id) {
		InvCategory po = invCategoryDao.findByPrimaryKey(id);
		if (po != null) {
			return new CategoryImpl(po);
		}

		return null;
	}

	public void updateCategory(Category category) {
		invCategoryDao.update(((CategoryImpl) category).getPersistentObject());
	}

}
