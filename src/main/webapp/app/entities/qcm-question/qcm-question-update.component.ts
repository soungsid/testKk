import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IQcmQuestion, QcmQuestion } from 'app/shared/model/qcm-question.model';
import { QcmQuestionService } from './qcm-question.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IQcmQuestionTag } from 'app/shared/model/qcm-question-tag.model';
import { QcmQuestionTagService } from 'app/entities/qcm-question-tag/qcm-question-tag.service';
import { IQcmReponse } from 'app/shared/model/qcm-reponse.model';
import { QcmReponseService } from 'app/entities/qcm-reponse/qcm-reponse.service';
import { IQcmTestResponse } from 'app/shared/model/qcm-test-response.model';
import { QcmTestResponseService } from 'app/entities/qcm-test-response/qcm-test-response.service';

type SelectableEntity = IQcmQuestionTag | IQcmReponse | IQcmTestResponse;

@Component({
  selector: 'jhi-qcm-question-update',
  templateUrl: './qcm-question-update.component.html'
})
export class QcmQuestionUpdateComponent implements OnInit {
  isSaving = false;

  qcmquestiontags: IQcmQuestionTag[] = [];

  qcmreponses: IQcmReponse[] = [];

  qcmtestresponses: IQcmTestResponse[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    type: [],
    explication: [],
    qcmQuestionTagId: [],
    qcmReponseId: [],
    qcmTestResponseId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected qcmQuestionService: QcmQuestionService,
    protected qcmQuestionTagService: QcmQuestionTagService,
    protected qcmReponseService: QcmReponseService,
    protected qcmTestResponseService: QcmTestResponseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcmQuestion }) => {
      this.updateForm(qcmQuestion);

      this.qcmQuestionTagService
        .query()
        .pipe(
          map((res: HttpResponse<IQcmQuestionTag[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IQcmQuestionTag[]) => (this.qcmquestiontags = resBody));

      this.qcmReponseService
        .query()
        .pipe(
          map((res: HttpResponse<IQcmReponse[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IQcmReponse[]) => (this.qcmreponses = resBody));

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

  updateForm(qcmQuestion: IQcmQuestion): void {
    this.editForm.patchValue({
      id: qcmQuestion.id,
      libelle: qcmQuestion.libelle,
      type: qcmQuestion.type,
      explication: qcmQuestion.explication,
      qcmQuestionTagId: qcmQuestion.qcmQuestionTagId,
      qcmReponseId: qcmQuestion.qcmReponseId,
      qcmTestResponseId: qcmQuestion.qcmTestResponseId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('testKkApp.error', { message: err.message })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const qcmQuestion = this.createFromForm();
    if (qcmQuestion.id !== undefined) {
      this.subscribeToSaveResponse(this.qcmQuestionService.update(qcmQuestion));
    } else {
      this.subscribeToSaveResponse(this.qcmQuestionService.create(qcmQuestion));
    }
  }

  private createFromForm(): IQcmQuestion {
    return {
      ...new QcmQuestion(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      type: this.editForm.get(['type'])!.value,
      explication: this.editForm.get(['explication'])!.value,
      qcmQuestionTagId: this.editForm.get(['qcmQuestionTagId'])!.value,
      qcmReponseId: this.editForm.get(['qcmReponseId'])!.value,
      qcmTestResponseId: this.editForm.get(['qcmTestResponseId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQcmQuestion>>): void {
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
