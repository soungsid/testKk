import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IQcmTest } from 'app/shared/model/qcm-test.model';

type EntityResponseType = HttpResponse<IQcmTest>;
type EntityArrayResponseType = HttpResponse<IQcmTest[]>;

@Injectable({ providedIn: 'root' })
export class QcmTestService {
  public resourceUrl = SERVER_API_URL + 'api/qcm-tests';

  constructor(protected http: HttpClient) {}

  create(qcmTest: IQcmTest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(qcmTest);
    return this.http
      .post<IQcmTest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(qcmTest: IQcmTest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(qcmTest);
    return this.http
      .put<IQcmTest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IQcmTest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IQcmTest[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(qcmTest: IQcmTest): IQcmTest {
    const copy: IQcmTest = Object.assign({}, qcmTest, {
      dateDebut: qcmTest.dateDebut && qcmTest.dateDebut.isValid() ? qcmTest.dateDebut.toJSON() : undefined,
      dateFin: qcmTest.dateFin && qcmTest.dateFin.isValid() ? qcmTest.dateFin.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDebut = res.body.dateDebut ? moment(res.body.dateDebut) : undefined;
      res.body.dateFin = res.body.dateFin ? moment(res.body.dateFin) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((qcmTest: IQcmTest) => {
        qcmTest.dateDebut = qcmTest.dateDebut ? moment(qcmTest.dateDebut) : undefined;
        qcmTest.dateFin = qcmTest.dateFin ? moment(qcmTest.dateFin) : undefined;
      });
    }
    return res;
  }
}
