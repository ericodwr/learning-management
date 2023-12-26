import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { TaskInsertReqDto } from '@dto/task/task-insert.req.dto';
import { Observable } from 'rxjs';
import { InsertResDto } from '@dto/InsertResDto';
import { BASE_URL } from '../constant/api.constant';
import { TaskResDto } from '@dto/task/task.res.dto';
import { QuestionResDto } from '@dto/task/question.res.dto';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  constructor(private base: BaseService) {}

  insert(data: TaskInsertReqDto[]): Observable<InsertResDto> {
    return this.base.post(`${BASE_URL}/task`, data);
  }

  getByLearningId(id: number): Observable<TaskResDto[]> {
    return this.base.get<TaskResDto[]>(`${BASE_URL}/task/?learningId=${id}`);
  }

  getById(id: number): Observable<TaskResDto> {
    return this.base.get(`${BASE_URL}/task/detail?id=${id}`);
  }
}
