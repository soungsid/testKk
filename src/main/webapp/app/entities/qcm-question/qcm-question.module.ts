import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestKkSharedModule } from 'app/shared/shared.module';
import { QcmQuestionComponent } from './qcm-question.component';
import { QcmQuestionDetailComponent } from './qcm-question-detail.component';
import { QcmQuestionUpdateComponent } from './qcm-question-update.component';
import { QcmQuestionDeleteDialogComponent } from './qcm-question-delete-dialog.component';
import { qcmQuestionRoute } from './qcm-question.route';

@NgModule({
  imports: [TestKkSharedModule, RouterModule.forChild(qcmQuestionRoute)],
  declarations: [QcmQuestionComponent, QcmQuestionDetailComponent, QcmQuestionUpdateComponent, QcmQuestionDeleteDialogComponent],
  entryComponents: [QcmQuestionDeleteDialogComponent]
})
export class TestKkQcmQuestionModule {}
