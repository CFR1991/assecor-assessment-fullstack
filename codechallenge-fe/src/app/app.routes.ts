import { Routes } from '@angular/router';
import { PersonCreateComponent } from './features/person-create/person-create.component';
import { PersonOverviewComponent } from './features/person-overview/person-overview.component';
import { PersonDetailsComponent } from './features/person-details/person-details.component';

export const routes: Routes = [
    {
        title: 'Person Overview',
        path: '',
        component: PersonOverviewComponent
    },
    {
        title: 'Add Person',
        path: 'addPerson',
        component: PersonCreateComponent
    },
    {
        title: 'Person Details',
        path: 'personDetails/:id',
        component: PersonDetailsComponent
    }

];
