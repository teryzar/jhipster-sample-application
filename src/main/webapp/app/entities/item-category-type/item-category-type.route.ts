import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ItemCategoryType } from 'app/shared/model/item-category-type.model';
import { ItemCategoryTypeService } from './item-category-type.service';
import { ItemCategoryTypeComponent } from './item-category-type.component';
import { ItemCategoryTypeDetailComponent } from './item-category-type-detail.component';
import { ItemCategoryTypeUpdateComponent } from './item-category-type-update.component';
import { ItemCategoryTypeDeletePopupComponent } from './item-category-type-delete-dialog.component';
import { IItemCategoryType } from 'app/shared/model/item-category-type.model';

@Injectable({ providedIn: 'root' })
export class ItemCategoryTypeResolve implements Resolve<IItemCategoryType> {
    constructor(private service: ItemCategoryTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((itemCategoryType: HttpResponse<ItemCategoryType>) => itemCategoryType.body));
        }
        return of(new ItemCategoryType());
    }
}

export const itemCategoryTypeRoute: Routes = [
    {
        path: 'item-category-type',
        component: ItemCategoryTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategoryTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-category-type/:id/view',
        component: ItemCategoryTypeDetailComponent,
        resolve: {
            itemCategoryType: ItemCategoryTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategoryTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-category-type/new',
        component: ItemCategoryTypeUpdateComponent,
        resolve: {
            itemCategoryType: ItemCategoryTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategoryTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-category-type/:id/edit',
        component: ItemCategoryTypeUpdateComponent,
        resolve: {
            itemCategoryType: ItemCategoryTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategoryTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemCategoryTypePopupRoute: Routes = [
    {
        path: 'item-category-type/:id/delete',
        component: ItemCategoryTypeDeletePopupComponent,
        resolve: {
            itemCategoryType: ItemCategoryTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategoryTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
