/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TenderTypeComponent } from 'app/entities/tender-type/tender-type.component';
import { TenderTypeService } from 'app/entities/tender-type/tender-type.service';
import { TenderType } from 'app/shared/model/tender-type.model';

describe('Component Tests', () => {
    describe('TenderType Management Component', () => {
        let comp: TenderTypeComponent;
        let fixture: ComponentFixture<TenderTypeComponent>;
        let service: TenderTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TenderTypeComponent],
                providers: []
            })
                .overrideTemplate(TenderTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TenderTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TenderTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TenderType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.tenderTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
