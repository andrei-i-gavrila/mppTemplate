import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ProductsComponent} from './products/products.component';
import {CartComponent} from './cart/cart.component';
import {AppRoutingModule} from './app.routing';
import {CartService} from './cart.service';


@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [HttpClientModule, CartService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
