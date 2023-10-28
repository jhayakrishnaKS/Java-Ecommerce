package org.example.Controller;

import org.example.Controller.impl.IHomeController;

import org.example.utils.AppExecption;
import org.example.utils.StringUtils;
import org.example.view.HomePage;

import static org.example.utils.Appinput.enterInt;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.println;

public class HomeController implements IHomeController {

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
        categoryController=new CategoryController(this);
    }

    @Override
    public void printMenu() {
        homePage.printMenu();
        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            switch (choice) {
                case 1:
                    displayCategories();
                    break;
                case 2:
                    productController.loadproduct();
                    break;
                case 3:
                    // cartController.printCart();
                    break;
                case 4:
                    // orderController.viewOrders();
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
        categoryController.chooseCategory();
    }

//    private void chooseCategory() {
//        try {
//            int categoryChoice = enterInt("Enter category choice: ");
//            switch (categoryChoice) {
//                case 1:
//                case 2:
//                    productController.loadproduct();
//                    break;
//                default:
//                    invalidChoice(new AppExecption("Invalid category choice."));
//            }
//        } catch (AppExecption appException) {
//            invalidChoice(appException);
//        }
//    }

    private void invalidChoice(AppExecption appException) {
        println(appException.getMessage());
        printMenu();
    }

    public void loadProducts(String category) {

    }
}
