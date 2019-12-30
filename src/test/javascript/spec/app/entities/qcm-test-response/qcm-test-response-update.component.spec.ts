import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmTestResponseUpdateComponent } from 'app/entities/qcm-test-response/qcm-test-response-update.component';
import { QcmTestResponseService } from 'app/entities/qcm-test-response/qcm-test-response.service';
import { QcmTestResponse } from 'app/shared/model/qcm-test-response.model';

describe('Component Tests', () => {
  describe('QcmTestResponse Management Update Component', () => {
    let comp: QcmTestResponseUpdateComponent;
    let fixture: ComponentFixture<QcmTestResponseUpdateComponent>;
    let service: QcmTestResponseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmTestResponseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(QcmTestResponseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(QcmTestResponseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(QcmTestResponseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new QcmTestResponse(123);
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
        const entity = new QcmTestResponse();
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
