import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IQcm, Qcm } from 'app/shared/model/qcm.model';
import { QcmService } from './qcm.service';
import { QcmComponent } from './qcm.component';
import { QcmDetailComponent } from './qcm-detail.component';
import { QcmUpdateComponent } from './qcm-update.component';

@Injectable({ providedIn: 'root' })
export class QcmResolve implements Resolve<IQcm> {
  constructor(private service: QcmService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQcm> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((qcm: HttpResponse<Qcm>) => {
          if (qcm.body) {
            return of(qcm.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Qcm());
  }
}

export const qcmRoute: Routes = [
  {
    path: '',
    component: QcmComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Qcms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: QcmDetailComponent,
    resolve: {
      qcm: QcmResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Qcms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: QcmUpdateComponent,
    resolve: {
      qcm: QcmResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Qcms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: QcmUpdateComponent,
    resolve: {
      qcm: QcmResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Qcms'
    },
    canActivate: [UserRouteAccessService]
  }
];
