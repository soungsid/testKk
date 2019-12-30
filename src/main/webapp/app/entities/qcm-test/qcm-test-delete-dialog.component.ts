import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQcmTest } from 'app/shared/model/qcm-test.model';
import { QcmTestService } from './qcm-test.service';

@Component({
  templateUrl: './qcm-test-delete-dialog.component.html'
})
export class QcmTestDeleteDialogComponent {
  qcmTest?: IQcmTest;

  constructor(protected qcmTestService: QcmTestService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.qcmTestService.delete(id).subscribe(() => {
      this.eventManager.broadcast('qcmTestListModification');
      this.activeModal.close();
    });
  }
}
