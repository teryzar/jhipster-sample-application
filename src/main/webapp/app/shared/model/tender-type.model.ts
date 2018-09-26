import { ITender } from 'app/shared/model//tender.model';

export interface ITenderType {
    id?: number;
    tenderTypes?: ITender[];
}

export class TenderType implements ITenderType {
    constructor(public id?: number, public tenderTypes?: ITender[]) {}
}
