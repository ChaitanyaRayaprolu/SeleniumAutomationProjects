const {test, expect} = require('@playwright/test');

test ('Browser context & validating login error', async ({browser})=>
    {
         const context = await browser.newContext();
         const page = await context.newPage();
         const userName = page.locator('input#username');
         const password = page.locator("[name='password']");
         const signInBtn = page.locator('#signInBtn');
         const cardTitles = page.locator(".card-body a");


         await page.goto("https://rahulshettyacademy.com/loginpagePractise/");
         console.log(await page.title());
         await userName.fill("rahulshetty");
         await password.fill("learning");
         await signInBtn.click();
         console.log(await page.locator("[style*='block']").textContent());
         await expect(page.locator("[style*='block']")).toContainText("Incorrect");
         await userName.fill("");
         await userName.fill("rahulshettyacademy");
         await signInBtn.click();
          console.log(await cardTitles.first().textContent());
          console.log(await cardTitles.nth(1).textContent());
         const allTitles =  console.log(await cardTitles.allTextContents());
         console.log(allTitles);

    });
    
test ('UI Controls', async ({page})=>
    {
         await page.goto("https://rahulshettyacademy.com/loginpagePractise/");
         const userName = page.locator('input#username');
         const password = page.locator("[name='password']");
         const dropDown = page.locator("select.form-control");
         const documentLink = page.locator("[href*=documents-request]");

         await dropDown.selectOption("consult");
         await page.locator(".radiotextsty").last().click();
         await page.locator("#okayBtn").click();
         await expect(page.locator(".radiotextsty").last()).toBeChecked();
         await page.locator("#terms").click();
         await expect(page.locator("#terms")).toBeChecked();
         await page.locator("#terms").uncheck();
         expect(await page.locator("#terms").isChecked()).toBeFalsy();
         await expect(documentLink).toHaveAttribute("class","blinkingText");

          // await page.pause();
    }); 

test ('Child Window Handling', async ({browser})=>
     {
        const context = await browser.newContext();
        const page = await context.newPage();
        const userName = page.locator('input#username');
        await page.goto("https://rahulshettyacademy.com/loginpagePractise/");
        const documentLink = page.locator("[href*=documents-request]");

        const [newPage] = await Promise.all(
            [
                context.waitForEvent('page'),documentLink.click()
            ])

        const text = await newPage.locator(".red").textContent();
        const arrayText = text.split("@")
        const domain = arrayText[1].split(" ")[0]
        console.log(domain);
        await page.locator('input#username').fill(domain);
        
    

     });
    