import { RouterModule, Routes } from '@angular/router';
import { ForumComponent } from './forum.component';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

const routes: Routes = [
  {
    path: 'learning/:id',
    component: ForumComponent,
  },
];

@NgModule({
  declarations: [ForumComponent],
  imports: [RouterModule.forChild(routes), ReactiveFormsModule, CommonModule],
  exports: [RouterModule],
})
export class ForumRouting {}
