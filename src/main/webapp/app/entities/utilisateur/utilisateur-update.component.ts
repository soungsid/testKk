import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IUtilisateur, Utilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from './utilisateur.service';
import { IQcm } from 'app/shared/model/qcm.model';
import { QcmService } from 'app/entities/qcm/qcm.service';
import { IQcmTest } from 'app/shared/model/qcm-test.model';
import { QcmTestService } from 'app/entities/qcm-test/qcm-test.service';

type SelectableEntity = IQcm | IQcmTest;

@Component({
  selector: 'jhi-utilisateur-update',
  templateUrl: './utilisateur-update.component.html'
})
export class UtilisateurUpdateComponent implements OnInit {
  isSaving = false;

  qcms: IQcm[] = [];

  qcmtests: IQcmTest[] = [];

  editForm = this.fb.group({
    id: [],
    lastname: [],
    firstname: [],
    email: [],
    linkedin: [],
    googleplus: [],
    role: [],
    qcmId: [],
    qcmTestId: []
  });

  constructor(
    protected utilisateurService: UtilisateurService,
    protected qcmService: QcmService,
    protected qcmTestService: QcmTestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ utilisateur }) => {
      this.updateForm(utilisateur);

      this.qcmService
        .query()
        .pipe(
          map((res: HttpResponse<IQcm[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IQcm[]) => (this.qcms = resBody));

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

  updateForm(utilisateur: IUtilisateur): void {
    this.editForm.patchValue({
      id: utilisateur.id,
      lastname: utilisateur.lastname,
      firstname: utilisateur.firstname,
      email: utilisateur.email,
      linkedin: utilisateur.linkedin,
      googleplus: utilisateur.googleplus,
      role: utilisateur.role,
      qcmId: utilisateur.qcmId,
      qcmTestId: utilisateur.qcmTestId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const utilisateur = this.createFromForm();
    if (utilisateur.id !== undefined) {
      this.subscribeToSaveResponse(this.utilisateurService.update(utilisateur));
    } else {
      this.subscribeToSaveResponse(this.utilisateurService.create(utilisateur));
    }
  }

  private createFromForm(): IUtilisateur {
    return {
      ...new Utilisateur(),
      id: this.editForm.get(['id'])!.value,
      lastname: this.editForm.get(['lastname'])!.value,
      firstname: this.editForm.get(['firstname'])!.value,
      email: this.editForm.get(['email'])!.value,
      linkedin: this.editForm.get(['linkedin'])!.value,
      googleplus: this.editForm.get(['googleplus'])!.value,
      role: this.editForm.get(['role'])!.value,
      qcmId: this.editForm.get(['qcmId'])!.value,
      qcmTestId: this.editForm.get(['qcmTestId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUtilisateur>>): void {
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
