import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IGnome } from 'app/shared/model/gnome.model';
import { GnomeService } from './gnome.service';

@Component({
    selector: 'jhi-gnome-update',
    templateUrl: './gnome-update.component.html'
})
export class GnomeUpdateComponent implements OnInit {
    private _gnome: IGnome;
    isSaving: boolean;

    constructor(private gnomeService: GnomeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ gnome }) => {
            this.gnome = gnome;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.gnome.id !== undefined) {
            this.subscribeToSaveResponse(this.gnomeService.update(this.gnome));
        } else {
            this.subscribeToSaveResponse(this.gnomeService.create(this.gnome));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGnome>>) {
        result.subscribe((res: HttpResponse<IGnome>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get gnome() {
        return this._gnome;
    }

    set gnome(gnome: IGnome) {
        this._gnome = gnome;
    }
}
