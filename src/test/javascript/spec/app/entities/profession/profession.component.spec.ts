/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BrastlewarkTestModule } from '../../../test.module';
import { ProfessionComponent } from 'app/entities/profession/profession.component';
import { ProfessionService } from 'app/entities/profession/profession.service';
import { Profession } from 'app/shared/model/profession.model';

describe('Component Tests', () => {
    describe('Profession Management Component', () => {
        let comp: ProfessionComponent;
        let fixture: ComponentFixture<ProfessionComponent>;
        let service: ProfessionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BrastlewarkTestModule],
                declarations: [ProfessionComponent],
                providers: []
            })
                .overrideTemplate(ProfessionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfessionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfessionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Profession(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.professions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
