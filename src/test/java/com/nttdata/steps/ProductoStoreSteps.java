package com.nttdata.steps;

import com.nttdata.page.ProducStorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductoStoreSteps {

    private static WebDriver driver;

    public ProductoStoreSteps(WebDriver driver) {
        this.driver = driver;
    }


    public void ensureLoginButtonIsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement loginButtonOnMainPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Acceda a su cuenta de cliente']//span[@class='hidden-sm-down']"))); // Reemplaza con el selector adecuado
        loginButtonOnMainPage.click();

    }

    /**
     * Escribir el usuario
     *
     * @param user el usuario
     */
    public void typeUser(String user) {
        WebElement userInputElement = driver.findElement(ProducStorePage.userInput);
        userInputElement.sendKeys(user);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ProducStorePage.loginButton));


    }


    /**
     * Escribir el password
     *
     * @param password el password del usuario
     */
    public void typePassword(String password) {
        this.driver.findElement(ProducStorePage.passInput).sendKeys(password);
    }

    /**
     * Hacer click en el botón login
     */
    public void login() {
        this.driver.findElement(ProducStorePage.loginButton).click();
    }


    public static void navigateToCategoryAndSubcategory(String categoria, String subcategoria) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(ProducStorePage.categoryLink(categoria)));
        categoryElement.click();

        WebElement subcategoryElement = wait.until(ExpectedConditions.elementToBeClickable(ProducStorePage.subcategoryLink(subcategoria)));
        subcategoryElement.click();


    }

    public static void addProductToCart(int quantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));



        WebElement firstProductElement = wait.until(ExpectedConditions.elementToBeClickable(ProducStorePage.primerProducto));
        System.out.println("Primer producto localizado");
        firstProductElement.click();


        WebElement quantityInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ProducStorePage.cantidadInput));
        System.out.println("Campo de cantidad localizado");


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", quantityInputElement, quantity);


        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(ProducStorePage.agregarCarritoButton));
        System.out.println("Botón de añadir al carrito localizado");
        addToCartButton.click();


    }


    public static void validateProductConfirmationPopup(String expectedConfirmationMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        WebElement popupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ProducStorePage.productoAgregadoTitle));
        System.out.println("Popup visible");


        WebElement confirmationMessageElement = popupElement.findElement(ProducStorePage.productoAgregadoTitle);
        System.out.println("Elemento de mensaje de confirmación localizado");


        String actualConfirmationMessage = confirmationMessageElement.getText();
        System.out.println("Mensaje de confirmación: " + actualConfirmationMessage);


        if (!actualConfirmationMessage.contains(expectedConfirmationMessage)) {
            throw new AssertionError("El mensaje de confirmación del producto agregado es incorrecto. Esperado: " + expectedConfirmationMessage + ", Actual: " + actualConfirmationMessage);
        }

        System.out.println("Mensaje de confirmación validado correctamente.");
    }

    public static void validateTotalAmountInPopup(double expectedTotalAmount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        WebElement popupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ProducStorePage.productoAgregadoTitle));
        System.out.println("Popup visible");


        WebElement totalAmountElement = popupElement.findElement(ProducStorePage.precioTotalLbl);
        System.out.println("Elemento de monto total localizado");


        String totalAmountText = totalAmountElement.getText();
        System.out.println("Texto del monto total: " + totalAmountText);


        totalAmountText = totalAmountText.replaceAll("[^\\d.,]", "");
        if (totalAmountText.contains(",")) {
            totalAmountText = totalAmountText.replace(",", ".");
        }
        double actualTotalAmount = Double.parseDouble(totalAmountText);
        System.out.println("Monto total convertido: " + actualTotalAmount);


        if (Math.abs(actualTotalAmount - expectedTotalAmount) > 0.01) {
            throw new AssertionError("El monto total calculado es incorrecto. Esperado: " + expectedTotalAmount + ", Actual: " + actualTotalAmount);
        }

        System.out.println("Monto total validado correctamente.");

    }

    public static void finalizarCompra() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement finalizarCompra = wait.until(ExpectedConditions.visibilityOfElementLocated(ProducStorePage.finalizarCompra));
        finalizarCompra.click();
    }


    public static void validoElTituloDeLaPaginaDelCarrito(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ProducStorePage.tituloCarritoTitle));
        System.out.println("Título de la página del carrito visible");

        String actualTitle = titleElement.getText();
        System.out.println("Título de la página del carrito actual: " + actualTitle);

        if (!actualTitle.equals(expectedTitle)) {
            throw new AssertionError("El título del elemento en la página del carrito es incorrecto. Esperado: " + expectedTitle + ", Actual: " + actualTitle);
        }

        System.out.println("Título del elemento en la página del carrito validado correctamente.");
    }

}
