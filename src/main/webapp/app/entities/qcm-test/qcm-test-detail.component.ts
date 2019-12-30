import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQcmTest } from 'app/shared/model/qcm-test.model';

@Component({
  selector: 'jhi-qcm-test-detail',
  templateUrl: './qcm-test-detail.component.html'
})
export class QcmTestDetailComponent implements OnInit {
  qcmTest: IQcmTest | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcmTest }) => {
      this.qcmTest = qcmTest;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
