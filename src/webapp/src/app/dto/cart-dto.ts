import {CartProductDto} from './cart-product-dto';

export class CartDto {
  public itemCount: number;

  public products: Array<CartProductDto>;

  public email: string;

  public address: string;
}
