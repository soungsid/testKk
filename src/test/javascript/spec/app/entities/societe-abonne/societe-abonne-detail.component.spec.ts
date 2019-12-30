import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { TestKkTestModule } from '../../../test.module';
import { SocieteAbonneDetailComponent } from 'app/entities/societe-abonne/societe-abonne-detail.component';
import { SocieteAbonne } from 'app/shared/model/societe-abonne.model';

describe('Component Tests', () => {
  describe('SocieteAbonne Management Detail Component', () => {
    let comp: SocieteAbonneDetailComponent;
    let fixture: ComponentFixture<SocieteAbonneDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ societeAbonne: new SocieteAbonne(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [SocieteAbonneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SocieteAbonneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SocieteAbonneDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load societeAbonne on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.societeAbonne).toEqual(jasmine.objectContaining({ id: 123 }));
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
