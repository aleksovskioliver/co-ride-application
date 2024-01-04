import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentMethodDialogComponent } from './payment-method-dialog.component';

describe('DialogComponent', () => {
  let component: PaymentMethodDialogComponent;
  let fixture: ComponentFixture<PaymentMethodDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentMethodDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentMethodDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
