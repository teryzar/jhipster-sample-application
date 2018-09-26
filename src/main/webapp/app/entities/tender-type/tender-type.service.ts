import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITenderType } from 'app/shared/model/tender-type.model';

type EntityResponseType = HttpResponse<ITenderType>;
type EntityArrayResponseType = HttpResponse<ITenderType[]>;

@Injectable({ providedIn: 'root' })
export class TenderTypeService {
    private resourceUrl = SERVER_API_URL + 'api/tender-types';

    constructor(private http: HttpClient) {}

    create(tenderType: ITenderType): Observable<EntityResponseType> {
        return this.http.post<ITenderType>(this.resourceUrl, tenderType, { observe: 'response' });
    }

    update(tenderType: ITenderType): Observable<EntityResponseType> {
        return this.http.put<ITenderType>(this.resourceUrl, tenderType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITenderType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITenderType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
