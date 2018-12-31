import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGnome } from 'app/shared/model/gnome.model';

@Component({
    selector: 'jhi-gnome-detail',
    templateUrl: './gnome-detail.component.html'
})
export class GnomeDetailComponent implements OnInit {
    gnome: IGnome;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gnome }) => {
            this.gnome = gnome;
        });
    }

    previousState() {
        window.history.back();
    }
}
