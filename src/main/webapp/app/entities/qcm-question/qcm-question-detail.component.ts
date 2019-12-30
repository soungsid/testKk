import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IQcmQuestion } from 'app/shared/model/qcm-question.model';

@Component({
  selector: 'jhi-qcm-question-detail',
  templateUrl: './qcm-question-detail.component.html'
})
export class QcmQuestionDetailComponent implements OnInit {
  qcmQuestion: IQcmQuestion | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcmQuestion }) => {
      this.qcmQuestion = qcmQuestion;
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
