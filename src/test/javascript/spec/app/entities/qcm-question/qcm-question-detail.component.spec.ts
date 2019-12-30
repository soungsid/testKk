import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { TestKkTestModule } from '../../../test.module';
import { QcmQuestionDetailComponent } from 'app/entities/qcm-question/qcm-question-detail.component';
import { QcmQuestion } from 'app/shared/model/qcm-question.model';

describe('Component Tests', () => {
  describe('QcmQuestion Management Detail Component', () => {
    let comp: QcmQuestionDetailComponent;
    let fixture: ComponentFixture<QcmQuestionDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ qcmQuestion: new QcmQuestion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmQuestionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(QcmQuestionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(QcmQuestionDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load qcmQuestion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.qcmQuestion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
