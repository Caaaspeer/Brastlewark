import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BrastlewarkSharedModule } from 'app/shared';
import {
    GnomeComponent,
    GnomeDetailComponent,
    GnomeUpdateComponent,
    GnomeDeletePopupComponent,
    GnomeDeleteDialogComponent,
    gnomeRoute,
    gnomePopupRoute
} from './';

const ENTITY_STATES = [...gnomeRoute, ...gnomePopupRoute];

@NgModule({
    imports: [BrastlewarkSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [GnomeComponent, GnomeDetailComponent, GnomeUpdateComponent, GnomeDeleteDialogComponent, GnomeDeletePopupComponent],
    entryComponents: [GnomeComponent, GnomeUpdateComponent, GnomeDeleteDialogComponent, GnomeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BrastlewarkGnomeModule {}
