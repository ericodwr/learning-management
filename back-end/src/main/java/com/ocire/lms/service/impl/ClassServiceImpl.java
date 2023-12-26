package com.ocire.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.ClassDao;
import com.ocire.lms.dao.EnrollDao;
import com.ocire.lms.dao.FileDao;
import com.ocire.lms.dao.UserDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.classlms.ClassInsertReqDto;
import com.ocire.lms.dto.classlms.ClassResDto;
import com.ocire.lms.model.Class;
import com.ocire.lms.model.File;
import com.ocire.lms.model.User;
import com.ocire.lms.service.ClassService;
import com.ocire.lms.service.PrincipalService;

@Service
public class ClassServiceImpl implements ClassService {
	@PersistenceContext
	private EntityManager em;
	
	private final UserDao userDao;
	private final PrincipalService principalService;
	private final ClassDao classDao;
	private final FileDao fileDao;
	private final EnrollDao enrollDao;
	
	public ClassServiceImpl(ClassDao classDao, UserDao userDao,FileDao fileDao, PrincipalService principalService,EnrollDao enrollDao) {
		this.classDao = classDao;
		this.enrollDao = enrollDao;
		this.userDao = userDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}

	@Override
	public List<ClassResDto> getClassByStudentId() {
		final List<ClassResDto> classList = new ArrayList<>();
		classDao.getClassByUnenrollStudent(principalService.getPrincipal()).forEach(classes -> {
			final ClassResDto classDetail = new ClassResDto();
			classDetail.setId(classes.getId());
			classDetail.setClassName(classes.getClassName());
			classDetail.setClassCode(classes.getClassCode());
			classDetail.setTeacherId(classes.getTeacher().getId());
			classDetail.setPhotoId(classes.getClassImage().getId());
			classDetail.setTeacherName(classes.getTeacher().getProfile().getFullName());
			classList.add(classDetail);
		});
		return classList;
	}

	@Override
	public List<ClassResDto> getAllClasses() {
		final List<ClassResDto> classList = new ArrayList<>();
		classDao.getAllClass().forEach(classes -> {
			final ClassResDto classDetail = new ClassResDto();
			classDetail.setId(classes.getId());
			classDetail.setClassName(classes.getClassName());
			classDetail.setClassCode(classes.getClassCode());
			classDetail.setTeacherId(classes.getTeacher().getId());
			classDetail.setPhotoId(classes.getClassImage().getId());
			classDetail.setTeacherName(classes.getTeacher().getProfile().getFullName());
			classList.add(classDetail);
		});
		return classList;
	}
	
	@Override
	public List<ClassResDto> getClassByTeacherId() {
		final List<ClassResDto> classList = new ArrayList<>();
		classDao.getClassByTeacherId(principalService.getPrincipal()).forEach(classes -> {
			final ClassResDto classDetail = new ClassResDto();
			classDetail.setId(classes.getId());
			classDetail.setClassName(classes.getClassName());
			classDetail.setClassCode(classes.getClassCode());
			classDetail.setTeacherId(classes.getTeacher().getId());
			classDetail.setPhotoId(classes.getClassImage().getId());
			classDetail.setTeacherName(classes.getTeacher().getProfile().getFullName());
			
			classList.add(classDetail);
		});
		return classList;
	}
	
	@Transactional
	@Override
	public InsertResDto insertClass(ClassInsertReqDto data) {
		final InsertResDto result = new InsertResDto();

		final Class newClass = new Class();
		newClass.setClassName(data.getClassName());
		newClass.setClassCode(data.getClassCode());
		
		final User user = userDao.getById(data.getTeacherId());
		newClass.setTeacher(user);
		newClass.setCreatedBy(principalService.getPrincipal());
		
		final File classImg = new File();
		classImg.setFileName(data.getFileName());
		classImg.setFileExtens(data.getFileExt());
		classImg.setCreatedBy(principalService.getPrincipal());
		
		final File newFile = fileDao.insert(classImg);
		
		
		newClass.setClassImage(newFile);
		
		Class addedClass = classDao.insert(newClass);

		result.setId(addedClass.getId());
		result.setMessage("Successfully added new class!");

		return result;
	}

	@Override
	public List<ClassResDto> getClassByStudent() {
		List<ClassResDto> classList = new ArrayList<>();
		classDao.getClassByStudentId(principalService.getPrincipal()).forEach(c -> {
			final ClassResDto classSt = new ClassResDto();
			classSt.setClassName(c.getStudentClass().getClassName());
			classSt.setClassCode(c.getStudentClass().getClassCode());
			classSt.setId(c.getStudentClass().getId());
			classSt.setPhotoId(c.getStudentClass().getClassImage().getId());
			classSt.setTeacherId(c.getStudentClass().getTeacher().getId());
			classSt.setTeacherName(c.getStudentClass().getTeacher().getProfile().getFullName());
			classList.add(classSt);
		});
		return classList;
	}

}
