CREATE TABLE t_role(
	id serial,
	role_name VARCHAR(15) NOT NULL,
	role_code VARCHAR(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_role ADD CONSTRAINT id_role_pk
	PRIMARY KEY(id);

CREATE TABLE t_file(
	id serial,
	file_name TEXT NOT NULL,
	file_extens TEXT NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_file ADD CONSTRAINT id_file_pk
	PRIMARY KEY(id);

CREATE TABLE t_profile(
	id serial,
	full_name TEXT NOT NULL,
	profile_photo int,
	phone_numb TEXT NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_profile ADD CONSTRAINT id_profile_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_profile ADD CONSTRAINT id_profile_photo_fk
	FOREIGN KEY(profile_photo)
	REFERENCES t_file(id);

CREATE TABLE t_user(
	id serial,
	username TEXT NOT NULL,
	user_password TEXT NOT NULL,
	role_id int NOT NULL,
	profile_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_user ADD CONSTRAINT id_user_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_user ADD CONSTRAINT id_user_profile_fk
	FOREIGN KEY(profile_id)
	REFERENCES t_profile(id);

ALTER TABLE t_user ADD CONSTRAINT id_user_role_fk
	FOREIGN KEY(role_id)
	REFERENCES t_role(id);

CREATE TABLE t_class(
	id serial,
	class_name VARCHAR(30) NOT NULL,
	class_code VARCHAR(5) NOT NULL,
	teacher_id int NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_class ADD CONSTRAINT id_class_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_class ADD CONSTRAINT id_class_teacher_fk
	FOREIGN KEY(teacher_id)
	REFERENCES t_user(id);
	
ALTER TABLE t_class ADD CONSTRAINT id_class_photo_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

CREATE TABLE t_enroll(
	id serial,
	student_id int NOT NULL,
	class_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_enroll ADD CONSTRAINT id_enroll_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_enroll ADD CONSTRAINT id_enroll_class_fk
	FOREIGN KEY(class_id)
	REFERENCES t_class(id);

ALTER TABLE t_enroll ADD CONSTRAINT id_enroll_student_fk
	FOREIGN KEY(student_id)
	REFERENCES t_user(id);


create table t_learning(
	id serial,
	learning_name VARCHAR(40) not null,
	learning_code VARCHAR(5) not null,
	class_id int not null,
	start_time timestamp not null,
	end_time timestamp not null,
	created_by int not null,
	created_at timestamp not null,
	updated_by int,
	updated_at timestamp,
	is_active boolean not null,
	ver int not null
);

-- Primary Key
ALTER TABLE t_learning ADD CONSTRAINT id_learning_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_learning ADD CONSTRAINT id_learning_class_fk
	FOREIGN KEY(class_id)
	REFERENCES t_class(id);

CREATE TABLE t_forum(
	id serial,
	forum_name VARCHAR(30) NOT NULL,
	learning_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_forum ADD CONSTRAINT id_forum_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_forum ADD CONSTRAINT id_forum_learning_fk
	FOREIGN KEY(learning_id)
	REFERENCES t_learning(id);

CREATE TABLE t_comment(
	id serial,
	forum_chat TEXT,
	forum_id int NOT NULL,
	user_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_comment ADD CONSTRAINT id_forum_details_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_comment ADD CONSTRAINT id_forum_details_forum_fk
	FOREIGN KEY(forum_id)
	REFERENCES t_forum(id);

ALTER TABLE t_comment ADD CONSTRAINT id_forum_details_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_user(id);

CREATE TABLE t_student_attendance(
	id serial,
	student_id int NOT NULL,
	learning_id int NOT NULL,
	approvement boolean NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_student_attendance ADD CONSTRAINT id_student_attendance_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_student_attendance ADD CONSTRAINT id_student_attendance_student_fk
	FOREIGN KEY(student_id)
	REFERENCES t_user(id);

ALTER TABLE t_student_attendance ADD CONSTRAINT id_student_attendance_learning_fk
	FOREIGN KEY(learning_id)
	REFERENCES t_learning(id);

CREATE TABLE t_material(
	id serial,
	material_name VARCHAR(30) NOT NULL,
	material_title VARCHAR(30) NOT NULL,
	material_text TEXT,
	learning_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_material ADD CONSTRAINT id_material_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_material ADD CONSTRAINT id_material_learning_fk
	FOREIGN KEY(learning_id)
	REFERENCES t_learning(id);

CREATE TABLE t_material_files(
	id serial,
	file_id int NOT NULL,
	material_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_material_files ADD CONSTRAINT id_material_files_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_material_files ADD CONSTRAINT id_material_files_file_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

ALTER TABLE t_material_files ADD CONSTRAINT id_material_files_material_fk
	FOREIGN KEY(material_id)
	REFERENCES t_material(id);


CREATE TABLE t_task(
	id serial,
	task_name TEXT NOT NULL,
	task_code VARCHAR(5) NOT NULL,
	learning_id int NOT NULL,
	start_time timestamp NOT NULL,
	end_time timestamp NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_task ADD CONSTRAINT id_task_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_task ADD CONSTRAINT id_task_learning_fk
	FOREIGN KEY(learning_id)
	REFERENCES t_learning(id);

CREATE TABLE t_question_type(
	id serial,
	type_name VARCHAR(20) NOT NULL,
	type_code VARCHAR(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_question_type ADD CONSTRAINT id_question_type_pk
	PRIMARY KEY(id);


CREATE TABLE t_question(
	id serial,
	question_name TEXT NOT NULL,
	task_id int NOT NULL,
	type_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_question ADD CONSTRAINT id_question_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_question ADD CONSTRAINT id_question_task_fk
	FOREIGN KEY(task_id)
	REFERENCES t_task(id);

ALTER TABLE t_question ADD CONSTRAINT id_question_type_fk
	FOREIGN KEY(type_id)
	REFERENCES t_question_type(id);

CREATE TABLE t_question_files(
	id serial,
	file_id int NOT NULL,
	question_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_question_files ADD CONSTRAINT id_question_files_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_question_files ADD CONSTRAINT id_question_option_file_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

ALTER TABLE t_question_files ADD CONSTRAINT id_question_option_question_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);

CREATE TABLE t_question_answer(
	id serial,
	id_question int NOT NULL,
	essay_answer text,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_question_answer ADD CONSTRAINT id_task_answer_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_question_answer ADD CONSTRAINT id_task_answer_question_fk
	FOREIGN KEY(id_question)
	REFERENCES t_question(id);

CREATE TABLE t_question_answer_files(
	id serial,
	file_id int NOT NULL,
	question_answer_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_question_answer_files ADD CONSTRAINT id_task_answer_files_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_question_answer_files ADD CONSTRAINT id_task_answer_files_file_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

ALTER TABLE t_question_answer_files ADD CONSTRAINT id_task_answer_task_fk
	FOREIGN KEY(question_answer_id)
	REFERENCES t_question_answer(id);

CREATE TABLE t_review(
	id serial,
	score float,
	notes TEXT,
	teacher_id int NOT NULL,
	student_id int NOT NULL,
	task_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

-- Primary Key
ALTER TABLE t_review ADD CONSTRAINT id_review_pk
	PRIMARY KEY(id);

-- Foreign Key
ALTER TABLE t_review ADD CONSTRAINT id_review_task_fk
	FOREIGN KEY(task_id)
	REFERENCES t_task(id);

ALTER TABLE t_review ADD CONSTRAINT id_review_teacher_fk
	FOREIGN KEY(teacher_id)
	REFERENCES t_user(id);

ALTER TABLE t_review ADD CONSTRAINT id_review_student_fk
	FOREIGN KEY(student_id)
	REFERENCES t_user(id);