import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTaskComponent } from './create/create-task.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TaskDetailComponent } from './details/task-details.component';
import { UrlPipe } from 'src/app/pipes/url.pipe';

const routes: Routes = [
  {
    path: ':learningId/create',
    component: CreateTaskComponent,
  },
  {
    path: ':learningId/details/:taskId',
    component: TaskDetailComponent,
  },
];

@NgModule({
  declarations: [CreateTaskComponent, TaskDetailComponent],
  imports: [
    RouterModule.forChild(routes),
    ReactiveFormsModule,
    CommonModule,
    UrlPipe,
  ],
  exports: [RouterModule],
})
export class TaskRouting {}
