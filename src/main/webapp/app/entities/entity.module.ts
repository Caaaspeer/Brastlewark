import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BrastlewarkGnomeModule } from './gnome/gnome.module';
import { BrastlewarkProfessionModule } from './profession/profession.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        BrastlewarkGnomeModule,
        BrastlewarkProfessionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BrastlewarkEntityModule {}
