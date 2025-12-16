import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpandtextComponent } from './expandtext.component';

describe('ExpandtextComponent', () => {
  let component: ExpandtextComponent;
  let fixture: ComponentFixture<ExpandtextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExpandtextComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpandtextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
