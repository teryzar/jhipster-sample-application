import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITenderType } from 'app/shared/model/tender-type.model';
import { TenderTypeService } from './tender-type.service';

@Component({
    selector: 'jhi-tender-type-delete-dialog',
    templateUrl: './tender-type-delete-dialog.component.html'
})
export class TenderTypeDeleteDialogComponent {
    tenderType: ITenderType;

    constructor(private tenderTypeService: TenderTypeService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tenderTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tenderTypeListModification',
                content: 'Deleted an tenderType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tender-type-delete-popup',
    template: ''
})
export class TenderTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tenderType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TenderTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tenderType = tenderType;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
