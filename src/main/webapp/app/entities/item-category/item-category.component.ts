import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IItemCategory } from 'app/shared/model/item-category.model';
import { Principal } from 'app/core';
import { ItemCategoryService } from './item-category.service';

@Component({
    selector: 'jhi-item-category',
    templateUrl: './item-category.component.html'
})
export class ItemCategoryComponent implements OnInit, OnDestroy {
    itemCategories: IItemCategory[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private itemCategoryService: ItemCategoryService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.itemCategoryService.query().subscribe(
            (res: HttpResponse<IItemCategory[]>) => {
                this.itemCategories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInItemCategories();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IItemCategory) {
        return item.id;
    }

    registerChangeInItemCategories() {
        this.eventSubscriber = this.eventManager.subscribe('itemCategoryListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
