import { TestBed } from '@angular/core/testing';

import { UserServices } from './user.service';

describe('UserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserServices = TestBed.get(UserServices);
    expect(service).toBeTruthy();
  });
});
