import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    ItemCategoryComponent,
    ItemCategoryDetailComponent,
    ItemCategoryUpdateComponent,
    ItemCategoryDeletePopupComponent,
    ItemCategoryDeleteDialogComponent,
    itemCategoryRoute,
    itemCategoryPopupRoute
} from './';

const ENTITY_STATES = [...itemCategoryRoute, ...itemCategoryPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ItemCategoryComponent,
        ItemCategoryDetailComponent,
        ItemCategoryUpdateComponent,
        ItemCategoryDeleteDialogComponent,
        ItemCategoryDeletePopupComponent
    ],
    entryComponents: [
        ItemCategoryComponent,
        ItemCategoryUpdateComponent,
        ItemCategoryDeleteDialogComponent,
        ItemCategoryDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationItemCategoryModule {}
