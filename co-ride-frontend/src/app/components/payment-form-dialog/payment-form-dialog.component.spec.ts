import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentFormDialogComponent } from './payment-form-dialog.component';

describe('PaymentFormDialogComponent', () => {
  let component: PaymentFormDialogComponent;
  let fixture: ComponentFixture<PaymentFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentFormDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
