package dwz.business.inv;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public interface CategoryManager extends BusinessObjectManager {

	public Collection<Category> getCategorys();

	public Category newCategory();

	@Transactional
	public String createCategory(Category category)
			throws ValidateFieldsException;

	@Transactional
	public void deleteCategory(int id);

	@Transactional
	public void updateCategory(Category category);

	public Category getCategory(int id);
}
