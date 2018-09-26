import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IItemCategoryType } from 'app/shared/model/item-category-type.model';
import { Principal } from 'app/core';
import { ItemCategoryTypeService } from './item-category-type.service';

@Component({
    selector: 'jhi-item-category-type',
    templateUrl: './item-category-type.component.html'
})
export class ItemCategoryTypeComponent implements OnInit, OnDestroy {
    itemCategoryTypes: IItemCategoryType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private itemCategoryTypeService: ItemCategoryTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.itemCategoryTypeService.query().subscribe(
            (res: HttpResponse<IItemCategoryType[]>) => {
                this.itemCategoryTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInItemCategoryTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IItemCategoryType) {
        return item.id;
    }

    registerChangeInItemCategoryTypes() {
        this.eventSubscriber = this.eventManager.subscribe('itemCategoryTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
