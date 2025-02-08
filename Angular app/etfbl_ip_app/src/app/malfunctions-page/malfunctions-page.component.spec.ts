import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MalfunctionsPageComponent } from './malfunctions-page.component';

describe('MalfunctionsPageComponent', () => {
  let component: MalfunctionsPageComponent;
  let fixture: ComponentFixture<MalfunctionsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MalfunctionsPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MalfunctionsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
