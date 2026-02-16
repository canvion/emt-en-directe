import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LineasList } from './lineas-list';

describe('LineasList', () => {
  let component: LineasList;
  let fixture: ComponentFixture<LineasList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LineasList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LineasList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
