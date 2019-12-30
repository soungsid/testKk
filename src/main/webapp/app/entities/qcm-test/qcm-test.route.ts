import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IQcmTest, QcmTest } from 'app/shared/model/qcm-test.model';
import { QcmTestService } from './qcm-test.service';
import { QcmTestComponent } from './qcm-test.component';
import { QcmTestDetailComponent } from './qcm-test-detail.component';
import { QcmTestUpdateComponent } from './qcm-test-update.component';

@Injectable({ providedIn: 'root' })
export class QcmTestResolve implements Resolve<IQcmTest> {
  constructor(private service: QcmTestService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQcmTest> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((qcmTest: HttpResponse<QcmTest>) => {
          if (qcmTest.body) {
            return of(qcmTest.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new QcmTest());
  }
}

export const qcmTestRoute: Routes = [
  {
    path: '',
    component: QcmTestComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmTests'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: QcmTestDetailComponent,
    resolve: {
      qcmTest: QcmTestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmTests'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: QcmTestUpdateComponent,
    resolve: {
      qcmTest: QcmTestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmTests'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: QcmTestUpdateComponent,
    resolve: {
      qcmTest: QcmTestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmTests'
    },
    canActivate: [UserRouteAccessService]
  }
];
