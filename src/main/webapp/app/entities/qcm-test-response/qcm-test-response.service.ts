import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IQcmTestResponse } from 'app/shared/model/qcm-test-response.model';

type EntityResponseType = HttpResponse<IQcmTestResponse>;
type EntityArrayResponseType = HttpResponse<IQcmTestResponse[]>;

@Injectable({ providedIn: 'root' })
export class QcmTestResponseService {
  public resourceUrl = SERVER_API_URL + 'api/qcm-test-responses';

  constructor(protected http: HttpClient) {}

  create(qcmTestResponse: IQcmTestResponse): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(qcmTestResponse);
    return this.http
      .post<IQcmTestResponse>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(qcmTestResponse: IQcmTestResponse): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(qcmTestResponse);
    return this.http
      .put<IQcmTestResponse>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IQcmTestResponse>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IQcmTestResponse[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(qcmTestResponse: IQcmTestResponse): IQcmTestResponse {
    const copy: IQcmTestResponse = Object.assign({}, qcmTestResponse, {
      dateSoumission:
        qcmTestResponse.dateSoumission && qcmTestResponse.dateSoumission.isValid() ? qcmTestResponse.dateSoumission.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateSoumission = res.body.dateSoumission ? moment(res.body.dateSoumission) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((qcmTestResponse: IQcmTestResponse) => {
        qcmTestResponse.dateSoumission = qcmTestResponse.dateSoumission ? moment(qcmTestResponse.dateSoumission) : undefined;
      });
    }
    return res;
  }
}
