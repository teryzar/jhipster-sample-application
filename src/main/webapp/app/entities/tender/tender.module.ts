import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    TenderComponent,
    TenderDetailComponent,
    TenderUpdateComponent,
    TenderDeletePopupComponent,
    TenderDeleteDialogComponent,
    tenderRoute,
    tenderPopupRoute
} from './';

const ENTITY_STATES = [...tenderRoute, ...tenderPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [TenderComponent, TenderDetailComponent, TenderUpdateComponent, TenderDeleteDialogComponent, TenderDeletePopupComponent],
    entryComponents: [TenderComponent, TenderUpdateComponent, TenderDeleteDialogComponent, TenderDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationTenderModule {}
