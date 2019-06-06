import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTransportRouteComponent } from './add-transport-route.component';

describe('AddTransportRouteComponent', () => {
  let component: AddTransportRouteComponent;
  let fixture: ComponentFixture<AddTransportRouteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTransportRouteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTransportRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
