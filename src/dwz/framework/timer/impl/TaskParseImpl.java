package dwz.framework.timer.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dwz.framework.timer.TaskParse;
import dwz.framework.timer.TaskUnit;

public class TaskParseImpl implements TaskParse {

	private static final String TIMER_TASK = "timer-task";

	private static final String TASK_NAME = "name";

	private static final String TASK_PERIOD = "period";

	private static final String TASK_PRIORITY = "priority";

	private static final String TASK_CLASS = "class";

	private static final String TASK_DELAY = "delay";

	private static final String TASK_START_TIME = "start-time";

	private static final String TASK_RUNNABLE = "runnable";

	private String configFile = null;

	private Map<String, TaskUnit> taskUnits = null;

	public TaskParseImpl(String filePath) {
		this.configFile = filePath;
		this.taskUnits = new HashMap<String, TaskUnit>();
		this.parse();
	}

	public Collection<TaskUnit> getTaskUnits() {
		return this.taskUnits.values();
	}

	private void parse() {
		Document doc = this.getDocument();
		NodeList list = doc.getElementsByTagName(TIMER_TASK);
		int len = list.getLength();
		for (int i = 0; i < len; i++) {
			String name = this.getElementText(doc.getElementsByTagName(
					TASK_NAME).item(i));
			String period = this.getElementText(doc.getElementsByTagName(
					TASK_PERIOD).item(i));
			String priority = this.getElementText(doc.getElementsByTagName(
					TASK_PRIORITY).item(i));
			String taskClass = this.getElementText(doc.getElementsByTagName(
					TASK_CLASS).item(i));
			String delay = this.getElementText(doc.getElementsByTagName(
					TASK_DELAY).item(i));
			String startTime = this.getElementText(doc.getElementsByTagName(
					TASK_START_TIME).item(i));
			String runnable = this.getElementText(doc.getElementsByTagName(
					TASK_RUNNABLE).item(i));

			Class<?> clazz = null;
			try {
				clazz = Class.forName(taskClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			TaskUnit taskUnit = new TaskUnitImpl(name, priority, period, delay,
					clazz, startTime, runnable);
			this.taskUnits.put(name, taskUnit);
		}

	}

	public TaskUnit getTaskUnit(String name) {
		return this.taskUnits.get(name);
	}

	private Document getDocument() {
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;

		factory = DocumentBuilderFactory.newInstance();

		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse(this.configFile);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	private String getElementText(Node node) {
		StringBuffer buf = new StringBuffer();

		NodeList list = node.getChildNodes();
		int len = list.getLength();
		for (int i = 0; i < len; i++) {
			if (list.item(i).getNodeType() == Node.TEXT_NODE) {
				buf.append(list.item(i).getNodeValue());
			}
		}
		return buf.toString().trim();
	}
}
