import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestKkSharedModule } from 'app/shared/shared.module';
import { QcmQuestionTagComponent } from './qcm-question-tag.component';
import { QcmQuestionTagDetailComponent } from './qcm-question-tag-detail.component';
import { QcmQuestionTagUpdateComponent } from './qcm-question-tag-update.component';
import { QcmQuestionTagDeleteDialogComponent } from './qcm-question-tag-delete-dialog.component';
import { qcmQuestionTagRoute } from './qcm-question-tag.route';

@NgModule({
  imports: [TestKkSharedModule, RouterModule.forChild(qcmQuestionTagRoute)],
  declarations: [
    QcmQuestionTagComponent,
    QcmQuestionTagDetailComponent,
    QcmQuestionTagUpdateComponent,
    QcmQuestionTagDeleteDialogComponent
  ],
  entryComponents: [QcmQuestionTagDeleteDialogComponent]
})
export class TestKkQcmQuestionTagModule {}
