import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { CarnetAdresseUpdateComponent } from 'app/entities/carnet-adresse/carnet-adresse-update.component';
import { CarnetAdresseService } from 'app/entities/carnet-adresse/carnet-adresse.service';
import { CarnetAdresse } from 'app/shared/model/carnet-adresse.model';

describe('Component Tests', () => {
  describe('CarnetAdresse Management Update Component', () => {
    let comp: CarnetAdresseUpdateComponent;
    let fixture: ComponentFixture<CarnetAdresseUpdateComponent>;
    let service: CarnetAdresseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [CarnetAdresseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CarnetAdresseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CarnetAdresseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CarnetAdresseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CarnetAdresse(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CarnetAdresse();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
