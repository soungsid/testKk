import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISocieteAbonne } from 'app/shared/model/societe-abonne.model';
import { SocieteAbonneService } from './societe-abonne.service';

@Component({
  templateUrl: './societe-abonne-delete-dialog.component.html'
})
export class SocieteAbonneDeleteDialogComponent {
  societeAbonne?: ISocieteAbonne;

  constructor(
    protected societeAbonneService: SocieteAbonneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.societeAbonneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('societeAbonneListModification');
      this.activeModal.close();
    });
  }
}
