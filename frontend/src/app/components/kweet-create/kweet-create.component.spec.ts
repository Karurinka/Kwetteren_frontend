import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KweetCreateComponent } from './kweet-create.component';

describe('KweetCreateComponent', () => {
  let component: KweetCreateComponent;
  let fixture: ComponentFixture<KweetCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KweetCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KweetCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
