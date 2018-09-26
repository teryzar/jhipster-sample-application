import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IItemCategory } from 'app/shared/model/item-category.model';
import { ItemCategoryService } from './item-category.service';
import { IItemCategoryType } from 'app/shared/model/item-category-type.model';
import { ItemCategoryTypeService } from 'app/entities/item-category-type';

@Component({
    selector: 'jhi-item-category-update',
    templateUrl: './item-category-update.component.html'
})
export class ItemCategoryUpdateComponent implements OnInit {
    private _itemCategory: IItemCategory;
    isSaving: boolean;

    itemcategorytypes: IItemCategoryType[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private itemCategoryService: ItemCategoryService,
        private itemCategoryTypeService: ItemCategoryTypeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ itemCategory }) => {
            this.itemCategory = itemCategory;
        });
        this.itemCategoryTypeService.query().subscribe(
            (res: HttpResponse<IItemCategoryType[]>) => {
                this.itemcategorytypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.itemCategory.id !== undefined) {
            this.subscribeToSaveResponse(this.itemCategoryService.update(this.itemCategory));
        } else {
            this.subscribeToSaveResponse(this.itemCategoryService.create(this.itemCategory));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IItemCategory>>) {
        result.subscribe((res: HttpResponse<IItemCategory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackItemCategoryTypeById(index: number, item: IItemCategoryType) {
        return item.id;
    }
    get itemCategory() {
        return this._itemCategory;
    }

    set itemCategory(itemCategory: IItemCategory) {
        this._itemCategory = itemCategory;
    }
}
