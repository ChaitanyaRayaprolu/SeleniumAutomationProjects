const {test,expect} = require('@playwright/test');

test('Pop-up validation', async ({page})=>    
{
    await page.goto("https://rahulshettyacademy.com/AutomationPractice/");
    // await page.goto("https://google.co.in");
    // await page.goBack();
    // await page.goForward();

    await expect(page.locator("#displayed-text")).toBeVisible();
    await page.locator("#hide-textbox").click();
    await expect(page.locator("#displayed-text")).toBeHidden();

    page.on('dialog',dialog => dialog.dismiss());
    await page.locator("#confirmbtn").click();

    await page.locator("#mousehover").hover();

    const iframePage = page.frameLocator("#courses-iframe");
    await iframePage.locator("li a[href='lifetime-access']:visible").click();
     const textCheck = await iframePage.locator(".text h2").textContent();
     console.log(textCheck.split(" ")[1]);




});