/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ItemCategoryTypeDetailComponent } from 'app/entities/item-category-type/item-category-type-detail.component';
import { ItemCategoryType } from 'app/shared/model/item-category-type.model';

describe('Component Tests', () => {
    describe('ItemCategoryType Management Detail Component', () => {
        let comp: ItemCategoryTypeDetailComponent;
        let fixture: ComponentFixture<ItemCategoryTypeDetailComponent>;
        const route = ({ data: of({ itemCategoryType: new ItemCategoryType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ItemCategoryTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ItemCategoryTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemCategoryTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.itemCategoryType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
