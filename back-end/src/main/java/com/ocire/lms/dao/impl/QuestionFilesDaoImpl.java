package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionFilesDao;
import com.ocire.lms.model.QuestionFiles;

@Repository
@Profile("native-query")
public class QuestionFilesDaoImpl implements QuestionFilesDao {
	@PersistenceContext
	private EntityManager em;

	public QuestionFilesDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionFiles> getQuestionFilesByQuestionId(Long questionId)  {
		final String sql = "SELECT * FROM t_question_files qf " + "INNER JOIN t_file tf " + "ON qf.file_id = tf.id "
				+ "WHERE qf.question_id = :questionId";

		final List<QuestionFiles> questionFilesList = this.em.createNativeQuery(sql, QuestionFiles.class)
				.setParameter("questionId", questionId).getResultList();

		return questionFilesList;
	}

	@Override
	public QuestionFiles insert(QuestionFiles questionFiles)  {
		em.persist(questionFiles);
		return questionFiles;
	}

}
