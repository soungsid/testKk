import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IQcmTestResponse, QcmTestResponse } from 'app/shared/model/qcm-test-response.model';
import { QcmTestResponseService } from './qcm-test-response.service';
import { QcmTestResponseComponent } from './qcm-test-response.component';
import { QcmTestResponseDetailComponent } from './qcm-test-response-detail.component';
import { QcmTestResponseUpdateComponent } from './qcm-test-response-update.component';

@Injectable({ providedIn: 'root' })
export class QcmTestResponseResolve implements Resolve<IQcmTestResponse> {
  constructor(private service: QcmTestResponseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQcmTestResponse> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((qcmTestResponse: HttpResponse<QcmTestResponse>) => {
          if (qcmTestResponse.body) {
            return of(qcmTestResponse.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new QcmTestResponse());
  }
}

export const qcmTestResponseRoute: Routes = [
  {
    path: '',
    component: QcmTestResponseComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmTestResponses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: QcmTestResponseDetailComponent,
    resolve: {
      qcmTestResponse: QcmTestResponseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmTestResponses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: QcmTestResponseUpdateComponent,
    resolve: {
      qcmTestResponse: QcmTestResponseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmTestResponses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: QcmTestResponseUpdateComponent,
    resolve: {
      qcmTestResponse: QcmTestResponseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmTestResponses'
    },
    canActivate: [UserRouteAccessService]
  }
];
