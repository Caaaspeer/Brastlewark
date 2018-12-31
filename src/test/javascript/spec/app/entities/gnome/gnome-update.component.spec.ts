/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { BrastlewarkTestModule } from '../../../test.module';
import { GnomeUpdateComponent } from 'app/entities/gnome/gnome-update.component';
import { GnomeService } from 'app/entities/gnome/gnome.service';
import { Gnome } from 'app/shared/model/gnome.model';

describe('Component Tests', () => {
    describe('Gnome Management Update Component', () => {
        let comp: GnomeUpdateComponent;
        let fixture: ComponentFixture<GnomeUpdateComponent>;
        let service: GnomeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BrastlewarkTestModule],
                declarations: [GnomeUpdateComponent]
            })
                .overrideTemplate(GnomeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GnomeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GnomeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Gnome(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.gnome = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Gnome();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.gnome = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
