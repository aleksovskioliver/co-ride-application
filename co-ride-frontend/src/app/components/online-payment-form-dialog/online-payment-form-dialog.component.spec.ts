import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnlinePaymentFormDialogComponent } from './online-payment-form-dialog.component';

describe('PaymentFormDialogComponent', () => {
  let component: OnlinePaymentFormDialogComponent;
  let fixture: ComponentFixture<OnlinePaymentFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OnlinePaymentFormDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OnlinePaymentFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
