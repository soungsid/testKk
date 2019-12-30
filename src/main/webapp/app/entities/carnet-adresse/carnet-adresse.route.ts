import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICarnetAdresse, CarnetAdresse } from 'app/shared/model/carnet-adresse.model';
import { CarnetAdresseService } from './carnet-adresse.service';
import { CarnetAdresseComponent } from './carnet-adresse.component';
import { CarnetAdresseDetailComponent } from './carnet-adresse-detail.component';
import { CarnetAdresseUpdateComponent } from './carnet-adresse-update.component';

@Injectable({ providedIn: 'root' })
export class CarnetAdresseResolve implements Resolve<ICarnetAdresse> {
  constructor(private service: CarnetAdresseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICarnetAdresse> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((carnetAdresse: HttpResponse<CarnetAdresse>) => {
          if (carnetAdresse.body) {
            return of(carnetAdresse.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CarnetAdresse());
  }
}

export const carnetAdresseRoute: Routes = [
  {
    path: '',
    component: CarnetAdresseComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CarnetAdresses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CarnetAdresseDetailComponent,
    resolve: {
      carnetAdresse: CarnetAdresseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CarnetAdresses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CarnetAdresseUpdateComponent,
    resolve: {
      carnetAdresse: CarnetAdresseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CarnetAdresses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CarnetAdresseUpdateComponent,
    resolve: {
      carnetAdresse: CarnetAdresseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CarnetAdresses'
    },
    canActivate: [UserRouteAccessService]
  }
];
