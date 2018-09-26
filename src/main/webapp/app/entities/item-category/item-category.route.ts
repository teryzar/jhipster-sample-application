import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ItemCategory } from 'app/shared/model/item-category.model';
import { ItemCategoryService } from './item-category.service';
import { ItemCategoryComponent } from './item-category.component';
import { ItemCategoryDetailComponent } from './item-category-detail.component';
import { ItemCategoryUpdateComponent } from './item-category-update.component';
import { ItemCategoryDeletePopupComponent } from './item-category-delete-dialog.component';
import { IItemCategory } from 'app/shared/model/item-category.model';

@Injectable({ providedIn: 'root' })
export class ItemCategoryResolve implements Resolve<IItemCategory> {
    constructor(private service: ItemCategoryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((itemCategory: HttpResponse<ItemCategory>) => itemCategory.body));
        }
        return of(new ItemCategory());
    }
}

export const itemCategoryRoute: Routes = [
    {
        path: 'item-category',
        component: ItemCategoryComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategories'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-category/:id/view',
        component: ItemCategoryDetailComponent,
        resolve: {
            itemCategory: ItemCategoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategories'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-category/new',
        component: ItemCategoryUpdateComponent,
        resolve: {
            itemCategory: ItemCategoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategories'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-category/:id/edit',
        component: ItemCategoryUpdateComponent,
        resolve: {
            itemCategory: ItemCategoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategories'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemCategoryPopupRoute: Routes = [
    {
        path: 'item-category/:id/delete',
        component: ItemCategoryDeletePopupComponent,
        resolve: {
            itemCategory: ItemCategoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCategories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
