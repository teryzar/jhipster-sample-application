import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITender } from 'app/shared/model/tender.model';
import { TenderService } from './tender.service';
import { ITenderType } from 'app/shared/model/tender-type.model';
import { TenderTypeService } from 'app/entities/tender-type';

@Component({
    selector: 'jhi-tender-update',
    templateUrl: './tender-update.component.html'
})
export class TenderUpdateComponent implements OnInit {
    private _tender: ITender;
    isSaving: boolean;

    tendertypes: ITenderType[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tenderService: TenderService,
        private tenderTypeService: TenderTypeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tender }) => {
            this.tender = tender;
        });
        this.tenderTypeService.query().subscribe(
            (res: HttpResponse<ITenderType[]>) => {
                this.tendertypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tender.id !== undefined) {
            this.subscribeToSaveResponse(this.tenderService.update(this.tender));
        } else {
            this.subscribeToSaveResponse(this.tenderService.create(this.tender));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITender>>) {
        result.subscribe((res: HttpResponse<ITender>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTenderTypeById(index: number, item: ITenderType) {
        return item.id;
    }
    get tender() {
        return this._tender;
    }

    set tender(tender: ITender) {
        this._tender = tender;
    }
}
