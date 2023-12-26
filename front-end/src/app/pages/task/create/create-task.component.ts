import { Component, OnInit } from '@angular/core';
import { FormArray, NonNullableFormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { QuestionFilesInsertReqDto } from '@dto/task/question-files-insert.req.dto';
import { QuestionInsertReqDto } from '@dto/task/question-insert.req.dto';
import { QuestionTypesResDto } from '@dto/task/question-types.res.dto';
import { TaskInsertReqDto } from '@dto/task/task-insert.req.dto';
import { QuestionService } from '@services/question.service';
import { TaskService } from '@services/task.service';
import { Observable } from 'rxjs';

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
  selector: 'create-task',
  templateUrl: './create-task.component.html',
})
export class CreateTaskComponent implements OnInit {
  classId!: number;
  learningId!: number;
  isFileTask: boolean = false;

  questionTypes!: QuestionTypesResDto[];

  taskInsertReqDtoArr: TaskInsertReqDto[] = [];
  questionInsertReqDto: QuestionInsertReqDto[] = [];
  questionInsertFileReqDto: QuestionFilesInsertReqDto[] = [];

  taskInsertReqDto = this.fb.group({
    data: this.fb.array(this.taskInsertReqDtoArr),
  });

  constructor(
    private fb: NonNullableFormBuilder,
    private questionService: QuestionService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private taskService: TaskService
  ) {
    questionService
      .getAllTypes()
      .subscribe((res) => (this.questionTypes = res));
  }

  ngOnInit(): void {
    getParams(this.activatedRoute, 1).subscribe((res) => {
      this.classId = Number(res['classId']);
    });

    this.activatedRoute.paramMap.subscribe((param) => {
      this.learningId = Number(param.get('learningId'));
    });
  }

  onChangeType(i: any) {
    if (i == 2) {
      this.isFileTask = true;
    } else {
      this.isFileTask = false;
    }
  }

  get data() {
    return this.taskInsertReqDto.get('data') as FormArray;
  }

  onAddTask() {
    this.data.push(
      this.fb.group({
        taskName: ['', Validators.required],
        taskCode: ['', Validators.required],
        learningId: [this.learningId, Validators.required],
        startTime: ['', Validators.required],
        endTime: ['', Validators.required],
        questionListReq: this.fb.array(this.questionInsertReqDto),
      })
    );
  }

  onDeleteTask(i: number) {
    this.data.removeAt(i);
  }

  questionTask(i: number) {
    return this.data.at(i).get('questionListReq') as FormArray;
  }

  onAddQuestion(i: number) {
    this.questionTask(i).push(
      this.fb.group({
        questionName: ['', Validators.required],
        typeId: [0, Validators.required],
        questionFilesReq: this.fb.array(this.questionInsertFileReqDto),
      })
    );
  }

  onDeleteQuestion(i: number, j: number) {
    this.questionTask(i).removeAt(j);
  }

  questionFilesReq(i: number, j: number) {
    return this.questionTask(i).at(j).get('questionFilesReq') as FormArray;
  }

  fileUpload(event: any, i: number, j: number) {
    const toBase64 = (file: File) =>
      new Promise<string>((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          if (typeof reader.result === 'string') resolve(reader.result);
        };
        reader.onerror = (error) => reject(error);
      });

    this.questionFilesReq(i, j).clear();
    for (let file of event.target.files) {
      toBase64(file).then((result) => {
        const resultBase64 = result.substring(
          result.indexOf(',') + 1,
          result.length
        );
        const resultExtension = file.name.substring(
          file.name.indexOf('.') + 1,
          file.name.length
        );
        this.questionFilesReq(i, j).push(
          this.fb.group({
            fileName: [resultBase64],
            fileExtens: [resultExtension],
          })
        );
      });
    }
  }

  onSubmit() {
    if (this.taskInsertReqDto.valid) {
      const data = this.taskInsertReqDto.getRawValue().data;

      if (data.length > 0) {
        this.taskService.insert(data).subscribe((res) => {
          this.router.navigateByUrl(`learning/${this.classId}`);
        });
      } else {
        console.log('error');
      }
    } else {
      console.log('input task');
    }
  }
}
