import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {environment} from '../environments/environment';
import {CartDto} from './dto/cart-dto';
import {ProductDto} from './dto/product-dto';

@Injectable()
export class CartService {

  constructor(private httpClient: HttpClient) {
  }


  public getCartCount(): Observable<number> {
    return this.httpClient.get<number>(environment.baseUrl + '/cart/count');
  }

  public getCart(): Observable<CartDto> {
    return this.httpClient.get<CartDto>(environment.baseUrl + '/cart');
  }


  public getProducts(): Observable<Array<ProductDto>> {
    return this.httpClient.get<Array<ProductDto>>(environment.baseUrl + '/products');
  }

  public addToCart(productId: number): Observable<void> {
    return this.httpClient.post<void>(environment.baseUrl + '/add', {
      productId: productId
    });
  }

  public updateItem(productId: number, quantity: number): Observable<void> {
    return this.httpClient.patch<void>(environment.baseUrl + '/update', {
      productId: productId.toString(),
      quantity: quantity.toString()
    });
  }

  public createOrder(email: string, address: string): Observable<void> {
    return this.httpClient.post<void>(environment.baseUrl + '/order', {
      email: email,
      address: address
    });
  }


  public submitOrder(): Observable<void> {
    return this.httpClient.post<void>(environment.baseUrl + '/submit', {});
  }
}
