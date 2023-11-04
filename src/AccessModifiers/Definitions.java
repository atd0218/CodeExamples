package AccessModifiers;

import AccessModifiers.*;

public class Definitions {

    //Access Modifiers can be used for variables and classes

    // with the default modifier only classes in the same package can see these variables.
    String defaultMessage = "This is the default";

    // anything with the public keyword is visible to any package within the project folder. 
    public String publicMessage = "This is the public message";

    // accessible to a different class in a different package as long as that class is a subclass 
    protected String protectedMessage = "This is protected";

    //private variables can only be accessed to the class where it is created. 
    private String privateMessage = "This is private";

    

    
    
}
