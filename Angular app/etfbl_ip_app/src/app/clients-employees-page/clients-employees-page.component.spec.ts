import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientsEmployeesPageComponent } from './clients-employees-page.component';

describe('ClientsEmployeesPageComponent', () => {
  let component: ClientsEmployeesPageComponent;
  let fixture: ComponentFixture<ClientsEmployeesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClientsEmployeesPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClientsEmployeesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
