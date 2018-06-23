import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {ProductsComponent} from './products/products.component';
import {CartComponent} from './cart/cart.component';

const routes: Routes = [
  {path: 'store/products', component: ProductsComponent},
  {path: 'store/cart', component: CartComponent},
  {path: '', redirectTo: '/store/products', pathMatch: 'full'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
