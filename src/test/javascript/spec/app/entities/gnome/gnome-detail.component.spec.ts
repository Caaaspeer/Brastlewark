/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BrastlewarkTestModule } from '../../../test.module';
import { GnomeDetailComponent } from 'app/entities/gnome/gnome-detail.component';
import { Gnome } from 'app/shared/model/gnome.model';

describe('Component Tests', () => {
    describe('Gnome Management Detail Component', () => {
        let comp: GnomeDetailComponent;
        let fixture: ComponentFixture<GnomeDetailComponent>;
        const route = ({ data: of({ gnome: new Gnome(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BrastlewarkTestModule],
                declarations: [GnomeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GnomeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GnomeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.gnome).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
