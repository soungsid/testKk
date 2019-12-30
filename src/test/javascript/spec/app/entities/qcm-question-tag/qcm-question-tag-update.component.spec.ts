import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmQuestionTagUpdateComponent } from 'app/entities/qcm-question-tag/qcm-question-tag-update.component';
import { QcmQuestionTagService } from 'app/entities/qcm-question-tag/qcm-question-tag.service';
import { QcmQuestionTag } from 'app/shared/model/qcm-question-tag.model';

describe('Component Tests', () => {
  describe('QcmQuestionTag Management Update Component', () => {
    let comp: QcmQuestionTagUpdateComponent;
    let fixture: ComponentFixture<QcmQuestionTagUpdateComponent>;
    let service: QcmQuestionTagService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmQuestionTagUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(QcmQuestionTagUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(QcmQuestionTagUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(QcmQuestionTagService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new QcmQuestionTag(123);
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
        const entity = new QcmQuestionTag();
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
