import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestKkTestModule } from '../../../test.module';
import { QcmDetailComponent } from 'app/entities/qcm/qcm-detail.component';
import { Qcm } from 'app/shared/model/qcm.model';

describe('Component Tests', () => {
  describe('Qcm Management Detail Component', () => {
    let comp: QcmDetailComponent;
    let fixture: ComponentFixture<QcmDetailComponent>;
    const route = ({ data: of({ qcm: new Qcm(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestKkTestModule],
        declarations: [QcmDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(QcmDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(QcmDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load qcm on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.qcm).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
