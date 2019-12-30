import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { TestKkTestModule } from '../../../test.module';
import { QcmReponseDetailComponent } from 'app/entities/qcm-reponse/qcm-reponse-detail.component';
import { QcmReponse } from 'app/shared/model/qcm-reponse.model';

describe('Component Tests', () => {
  describe('QcmReponse Management Detail Component', () => {
    let comp: QcmReponseDetailComponent;
    let fixture: ComponentFixture<QcmReponseDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ qcmReponse: new QcmReponse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmReponseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(QcmReponseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(QcmReponseDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load qcmReponse on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.qcmReponse).toEqual(jasmine.objectContaining({ id: 123 }));
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
