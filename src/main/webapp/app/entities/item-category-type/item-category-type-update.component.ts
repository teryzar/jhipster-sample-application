import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IItemCategoryType } from 'app/shared/model/item-category-type.model';
import { ItemCategoryTypeService } from './item-category-type.service';

@Component({
    selector: 'jhi-item-category-type-update',
    templateUrl: './item-category-type-update.component.html'
})
export class ItemCategoryTypeUpdateComponent implements OnInit {
    private _itemCategoryType: IItemCategoryType;
    isSaving: boolean;

    constructor(private itemCategoryTypeService: ItemCategoryTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ itemCategoryType }) => {
            this.itemCategoryType = itemCategoryType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.itemCategoryType.id !== undefined) {
            this.subscribeToSaveResponse(this.itemCategoryTypeService.update(this.itemCategoryType));
        } else {
            this.subscribeToSaveResponse(this.itemCategoryTypeService.create(this.itemCategoryType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IItemCategoryType>>) {
        result.subscribe((res: HttpResponse<IItemCategoryType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get itemCategoryType() {
        return this._itemCategoryType;
    }

    set itemCategoryType(itemCategoryType: IItemCategoryType) {
        this._itemCategoryType = itemCategoryType;
    }
}
