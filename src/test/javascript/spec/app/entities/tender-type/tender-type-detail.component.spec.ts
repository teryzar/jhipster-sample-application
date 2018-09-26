/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TenderTypeDetailComponent } from 'app/entities/tender-type/tender-type-detail.component';
import { TenderType } from 'app/shared/model/tender-type.model';

describe('Component Tests', () => {
    describe('TenderType Management Detail Component', () => {
        let comp: TenderTypeDetailComponent;
        let fixture: ComponentFixture<TenderTypeDetailComponent>;
        const route = ({ data: of({ tenderType: new TenderType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TenderTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TenderTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TenderTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tenderType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
