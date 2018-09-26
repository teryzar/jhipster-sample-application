import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    ItemCategoryTypeComponent,
    ItemCategoryTypeDetailComponent,
    ItemCategoryTypeUpdateComponent,
    ItemCategoryTypeDeletePopupComponent,
    ItemCategoryTypeDeleteDialogComponent,
    itemCategoryTypeRoute,
    itemCategoryTypePopupRoute
} from './';

const ENTITY_STATES = [...itemCategoryTypeRoute, ...itemCategoryTypePopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ItemCategoryTypeComponent,
        ItemCategoryTypeDetailComponent,
        ItemCategoryTypeUpdateComponent,
        ItemCategoryTypeDeleteDialogComponent,
        ItemCategoryTypeDeletePopupComponent
    ],
    entryComponents: [
        ItemCategoryTypeComponent,
        ItemCategoryTypeUpdateComponent,
        ItemCategoryTypeDeleteDialogComponent,
        ItemCategoryTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationItemCategoryTypeModule {}
