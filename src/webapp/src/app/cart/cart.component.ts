import {Component, OnInit} from '@angular/core';
import {CartDto} from '../dto/cart-dto';
import {CartService} from '../cart.service';
import {Router, RouterEvent} from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
})
export class CartComponent implements OnInit {
  cart: CartDto;

  constructor(private cartService: CartService, private router: Router) {
  }

  ngOnInit() {
    this.getCart();
  }

  updateCart(productId: number, $event) {
    this.cartService.updateItem(productId, $event.target.value).subscribe(
      () => this.getCart(),
      () => this.getCart()
    );
  }

  getCart() {
    this.cartService.getCart().subscribe(value => this.cart = value);
  }

  updateOrder() {
    this.cartService.createOrder(this.cart.email, this.cart.address);
  }

  submitOrder() {
    this.cartService.submitOrder().subscribe(() => this.router.navigate(['/store/products']));
  }
}
