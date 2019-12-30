import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { QcmQuestionService } from 'app/entities/qcm-question/qcm-question.service';
import { IQcmQuestion, QcmQuestion } from 'app/shared/model/qcm-question.model';
import { TypeQuestion } from 'app/shared/model/enumerations/type-question.model';

describe('Service Tests', () => {
  describe('QcmQuestion Service', () => {
    let injector: TestBed;
    let service: QcmQuestionService;
    let httpMock: HttpTestingController;
    let elemDefault: IQcmQuestion;
    let expectedResult: IQcmQuestion | IQcmQuestion[] | boolean | null;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(QcmQuestionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new QcmQuestion(0, 'AAAAAAA', TypeQuestion.YES_NO, 'AAAAAAA');
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

      it('should create a QcmQuestion', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new QcmQuestion())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a QcmQuestion', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            type: 'BBBBBB',
            explication: 'BBBBBB'
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

      it('should return a list of QcmQuestion', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            type: 'BBBBBB',
            explication: 'BBBBBB'
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

      it('should delete a QcmQuestion', () => {
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
