import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICategorie, Categorie } from 'app/shared/model/categorie.model';
import { CategorieService } from './categorie.service';
import { IQcm } from 'app/shared/model/qcm.model';
import { QcmService } from 'app/entities/qcm/qcm.service';

@Component({
  selector: 'jhi-categorie-update',
  templateUrl: './categorie-update.component.html'
})
export class CategorieUpdateComponent implements OnInit {
  isSaving = false;

  qcms: IQcm[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.minLength(1)]],
    libelle: [],
    qcmId: []
  });

  constructor(
    protected categorieService: CategorieService,
    protected qcmService: QcmService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorie }) => {
      this.updateForm(categorie);

      this.qcmService
        .query()
        .pipe(
          map((res: HttpResponse<IQcm[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IQcm[]) => (this.qcms = resBody));
    });
  }

  updateForm(categorie: ICategorie): void {
    this.editForm.patchValue({
      id: categorie.id,
      code: categorie.code,
      libelle: categorie.libelle,
      qcmId: categorie.qcmId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const categorie = this.createFromForm();
    if (categorie.id !== undefined) {
      this.subscribeToSaveResponse(this.categorieService.update(categorie));
    } else {
      this.subscribeToSaveResponse(this.categorieService.create(categorie));
    }
  }

  private createFromForm(): ICategorie {
    return {
      ...new Categorie(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      qcmId: this.editForm.get(['qcmId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorie>>): void {
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

  trackById(index: number, item: IQcm): any {
    return item.id;
  }
}
