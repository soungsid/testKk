import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISocieteAbonne, SocieteAbonne } from 'app/shared/model/societe-abonne.model';
import { SocieteAbonneService } from './societe-abonne.service';
import { SocieteAbonneComponent } from './societe-abonne.component';
import { SocieteAbonneDetailComponent } from './societe-abonne-detail.component';
import { SocieteAbonneUpdateComponent } from './societe-abonne-update.component';

@Injectable({ providedIn: 'root' })
export class SocieteAbonneResolve implements Resolve<ISocieteAbonne> {
  constructor(private service: SocieteAbonneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISocieteAbonne> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((societeAbonne: HttpResponse<SocieteAbonne>) => {
          if (societeAbonne.body) {
            return of(societeAbonne.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SocieteAbonne());
  }
}

export const societeAbonneRoute: Routes = [
  {
    path: '',
    component: SocieteAbonneComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SocieteAbonnes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SocieteAbonneDetailComponent,
    resolve: {
      societeAbonne: SocieteAbonneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SocieteAbonnes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SocieteAbonneUpdateComponent,
    resolve: {
      societeAbonne: SocieteAbonneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SocieteAbonnes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SocieteAbonneUpdateComponent,
    resolve: {
      societeAbonne: SocieteAbonneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SocieteAbonnes'
    },
    canActivate: [UserRouteAccessService]
  }
];
