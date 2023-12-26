import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './components/base/base.component';
import {
  authNonLoginValidation,
  authValidation,
} from './validation/auth.validation';

import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { BaseModule } from './components/base/base.module';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { StudentComponent } from './pages/student/student-create.component';
import { ButtonComponent } from './components/button/button.component';

const routes: Routes = [
  {
    path: 'users',
    component: BaseComponent,
    loadChildren: () =>
      import('./pages/users/user.module').then((u) => u.UserModule),
    canMatch: [authNonLoginValidation],
  },
  {
    path: 'class',
    component: BaseComponent,
    loadChildren: () =>
      import('./pages/class/class.module').then((u) => u.ClassModule),
    canMatch: [authNonLoginValidation],
  },
  {
    path: 'learning',
    component: BaseComponent,
    loadChildren: () =>
      import('./pages/learning/learning.module').then((u) => u.LearningModule),
    canMatch: [authNonLoginValidation],
  },
  {
    path: ':classId/material',
    component: BaseComponent,
    loadChildren: () =>
      import('./pages/material/material.module').then((u) => u.MaterialModule),
    canMatch: [authNonLoginValidation],
  },
  {
    path: ':classId/task',
    component: BaseComponent,
    loadChildren: () =>
      import('./pages/task/task.module').then((u) => u.TaskModule),
    canMatch: [authNonLoginValidation],
  },
  {
    path: ':classId/forum',
    component: BaseComponent,
    loadChildren: () =>
      import('./pages/forum/forum.module').then((u) => u.ForumModule),
    canMatch: [authNonLoginValidation],
  },

  {
    path: 'create-student',
    component: StudentComponent,
    canMatch: [authValidation],
  },

  {
    path: 'login',
    component: LoginComponent,
    canMatch: [authValidation],
  },
  {
    path: 'dashboard',
    component: BaseComponent,
    children: [
      {
        path: '',
        component: DashboardComponent,
        canMatch: [authNonLoginValidation],
      },
    ],
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];

@NgModule({
  declarations: [LoginComponent, StudentComponent],
  imports: [
    RouterModule.forRoot(routes),
    BaseModule,
    CommonModule,
    ReactiveFormsModule,
    ButtonComponent,
  ],
  exports: [RouterModule],
})
export class AppRouting {}
