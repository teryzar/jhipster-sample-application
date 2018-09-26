import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Tender } from 'app/shared/model/tender.model';
import { TenderService } from './tender.service';
import { TenderComponent } from './tender.component';
import { TenderDetailComponent } from './tender-detail.component';
import { TenderUpdateComponent } from './tender-update.component';
import { TenderDeletePopupComponent } from './tender-delete-dialog.component';
import { ITender } from 'app/shared/model/tender.model';

@Injectable({ providedIn: 'root' })
export class TenderResolve implements Resolve<ITender> {
    constructor(private service: TenderService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((tender: HttpResponse<Tender>) => tender.body));
        }
        return of(new Tender());
    }
}

export const tenderRoute: Routes = [
    {
        path: 'tender',
        component: TenderComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenders'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tender/:id/view',
        component: TenderDetailComponent,
        resolve: {
            tender: TenderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenders'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tender/new',
        component: TenderUpdateComponent,
        resolve: {
            tender: TenderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenders'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tender/:id/edit',
        component: TenderUpdateComponent,
        resolve: {
            tender: TenderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenders'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tenderPopupRoute: Routes = [
    {
        path: 'tender/:id/delete',
        component: TenderDeletePopupComponent,
        resolve: {
            tender: TenderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
