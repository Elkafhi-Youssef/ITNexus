import { TestBed } from '@angular/core/testing';

import { OfferByIdService } from './offer-by-id.service';

describe('OfferByIdService', () => {
  let service: OfferByIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OfferByIdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
