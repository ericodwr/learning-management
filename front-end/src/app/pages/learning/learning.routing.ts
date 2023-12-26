import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LearningListComponent } from './list/learning-list.component';
import { CreateLearningComponent } from './create/create-learning.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MaterialPipe } from 'src/app/pipes/material.pipe';
import { UrlPipe } from 'src/app/pipes/url.pipe';
import { AttendanceComponet } from './enroll-detail/attendance-detail.component';

const routes: Routes = [
  {
    path: ':id',
    component: LearningListComponent,
  },
  {
    path: ':id/create',
    component: CreateLearningComponent,
  },
  {
    path: ':classId/attendance/:learningId',
    component: AttendanceComponet,
  },
];

@NgModule({
  declarations: [
    LearningListComponent,
    CreateLearningComponent,
    AttendanceComponet,
  ],
  imports: [
    RouterModule.forChild(routes),
    ReactiveFormsModule,
    CommonModule,
    MaterialPipe,
    UrlPipe,
  ],
  exports: [RouterModule],
})
export class LearningRouting {}
