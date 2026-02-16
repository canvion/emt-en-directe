import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoritosList } from './favoritos-list';

describe('FavoritosList', () => {
  let component: FavoritosList;
  let fixture: ComponentFixture<FavoritosList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FavoritosList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FavoritosList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
