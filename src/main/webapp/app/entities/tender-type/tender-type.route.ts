import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TenderType } from 'app/shared/model/tender-type.model';
import { TenderTypeService } from './tender-type.service';
import { TenderTypeComponent } from './tender-type.component';
import { TenderTypeDetailComponent } from './tender-type-detail.component';
import { TenderTypeUpdateComponent } from './tender-type-update.component';
import { TenderTypeDeletePopupComponent } from './tender-type-delete-dialog.component';
import { ITenderType } from 'app/shared/model/tender-type.model';

@Injectable({ providedIn: 'root' })
export class TenderTypeResolve implements Resolve<ITenderType> {
    constructor(private service: TenderTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((tenderType: HttpResponse<TenderType>) => tenderType.body));
        }
        return of(new TenderType());
    }
}

export const tenderTypeRoute: Routes = [
    {
        path: 'tender-type',
        component: TenderTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TenderTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tender-type/:id/view',
        component: TenderTypeDetailComponent,
        resolve: {
            tenderType: TenderTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TenderTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tender-type/new',
        component: TenderTypeUpdateComponent,
        resolve: {
            tenderType: TenderTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TenderTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tender-type/:id/edit',
        component: TenderTypeUpdateComponent,
        resolve: {
            tenderType: TenderTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TenderTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tenderTypePopupRoute: Routes = [
    {
        path: 'tender-type/:id/delete',
        component: TenderTypeDeletePopupComponent,
        resolve: {
            tenderType: TenderTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TenderTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
