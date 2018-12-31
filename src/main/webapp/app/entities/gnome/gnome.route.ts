import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Gnome } from 'app/shared/model/gnome.model';
import { GnomeService } from './gnome.service';
import { GnomeComponent } from './gnome.component';
import { GnomeDetailComponent } from './gnome-detail.component';
import { GnomeUpdateComponent } from './gnome-update.component';
import { GnomeDeletePopupComponent } from './gnome-delete-dialog.component';
import { IGnome } from 'app/shared/model/gnome.model';

@Injectable({ providedIn: 'root' })
export class GnomeResolve implements Resolve<IGnome> {
    constructor(private service: GnomeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((gnome: HttpResponse<Gnome>) => gnome.body));
        }
        return of(new Gnome());
    }
}

export const gnomeRoute: Routes = [
    {
        path: 'gnome',
        component: GnomeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.gnome.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'gnome/:id/view',
        component: GnomeDetailComponent,
        resolve: {
            gnome: GnomeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.gnome.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'gnome/new',
        component: GnomeUpdateComponent,
        resolve: {
            gnome: GnomeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.gnome.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'gnome/:id/edit',
        component: GnomeUpdateComponent,
        resolve: {
            gnome: GnomeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.gnome.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const gnomePopupRoute: Routes = [
    {
        path: 'gnome/:id/delete',
        component: GnomeDeletePopupComponent,
        resolve: {
            gnome: GnomeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'brastlewarkApp.gnome.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
