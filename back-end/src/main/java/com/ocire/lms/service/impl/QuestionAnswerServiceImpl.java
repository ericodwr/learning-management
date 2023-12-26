package com.ocire.lms.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.FileDao;
import com.ocire.lms.dao.QuestionAnswerDao;
import com.ocire.lms.dao.QuestionAnswerFilesDao;
import com.ocire.lms.dao.QuestionDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.student.AnswerInsertReqDto;
import com.ocire.lms.model.File;
import com.ocire.lms.model.Question;
import com.ocire.lms.model.QuestionAnswer;
import com.ocire.lms.model.QuestionAnswerFiles;
import com.ocire.lms.service.PrincipalService;
import com.ocire.lms.service.QuestionAnswerService;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
	@PersistenceContext
	private EntityManager em;
	private QuestionAnswerDao questionAnswerDao;
	private QuestionDao questionDao;
	private QuestionAnswerFilesDao questionAnswerFilesDao;
	private PrincipalService principalService;
	private final FileDao fileDao;

	public QuestionAnswerServiceImpl(QuestionAnswerDao questionAnswerDao, QuestionAnswerFilesDao questionAnswerFilesDao,
			QuestionDao questionDao, PrincipalService principalService, FileDao fileDao) {
		this.questionAnswerDao = questionAnswerDao;
		this.questionAnswerFilesDao = questionAnswerFilesDao;
		this.questionDao = questionDao;
		this.principalService = principalService;
		this.fileDao = fileDao;
	}

	@Override
	public QuestionAnswer insertEssayAnswer(QuestionAnswer questionAnswer) {
		try {
			this.em.getTransaction().begin();
			QuestionAnswer answer = questionAnswerDao.insert(questionAnswer);
			this.em.getTransaction().commit();
			return answer;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public QuestionAnswerFiles insertAnswerFiles(QuestionAnswerFiles questionAnswerFiles) {
		try {
			this.em.getTransaction().begin();
			QuestionAnswerFiles answerFile = questionAnswerFilesDao.insert(questionAnswerFiles);
			this.em.getTransaction().commit();
			return answerFile;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public List<QuestionAnswer> getQuestionAnswerByStudent(Long studentId) {
		return questionAnswerDao.getByCreatedBy(studentId);
	}

	@Override
	public List<QuestionAnswerFiles> getQuestionAnswerFiles(Long questionAnswerId) {
		return questionAnswerFilesDao.getByAnswerId(questionAnswerId);
	}

	@Transactional
	@Override
	public InsertResDto insertAnswer(List<AnswerInsertReqDto> data) {
		final InsertResDto result = new InsertResDto();

		data.forEach(answerData -> {
			final Question question = questionDao.getById(answerData.getQuestionId());
			final QuestionAnswer answer = new QuestionAnswer();
			if (answerData.getAnswerFiles() == null) {
				answer.setEssayAnswer(answerData.getEssayAnswer());
				answer.setQuestion(question);
				answer.setCreatedBy(principalService.getPrincipal());
				questionAnswerDao.insert(answer);
			} else {
				answer.setQuestion(question);
				answer.setCreatedBy(principalService.getPrincipal());
				final QuestionAnswer newAnswer = questionAnswerDao.insert(answer);
				
				answerData.getAnswerFiles().forEach(answerFile -> {
					final File file = new File();
					file.setFileName(answerFile.getFileName());
					file.setFileExtens(answerFile.getFileExtens());
					file.setCreatedBy(principalService.getPrincipal());
					final File newFile = fileDao.insert(file);

					final QuestionAnswerFiles answerFiles = new QuestionAnswerFiles();
					answerFiles.setFile(newFile);
					answerFiles.setQuestionAnswer(newAnswer);
					answerFiles.setCreatedBy(principalService.getPrincipal());
					questionAnswerFilesDao.insert(answerFiles);
				});
			}
		});

		result.setId(null);
		result.setMessage("Successfully answer all the question!");

		return result;
	}

}
