import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITenderType } from 'app/shared/model/tender-type.model';
import { Principal } from 'app/core';
import { TenderTypeService } from './tender-type.service';

@Component({
    selector: 'jhi-tender-type',
    templateUrl: './tender-type.component.html'
})
export class TenderTypeComponent implements OnInit, OnDestroy {
    tenderTypes: ITenderType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tenderTypeService: TenderTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.tenderTypeService.query().subscribe(
            (res: HttpResponse<ITenderType[]>) => {
                this.tenderTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTenderTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITenderType) {
        return item.id;
    }

    registerChangeInTenderTypes() {
        this.eventSubscriber = this.eventManager.subscribe('tenderTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
