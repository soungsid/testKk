import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQcm } from 'app/shared/model/qcm.model';
import { QcmService } from './qcm.service';

@Component({
  templateUrl: './qcm-delete-dialog.component.html'
})
export class QcmDeleteDialogComponent {
  qcm?: IQcm;

  constructor(protected qcmService: QcmService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.qcmService.delete(id).subscribe(() => {
      this.eventManager.broadcast('qcmListModification');
      this.activeModal.close();
    });
  }
}
