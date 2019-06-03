import {TransportRoute} from './TransportRoute';
import {RaspberryPi} from './RaspberryPi';

export class Vehicle {
  id: number;
  name: string;
  route: TransportRoute;
  pi: RaspberryPi;
}
