import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KweetHomepageComponent } from './kweet-homepage.component';

describe('KweetHomepageComponent', () => {
  let component: KweetHomepageComponent;
  let fixture: ComponentFixture<KweetHomepageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KweetHomepageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KweetHomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
