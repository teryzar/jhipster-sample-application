import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IItemCategory } from 'app/shared/model/item-category.model';

type EntityResponseType = HttpResponse<IItemCategory>;
type EntityArrayResponseType = HttpResponse<IItemCategory[]>;

@Injectable({ providedIn: 'root' })
export class ItemCategoryService {
    private resourceUrl = SERVER_API_URL + 'api/item-categories';

    constructor(private http: HttpClient) {}

    create(itemCategory: IItemCategory): Observable<EntityResponseType> {
        return this.http.post<IItemCategory>(this.resourceUrl, itemCategory, { observe: 'response' });
    }

    update(itemCategory: IItemCategory): Observable<EntityResponseType> {
        return this.http.put<IItemCategory>(this.resourceUrl, itemCategory, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IItemCategory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IItemCategory[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
