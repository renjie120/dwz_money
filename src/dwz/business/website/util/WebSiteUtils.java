package dwz.business.website.util;

import java.util.Map;

import dwz.business.website.Template;
import dwz.business.website.Website;
import dwz.business.website.WebsiteManager;
import dwz.business.website.impl.TemplateImpl;
import dwz.framework.core.factory.BusinessFactory;

public class WebSiteUtils {

	public static Template buildTemplate(Map<String, Object> properties) {

		String name = (String) properties.get("name");

		String label = (String) properties.get("label");

		String description = (String) properties.get("description");

		String prePicBase = (String) properties.get("prePicBase");

		String prePicExt = (String) properties.get("prePicExt");

		Template commonTemplate = new TemplateImpl(name, label, description,
				prePicBase, prePicExt);
		return commonTemplate;
	}
}
