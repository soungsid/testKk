import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestKkSharedModule } from 'app/shared/shared.module';
import { QcmReponseComponent } from './qcm-reponse.component';
import { QcmReponseDetailComponent } from './qcm-reponse-detail.component';
import { QcmReponseUpdateComponent } from './qcm-reponse-update.component';
import { QcmReponseDeleteDialogComponent } from './qcm-reponse-delete-dialog.component';
import { qcmReponseRoute } from './qcm-reponse.route';

@NgModule({
  imports: [TestKkSharedModule, RouterModule.forChild(qcmReponseRoute)],
  declarations: [QcmReponseComponent, QcmReponseDetailComponent, QcmReponseUpdateComponent, QcmReponseDeleteDialogComponent],
  entryComponents: [QcmReponseDeleteDialogComponent]
})
export class TestKkQcmReponseModule {}
