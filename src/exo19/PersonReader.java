package exo19;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.Field;

public class PersonReader {

public static void main(String []args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {


Path peopleAndEmployeeFile = Path.of("files/fichier.txt");
System.out.println("\nla 6eme qst : les informations du fichiers à lire: \n");
           

Set<String> keyOfBean = new HashSet<>();
Map<String, Object> registryBean = new HashMap<>();

try (BufferedReader personOrEmployee = Files.newBufferedReader(peopleAndEmployeeFile);){

String line = personOrEmployee.readLine();


while (line != null) {
if (!line.startsWith("#")) {
String[] separator = line.split("=");

if (separator[0].equals("bean.name"))    
keyOfBean.add(separator[1]);

else if (separator[0].endsWith("class")) {
String keyOfOneBean = separator[0].substring(0, separator[0].indexOf('.'));      
           String nameOfClass = separator[1];

           Class<?> classOfBean = Class.forName(nameOfClass);
   Constructor<?> emptyConstructorOfBean = classOfBean.getConstructor();
   
   
   registryBean.put(keyOfOneBean,emptyConstructorOfBean.newInstance());
}  


else if (separator[0].endsWith(".lastName")) {

   String keyOfOneBean = separator[0].substring(0, separator[0].indexOf('.'));    
   String name = separator[1];
   
   Object bean = registryBean.get(keyOfOneBean);
   Field lastNameField = bean.getClass().getDeclaredField("lastName");
   lastNameField.setAccessible(true);
   lastNameField.set(bean,name);
   }
                 else if (separator[0].endsWith(".firstName")) {
   String keyOfOneBean = separator[0].substring(0, separator[0].indexOf('.'));    
   String name = separator[1];
   Object bean = registryBean.get(keyOfOneBean);
   Field firstNameField = bean.getClass().getDeclaredField("firstName");
   firstNameField.setAccessible(true);
   firstNameField.set(bean,name);
   }
                 else if (separator[0].endsWith(".age")) {
     String keyOfOneBean = separator[0].substring(0, separator[0].indexOf('.'));    
     int age = Integer.parseInt(separator[1]);
     
     Object bean = registryBean.get(keyOfOneBean);
     Field ageField = bean.getClass().getDeclaredField("age");
     ageField.setAccessible(true);
     ageField.set(bean,age);
     }

}

line = personOrEmployee.readLine();
}

}catch ( IOException | ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException|NoSuchFieldException exc)
{

exc.printStackTrace();

}
System.out.println("L'instance des classes créées dans'Analyzed Keys' : ");
keyOfBean.forEach(System.out::println);

System.out.println("\n Les beans des classes créées: ");
registryBean.forEach((k,obj) -> System.out.println(k+" : " +obj));
}
}
