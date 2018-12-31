import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IProfession } from 'app/shared/model/profession.model';
import { ProfessionService } from './profession.service';

@Component({
    selector: 'jhi-profession-update',
    templateUrl: './profession-update.component.html'
})
export class ProfessionUpdateComponent implements OnInit {
    private _profession: IProfession;
    isSaving: boolean;

    constructor(private professionService: ProfessionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profession }) => {
            this.profession = profession;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.profession.id !== undefined) {
            this.subscribeToSaveResponse(this.professionService.update(this.profession));
        } else {
            this.subscribeToSaveResponse(this.professionService.create(this.profession));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProfession>>) {
        result.subscribe((res: HttpResponse<IProfession>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get profession() {
        return this._profession;
    }

    set profession(profession: IProfession) {
        this._profession = profession;
    }
}
