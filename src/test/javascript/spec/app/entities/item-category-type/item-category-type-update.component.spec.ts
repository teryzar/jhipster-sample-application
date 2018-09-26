/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ItemCategoryTypeUpdateComponent } from 'app/entities/item-category-type/item-category-type-update.component';
import { ItemCategoryTypeService } from 'app/entities/item-category-type/item-category-type.service';
import { ItemCategoryType } from 'app/shared/model/item-category-type.model';

describe('Component Tests', () => {
    describe('ItemCategoryType Management Update Component', () => {
        let comp: ItemCategoryTypeUpdateComponent;
        let fixture: ComponentFixture<ItemCategoryTypeUpdateComponent>;
        let service: ItemCategoryTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ItemCategoryTypeUpdateComponent]
            })
                .overrideTemplate(ItemCategoryTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ItemCategoryTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemCategoryTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ItemCategoryType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.itemCategoryType = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ItemCategoryType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.itemCategoryType = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
