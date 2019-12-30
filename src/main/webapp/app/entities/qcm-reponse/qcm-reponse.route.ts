import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IQcmReponse, QcmReponse } from 'app/shared/model/qcm-reponse.model';
import { QcmReponseService } from './qcm-reponse.service';
import { QcmReponseComponent } from './qcm-reponse.component';
import { QcmReponseDetailComponent } from './qcm-reponse-detail.component';
import { QcmReponseUpdateComponent } from './qcm-reponse-update.component';

@Injectable({ providedIn: 'root' })
export class QcmReponseResolve implements Resolve<IQcmReponse> {
  constructor(private service: QcmReponseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQcmReponse> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((qcmReponse: HttpResponse<QcmReponse>) => {
          if (qcmReponse.body) {
            return of(qcmReponse.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new QcmReponse());
  }
}

export const qcmReponseRoute: Routes = [
  {
    path: '',
    component: QcmReponseComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmReponses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: QcmReponseDetailComponent,
    resolve: {
      qcmReponse: QcmReponseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmReponses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: QcmReponseUpdateComponent,
    resolve: {
      qcmReponse: QcmReponseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmReponses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: QcmReponseUpdateComponent,
    resolve: {
      qcmReponse: QcmReponseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmReponses'
    },
    canActivate: [UserRouteAccessService]
  }
];
