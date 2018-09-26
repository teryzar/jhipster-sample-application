/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ItemCategoryTypeComponent } from 'app/entities/item-category-type/item-category-type.component';
import { ItemCategoryTypeService } from 'app/entities/item-category-type/item-category-type.service';
import { ItemCategoryType } from 'app/shared/model/item-category-type.model';

describe('Component Tests', () => {
    describe('ItemCategoryType Management Component', () => {
        let comp: ItemCategoryTypeComponent;
        let fixture: ComponentFixture<ItemCategoryTypeComponent>;
        let service: ItemCategoryTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ItemCategoryTypeComponent],
                providers: []
            })
                .overrideTemplate(ItemCategoryTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ItemCategoryTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemCategoryTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ItemCategoryType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.itemCategoryTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
