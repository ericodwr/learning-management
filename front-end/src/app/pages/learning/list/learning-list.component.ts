import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LearningResDto } from '@dto/learning/learning.res.dto';
import { AuthService } from '@services/auth.service';
import { EnrollService } from '@services/enroll.service';
import { LearningService } from '@services/learning.service';
import { MaterialService } from '@services/material.service';
import { TaskService } from '@services/task.service';
import { lastValueFrom } from 'rxjs';
import { Roles } from 'src/app/constant/role.constant';

@Component({
  selector: 'learning-list',
  templateUrl: './learning-list.component.html',
})
export class LearningListComponent implements OnInit {
  classId!: number | null;
  roleCode!: string;

  learningList!: LearningResDto[];

  constructor(
    private route: ActivatedRoute,
    private learningService: LearningService,
    private materialService: MaterialService,
    private taskService: TaskService,
    private authService: AuthService,
    private fb: NonNullableFormBuilder,
    private enrollService: EnrollService
  ) {}

  ngOnInit(): void {
    this.getClassId();
    this.getLearningByClassId();

    const profile = this.authService.getProfile();
    if (profile?.roleCode) {
      this.roleCode = profile?.roleCode;
    }
  }

  get isTeacher() {
    return Roles.TEACHER === this.roleCode;
  }

  get isStudent() {
    return Roles.STUDENT === this.roleCode;
  }

  studentAttend(learningId: number) {
    const data = {
      studentId: 0,
      learningId: learningId,
      approvement: false,
    };

    this.enrollService.studentAttend(data).subscribe((res) => {
      console.log('attend successfully');
    });
  }

  getClassId() {
    this.route.paramMap.subscribe((params) => {
      this.classId = Number(params.get('id'));
    });
  }

  getLearningByClassId() {
    this.learningService.getByClassId(this.classId).subscribe((res) => {
      this.learningList = res;

      for (let l of this.learningList) {
        const getDetails = async () => {
          const materials = await lastValueFrom(
            this.materialService.getByLearningId(l.id)
          );
          l.materials = materials;

          const tasks = await lastValueFrom(
            this.taskService.getByLearningId(l.id)
          );
          l.tasks = tasks;
        };
        getDetails();
      }
    });
  }
}
