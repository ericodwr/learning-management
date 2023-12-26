import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { MaterialInsertReqDto } from '@dto/material/material-insert.req.dto';
import { Observable } from 'rxjs';
import { InsertResDto } from '@dto/InsertResDto';
import { BASE_URL } from '../constant/api.constant';
import { MaterialResDto } from '@dto/material/material.res.dto';

@Injectable({
  providedIn: 'root',
})
export class MaterialService {
  constructor(private base: BaseService) {}

  insert(data: MaterialInsertReqDto[]): Observable<InsertResDto> {
    return this.base.post<InsertResDto>(`${BASE_URL}/material`, data);
  }

  getByLearningId(id: number): Observable<MaterialResDto[]> {
    return this.base.get<MaterialResDto[]>(
      `${BASE_URL}/material/?learningId=${id}`
    );
  }

  getById(id: number): Observable<MaterialResDto> {
    return this.base.get(`${BASE_URL}/material/detail/?id=${id}`);
  }
}
