const {test, expect, request} = require('@playwright/test');
const {ApiUtils} = require('./utils/ApiUtils');
const loginPayLoad = {userEmail: "rahultest24@gmail.com", userPassword: "Test@12345"}
const orderPayLoad = {orders: [{country: "cuba", productOrderedId: "6581ca979fd99c85e8ee7faf"}]}
let response;

test.beforeAll( async()=>
{
     
    const apiContext = await request.newContext();
    const apiUtils = new ApiUtils(apiContext,loginPayLoad);
    response = await apiUtils.createOrder(orderPayLoad);     

});


test ('Client App Login', async ({page})=>
    {
          page.addInitScript(value => {

               window.localStorage.setItem('token',value);
          },response.token);
  
         const email = "rahultest24@gmail.com";
         const password = "Test@12345";
         await page.goto("https://rahulshettyacademy.com/client/");
     
         await page.locator("button[routerlink*='/dashboard/myorders']").click();
         await page.locator("tbody").waitFor();
         const rows = await page.locator("tbody tr");
         for(let i=0; i<await rows.count(); ++i)
          {
               const rowOrderId = await rows.nth(i).locator("th").textContent();
               if(response.orderId.includes(rowOrderId)){
                    await rows.nth(i).locator("button").first().click();
                    break;
               }
         }
         const orderIdDetails= await page.locator(".col-text.-main").textContent();
         expect (response.orderId.includes(orderIdDetails)).toBeTruthy();
         

    }); 