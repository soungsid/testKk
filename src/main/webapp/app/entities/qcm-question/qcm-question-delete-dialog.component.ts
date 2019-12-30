import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQcmQuestion } from 'app/shared/model/qcm-question.model';
import { QcmQuestionService } from './qcm-question.service';

@Component({
  templateUrl: './qcm-question-delete-dialog.component.html'
})
export class QcmQuestionDeleteDialogComponent {
  qcmQuestion?: IQcmQuestion;

  constructor(
    protected qcmQuestionService: QcmQuestionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.qcmQuestionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('qcmQuestionListModification');
      this.activeModal.close();
    });
  }
}
