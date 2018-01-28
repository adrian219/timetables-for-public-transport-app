package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.helper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class ReflectionHelper {
    public static final String DEFAULT_PACKAGE = ReflectionHelper.class.getPackage().getName().substring(0, ReflectionHelper.class.getPackage().getName().length() - 7);

//    public static void main(String[] args){
//        try {
//            for(Class<?> c : getClassesImplementingInterface(DEFAULT_PACKAGE + ".enums.MenuElement")){
//                System.out.println(c.getName());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static ArrayList<Class<?>> getClassesImplementingInterface(String interfaceName) throws IOException, ClassNotFoundException {
        ArrayList<Class<?>> resultList = new ArrayList<>();

        Class<?> interfaceClass = Class.forName(interfaceName);

        ArrayList<Class<?>> classesList = getClasses(DEFAULT_PACKAGE);

        ArrayList<Class<?>> tempList;
        for(Class<?> c : classesList){
            tempList = new ArrayList<>(Arrays.asList(c.getInterfaces()));
            if(tempList.contains(interfaceClass)){
                resultList.add(c);
            }
        }

        return resultList;
    }

    private static ArrayList<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        ArrayList<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile().replace("%20", " ")));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static ArrayList<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        ArrayList<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if(files == null){
            System.out.println("files is null!");
        }else{
            for (File file : files) {
                if (file.isDirectory()) {
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }

        return classes;
    }
}
