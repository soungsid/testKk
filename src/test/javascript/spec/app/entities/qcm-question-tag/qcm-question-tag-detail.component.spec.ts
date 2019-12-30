import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmQuestionTagDetailComponent } from 'app/entities/qcm-question-tag/qcm-question-tag-detail.component';
import { QcmQuestionTag } from 'app/shared/model/qcm-question-tag.model';

describe('Component Tests', () => {
  describe('QcmQuestionTag Management Detail Component', () => {
    let comp: QcmQuestionTagDetailComponent;
    let fixture: ComponentFixture<QcmQuestionTagDetailComponent>;
    const route = ({ data: of({ qcmQuestionTag: new QcmQuestionTag(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmQuestionTagDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(QcmQuestionTagDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(QcmQuestionTagDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load qcmQuestionTag on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.qcmQuestionTag).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
