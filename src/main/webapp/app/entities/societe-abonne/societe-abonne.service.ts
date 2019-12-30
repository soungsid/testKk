import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISocieteAbonne } from 'app/shared/model/societe-abonne.model';

type EntityResponseType = HttpResponse<ISocieteAbonne>;
type EntityArrayResponseType = HttpResponse<ISocieteAbonne[]>;

@Injectable({ providedIn: 'root' })
export class SocieteAbonneService {
  public resourceUrl = SERVER_API_URL + 'api/societe-abonnes';

  constructor(protected http: HttpClient) {}

  create(societeAbonne: ISocieteAbonne): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(societeAbonne);
    return this.http
      .post<ISocieteAbonne>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(societeAbonne: ISocieteAbonne): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(societeAbonne);
    return this.http
      .put<ISocieteAbonne>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISocieteAbonne>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISocieteAbonne[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(societeAbonne: ISocieteAbonne): ISocieteAbonne {
    const copy: ISocieteAbonne = Object.assign({}, societeAbonne, {
      dateAbonnement:
        societeAbonne.dateAbonnement && societeAbonne.dateAbonnement.isValid() ? societeAbonne.dateAbonnement.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateAbonnement = res.body.dateAbonnement ? moment(res.body.dateAbonnement) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((societeAbonne: ISocieteAbonne) => {
        societeAbonne.dateAbonnement = societeAbonne.dateAbonnement ? moment(societeAbonne.dateAbonnement) : undefined;
      });
    }
    return res;
  }
}
