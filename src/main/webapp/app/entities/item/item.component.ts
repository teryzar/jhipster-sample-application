import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IItem } from 'app/shared/model/item.model';
import { Principal } from 'app/core';
import { ItemService } from './item.service';

@Component({
    selector: 'jhi-item',
    templateUrl: './item.component.html'
})
export class ItemComponent implements OnInit, OnDestroy {
    items: IItem[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private itemService: ItemService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.itemService.query().subscribe(
            (res: HttpResponse<IItem[]>) => {
                this.items = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInItems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IItem) {
        return item.id;
    }

    registerChangeInItems() {
        this.eventSubscriber = this.eventManager.subscribe('itemListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
