import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IQcmQuestionTag, QcmQuestionTag } from 'app/shared/model/qcm-question-tag.model';
import { QcmQuestionTagService } from './qcm-question-tag.service';
import { QcmQuestionTagComponent } from './qcm-question-tag.component';
import { QcmQuestionTagDetailComponent } from './qcm-question-tag-detail.component';
import { QcmQuestionTagUpdateComponent } from './qcm-question-tag-update.component';

@Injectable({ providedIn: 'root' })
export class QcmQuestionTagResolve implements Resolve<IQcmQuestionTag> {
  constructor(private service: QcmQuestionTagService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQcmQuestionTag> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((qcmQuestionTag: HttpResponse<QcmQuestionTag>) => {
          if (qcmQuestionTag.body) {
            return of(qcmQuestionTag.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new QcmQuestionTag());
  }
}

export const qcmQuestionTagRoute: Routes = [
  {
    path: '',
    component: QcmQuestionTagComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmQuestionTags'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: QcmQuestionTagDetailComponent,
    resolve: {
      qcmQuestionTag: QcmQuestionTagResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmQuestionTags'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: QcmQuestionTagUpdateComponent,
    resolve: {
      qcmQuestionTag: QcmQuestionTagResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmQuestionTags'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: QcmQuestionTagUpdateComponent,
    resolve: {
      qcmQuestionTag: QcmQuestionTagResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmQuestionTags'
    },
    canActivate: [UserRouteAccessService]
  }
];
