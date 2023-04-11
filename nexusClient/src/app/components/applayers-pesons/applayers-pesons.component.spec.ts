import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplayersPesonsComponent } from './applayers-pesons.component';

describe('ApplayersPesonsComponent', () => {
  let component: ApplayersPesonsComponent;
  let fixture: ComponentFixture<ApplayersPesonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApplayersPesonsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApplayersPesonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
