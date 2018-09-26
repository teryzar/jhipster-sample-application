import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    ItemComponent,
    ItemDetailComponent,
    ItemUpdateComponent,
    ItemDeletePopupComponent,
    ItemDeleteDialogComponent,
    itemRoute,
    itemPopupRoute
} from './';

const ENTITY_STATES = [...itemRoute, ...itemPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ItemComponent, ItemDetailComponent, ItemUpdateComponent, ItemDeleteDialogComponent, ItemDeletePopupComponent],
    entryComponents: [ItemComponent, ItemUpdateComponent, ItemDeleteDialogComponent, ItemDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationItemModule {}
