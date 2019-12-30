import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICarnetAdresse } from 'app/shared/model/carnet-adresse.model';

@Component({
  selector: 'jhi-carnet-adresse-detail',
  templateUrl: './carnet-adresse-detail.component.html'
})
export class CarnetAdresseDetailComponent implements OnInit {
  carnetAdresse: ICarnetAdresse | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ carnetAdresse }) => {
      this.carnetAdresse = carnetAdresse;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
