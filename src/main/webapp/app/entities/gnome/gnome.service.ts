import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGnome } from 'app/shared/model/gnome.model';

type EntityResponseType = HttpResponse<IGnome>;
type EntityArrayResponseType = HttpResponse<IGnome[]>;

@Injectable({ providedIn: 'root' })
export class GnomeService {
    private resourceUrl = SERVER_API_URL + 'api/gnomes';

    constructor(private http: HttpClient) {}

    create(gnome: IGnome): Observable<EntityResponseType> {
        return this.http.post<IGnome>(this.resourceUrl, gnome, { observe: 'response' });
    }

    update(gnome: IGnome): Observable<EntityResponseType> {
        return this.http.put<IGnome>(this.resourceUrl, gnome, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGnome>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGnome[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    getData(): Observable<EntityArrayResponseType> {
        return this.http.get<IGnome[]>(`${this.resourceUrl}`, { observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
