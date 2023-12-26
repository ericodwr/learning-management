import { NgModule } from '@angular/core';
import { LearningRouting } from './learning.routing';
import { UrlPipe } from 'src/app/pipes/url.pipe';

@NgModule({
  imports: [LearningRouting],
})
export class LearningModule {}
