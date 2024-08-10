package com.nttdata.page;

import org.openqa.selenium.By;

public class ProducStorePage {

    public static By mainLoginButton = By.xpath("//a[@title='Acceda a su cuenta de cliente']//span[@class='hidden-sm-down']");
    public static By userInput = By.xpath("//input[@id='field-email']");
    public static By passInput = By.id("field-password");
    public static By loginButton = By.id("submit-login");

    /*
    public static By categoryLink = By.xpath("//li[@id='category-3']//a[@class='dropdown-item']");
    public static By subcategoryLink = By.xpath("//div[@class='subcategory-image']//a[@title='Men']");
*/
    public static By categoryLink(String categoryName) {
        return By.xpath("//li[@id='category-3']//a[@class='dropdown-item']");
    }

    public static By subcategoryLink(String subcategoryName) {
        return By.xpath("//div[@class='subcategory-image']//a[@title='Men']");
    }



    public static By primerProducto = By.xpath("//h2[@class='h3 product-title']//a[@href='https://qalab.bensg.com/store/es/men/1-1-hummingbird-printed-t-shirt.html#/1-tamano-s/8-color-blanco']"); // Ajusta el XPath según la estructura de tu página

    public static By cantidadInput = By.id("quantity_wanted"); // Ajusta el ID según tu página

    public static By agregarCarritoButton = By.xpath("//button[@class='btn btn-primary add-to-cart']");

    public static By confirmButton = By.xpath("//button[@class='btn btn-primary add-to-cart']");


    //public static By productoAgregadoTitle = By.id("myModalLabel");
    public static By productoAgregadoTitle = By.xpath("//h4[@id='myModalLabel']");
    public static By precioTotalLbl = By.xpath("//span[@class='subtotal value']");


    public static By finalizarCompra = By.xpath("//div[@class='cart-content-btn']//a[@class='btn btn-primary']");
    public static By tituloCarritoTitle = By.xpath("//h1[@class='h1']");
}
