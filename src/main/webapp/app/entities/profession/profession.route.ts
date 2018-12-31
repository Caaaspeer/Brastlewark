import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Profession } from 'app/shared/model/profession.model';
import { ProfessionService } from './profession.service';
import { ProfessionComponent } from './profession.component';
import { ProfessionDetailComponent } from './profession-detail.component';
import { ProfessionUpdateComponent } from './profession-update.component';
import { ProfessionDeletePopupComponent } from './profession-delete-dialog.component';
import { IProfession } from 'app/shared/model/profession.model';

@Injectable({ providedIn: 'root' })
export class ProfessionResolve implements Resolve<IProfession> {
    constructor(private service: ProfessionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((profession: HttpResponse<Profession>) => profession.body));
        }
        return of(new Profession());
    }
}

export const professionRoute: Routes = [
    {
        path: 'profession',
        component: ProfessionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.profession.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profession/:id/view',
        component: ProfessionDetailComponent,
        resolve: {
            profession: ProfessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.profession.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profession/new',
        component: ProfessionUpdateComponent,
        resolve: {
            profession: ProfessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.profession.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profession/:id/edit',
        component: ProfessionUpdateComponent,
        resolve: {
            profession: ProfessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.profession.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const professionPopupRoute: Routes = [
    {
        path: 'profession/:id/delete',
        component: ProfessionDeletePopupComponent,
        resolve: {
            profession: ProfessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.profession.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
