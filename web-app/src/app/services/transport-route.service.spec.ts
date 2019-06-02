import { TestBed } from '@angular/core/testing';

import { TransportRouteService } from './transport-route.service';

describe('TransportRouteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TransportRouteService = TestBed.get(TransportRouteService);
    expect(service).toBeTruthy();
  });
});
