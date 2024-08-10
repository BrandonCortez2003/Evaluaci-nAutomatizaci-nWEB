package com.nttdata.stepsdefinitions;

import com.nttdata.page.ProducStorePage;
import com.nttdata.steps.ProductoStoreSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class ProductStoreStepsDef {

    private WebDriver driver;

    @Dado("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver = getDriver();https://qalab.bensg.com/store
        //driver.get("https://qalab.bensg.com/store/es/iniciar-sesion?back=https%3A%2F%2Fqalab.bensg.com%2Fstore%2Fes%2F");
        driver.get("https://qalab.bensg.com/store");
        screenShot();
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String user, String password) {
        ProductoStoreSteps productoStoreSteps = new ProductoStoreSteps(driver);
        productoStoreSteps.ensureLoginButtonIsVisible();
        productoStoreSteps.typeUser(user);
        productoStoreSteps.typePassword(password);
        productoStoreSteps.login();
        screenShot();
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        ProductoStoreSteps.navigateToCategoryAndSubcategory(categoria, subcategoria);
        screenShot();
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int quantity) {
        ProductoStoreSteps.addProductToCart(quantity);
        screenShot();

    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {

        ProductoStoreSteps.validateProductConfirmationPopup("Producto añadido correctamente a su carrito de compra");
        screenShot();

    }



    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        ProductoStoreSteps.validateTotalAmountInPopup(38.24);
        screenShot();
    }

    @Cuando("finalizo la compra")
    public void finalizoLaCompra() {
        ProductoStoreSteps.finalizarCompra();
        screenShot();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        ProductoStoreSteps.validoElTituloDeLaPaginaDelCarrito("CARRITO");
        screenShot();
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
    }
}
