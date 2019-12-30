import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmTestResponseDetailComponent } from 'app/entities/qcm-test-response/qcm-test-response-detail.component';
import { QcmTestResponse } from 'app/shared/model/qcm-test-response.model';

describe('Component Tests', () => {
  describe('QcmTestResponse Management Detail Component', () => {
    let comp: QcmTestResponseDetailComponent;
    let fixture: ComponentFixture<QcmTestResponseDetailComponent>;
    const route = ({ data: of({ qcmTestResponse: new QcmTestResponse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmTestResponseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(QcmTestResponseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(QcmTestResponseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load qcmTestResponse on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.qcmTestResponse).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
