import {Component, OnInit} from '@angular/core';
import {CartService} from '../cart.service';
import {ProductDto} from '../dto/product-dto';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
})
export class ProductsComponent implements OnInit {

  products: Array<ProductDto>;
  itemCount: number;

  constructor(private cartService: CartService) {
  }

  ngOnInit() {
    this.getItemCount();
    this.getProducts();
  }

  private getProducts() {
    this.cartService.getProducts().subscribe(value => this.products = value);
  }

  private getItemCount() {
    this.cartService.getCartCount().subscribe(value => this.itemCount = value);
  }

  public addToCart(productId: number) {
    this.cartService.addToCart(productId).subscribe(() => this.getItemCount());
  }

}
