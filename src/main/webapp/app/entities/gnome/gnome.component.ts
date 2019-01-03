import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IGnome } from 'app/shared/model/gnome.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { GnomeService } from './gnome.service';
import { Filter } from './filter.model';

@Component({
    selector: 'jhi-gnome',
    templateUrl: './gnome.component.html'
})
export class GnomeComponent implements OnInit, OnDestroy {
    gnomes: IGnome[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    queryCount: any;
    reverse: any;
    totalItems: number;
    filter: Filter = new Filter();

    constructor(
        private gnomeService: GnomeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private parseLinks: JhiParseLinks,
        private principal: Principal
    ) {}

    loadAll() {
        this.gnomeService.getData().subscribe(res => {
            this.gnomes = res;
        });
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGnome) {
        return item.id;
    }
}
