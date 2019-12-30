import { ICountry } from 'app/shared/model/country.model';
import { ISocieteAbonne } from 'app/shared/model/societe-abonne.model';

export interface IUtilisateur {
  id?: number;
  lastname?: string;
  firstname?: string;
  email?: string;
  linkedin?: string;
  googleplus?: string;
  role?: string;
  idCountries?: ICountry[];
  idSocietes?: ISocieteAbonne[];
  qcmId?: number;
  qcmTestId?: number;
}

export class Utilisateur implements IUtilisateur {
  constructor(
    public id?: number,
    public lastname?: string,
    public firstname?: string,
    public email?: string,
    public linkedin?: string,
    public googleplus?: string,
    public role?: string,
    public idCountries?: ICountry[],
    public idSocietes?: ISocieteAbonne[],
    public qcmId?: number,
    public qcmTestId?: number
  ) {}
}
