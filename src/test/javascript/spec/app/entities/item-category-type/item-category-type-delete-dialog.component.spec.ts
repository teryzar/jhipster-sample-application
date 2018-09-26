/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ItemCategoryTypeDeleteDialogComponent } from 'app/entities/item-category-type/item-category-type-delete-dialog.component';
import { ItemCategoryTypeService } from 'app/entities/item-category-type/item-category-type.service';

describe('Component Tests', () => {
    describe('ItemCategoryType Management Delete Component', () => {
        let comp: ItemCategoryTypeDeleteDialogComponent;
        let fixture: ComponentFixture<ItemCategoryTypeDeleteDialogComponent>;
        let service: ItemCategoryTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ItemCategoryTypeDeleteDialogComponent]
            })
                .overrideTemplate(ItemCategoryTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemCategoryTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemCategoryTypeService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
