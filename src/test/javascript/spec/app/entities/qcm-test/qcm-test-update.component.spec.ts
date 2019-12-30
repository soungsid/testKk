import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmTestUpdateComponent } from 'app/entities/qcm-test/qcm-test-update.component';
import { QcmTestService } from 'app/entities/qcm-test/qcm-test.service';
import { QcmTest } from 'app/shared/model/qcm-test.model';

describe('Component Tests', () => {
  describe('QcmTest Management Update Component', () => {
    let comp: QcmTestUpdateComponent;
    let fixture: ComponentFixture<QcmTestUpdateComponent>;
    let service: QcmTestService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmTestUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(QcmTestUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(QcmTestUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(QcmTestService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new QcmTest(123);
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
        const entity = new QcmTest();
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
