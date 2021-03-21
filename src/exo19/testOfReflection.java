package exo19;

import java.lang.reflect.Method;
import java.util.List;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class testOfReflection {


public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {


AnalyzeBean beanAnalyzer = new AnalyzeBean();
System.out.println("la 1ere qst : getClassName ajouté à la classe Person :"+beanAnalyzer.getClassName(String.class));


Person p1 = new Person();
Class <?> BeanClass1 = p1.getClass();
Object newInstance = BeanClass1.getConstructor().newInstance();
System.out.println("\n la 2eme qst : La nouvelle instance (en procédant par la méthode 'newInstance') : "+newInstance);

Person personBeanAnalyzer = (Person)beanAnalyzer.getInstance("Exo19.Person");
System.out.println("\n La nouvelle instance (en procédant par la méthode 'newInstance') :') : "+personBeanAnalyzer);

Class<?> stringClass = String.class;


Method[] tableOfMethods = stringClass.getMethods();
System.out.println("la 3eme qst : Le nom de toutes les méthodes contenues dans 'string.class':: ");
for (Method method : tableOfMethods)
System.out.println(method.getName());



Method[] tableOfDeclaredMethods = stringClass.getDeclaredMethods();
System.out.println("\n Le nom de toutes les méthodes contenues dans 'string.class' (uniquement contenues dans la classe 'Person'): ");
for (Method method : tableOfDeclaredMethods)
System.out.println(method.getName());


Class<?> personClass = Person.class;

for (Method method : tableOfDeclaredMethods) {
String methodName = method.getName();
  if ((methodName.startsWith("get") || methodName.startsWith("is")) && method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers())){
   
       String nameOfProperty = "";
  if (methodName.startsWith("get"))
  nameOfProperty = methodName.substring(3);
  else
  nameOfProperty = methodName.substring(2);


nameOfProperty = nameOfProperty.substring(0,1).toLowerCase() + nameOfProperty.substring(1);
    }
    }

List<String> properties = beanAnalyzer.getProperties(personBeanAnalyzer);
System.out.println("\n la 3eme qst :Propriétés (par getProperties) :  "+properties);


       System.out.println("\n Propriétés (par getDeclaredField): ");
Field[] Fields = personClass.getDeclaredFields();

for (Field f : Fields)
       System.out.println("\n field : "+f.getName()+"\n");

 
Field firstName = personClass.getDeclaredField("firstName");

firstName.setAccessible(true);
firstName.set(personBeanAnalyzer, "CELINA");

 
Field lastName = personClass.getDeclaredField("lastName");

lastName.setAccessible(true);
    lastName.set(personBeanAnalyzer, "LOUNAS");



Field age = personClass.getDeclaredField("age");
age.setAccessible(true);
age.set(personBeanAnalyzer, 23);
System.out.println("\nla 4eme question : person invoquée grâce à la méthode : "+personBeanAnalyzer);

System.out.println("\n privé? : "+Modifier.isPrivate(age.getModifiers()));

String propertyName = "age";
int realAge = 38;
String NameOfMethod = "set" + propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
Method setAge = personBeanAnalyzer.getClass().getMethod(NameOfMethod,int.class);

setAge.invoke(personBeanAnalyzer,realAge);
System.out.println("\nla 5eme qst : person invoquée grâce à la méthode définie est : "+personBeanAnalyzer);



Constructor<?> constructorOfPerson = personClass.getConstructor();
Person person3 = (Person) constructorOfPerson.newInstance("CELINA","LOUNAS",23);
System.out.println("\n- Le constructeur invoqué grâce à une méthode de constructeur(getConstructor) :"+person3);
}
}














