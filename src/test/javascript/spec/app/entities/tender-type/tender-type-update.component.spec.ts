/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TenderTypeUpdateComponent } from 'app/entities/tender-type/tender-type-update.component';
import { TenderTypeService } from 'app/entities/tender-type/tender-type.service';
import { TenderType } from 'app/shared/model/tender-type.model';

describe('Component Tests', () => {
    describe('TenderType Management Update Component', () => {
        let comp: TenderTypeUpdateComponent;
        let fixture: ComponentFixture<TenderTypeUpdateComponent>;
        let service: TenderTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TenderTypeUpdateComponent]
            })
                .overrideTemplate(TenderTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TenderTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TenderTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TenderType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tenderType = entity;
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
                    const entity = new TenderType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tenderType = entity;
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
