import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestKkSharedModule } from 'app/shared/shared.module';
import { QcmTestResponseComponent } from './qcm-test-response.component';
import { QcmTestResponseDetailComponent } from './qcm-test-response-detail.component';
import { QcmTestResponseUpdateComponent } from './qcm-test-response-update.component';
import { QcmTestResponseDeleteDialogComponent } from './qcm-test-response-delete-dialog.component';
import { qcmTestResponseRoute } from './qcm-test-response.route';

@NgModule({
  imports: [TestKkSharedModule, RouterModule.forChild(qcmTestResponseRoute)],
  declarations: [
    QcmTestResponseComponent,
    QcmTestResponseDetailComponent,
    QcmTestResponseUpdateComponent,
    QcmTestResponseDeleteDialogComponent
  ],
  entryComponents: [QcmTestResponseDeleteDialogComponent]
})
export class TestKkQcmTestResponseModule {}
