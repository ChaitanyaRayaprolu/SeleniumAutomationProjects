const {test, expect} = require('@playwright/test');

test('Playwright Special Locators', async ({page}) =>
{
    await page.goto("https://rahulshettyacademy.com/angularpractice/");
    await page.getByLabel("Check me out if you Love IceCreams!").check();
    await page.getByLabel("Employed").check();  // getByLabel works only if there an association between the label and the edit box
                                                // if both are different DOm elements then this method will not work
    await page.getByLabel("Gender").selectOption("Male"); 
    await page.getByPlaceholder("Password").fill("abc123");  

    await page.getByRole("button",{name: 'Submit'}).click();
    const successAssert = await page.getByText("Success! The Form has been submitted successfully!.").isVisible();
    expect(successAssert).toBeTruthy();
    await page.getByRole("link", {name: "Shop"}).click();
    await page.locator("app-card").filter({hasText: "Nokia Edge"}).getByRole("button").click();

});
