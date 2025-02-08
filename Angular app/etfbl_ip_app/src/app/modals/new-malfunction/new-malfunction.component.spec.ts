import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMalfunctionComponent } from './new-malfunction.component';

describe('NewMalfunctionComponent', () => {
  let component: NewMalfunctionComponent;
  let fixture: ComponentFixture<NewMalfunctionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NewMalfunctionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NewMalfunctionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
