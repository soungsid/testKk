import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IQcmTestResponse, QcmTestResponse } from 'app/shared/model/qcm-test-response.model';
import { QcmTestResponseService } from './qcm-test-response.service';

@Component({
  selector: 'jhi-qcm-test-response-update',
  templateUrl: './qcm-test-response-update.component.html'
})
export class QcmTestResponseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dateSoumission: []
  });

  constructor(
    protected qcmTestResponseService: QcmTestResponseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcmTestResponse }) => {
      this.updateForm(qcmTestResponse);
    });
  }

  updateForm(qcmTestResponse: IQcmTestResponse): void {
    this.editForm.patchValue({
      id: qcmTestResponse.id,
      dateSoumission: qcmTestResponse.dateSoumission != null ? qcmTestResponse.dateSoumission.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const qcmTestResponse = this.createFromForm();
    if (qcmTestResponse.id !== undefined) {
      this.subscribeToSaveResponse(this.qcmTestResponseService.update(qcmTestResponse));
    } else {
      this.subscribeToSaveResponse(this.qcmTestResponseService.create(qcmTestResponse));
    }
  }

  private createFromForm(): IQcmTestResponse {
    return {
      ...new QcmTestResponse(),
      id: this.editForm.get(['id'])!.value,
      dateSoumission:
        this.editForm.get(['dateSoumission'])!.value != null
          ? moment(this.editForm.get(['dateSoumission'])!.value, DATE_TIME_FORMAT)
          : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQcmTestResponse>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
