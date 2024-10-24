const {test,expect} = require('@playwright/test');

test("Calendar Validation", async ({page})=>
{
    const month = "6";
    const date = "12"
    const year = "2024"
    const expectedList = [month,date,year]
    await page.goto('https://rahulshettyacademy.com/seleniumPractise/#/offers');
    await page.locator(".react-date-picker__inputGroup").click();
    await page.locator(".react-calendar__navigation__label").click();
    await page.locator(".react-calendar__navigation__label").click();
    await page.getByText(year).click();
    await page.locator(".react-calendar__tile").nth(Number(month)-1).click();
    await page.locator("//abbr[text()='"+date+"']").click();
    const inputs = await page.locator(".react-date-picker__inputGroup input")

    for(let index=0; index<inputs.length; ++index)        
    {
        const value = inputs[index].getAttribute("value");
        expect(value).toEqual(expectedList[index]);

    }

    




});