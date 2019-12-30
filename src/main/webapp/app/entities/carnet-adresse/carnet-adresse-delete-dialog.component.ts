import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICarnetAdresse } from 'app/shared/model/carnet-adresse.model';
import { CarnetAdresseService } from './carnet-adresse.service';

@Component({
  templateUrl: './carnet-adresse-delete-dialog.component.html'
})
export class CarnetAdresseDeleteDialogComponent {
  carnetAdresse?: ICarnetAdresse;

  constructor(
    protected carnetAdresseService: CarnetAdresseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.carnetAdresseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('carnetAdresseListModification');
      this.activeModal.close();
    });
  }
}
