import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterItemComponent } from './register-item.component';

describe('RegisterItemComponent', () => {
  let component: RegisterItemComponent;
  let fixture: ComponentFixture<RegisterItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterItemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
