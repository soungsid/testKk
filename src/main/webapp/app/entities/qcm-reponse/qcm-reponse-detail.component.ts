import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IQcmReponse } from 'app/shared/model/qcm-reponse.model';

@Component({
  selector: 'jhi-qcm-reponse-detail',
  templateUrl: './qcm-reponse-detail.component.html'
})
export class QcmReponseDetailComponent implements OnInit {
  qcmReponse: IQcmReponse | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcmReponse }) => {
      this.qcmReponse = qcmReponse;
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
