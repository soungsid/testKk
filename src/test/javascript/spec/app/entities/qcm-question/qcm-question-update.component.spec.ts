import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmQuestionUpdateComponent } from 'app/entities/qcm-question/qcm-question-update.component';
import { QcmQuestionService } from 'app/entities/qcm-question/qcm-question.service';
import { QcmQuestion } from 'app/shared/model/qcm-question.model';

describe('Component Tests', () => {
  describe('QcmQuestion Management Update Component', () => {
    let comp: QcmQuestionUpdateComponent;
    let fixture: ComponentFixture<QcmQuestionUpdateComponent>;
    let service: QcmQuestionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmQuestionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(QcmQuestionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(QcmQuestionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(QcmQuestionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new QcmQuestion(123);
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
        const entity = new QcmQuestion();
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
