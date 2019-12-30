import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQcmReponse } from 'app/shared/model/qcm-reponse.model';
import { QcmReponseService } from './qcm-reponse.service';

@Component({
  templateUrl: './qcm-reponse-delete-dialog.component.html'
})
export class QcmReponseDeleteDialogComponent {
  qcmReponse?: IQcmReponse;

  constructor(
    protected qcmReponseService: QcmReponseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.qcmReponseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('qcmReponseListModification');
      this.activeModal.close();
    });
  }
}
