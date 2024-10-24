const {test, expect} = require('@playwright/test');

test ('Client App Login', async ({page})=>
    {
         const email = "rahultest24@gmail.com";
         const password = "Test@12345";
         const products = page.locator(".card-body")         
         const productName = 'ADIDAS ORIGINAL';


         await page.goto("https://rahulshettyacademy.com/client/");
         await page.getByPlaceholder("email@example.com").fill(email);
         await page.getByPlaceholder("enter your passsword").fill(password);
         await page.getByRole("button", {name: 'login'}).click();
         await page.waitForLoadState('networkidle');  //kind of wait
         await page.locator(".card-body b").first().waitFor();
        //  const titles =  await page.locator(".card-body b").allTextContents();
         await page.locator(".card-body").filter({hasText: "ADIDAS ORIGINAL"}).getByRole("button",{name: 'Add to Cart'}).click();

         await page.getByRole("listitem").getByRole("button",{name: 'Cart'}).click();
         await page.locator("div li").first().waitFor();  //kind of wait
         await expect(page.getByText("ADIDAS ORIGINAL")).toBeVisible();         
         await page.getByRole("button",{name: 'Checkout'}).click();
         await page.getByPlaceholder("Select Country").pressSequentially("ind");
         await page.getByRole("button",{name: 'India'}).nth(1).click();

        //  await page.locator(".field input").nth(1).fill("123");
        //  await page.locator(".field input").nth(2).fill("Rahul Shetty");
        //  await page.locator(".field input").nth(3).fill("rahulshettyacademy");
        //  await page.locator(".btn.btn.btn-primary.mt-1").click();
        //  await page.waitForLoadState("networkidle");
        //  await page.locator("p:has-text('* Coupon Applied')").waitFor();
        //  const bln1 = await page.locator("p:has-text('* Coupon Applied')").isVisible();
        //  expect(bln1).toBeTruthy();
        //  expect(page.locator(".user__name [type='text']").first()).toHaveText(email); 

         await page.getByText("Place Order").click();
         await expect (page.getByText("Thankyou for the order.")).toBeVisible();
         
         
    //      const orderId =  await page.locator(".em-spacer-1 .ng-star-inserted").textContent();
    //  //     const  trimOrderId = orderId.match(/\| (\w+) \|/)[1];
    //      await page.locator("button[routerlink*='/dashboard/myorders']").click();
    //      await page.locator("tbody").waitFor();
    //      const rows = await page.locator("tbody tr");
    //      for(let i=0; i<await rows.count(); ++i)
    //       {
    //            const rowOrderId = await rows.nth(i).locator("th").textContent();
    //            if(orderId.includes(rowOrderId)){
    //                 await rows.nth(i).locator("button").first().click();
    //                 break;
    //            }
    //      }
    //      const orderIdDetails= await page.locator(".col-text.-main").textContent();
    //      expect (orderId.includes(orderIdDetails)).toBeTruthy();
         

    }); 