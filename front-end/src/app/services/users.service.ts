import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UsersResDto } from '../dto/user/users.res.dto';
import { BaseService } from './base.service';
import { BASE_URL } from '../constant/api.constant';
import { UserInsertReqDto } from '../dto/user/user-insert.req.dto';
import { Roles } from '../constant/role.constant';
import { UserNewPasswordInsertDto } from '../dto/user/user-new-password.insert.dto';
import { UpdateResDto } from '../dto/UpdateResDto';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  constructor(private base: BaseService) {}

  getAllUsers(): Observable<UsersResDto[]> {
    return this.base.get<UsersResDto[]>(`${BASE_URL}/users`);
  }

  insert(data: UserInsertReqDto): Observable<UsersResDto> {
    return this.base.post<UsersResDto>(`${BASE_URL}/users`, data);
  }

  insertStudent(data: UserInsertReqDto): Observable<UsersResDto> {
    return this.base.post<UsersResDto>(`${BASE_URL}/student/create`, data);
  }

  getAllTeacher(): Observable<UsersResDto[]> {
    return this.base.get(`${BASE_URL}/users/role/?code=${Roles.TEACHER}`);
  }

  updatePassword(data: UserNewPasswordInsertDto): Observable<UpdateResDto> {
    return this.base.patch(`${BASE_URL}/users/new-password`, data);
  }
}
