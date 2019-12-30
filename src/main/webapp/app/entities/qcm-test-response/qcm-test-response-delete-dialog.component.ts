import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQcmTestResponse } from 'app/shared/model/qcm-test-response.model';
import { QcmTestResponseService } from './qcm-test-response.service';

@Component({
  templateUrl: './qcm-test-response-delete-dialog.component.html'
})
export class QcmTestResponseDeleteDialogComponent {
  qcmTestResponse?: IQcmTestResponse;

  constructor(
    protected qcmTestResponseService: QcmTestResponseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.qcmTestResponseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('qcmTestResponseListModification');
      this.activeModal.close();
    });
  }
}
