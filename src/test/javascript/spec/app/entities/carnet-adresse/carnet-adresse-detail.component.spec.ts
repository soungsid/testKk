import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { CarnetAdresseDetailComponent } from 'app/entities/carnet-adresse/carnet-adresse-detail.component';
import { CarnetAdresse } from 'app/shared/model/carnet-adresse.model';

describe('Component Tests', () => {
  describe('CarnetAdresse Management Detail Component', () => {
    let comp: CarnetAdresseDetailComponent;
    let fixture: ComponentFixture<CarnetAdresseDetailComponent>;
    const route = ({ data: of({ carnetAdresse: new CarnetAdresse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [CarnetAdresseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CarnetAdresseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CarnetAdresseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load carnetAdresse on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.carnetAdresse).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
