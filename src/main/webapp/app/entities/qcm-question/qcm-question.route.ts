import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IQcmQuestion, QcmQuestion } from 'app/shared/model/qcm-question.model';
import { QcmQuestionService } from './qcm-question.service';
import { QcmQuestionComponent } from './qcm-question.component';
import { QcmQuestionDetailComponent } from './qcm-question-detail.component';
import { QcmQuestionUpdateComponent } from './qcm-question-update.component';

@Injectable({ providedIn: 'root' })
export class QcmQuestionResolve implements Resolve<IQcmQuestion> {
  constructor(private service: QcmQuestionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQcmQuestion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((qcmQuestion: HttpResponse<QcmQuestion>) => {
          if (qcmQuestion.body) {
            return of(qcmQuestion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new QcmQuestion());
  }
}

export const qcmQuestionRoute: Routes = [
  {
    path: '',
    component: QcmQuestionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmQuestions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: QcmQuestionDetailComponent,
    resolve: {
      qcmQuestion: QcmQuestionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmQuestions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: QcmQuestionUpdateComponent,
    resolve: {
      qcmQuestion: QcmQuestionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmQuestions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: QcmQuestionUpdateComponent,
    resolve: {
      qcmQuestion: QcmQuestionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'QcmQuestions'
    },
    canActivate: [UserRouteAccessService]
  }
];
