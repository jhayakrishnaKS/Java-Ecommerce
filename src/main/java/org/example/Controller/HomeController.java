package org.example.Controller;

import org.example.Controller.impl.IHomeController;

import org.example.Model.Product;
import org.example.utils.AppExecption;
import org.example.utils.StringUtils;
import org.example.view.CartPage;
import org.example.view.HomePage;

import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Appinput.enterInt;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.println;
import static org.example.view.CartPage.CartMenu;

public class HomeController implements IHomeController {
    private List<Product> cart;

    private final HomePage homePage;
    private final AuthController authController;
    private final ProductController productController;
    private final CartController cartController;
    private final OrderController orderController;
    private final CategoryController categoryController;

    public HomeController(AuthController authController) {
        homePage = new HomePage();
        this.authController = authController;
        productController = new ProductController(this);
        cartController = new CartController(this);
        orderController = new OrderController(this);
        categoryController = new CategoryController(this);
        this.cart = new ArrayList<>();
    }

    @Override
    public void printMenu() {
        homePage.printMenu();
        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            switch (choice) {
                case 1:
                    displayCategories();
                    productController.selectProductAndAddToCart();
                    cartController.saveCartToCSV();
                    break;
                case 2:
                    productController.loadProduct();
                    after();
                    break;
                case 3:
                    cartController.viewCart();
                    CartMenu();


                    break;
                case 4:
                    break;
                case 5:
                    setLoggedInUser(null);
                    authController.authMenu();
                    break;
                default:
                    invalidChoice(new AppExecption(StringUtils.INVALID_CHOICE));
            }
        } catch (AppExecption appException) {
            invalidChoice(appException);
        }
    }


    private void displayCategories() {
        println("1. Phone");
        println("2. Accessories");
        println("88. Checkout");
        println("99. Go Back");
        categoryController.chooseCategory();
    }
    private void after(){
        CartMenu();
        int choice = 0;
        try {
            choice = enterInt(StringUtils.ENTER_CHOICE);
            println("88. Checkout");
            println("99. Go Back");
            if (choice == 88) {
                checkout();
            } else if (choice == 99) {
                printMenu();
            } else {
                invalidChoice(new AppExecption(StringUtils.INVALID_CHOICE));
            }
        } catch (AppExecption e) {
            throw new RuntimeException(e);
        }

    }

    private void checkout() {
    }
    private void CartMenu() {
        CartPage.CartMenu();
    }


    private void invalidChoice(AppExecption appException) {
        println(appException.getMessage());
        printMenu();
    }

    public void loadProducts(String category) {

    }

    public void addToCart(Product product) {
        cart.add(product);
        System.out.println("Product added to the cart: " + product.getTitle());
    }
}
