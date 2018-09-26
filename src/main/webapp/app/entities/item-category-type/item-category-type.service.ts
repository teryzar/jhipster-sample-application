import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IItemCategoryType } from 'app/shared/model/item-category-type.model';

type EntityResponseType = HttpResponse<IItemCategoryType>;
type EntityArrayResponseType = HttpResponse<IItemCategoryType[]>;

@Injectable({ providedIn: 'root' })
export class ItemCategoryTypeService {
    private resourceUrl = SERVER_API_URL + 'api/item-category-types';

    constructor(private http: HttpClient) {}

    create(itemCategoryType: IItemCategoryType): Observable<EntityResponseType> {
        return this.http.post<IItemCategoryType>(this.resourceUrl, itemCategoryType, { observe: 'response' });
    }

    update(itemCategoryType: IItemCategoryType): Observable<EntityResponseType> {
        return this.http.put<IItemCategoryType>(this.resourceUrl, itemCategoryType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IItemCategoryType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IItemCategoryType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
