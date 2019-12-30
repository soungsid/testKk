import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SocieteAbonneService } from 'app/entities/societe-abonne/societe-abonne.service';
import { ISocieteAbonne, SocieteAbonne } from 'app/shared/model/societe-abonne.model';

describe('Service Tests', () => {
  describe('SocieteAbonne Service', () => {
    let injector: TestBed;
    let service: SocieteAbonneService;
    let httpMock: HttpTestingController;
    let elemDefault: ISocieteAbonne;
    let expectedResult: ISocieteAbonne | ISocieteAbonne[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SocieteAbonneService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SocieteAbonne(0, 'AAAAAAA', currentDate, 'image/png', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateAbonnement: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SocieteAbonne', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateAbonnement: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateAbonnement: currentDate
          },
          returnedFromService
        );
        service
          .create(new SocieteAbonne())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SocieteAbonne', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            dateAbonnement: currentDate.format(DATE_TIME_FORMAT),
            logo: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAbonnement: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SocieteAbonne', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            dateAbonnement: currentDate.format(DATE_TIME_FORMAT),
            logo: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateAbonnement: currentDate
          },
          returnedFromService
        );
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

      it('should delete a SocieteAbonne', () => {
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
