import { IItem } from 'app/shared/model//item.model';
import { IItemCategory } from 'app/shared/model//item-category.model';

export interface IItem {
    id?: number;
    articleNr?: number;
    articleName?: string;
    primeCost?: number;
    price?: number;
    availableForSale?: boolean;
    scaleItem?: boolean;
    ean?: string;
    accountRest?: boolean;
    rest?: number;
    lowRest?: number;
    parentItem?: number;
    parentItem?: IItem;
    itemCategory?: IItemCategory;
}

export class Item implements IItem {
    constructor(
        public id?: number,
        public articleNr?: number,
        public articleName?: string,
        public primeCost?: number,
        public price?: number,
        public availableForSale?: boolean,
        public scaleItem?: boolean,
        public ean?: string,
        public accountRest?: boolean,
        public rest?: number,
        public lowRest?: number,
        public parentItem?: number,
        public parentItem?: IItem,
        public itemCategory?: IItemCategory
    ) {
        this.availableForSale = this.availableForSale || false;
        this.scaleItem = this.scaleItem || false;
        this.accountRest = this.accountRest || false;
    }
}
