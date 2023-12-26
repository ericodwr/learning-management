import { Component, OnInit } from '@angular/core';
import { FormArray, NonNullableFormBuilder } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { QuestionInsertReqDto } from '@dto/task/question-insert.req.dto';
import { QuestionResDto } from '@dto/task/question.res.dto';
import { TaskResDto } from '@dto/task/task.res.dto';
import { QuestionService } from '@services/question.service';
import { TaskService } from '@services/task.service';
import { Observable } from 'rxjs';
import { QuestionTypes } from 'src/app/constant/question-type.constant';

function getParams(
  activatedRoute: ActivatedRoute,
  parentLevel?: number
): Observable<Params> {
  let route = activatedRoute;
  if (parentLevel) {
    for (let i = 0; i < parentLevel; i++) {
      if (route.parent) {
        route = route.parent;
      }
    }
  }
  return route.params;
}

@Component({
  selector: 'task-detail',
  templateUrl: './task-details.component.html',
})
export class TaskDetailComponent implements OnInit {
  essay: string = QuestionTypes.ESSAY;
  files: string = QuestionTypes.FILES;
  classId!: number;
  learningId!: number;
  taskId!: number;

  questionInsertReqDto: QuestionInsertReqDto[] = [];

  task?: TaskResDto;
  questions?: QuestionResDto[];

  questionAnswer = this.fb.group({
    data: this.fb.array(this.questionInsertReqDto),
  });

  get data() {
    return this.questionAnswer.get('data') as FormArray;
  }

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private fb: NonNullableFormBuilder,
    private taskService: TaskService,
    private questionService: QuestionService
  ) {}

  ngOnInit(): void {
    getParams(this.activatedRoute, 0).subscribe((res) => {
      this.learningId = Number(res['learningId']);
      this.taskId = Number(res['taskId']);
    });

    getParams(this.activatedRoute, 1).subscribe((res) => {
      this.classId = Number(res['classId']);
    });

    this.taskService.getById(this.taskId).subscribe((res) => {
      this.task = res;
    });

    this.questionService.getQuestionByTaskId(this.taskId).subscribe((res) => {
      this.questions = res;

      for (let r of res) {
        if (r.questionType === QuestionTypes.ESSAY) {
        }
      }
    });
  }
}
