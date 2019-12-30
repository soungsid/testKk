import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQcmQuestionTag } from 'app/shared/model/qcm-question-tag.model';

@Component({
  selector: 'jhi-qcm-question-tag-detail',
  templateUrl: './qcm-question-tag-detail.component.html'
})
export class QcmQuestionTagDetailComponent implements OnInit {
  qcmQuestionTag: IQcmQuestionTag | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcmQuestionTag }) => {
      this.qcmQuestionTag = qcmQuestionTag;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
