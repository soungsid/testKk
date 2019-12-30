import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ISocieteAbonne, SocieteAbonne } from 'app/shared/model/societe-abonne.model';
import { SocieteAbonneService } from './societe-abonne.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur/utilisateur.service';

@Component({
  selector: 'jhi-societe-abonne-update',
  templateUrl: './societe-abonne-update.component.html'
})
export class SocieteAbonneUpdateComponent implements OnInit {
  isSaving = false;

  utilisateurs: IUtilisateur[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    dateAbonnement: [],
    logo: [],
    logoContentType: [],
    utilisateurId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected societeAbonneService: SocieteAbonneService,
    protected utilisateurService: UtilisateurService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ societeAbonne }) => {
      this.updateForm(societeAbonne);

      this.utilisateurService
        .query()
        .pipe(
          map((res: HttpResponse<IUtilisateur[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IUtilisateur[]) => (this.utilisateurs = resBody));
    });
  }

  updateForm(societeAbonne: ISocieteAbonne): void {
    this.editForm.patchValue({
      id: societeAbonne.id,
      name: societeAbonne.name,
      dateAbonnement: societeAbonne.dateAbonnement != null ? societeAbonne.dateAbonnement.format(DATE_TIME_FORMAT) : null,
      logo: societeAbonne.logo,
      logoContentType: societeAbonne.logoContentType,
      utilisateurId: societeAbonne.utilisateurId
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
    const societeAbonne = this.createFromForm();
    if (societeAbonne.id !== undefined) {
      this.subscribeToSaveResponse(this.societeAbonneService.update(societeAbonne));
    } else {
      this.subscribeToSaveResponse(this.societeAbonneService.create(societeAbonne));
    }
  }

  private createFromForm(): ISocieteAbonne {
    return {
      ...new SocieteAbonne(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      dateAbonnement:
        this.editForm.get(['dateAbonnement'])!.value != null
          ? moment(this.editForm.get(['dateAbonnement'])!.value, DATE_TIME_FORMAT)
          : undefined,
      logoContentType: this.editForm.get(['logoContentType'])!.value,
      logo: this.editForm.get(['logo'])!.value,
      utilisateurId: this.editForm.get(['utilisateurId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISocieteAbonne>>): void {
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

  trackById(index: number, item: IUtilisateur): any {
    return item.id;
  }
}
