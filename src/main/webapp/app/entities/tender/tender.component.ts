import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITender } from 'app/shared/model/tender.model';
import { Principal } from 'app/core';
import { TenderService } from './tender.service';

@Component({
    selector: 'jhi-tender',
    templateUrl: './tender.component.html'
})
export class TenderComponent implements OnInit, OnDestroy {
    tenders: ITender[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tenderService: TenderService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.tenderService.query().subscribe(
            (res: HttpResponse<ITender[]>) => {
                this.tenders = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTenders();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITender) {
        return item.id;
    }

    registerChangeInTenders() {
        this.eventSubscriber = this.eventManager.subscribe('tenderListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
