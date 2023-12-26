import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { Observable } from 'rxjs';
import { CommentResDto } from '@dto/comment/comment.res.dto';
import { BASE_URL } from '../constant/api.constant';
import { CommentInsertReqDto } from '@dto/comment/comment.insert.req.dto';
import { InsertResDto } from '@dto/InsertResDto';

@Injectable({
  providedIn: 'root',
})
export class ForumService {
  constructor(private base: BaseService) {}

  getCommentByForumId(id: number): Observable<CommentResDto[]> {
    return this.base.get(`${BASE_URL}/forum/comment/?id=${id}`);
  }

  insert(data: CommentInsertReqDto): Observable<InsertResDto> {
    return this.base.post(`${BASE_URL}/forum`, data);
  }
}
