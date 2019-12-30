import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IQcmReponse, QcmReponse } from 'app/shared/model/qcm-reponse.model';
import { QcmReponseService } from './qcm-reponse.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IQcmTestResponse } from 'app/shared/model/qcm-test-response.model';
import { QcmTestResponseService } from 'app/entities/qcm-test-response/qcm-test-response.service';

@Component({
  selector: 'jhi-qcm-reponse-update',
  templateUrl: './qcm-reponse-update.component.html'
})
export class QcmReponseUpdateComponent implements OnInit {
  isSaving = false;

  qcmtestresponses: IQcmTestResponse[] = [];

  editForm = this.fb.group({
    id: [],
    reponseText: [],
    reponseImage: [],
    reponseImageContentType: [],
    reponseNombre: [],
    correct: [],
    poids: [],
    qcmTestResponseId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected qcmReponseService: QcmReponseService,
    protected qcmTestResponseService: QcmTestResponseService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ qcmReponse }) => {
      this.updateForm(qcmReponse);

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

  updateForm(qcmReponse: IQcmReponse): void {
    this.editForm.patchValue({
      id: qcmReponse.id,
      reponseText: qcmReponse.reponseText,
      reponseImage: qcmReponse.reponseImage,
      reponseImageContentType: qcmReponse.reponseImageContentType,
      reponseNombre: qcmReponse.reponseNombre,
      correct: qcmReponse.correct,
      poids: qcmReponse.poids,
      qcmTestResponseId: qcmReponse.qcmTestResponseId
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

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const qcmReponse = this.createFromForm();
    if (qcmReponse.id !== undefined) {
      this.subscribeToSaveResponse(this.qcmReponseService.update(qcmReponse));
    } else {
      this.subscribeToSaveResponse(this.qcmReponseService.create(qcmReponse));
    }
  }

  private createFromForm(): IQcmReponse {
    return {
      ...new QcmReponse(),
      id: this.editForm.get(['id'])!.value,
      reponseText: this.editForm.get(['reponseText'])!.value,
      reponseImageContentType: this.editForm.get(['reponseImageContentType'])!.value,
      reponseImage: this.editForm.get(['reponseImage'])!.value,
      reponseNombre: this.editForm.get(['reponseNombre'])!.value,
      correct: this.editForm.get(['correct'])!.value,
      poids: this.editForm.get(['poids'])!.value,
      qcmTestResponseId: this.editForm.get(['qcmTestResponseId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQcmReponse>>): void {
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
