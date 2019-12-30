import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmTestDetailComponent } from 'app/entities/qcm-test/qcm-test-detail.component';
import { QcmTest } from 'app/shared/model/qcm-test.model';

describe('Component Tests', () => {
  describe('QcmTest Management Detail Component', () => {
    let comp: QcmTestDetailComponent;
    let fixture: ComponentFixture<QcmTestDetailComponent>;
    const route = ({ data: of({ qcmTest: new QcmTest(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmTestDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(QcmTestDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(QcmTestDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load qcmTest on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.qcmTest).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
