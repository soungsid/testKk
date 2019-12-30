import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IQcmTest, QcmTest } from 'app/shared/model/qcm-test.model';
import { QcmTestService } from './qcm-test.service';
import { IQcmTestResponse } from 'app/shared/model/qcm-test-response.model';
import { QcmTestResponseService } from 'app/entities/qcm-test-response/qcm-test-response.service';

@Component({
  selector: 'jhi-qcm-test-update',
  templateUrl: './qcm-test-update.component.html'
})
export class QcmTestUpdateComponent implements OnInit {
  isSaving = false;

  qcmtestresponses: IQcmTestResponse[] = [];

  editForm = this.fb.group({
    id: [],
    dateDebut: [],
    dateFin: [],
    email: [],
    score: [],
    highScore: [],
    qcmTestResponseId: []
  });

  constructor(
    protected qcmTestService: QcmTestService,
    protected qcmTestResponseService: QcmTestResponseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcmTest }) => {
      this.updateForm(qcmTest);

      this.qcmTestResponseService
        .query()
        .pipe(
          map((res: HttpResponse<IQcmTestResponse[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IQcmTestResponse[]) => (this.qcmtestresponses = resBody));
    });
  }

  updateForm(qcmTest: IQcmTest): void {
    this.editForm.patchValue({
      id: qcmTest.id,
      dateDebut: qcmTest.dateDebut != null ? qcmTest.dateDebut.format(DATE_TIME_FORMAT) : null,
      dateFin: qcmTest.dateFin != null ? qcmTest.dateFin.format(DATE_TIME_FORMAT) : null,
      email: qcmTest.email,
      score: qcmTest.score,
      highScore: qcmTest.highScore,
      qcmTestResponseId: qcmTest.qcmTestResponseId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const qcmTest = this.createFromForm();
    if (qcmTest.id !== undefined) {
      this.subscribeToSaveResponse(this.qcmTestService.update(qcmTest));
    } else {
      this.subscribeToSaveResponse(this.qcmTestService.create(qcmTest));
    }
  }

  private createFromForm(): IQcmTest {
    return {
      ...new QcmTest(),
      id: this.editForm.get(['id'])!.value,
      dateDebut:
        this.editForm.get(['dateDebut'])!.value != null ? moment(this.editForm.get(['dateDebut'])!.value, DATE_TIME_FORMAT) : undefined,
      dateFin: this.editForm.get(['dateFin'])!.value != null ? moment(this.editForm.get(['dateFin'])!.value, DATE_TIME_FORMAT) : undefined,
      email: this.editForm.get(['email'])!.value,
      score: this.editForm.get(['score'])!.value,
      highScore: this.editForm.get(['highScore'])!.value,
      qcmTestResponseId: this.editForm.get(['qcmTestResponseId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQcmTest>>): void {
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

  trackById(index: number, item: IQcmTestResponse): any {
    return item.id;
  }
}
