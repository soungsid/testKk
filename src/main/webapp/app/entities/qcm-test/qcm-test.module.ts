import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestKkSharedModule } from 'app/shared/shared.module';
import { QcmTestComponent } from './qcm-test.component';
import { QcmTestDetailComponent } from './qcm-test-detail.component';
import { QcmTestUpdateComponent } from './qcm-test-update.component';
import { QcmTestDeleteDialogComponent } from './qcm-test-delete-dialog.component';
import { qcmTestRoute } from './qcm-test.route';

@NgModule({
  imports: [TestKkSharedModule, RouterModule.forChild(qcmTestRoute)],
  declarations: [QcmTestComponent, QcmTestDetailComponent, QcmTestUpdateComponent, QcmTestDeleteDialogComponent],
  entryComponents: [QcmTestDeleteDialogComponent]
})
export class TestKkQcmTestModule {}
