package org.example.Controller;

import org.example.App;
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
    private final CategoryController categoryController;
    private final ProductController productController;
    private final CartController cartController;
    private final OrderController orderController;

    public HomeController(AuthController authController) {
        homePage = new HomePage();
        this.authController = authController;
        productController = new ProductController(this);
        categoryController = new CategoryController(this);
        cartController = new CartController(this);
        orderController = new OrderController(this);
    }

    @Override
    public void printMenu() {
        homePage.printMenu();
        try{
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            switch (choice){
                case 1:
                    categoryController.printMenu();
                    break;
                case 2:
                    break;
                case 3:
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


    private void invalidChoice(AppExecption appException) {
        println(appException.getMessage());
        printMenu();
    }
    }

