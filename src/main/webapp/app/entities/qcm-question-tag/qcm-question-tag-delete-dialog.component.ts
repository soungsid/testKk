import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQcmQuestionTag } from 'app/shared/model/qcm-question-tag.model';
import { QcmQuestionTagService } from './qcm-question-tag.service';

@Component({
  templateUrl: './qcm-question-tag-delete-dialog.component.html'
})
export class QcmQuestionTagDeleteDialogComponent {
  qcmQuestionTag?: IQcmQuestionTag;

  constructor(
    protected qcmQuestionTagService: QcmQuestionTagService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.qcmQuestionTagService.delete(id).subscribe(() => {
      this.eventManager.broadcast('qcmQuestionTagListModification');
      this.activeModal.close();
    });
  }
}
