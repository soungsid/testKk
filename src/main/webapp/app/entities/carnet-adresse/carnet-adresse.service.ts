import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICarnetAdresse } from 'app/shared/model/carnet-adresse.model';

type EntityResponseType = HttpResponse<ICarnetAdresse>;
type EntityArrayResponseType = HttpResponse<ICarnetAdresse[]>;

@Injectable({ providedIn: 'root' })
export class CarnetAdresseService {
  public resourceUrl = SERVER_API_URL + 'api/carnet-adresses';

  constructor(protected http: HttpClient) {}

  create(carnetAdresse: ICarnetAdresse): Observable<EntityResponseType> {
    return this.http.post<ICarnetAdresse>(this.resourceUrl, carnetAdresse, { observe: 'response' });
  }

  update(carnetAdresse: ICarnetAdresse): Observable<EntityResponseType> {
    return this.http.put<ICarnetAdresse>(this.resourceUrl, carnetAdresse, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICarnetAdresse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICarnetAdresse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
