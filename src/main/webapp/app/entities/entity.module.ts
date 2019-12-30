import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'tag',
        loadChildren: () => import('./tag/tag.module').then(m => m.TestKkTagModule)
      },
      {
        path: 'categorie',
        loadChildren: () => import('./categorie/categorie.module').then(m => m.TestKkCategorieModule)
      },
      {
        path: 'qcm',
        loadChildren: () => import('./qcm/qcm.module').then(m => m.TestKkQcmModule)
      },
      {
        path: 'qcm-question',
        loadChildren: () => import('./qcm-question/qcm-question.module').then(m => m.TestKkQcmQuestionModule)
      },
      {
        path: 'qcm-question-tag',
        loadChildren: () => import('./qcm-question-tag/qcm-question-tag.module').then(m => m.TestKkQcmQuestionTagModule)
      },
      {
        path: 'qcm-reponse',
        loadChildren: () => import('./qcm-reponse/qcm-reponse.module').then(m => m.TestKkQcmReponseModule)
      },
      {
        path: 'qcm-test',
        loadChildren: () => import('./qcm-test/qcm-test.module').then(m => m.TestKkQcmTestModule)
      },
      {
        path: 'qcm-test-response',
        loadChildren: () => import('./qcm-test-response/qcm-test-response.module').then(m => m.TestKkQcmTestResponseModule)
      },
      {
        path: 'utilisateur',
        loadChildren: () => import('./utilisateur/utilisateur.module').then(m => m.TestKkUtilisateurModule)
      },
      {
        path: 'societe-abonne',
        loadChildren: () => import('./societe-abonne/societe-abonne.module').then(m => m.TestKkSocieteAbonneModule)
      },
      {
        path: 'carnet-adresse',
        loadChildren: () => import('./carnet-adresse/carnet-adresse.module').then(m => m.TestKkCarnetAdresseModule)
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.TestKkCountryModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class TestKkEntityModule {}
