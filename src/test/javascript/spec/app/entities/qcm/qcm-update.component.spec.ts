import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmUpdateComponent } from 'app/entities/qcm/qcm-update.component';
import { QcmService } from 'app/entities/qcm/qcm.service';
import { Qcm } from 'app/shared/model/qcm.model';

describe('Component Tests', () => {
  describe('Qcm Management Update Component', () => {
    let comp: QcmUpdateComponent;
    let fixture: ComponentFixture<QcmUpdateComponent>;
    let service: QcmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(QcmUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(QcmUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(QcmService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Qcm(123);
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
        const entity = new Qcm();
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
