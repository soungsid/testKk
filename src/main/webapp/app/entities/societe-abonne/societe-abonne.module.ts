import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestKkSharedModule } from 'app/shared/shared.module';
import { SocieteAbonneComponent } from './societe-abonne.component';
import { SocieteAbonneDetailComponent } from './societe-abonne-detail.component';
import { SocieteAbonneUpdateComponent } from './societe-abonne-update.component';
import { SocieteAbonneDeleteDialogComponent } from './societe-abonne-delete-dialog.component';
import { societeAbonneRoute } from './societe-abonne.route';

@NgModule({
  imports: [TestKkSharedModule, RouterModule.forChild(societeAbonneRoute)],
  declarations: [SocieteAbonneComponent, SocieteAbonneDetailComponent, SocieteAbonneUpdateComponent, SocieteAbonneDeleteDialogComponent],
  entryComponents: [SocieteAbonneDeleteDialogComponent]
})
export class TestKkSocieteAbonneModule {}
