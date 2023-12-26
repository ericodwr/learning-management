package com.ocire.lms.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionAnswerFilesDao;
import com.ocire.lms.model.File;
import com.ocire.lms.model.QuestionAnswerFiles;

@Repository
@Profile("hql-query")
public class QuestionAnswerFilesDaoHqlImpl implements QuestionAnswerFilesDao {
	@PersistenceContext
	private EntityManager em;

	public QuestionAnswerFilesDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public QuestionAnswerFiles insert(QuestionAnswerFiles questionAnswerFiles) {
		em.persist(questionAnswerFiles);
		return questionAnswerFiles;
	}

	@Override
	public List<QuestionAnswerFiles> getByAnswerId(Long id) {
		final List<QuestionAnswerFiles> answerFiles = new ArrayList<>();
		final String sql = "SELECT tqa.id, tqa.file.fileName, tqa.file.fileExtens FROM QuestionAnswerFiles tqa "
				+ " WHERE tqa.questionAnswer.id = :id";

		final List<?> userObjs = this.em.createQuery(sql).setParameter("id", id).getResultList();

		if (userObjs.size() > 0) {
			for (Object userObj : userObjs) {
				final Object[] userArr = (Object[]) userObj;
				final QuestionAnswerFiles answerFile = new QuestionAnswerFiles();
				answerFile.setId(Long.valueOf(userArr[0].toString()));

				final File file = new File();
				file.setFileName(userArr[1].toString());
				file.setFileExtens(userArr[2].toString());
				answerFile.setFile(file);
				answerFiles.add(answerFile);
			}
		}
		return answerFiles;
	}

}
