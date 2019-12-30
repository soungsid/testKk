import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITag, Tag } from 'app/shared/model/tag.model';
import { TagService } from './tag.service';
import { IQcmQuestionTag } from 'app/shared/model/qcm-question-tag.model';
import { QcmQuestionTagService } from 'app/entities/qcm-question-tag/qcm-question-tag.service';

@Component({
  selector: 'jhi-tag-update',
  templateUrl: './tag-update.component.html'
})
export class TagUpdateComponent implements OnInit {
  isSaving = false;

  qcmquestiontags: IQcmQuestionTag[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    code: [],
    qcmQuestionTagId: []
  });

  constructor(
    protected tagService: TagService,
    protected qcmQuestionTagService: QcmQuestionTagService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tag }) => {
      this.updateForm(tag);

      this.qcmQuestionTagService
        .query()
        .pipe(
          map((res: HttpResponse<IQcmQuestionTag[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IQcmQuestionTag[]) => (this.qcmquestiontags = resBody));
    });
  }

  updateForm(tag: ITag): void {
    this.editForm.patchValue({
      id: tag.id,
      libelle: tag.libelle,
      code: tag.code,
      qcmQuestionTagId: tag.qcmQuestionTagId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tag = this.createFromForm();
    if (tag.id !== undefined) {
      this.subscribeToSaveResponse(this.tagService.update(tag));
    } else {
      this.subscribeToSaveResponse(this.tagService.create(tag));
    }
  }

  private createFromForm(): ITag {
    return {
      ...new Tag(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      code: this.editForm.get(['code'])!.value,
      qcmQuestionTagId: this.editForm.get(['qcmQuestionTagId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITag>>): void {
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

  trackById(index: number, item: IQcmQuestionTag): any {
    return item.id;
  }
}
