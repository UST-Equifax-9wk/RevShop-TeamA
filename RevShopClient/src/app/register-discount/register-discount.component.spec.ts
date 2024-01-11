import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterDiscountComponent } from './register-discount.component';

describe('RegisterDiscountComponent', () => {
  let component: RegisterDiscountComponent;
  let fixture: ComponentFixture<RegisterDiscountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterDiscountComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterDiscountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
