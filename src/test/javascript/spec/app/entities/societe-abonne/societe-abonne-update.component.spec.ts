import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { SocieteAbonneUpdateComponent } from 'app/entities/societe-abonne/societe-abonne-update.component';
import { SocieteAbonneService } from 'app/entities/societe-abonne/societe-abonne.service';
import { SocieteAbonne } from 'app/shared/model/societe-abonne.model';

describe('Component Tests', () => {
  describe('SocieteAbonne Management Update Component', () => {
    let comp: SocieteAbonneUpdateComponent;
    let fixture: ComponentFixture<SocieteAbonneUpdateComponent>;
    let service: SocieteAbonneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [SocieteAbonneUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SocieteAbonneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SocieteAbonneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SocieteAbonneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SocieteAbonne(123);
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
        const entity = new SocieteAbonne();
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
