import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmReponseUpdateComponent } from 'app/entities/qcm-reponse/qcm-reponse-update.component';
import { QcmReponseService } from 'app/entities/qcm-reponse/qcm-reponse.service';
import { QcmReponse } from 'app/shared/model/qcm-reponse.model';

describe('Component Tests', () => {
  describe('QcmReponse Management Update Component', () => {
    let comp: QcmReponseUpdateComponent;
    let fixture: ComponentFixture<QcmReponseUpdateComponent>;
    let service: QcmReponseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmReponseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(QcmReponseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(QcmReponseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(QcmReponseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new QcmReponse(123);
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
        const entity = new QcmReponse();
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
