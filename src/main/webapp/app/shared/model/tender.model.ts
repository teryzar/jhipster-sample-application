import { ITenderType } from 'app/shared/model//tender-type.model';

export interface ITender {
    id?: number;
    tenderType?: ITenderType;
}

export class Tender implements ITender {
    constructor(public id?: number, public tenderType?: ITenderType) {}
}
