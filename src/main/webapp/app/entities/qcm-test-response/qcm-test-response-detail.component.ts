import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQcmTestResponse } from 'app/shared/model/qcm-test-response.model';

@Component({
  selector: 'jhi-qcm-test-response-detail',
  templateUrl: './qcm-test-response-detail.component.html'
})
export class QcmTestResponseDetailComponent implements OnInit {
  qcmTestResponse: IQcmTestResponse | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcmTestResponse }) => {
      this.qcmTestResponse = qcmTestResponse;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
