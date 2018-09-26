import { IItemCategory } from 'app/shared/model//item-category.model';

export interface IItemCategoryType {
    id?: number;
    name?: string;
    description?: string;
    categories?: IItemCategory[];
}

export class ItemCategoryType implements IItemCategoryType {
    constructor(public id?: number, public name?: string, public description?: string, public categories?: IItemCategory[]) {}
}
