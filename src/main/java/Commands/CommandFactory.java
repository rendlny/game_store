package Commands;

/**
 *
 * @author Conno
 */
public class CommandFactory {

    private volatile static CommandFactory uniqueInstance;
    
    private CommandFactory() {
    
    }
    
    public static CommandFactory getInstance() {
        if(uniqueInstance == null) {
            synchronized(CommandFactory.class) { 
                if(uniqueInstance == null) {
                    uniqueInstance = new CommandFactory();
                }
            }
        }
        
        return uniqueInstance;
    }
    
    public Command createCommand(String action) {
        Command command = null;

        switch (action.toLowerCase()) {
            case "sign_up":
                command = new CreateUserCommand();
                break;
            case "login":
                command = new LoginUserCommand();
                break;
            case "add_product":
                command = new AddProductCommand();
                break;
            case "add_product_image":
                command = new AddProductImageCommand();
                break;
            case "search_products":
                command = new ProductSearchCommand();
                break;
            case "update_password":
                command = new UserUpdatePasswordCommand();
                break;
            case "update_email":
                command = new UserUpdateEmailCommand();
                break;
            case "update_name":
                command = new UserUpdateNameCommand();
                break;
            case "update_address":
                command = new UserUpdateAddressCommand();
                break;
            case "add_to_cart":
                command = new AddProductToCart();
                break;
            case "remove_from_cart":
                command = new RemoveFromCartCommand();
                break;
            case "delete_product_image":
                command = new DeleteProductImageCommand();
                break;
            case "update_product":
                command = new UpdateProductCommand();
                break;

            case "create_order":
                command = new CreateOrderCommand();
                break;
        }

        return command;

    }
}
