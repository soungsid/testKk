import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISocieteAbonne } from 'app/shared/model/societe-abonne.model';

@Component({
  selector: 'jhi-societe-abonne-detail',
  templateUrl: './societe-abonne-detail.component.html'
})
export class SocieteAbonneDetailComponent implements OnInit {
  societeAbonne: ISocieteAbonne | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ societeAbonne }) => {
      this.societeAbonne = societeAbonne;
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
