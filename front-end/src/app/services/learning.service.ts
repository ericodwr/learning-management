import { Injectable } from '@angular/core';
import { BaseModule } from '../components/base/base.module';
import { LearningInsertReqDto } from '@dto/learning/learning-insert.req.dto';
import { Observable } from 'rxjs';
import { InsertResDto } from '@dto/InsertResDto';
import { BaseService } from './base.service';
import { BASE_URL } from '../constant/api.constant';
import { LearningResDto } from '@dto/learning/learning.res.dto';

@Injectable({
  providedIn: 'root',
})
export class LearningService {
  constructor(private base: BaseService) {}

  insert(data: LearningInsertReqDto): Observable<InsertResDto> {
    return this.base.post(`${BASE_URL}/learning`, data);
  }

  getByClassId(id: number | null): Observable<LearningResDto[]> {
    return this.base.get<LearningResDto[]>(`${BASE_URL}/learning?id=${id}`);
  }
}
