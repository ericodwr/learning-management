import { Injectable } from '@angular/core';
import { InsertResDto } from '@dto/InsertResDto';
import { Observable } from 'rxjs';
import { BaseService } from './base.service';
import { BASE_URL } from '../constant/api.constant';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  constructor(private base: BaseService) {}

  enrollClass(data: any): Observable<InsertResDto> {
    return this.base.post(`${BASE_URL}/student/enroll`, data);
  }
}
