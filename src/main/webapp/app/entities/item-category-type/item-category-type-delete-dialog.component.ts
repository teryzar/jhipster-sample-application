import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemCategoryType } from 'app/shared/model/item-category-type.model';
import { ItemCategoryTypeService } from './item-category-type.service';

@Component({
    selector: 'jhi-item-category-type-delete-dialog',
    templateUrl: './item-category-type-delete-dialog.component.html'
})
export class ItemCategoryTypeDeleteDialogComponent {
    itemCategoryType: IItemCategoryType;

    constructor(
        private itemCategoryTypeService: ItemCategoryTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemCategoryTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'itemCategoryTypeListModification',
                content: 'Deleted an itemCategoryType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-category-type-delete-popup',
    template: ''
})
export class ItemCategoryTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemCategoryType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ItemCategoryTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.itemCategoryType = itemCategoryType;
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
