import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColourFilterComponent } from './colour-filter.component';

describe('ColourFilterComponent', () => {
  let component: ColourFilterComponent;
  let fixture: ComponentFixture<ColourFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ColourFilterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ColourFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
