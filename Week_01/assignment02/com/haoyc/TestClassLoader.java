package com.haoyc;

import java.io.*;
import java.lang.reflect.Method;

public class TestClassLoader extends ClassLoader{
    private String inputFilePath;
    private String clazzName;

    public TestClassLoader(String inputFilePath, String clazzName) {
        this.inputFilePath = inputFilePath;
        this.clazzName = clazzName;
    }

    public static void main(String[] args) {
        TestClassLoader testClassLoader = new TestClassLoader("Hello.xlass", "Hello");
        try {
            Class clazz = testClassLoader.findClass(testClassLoader.clazzName);
            Object ob = clazz.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(ob);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream is = null;
        File file = new File(inputFilePath);
        byte[] bytes = null;
        try {
            is = new FileInputStream(inputFilePath);
            bytes = new byte[(int) file.length()];
            is.read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
        } catch (Exception ex) {
            throw new ClassNotFoundException("error happens while operating file");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        Class clazz = null;
        if(bytes == null){
            throw new ClassNotFoundException();
        }else {
            clazz = defineClass(name, bytes, 0, bytes.length);
        }

        return clazz;
    }

}
