import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import {NavigationComponent} from "./components/navigation/navigation.component";
import {Component} from "@angular/core";
import {RouterTestingModule} from "@angular/router/testing";

@Component({selector: 'router-outlet', template: ''})
class RouterOutletStubComponent { }

describe('AppComponent', () => {

  beforeEach(async(() => {

   TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [AppComponent, NavigationComponent,
        RouterOutletStubComponent]

    }).compileComponents();

  }));

  it('should create the app', async(() => {
      const fixture = TestBed.createComponent(AppComponent);
      const app = fixture.debugElement.componentInstance;
      expect(app).toBeTruthy();
    })
  );

});
