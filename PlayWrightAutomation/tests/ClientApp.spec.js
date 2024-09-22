const {test, expect} = require('@playwright/test');

test ('Client App Login', async ({page})=>
    {
         const email = "rahultest24@gmail.com";
         const password = "Test@12345";
         const products = page.locator(".card-body")         
         const productName = 'ADIDAS ORIGINAL';


         await page.goto("https://rahulshettyacademy.com/client/");
         await page.locator("#userEmail").fill(email);
         await page.locator("#userPassword").fill(password);
         await page.locator("#login").click();
         await page.waitForLoadState("networkidle");  //kind of wait
         //await page.locator(".card-body b").first().waitFor();
         const titles =  await page.locator(".card-body b").allTextContents();
         const count = await products.count();
         for (let i=0; i<count; ++i)
         {
               if(await products.nth(i).locator("b").textContent() === productName){
                    await products.nth(i).locator("text= Add To Cart").click(); //locator based on text
                    break;
               }
         }
         await page.locator("[routerlink*='cart']").click();
         await page.locator("div li").first().waitFor();  //kind of wait
         const bln = await page.locator("h3:has-text('ADIDAS ORIGINAL')").isVisible(); //locator based on text
         expect(bln).toBeTruthy(); //assertion
         await page.locator("text=Checkout").click();
         await page.locator("[placeholder*='Country']").pressSequentially("ind",{delay:100});
         const dropDown = page.locator(".ta-results");
         await dropDown.waitFor();         
         const optionsCount = await dropDown.locator("button").count();

         for(let i=0; i<optionsCount; ++i)
          {
               const text = await dropDown.locator("button").nth(i).textContent();
               if(text === " India"){
                    await dropDown.locator("button").nth(i).click();
                    break;
               }
         }

         await page.locator(".field input").nth(1).fill("123");
         await page.locator(".field input").nth(2).fill("Rahul Shetty");
         await page.locator(".field input").nth(3).fill("rahulshettyacademy");
         await page.locator(".btn.btn.btn-primary.mt-1").click();
         await page.waitForLoadState("networkidle");
         await page.locator("p:has-text('* Coupon Applied')").waitFor();
         const bln1 = await page.locator("p:has-text('* Coupon Applied')").isVisible();
         expect(bln1).toBeTruthy();
         expect(page.locator(".user__name [type='text']").first()).toHaveText(email);         
         await page.locator(".action__submit").click();
         await expect (page.locator(".hero-primary")).toHaveText(" Thankyou for the order. ");
         const orderId =  await page.locator(".em-spacer-1 .ng-star-inserted").textContent();
     //     const  trimOrderId = orderId.match(/\| (\w+) \|/)[1];
         await page.locator("button[routerlink*='/dashboard/myorders']").click();
         await page.locator("tbody").waitFor();
         const rows = await page.locator("tbody tr");
         for(let i=0; i<await rows.count(); ++i)
          {
               const rowOrderId = await rows.nth(i).locator("th").textContent();
               if(orderId.includes(rowOrderId)){
                    await rows.nth(i).locator("button").first().click();
                    break;
               }
         }
         const orderIdDetails= await page.locator(".col-text.-main").textContent();
         expect (orderId.includes(orderIdDetails)).toBeTruthy();
         

    }); 