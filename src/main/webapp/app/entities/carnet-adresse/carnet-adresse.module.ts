import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestKkSharedModule } from 'app/shared/shared.module';
import { CarnetAdresseComponent } from './carnet-adresse.component';
import { CarnetAdresseDetailComponent } from './carnet-adresse-detail.component';
import { CarnetAdresseUpdateComponent } from './carnet-adresse-update.component';
import { CarnetAdresseDeleteDialogComponent } from './carnet-adresse-delete-dialog.component';
import { carnetAdresseRoute } from './carnet-adresse.route';

@NgModule({
  imports: [TestKkSharedModule, RouterModule.forChild(carnetAdresseRoute)],
  declarations: [CarnetAdresseComponent, CarnetAdresseDetailComponent, CarnetAdresseUpdateComponent, CarnetAdresseDeleteDialogComponent],
  entryComponents: [CarnetAdresseDeleteDialogComponent]
})
export class TestKkCarnetAdresseModule {}
