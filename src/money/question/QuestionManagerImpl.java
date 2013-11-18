package money.question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.util.CommonUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class QuestionManagerImpl extends AbstractBusinessObjectManager
		implements QuestionManager {

	private QuestionDao questionDao = null;

	public QuestionManagerImpl(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public Integer searchQuestionNum(
			Map<QuestionSearchFields, Object> criterias,
			QuestionQueryVO questionQueryVO) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias,
				questionQueryVO, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.questionDao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	public Collection<Question> searchQuestion(
			Map<QuestionSearchFields, Object> criterias,
			QuestionQueryVO questionQueryVO, String orderField, int startIndex,
			int count) {
		ArrayList<Question> eaList = new ArrayList<Question>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias,
				questionQueryVO, orderField);
		String hql = quertParas[0].toString();
		// 直接根据hql语句进行查询.
		Collection<QuestionVO> voList = this.questionDao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select1 = allSelect
				.getParamsByType(AllSelectContants.QUESTION_SORT.getName());
		ParamSelect select2 = allSelect
				.getParamsByType(AllSelectContants.QUESTION_STATUS.getName());
		if (select1 != null && select2 != null)
			for (QuestionVO po : voList) {
				po.setSortName(select1.getName("" + po.getSort()));
				po.setStatusName(select2.getName("" + po.getStatus()));
				eaList.add(new QuestionImpl(po));
			}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<QuestionSearchFields, Object> criterias,
			QuestionQueryVO questionQueryVO, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct question) "
						: "select distinct question ").append(
				"from QuestionVO as question ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<QuestionSearchFields, Object> entry : criterias
					.entrySet()) {
				QuestionSearchFields fd = entry.getKey();
				switch (fd) {
				case QUESTIONID:
					sb.append(count == 0 ? " where" : " and").append(
							" question.questionId =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case QUESTIONDESC:
					sb.append(count == 0 ? " where" : " and").append(
							" question.questionDesc like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case STATUS:
					sb.append(count == 0 ? " where" : " and").append(
							" question.status =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case QUESTIONDATE:
					sb.append(count == 0 ? " where" : " and").append(
							" question.questionDate=? ");
					argList.add(entry.getValue());
					count++;
					break;
				case CONSOLEDATE:
					sb.append(count == 0 ? " where" : " and").append(
							" question.consoleDate=? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ANSWER:
					sb.append(count == 0 ? " where" : " and").append(
							" question.answer like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case SORT:
					sb.append(count == 0 ? " where" : " and").append(
							" question.sort =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORDERID:
					sb.append(count == 0 ? " where" : " and").append(
							" question.orderId=? ");
					argList.add(entry.getValue());
					count++;
					break;
				default:
					break;
				}
			}

		// 如果是高级查询，含有问题描述信息查询
		if (!CommonUtil.isEmpty(questionQueryVO.getQuestionDescC())) {
			// 包含
			if ("1".equals(questionQueryVO.getQuestionDescConditionC())) {
				sb.append(count == 0 ? " where" : " and").append(
						" question.questionDesc like ? ");
				argList.add(questionQueryVO.getQuestionDescC());
				count++;
			} else {
				sb.append(count == 0 ? " where" : " and").append(
						" question.questionDesc not like ? ");
				argList.add(questionQueryVO.getQuestionDescC());
				count++;
			}
		}
		// 查询问题类别
		if (questionQueryVO.getQuestionSortC() > 0) {
			// 包含
			if ("1".equals(questionQueryVO.getQuestionSortConditionC())) {
				sb.append(count == 0 ? " where" : " and").append(
						" question.sort in (?) ");
				argList.add(questionQueryVO.getQuestionSortC());
				count++;
			} else {
				sb.append(count == 0 ? " where" : " and").append(
						" question.sort not in (?) ");
				argList.add(questionQueryVO.getQuestionSortC());
				count++;
			}
		}
		// 查询问题状态
		if (questionQueryVO.getQuestionStatusC() > 0) {
			// 包含
			if ("1".equals(questionQueryVO.getQuestionStatusConditionC())) {
				sb.append(count == 0 ? " where" : " and").append(
						" question.status in (?) ");
				argList.add(questionQueryVO.getQuestionStatusC());
				count++;
			} else {
				sb.append(count == 0 ? " where" : " and").append(
						" question.status not in (?) ");
				argList.add(questionQueryVO.getQuestionStatusC());
				count++;
			}
		}
		// 查询答案条件
		if (!CommonUtil.isEmpty(questionQueryVO.getAnswerC())) {
			// 包含
			if ("1".equals(questionQueryVO.getQuestionDescConditionC())) {
				sb.append(count == 0 ? " where" : " and").append(
						" question.answer like ? ");
				argList.add(questionQueryVO.getAnswerC());
				count++;
			} else {
				sb.append(count == 0 ? " where" : " and").append(
						" question.answer not like ? ");
				argList.add(questionQueryVO.getAnswerC());
				count++;
			}
		}
		// 较小的解决日期
		if (!CommonUtil.isBlank(questionQueryVO.getSmallConsoleDateC())) {
			sb.append(count == 0 ? " where" : " and").append(
					" question.consoleDate >= ? ");
			argList.add(questionQueryVO.getSmallConsoleDateC());
			count++;
		}
		// 较大的解决日期
		if (!CommonUtil.isBlank(questionQueryVO.getBigConsoleDateC())) {
			sb.append(count == 0 ? " where" : " and").append(
					" question.consoleDate <= ? ");
			argList.add(questionQueryVO.getBigConsoleDateC());
			count++;
		}
		// 较小的提问日期
		if (!CommonUtil.isBlank(questionQueryVO.getSmallQuestionDateC())) {
			sb.append(count == 0 ? " where" : " and").append(
					" question.questionDate >= ? ");
			argList.add(questionQueryVO.getSmallQuestionDateC());
			count++;
		}
		// 较大的提问日期
		if (!CommonUtil.isBlank(questionQueryVO.getBigQuestionDateC())) {
			sb.append(count == 0 ? " where" : " and").append(
					" question.questionDate <= ? ");
			argList.add(questionQueryVO.getBigQuestionDateC());
			count++;
		}
		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}
		QuestionOrderByFields orderBy = QuestionOrderByFields.QUESTIONID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = QuestionOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case QUESTIONID:
			sb.append(" order by question.questionId");
			break;
		case QUESTIONDATE:
			sb.append(" order by question.questionDate");
			break;
		case CONSOLEDATE:
			sb.append(" order by question.consoleDate");
			break;
		case SORT:
			sb.append(" order by question.sort");
			break;
		case ORDERID:
			sb.append(" order by question.orderId");
			break;
		case STATUS:
			sb.append(" order by question.status");
			break;
		case QUESTIONID_DESC:
			sb.append(" order by question.questionId desc");
			break;
		case QUESTIONDATE_DESC:
			sb.append(" order by question.questionDate desc");
			break;
		case CONSOLEDATE_DESC:
			sb.append(" order by question.consoleDate desc");
			break;
		case SORT_DESC:
			sb.append(" order by question.sort desc");
			break;
		case ORDERID_DESC:
			sb.append(" order by question.orderId desc");
			break;
		case STATUS_DESC:
			sb.append(" order by question.status desc");
			break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createQuestion(Question question)
			throws ValidateFieldsException {
		QuestionImpl questionImpl = (QuestionImpl) question;
		this.questionDao.insert(questionImpl.getQuestionVO());
	}

	public void removeQuestion(String moneyId) {
		String[] ids = moneyId.split(",");
		for (String s : ids) {
			QuestionVO vo = this.questionDao.findByPrimaryKey(Integer
					.parseInt(s));
			this.questionDao.delete(vo);
		}
	}

	public void updateQuestion(Question question)
			throws ValidateFieldsException {
		QuestionImpl questionImpl = (QuestionImpl) question;

		QuestionVO po = questionImpl.getQuestionVO();
		this.questionDao.update(po);
	}

	public Question getQuestion(Integer id) {
		Collection<QuestionVO> questions = this.questionDao.findRecordById(id);

		if (questions == null || questions.size() < 1)
			return null;

		QuestionVO question = questions
				.toArray(new QuestionVO[questions.size()])[0];

		return new QuestionImpl(question);
	}

}
