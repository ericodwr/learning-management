import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { Observable } from 'rxjs';
import { QuestionTypesResDto } from '@dto/task/question-types.res.dto';
import { BASE_URL } from '../constant/api.constant';
import { QuestionResDto } from '@dto/task/question.res.dto';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  constructor(private base: BaseService) {}

  getAllTypes(): Observable<QuestionTypesResDto[]> {
    return this.base.get(`${BASE_URL}/question/type`);
  }

  getQuestionByTaskId(i: number): Observable<QuestionResDto[]> {
    return this.base.get(`${BASE_URL}/question/task?id=${i}`);
  }
}
