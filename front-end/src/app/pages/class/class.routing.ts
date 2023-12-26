import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClassListComponent } from './list/class-list.component';
import { CreateClassComponent } from './create/create-class.component';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { UrlPipe } from 'src/app/pipes/url.pipe';
import { ClassEnrollComponent } from './unenroll-class/class-enroll.component';

const routes: Routes = [
  {
    path: '',
    component: ClassListComponent,
  },
  {
    path: 'enroll',
    component: ClassEnrollComponent,
  },
  {
    path: 'create',
    component: CreateClassComponent,
  },
];

@NgModule({
  declarations: [
    ClassListComponent,
    CreateClassComponent,
    ClassEnrollComponent,
  ],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    ReactiveFormsModule,
    UrlPipe,
  ],
  exports: [RouterModule],
})
export class ClassRouting {}
