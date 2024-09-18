const {test, expect} = require('@playwright/test');

test ('assignment1', async ({page})=>
    {
         await page.goto("https://rahulshettyacademy.com/client/");
         await page.locator("#userEmail").fill("rahultest24@gmail.com");
         await page.locator("#userPassword").fill("Test@12345");
         await page.locator("#login").click();
         // await page.waitForLoadState("networkidle");
         await page.locator(".card-body b").first().waitFor();
         console.log(await page.locator(".card-body b").allTextContents());
    }); 