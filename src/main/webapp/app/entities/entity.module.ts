import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationItemModule } from './item/item.module';
import { JhipsterSampleApplicationItemCategoryModule } from './item-category/item-category.module';
import { JhipsterSampleApplicationItemCategoryTypeModule } from './item-category-type/item-category-type.module';
import { JhipsterSampleApplicationTenderModule } from './tender/tender.module';
import { JhipsterSampleApplicationTenderTypeModule } from './tender-type/tender-type.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterSampleApplicationItemModule,
        JhipsterSampleApplicationItemCategoryModule,
        JhipsterSampleApplicationItemCategoryTypeModule,
        JhipsterSampleApplicationTenderModule,
        JhipsterSampleApplicationTenderTypeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}
