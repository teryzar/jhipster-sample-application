import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITenderType } from 'app/shared/model/tender-type.model';

@Component({
    selector: 'jhi-tender-type-detail',
    templateUrl: './tender-type-detail.component.html'
})
export class TenderTypeDetailComponent implements OnInit {
    tenderType: ITenderType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tenderType }) => {
            this.tenderType = tenderType;
        });
    }

    previousState() {
        window.history.back();
    }
}
