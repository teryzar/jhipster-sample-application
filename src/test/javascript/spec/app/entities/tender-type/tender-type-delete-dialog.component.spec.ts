/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TenderTypeDeleteDialogComponent } from 'app/entities/tender-type/tender-type-delete-dialog.component';
import { TenderTypeService } from 'app/entities/tender-type/tender-type.service';

describe('Component Tests', () => {
    describe('TenderType Management Delete Component', () => {
        let comp: TenderTypeDeleteDialogComponent;
        let fixture: ComponentFixture<TenderTypeDeleteDialogComponent>;
        let service: TenderTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TenderTypeDeleteDialogComponent]
            })
                .overrideTemplate(TenderTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TenderTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TenderTypeService);
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
