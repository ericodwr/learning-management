import { Observable } from 'rxjs';
import { BaseService } from './base.service';
import { InsertResDto } from '@dto/InsertResDto';
import { ClassInsertReqDto } from '@dto/class/class-insert.req.dto';
import { BASE_URL } from '../constant/api.constant';
import { Injectable } from '@angular/core';
import { ClassResDto } from '@dto/class/class.res.dto';

@Injectable({
  providedIn: 'root',
})
export class ClassService {
  constructor(private base: BaseService) {}

  insert(data: ClassInsertReqDto): Observable<InsertResDto> {
    return this.base.post(`${BASE_URL}/class`, data);
  }

  getAllClass(): Observable<ClassResDto[]> {
    return this.base.get(`${BASE_URL}/class`);
  }

  getAllTeacherClass(): Observable<ClassResDto[]> {
    return this.base.get(`${BASE_URL}/class/teacher`);
  }

  getAllStudentClass(): Observable<ClassResDto[]> {
    return this.base.get(`${BASE_URL}/class/student`);
  }

  getAllStudentUnenroll(): Observable<ClassResDto[]> {
    return this.base.get(`${BASE_URL}/class/student/unenroll`);
  }
}
