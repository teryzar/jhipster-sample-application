import { IItem } from 'app/shared/model//item.model';
import { IItemCategoryType } from 'app/shared/model//item-category-type.model';

export interface IItemCategory {
    id?: number;
    name?: string;
    description?: string;
    itemCategories?: IItem[];
    itemCategoryType?: IItemCategoryType;
}

export class ItemCategory implements IItemCategory {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public itemCategories?: IItem[],
        public itemCategoryType?: IItemCategoryType
    ) {}
}
