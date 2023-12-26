import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { Observable } from 'rxjs';
import { InsertResDto } from '@dto/InsertResDto';
import { BASE_URL } from '../constant/api.constant';
import { AttendanceInsertReqDto } from '@dto/student/attendance-insert.req.dto';
import { AttendanceResDto } from '@dto/student/attendance.res.dto';
import { UpdateResDto } from '@dto/UpdateResDto';

@Injectable({
  providedIn: 'root',
})
export class EnrollService {
  constructor(private base: BaseService) {}

  studentAttend(data: AttendanceInsertReqDto): Observable<InsertResDto> {
    return this.base.post(`${BASE_URL}/student/attendance`, data);
  }

  updateAttend(data: AttendanceInsertReqDto): Observable<UpdateResDto> {
    return this.base.patch(`${BASE_URL}/student/attendance`, data);
  }

  getStudentAttendance(id: number): Observable<AttendanceResDto[]> {
    return this.base.get(`${BASE_URL}/student/attendance?learningId=${id}`);
  }
}
