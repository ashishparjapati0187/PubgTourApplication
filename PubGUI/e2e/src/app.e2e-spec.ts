import { AppPage } from './app.po';
import { protractor } from 'protractor/built/ptor';
import { browser, by, element } from 'protractor';

import { async } from '@angular/core/testing';



describe('PubGUI', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    

    
  });

  it('should display Title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('PubGUI');
  });

  it("should redirect to login page on running application", ()=> {
    //browser.element(by.css('.register-user')).click()
    expect(browser.getCurrentUrl()).toContain('/login');
});
it("should redirect to register page after login", ()=> {
  browser.element(by.css('.register-button')).click()
    expect(browser.getCurrentUrl()).toContain('/register');
      });

it("should be able to register user", ()=> {
  browser.driver.manage().window().maximize();
  browser.driver.sleep(1000);
  browser.element(by.id('firstName')).sendKeys('ashish');
  browser.element(by.id('lastName')).sendKeys('kumar');
  browser.element(by.id('userId')).sendKeys('736953');
  browser.element(by.id('password')).sendKeys('ashish');
  browser.driver.manage().window().maximize();
  browser.driver.sleep(1000);
  browser.element(by.css('.register-user')).click()
  
        expect(browser.getCurrentUrl()).toContain('/login');
          });

it('should be able login and navigate to tournament page', ()=> {
  browser.driver.manage().window().maximize();
  browser.driver.sleep(1000);
  browser.element(by.css('.userId')).sendKeys('736953');
  browser.element(by.css('.password')).sendKeys('ashish');
  browser.element(by.css('.login-user')).click()
expect(browser.getCurrentUrl()).toContain('/pubg/tournament');
          });

          it('should be able login and navigate to tournament page', ()=> {
            browser.element(by.css('.favListButton')).click()
          expect(browser.getCurrentUrl()).toContain('/pubg/favouriteList');
                    });
          


});
