import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITenderType } from 'app/shared/model/tender-type.model';
import { TenderTypeService } from './tender-type.service';

@Component({
    selector: 'jhi-tender-type-update',
    templateUrl: './tender-type-update.component.html'
})
export class TenderTypeUpdateComponent implements OnInit {
    private _tenderType: ITenderType;
    isSaving: boolean;

    constructor(private tenderTypeService: TenderTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tenderType }) => {
            this.tenderType = tenderType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tenderType.id !== undefined) {
            this.subscribeToSaveResponse(this.tenderTypeService.update(this.tenderType));
        } else {
            this.subscribeToSaveResponse(this.tenderTypeService.create(this.tenderType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITenderType>>) {
        result.subscribe((res: HttpResponse<ITenderType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get tenderType() {
        return this._tenderType;
    }

    set tenderType(tenderType: ITenderType) {
        this._tenderType = tenderType;
    }
}
