import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemCategoryType } from 'app/shared/model/item-category-type.model';

@Component({
    selector: 'jhi-item-category-type-detail',
    templateUrl: './item-category-type-detail.component.html'
})
export class ItemCategoryTypeDetailComponent implements OnInit {
    itemCategoryType: IItemCategoryType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemCategoryType }) => {
            this.itemCategoryType = itemCategoryType;
        });
    }

    previousState() {
        window.history.back();
    }
}
