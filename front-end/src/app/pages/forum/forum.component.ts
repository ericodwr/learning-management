import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  NonNullableFormBuilder,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CommentResDto } from '@dto/comment/comment.res.dto';
import { AuthService } from '@services/auth.service';
import { ForumService } from '@services/forum.service';
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
  selector: 'forum-page',
  templateUrl: './forum.component.html',
})
export class ForumComponent implements OnInit {
  classId!: number;
  learningId!: number;
  userId!: number;
  commentList: CommentResDto[] = [];

  commentInsertReq = this.fb.group({
    forumChat: ['', Validators.required],
    forumId: [0, Validators.required],
  });

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private forumService: ForumService,
    private authService: AuthService,
    private fb: NonNullableFormBuilder
  ) {}

  ngOnInit(): void {
    const profile = this.authService.getProfile();
    if (profile?.id) {
      this.userId = profile.id;
    }

    getParams(this.activatedRoute, 1).subscribe((res) => {
      this.classId = Number(res['classId']);
    });

    getParams(this.activatedRoute, 0).subscribe((res) => {
      this.learningId = Number(res['id']);

      this.forumService
        .getCommentByForumId(Number(res['id']))
        .subscribe((res) => {
          this.commentList = res;
        });
    });

    this.getComment();
  }

  checkedComment(i: number) {
    return this.commentList[i].userId == this.userId;
  }

  getComment() {
    this.forumService.getCommentByForumId(this.learningId).subscribe((res) => {
      this.commentList = res;

      this.commentInsertReq = this.fb.group({
        forumChat: ['', Validators.required],
        forumId: [this.learningId, Validators.required],
      });
    });
  }

  onSubmit() {
    if (this.commentInsertReq.valid) {
      const data = this.commentInsertReq.getRawValue();
      this.forumService.insert(data).subscribe((res) => {
        console.log(res);
        this.getComment();
      });
    }
  }
}
