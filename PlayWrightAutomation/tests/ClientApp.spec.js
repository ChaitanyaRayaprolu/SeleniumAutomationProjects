const {test, expect} = require('@playwright/test');

test ('Client App Login', async ({page})=>
    {
         const products = page.locator(".card-body")         
         const productName = 'ADIDAS ORIGINAL';


         await page.goto("https://rahulshettyacademy.com/client/");
         await page.locator("#userEmail").fill("rahultest24@gmail.com");
         await page.locator("#userPassword").fill("Test@12345");
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

         await page.pause();
    }); 