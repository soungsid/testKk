import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISocieteAbonne } from 'app/shared/model/societe-abonne.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SocieteAbonneService } from './societe-abonne.service';
import { SocieteAbonneDeleteDialogComponent } from './societe-abonne-delete-dialog.component';

@Component({
  selector: 'jhi-societe-abonne',
  templateUrl: './societe-abonne.component.html'
})
export class SocieteAbonneComponent implements OnInit, OnDestroy {
  societeAbonnes: ISocieteAbonne[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected societeAbonneService: SocieteAbonneService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.societeAbonnes = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.societeAbonneService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ISocieteAbonne[]>) => this.paginateSocieteAbonnes(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.societeAbonnes = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSocieteAbonnes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISocieteAbonne): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInSocieteAbonnes(): void {
    this.eventSubscriber = this.eventManager.subscribe('societeAbonneListModification', () => this.reset());
  }

  delete(societeAbonne: ISocieteAbonne): void {
    const modalRef = this.modalService.open(SocieteAbonneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.societeAbonne = societeAbonne;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSocieteAbonnes(data: ISocieteAbonne[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.societeAbonnes.push(data[i]);
      }
    }
  }
}
