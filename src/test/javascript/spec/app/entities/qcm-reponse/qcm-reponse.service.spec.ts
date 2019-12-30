import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { QcmReponseService } from 'app/entities/qcm-reponse/qcm-reponse.service';
import { IQcmReponse, QcmReponse } from 'app/shared/model/qcm-reponse.model';

describe('Service Tests', () => {
  describe('QcmReponse Service', () => {
    let injector: TestBed;
    let service: QcmReponseService;
    let httpMock: HttpTestingController;
    let elemDefault: IQcmReponse;
    let expectedResult: IQcmReponse | IQcmReponse[] | boolean | null;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(QcmReponseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new QcmReponse(0, 'AAAAAAA', 'image/png', 'AAAAAAA', 0, false, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a QcmReponse', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new QcmReponse())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a QcmReponse', () => {
        const returnedFromService = Object.assign(
          {
            reponseText: 'BBBBBB',
            reponseImage: 'BBBBBB',
            reponseNombre: 1,
            correct: true,
            poids: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of QcmReponse', () => {
        const returnedFromService = Object.assign(
          {
            reponseText: 'BBBBBB',
            reponseImage: 'BBBBBB',
            reponseNombre: 1,
            correct: true,
            poids: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a QcmReponse', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
