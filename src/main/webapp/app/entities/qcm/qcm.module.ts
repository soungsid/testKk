import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestKkSharedModule } from 'app/shared/shared.module';
import { QcmComponent } from './qcm.component';
import { QcmDetailComponent } from './qcm-detail.component';
import { QcmUpdateComponent } from './qcm-update.component';
import { QcmDeleteDialogComponent } from './qcm-delete-dialog.component';
import { qcmRoute } from './qcm.route';

@NgModule({
  imports: [TestKkSharedModule, RouterModule.forChild(qcmRoute)],
  declarations: [QcmComponent, QcmDetailComponent, QcmUpdateComponent, QcmDeleteDialogComponent],
  entryComponents: [QcmDeleteDialogComponent]
})
export class TestKkQcmModule {}
