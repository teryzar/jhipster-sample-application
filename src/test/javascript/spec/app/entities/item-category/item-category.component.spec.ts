/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ItemCategoryComponent } from 'app/entities/item-category/item-category.component';
import { ItemCategoryService } from 'app/entities/item-category/item-category.service';
import { ItemCategory } from 'app/shared/model/item-category.model';

describe('Component Tests', () => {
    describe('ItemCategory Management Component', () => {
        let comp: ItemCategoryComponent;
        let fixture: ComponentFixture<ItemCategoryComponent>;
        let service: ItemCategoryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ItemCategoryComponent],
                providers: []
            })
                .overrideTemplate(ItemCategoryComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ItemCategoryComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemCategoryService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ItemCategory(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.itemCategories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
