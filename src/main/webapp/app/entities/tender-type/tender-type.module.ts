import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    TenderTypeComponent,
    TenderTypeDetailComponent,
    TenderTypeUpdateComponent,
    TenderTypeDeletePopupComponent,
    TenderTypeDeleteDialogComponent,
    tenderTypeRoute,
    tenderTypePopupRoute
} from './';

const ENTITY_STATES = [...tenderTypeRoute, ...tenderTypePopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TenderTypeComponent,
        TenderTypeDetailComponent,
        TenderTypeUpdateComponent,
        TenderTypeDeleteDialogComponent,
        TenderTypeDeletePopupComponent
    ],
    entryComponents: [TenderTypeComponent, TenderTypeUpdateComponent, TenderTypeDeleteDialogComponent, TenderTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationTenderTypeModule {}
