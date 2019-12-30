import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IQcm, Qcm } from 'app/shared/model/qcm.model';
import { QcmService } from './qcm.service';
import { IQcmQuestion } from 'app/shared/model/qcm-question.model';
import { QcmQuestionService } from 'app/entities/qcm-question/qcm-question.service';
import { IQcmTest } from 'app/shared/model/qcm-test.model';
import { QcmTestService } from 'app/entities/qcm-test/qcm-test.service';

type SelectableEntity = IQcmQuestion | IQcmTest;

@Component({
  selector: 'jhi-qcm-update',
  templateUrl: './qcm-update.component.html'
})
export class QcmUpdateComponent implements OnInit {
  isSaving = false;

  qcmquestions: IQcmQuestion[] = [];

  qcmtests: IQcmTest[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.minLength(3)]],
    nbQuestion: [],
    privee: [],
    qcmQuestionId: [],
    qcmTestId: []
  });

  constructor(
    protected qcmService: QcmService,
    protected qcmQuestionService: QcmQuestionService,
    protected qcmTestService: QcmTestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcm }) => {
      this.updateForm(qcm);

      this.qcmQuestionService
        .query()
        .pipe(
          map((res: HttpResponse<IQcmQuestion[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IQcmQuestion[]) => (this.qcmquestions = resBody));

      this.qcmTestService
        .query()
        .pipe(
          map((res: HttpResponse<IQcmTest[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IQcmTest[]) => (this.qcmtests = resBody));
    });
  }

  updateForm(qcm: IQcm): void {
    this.editForm.patchValue({
      id: qcm.id,
      name: qcm.name,
      nbQuestion: qcm.nbQuestion,
      privee: qcm.privee,
      qcmQuestionId: qcm.qcmQuestionId,
      qcmTestId: qcm.qcmTestId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const qcm = this.createFromForm();
    if (qcm.id !== undefined) {
      this.subscribeToSaveResponse(this.qcmService.update(qcm));
    } else {
      this.subscribeToSaveResponse(this.qcmService.create(qcm));
    }
  }

  private createFromForm(): IQcm {
    return {
      ...new Qcm(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      nbQuestion: this.editForm.get(['nbQuestion'])!.value,
      privee: this.editForm.get(['privee'])!.value,
      qcmQuestionId: this.editForm.get(['qcmQuestionId'])!.value,
      qcmTestId: this.editForm.get(['qcmTestId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQcm>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
