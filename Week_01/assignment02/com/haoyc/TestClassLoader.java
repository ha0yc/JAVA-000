package com.haoyc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class TestClassLoader extends ClassLoader{
    private String inputFilePath;
    private String outputFilePath;
    private String clazzName;

    public TestClassLoader(String inputFilePath, String outputFilePath, String clazzName) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.clazzName = clazzName;
    }

    public static void main(String[] args) {

    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(inputFilePath);
            os = new FileOutputStream(outputFilePath);
            while (is.read() != 0) {
                byte[] bytes = new byte[256];
                is.read(bytes);
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) (255 - bytes[i]);
                }
                os.write(bytes);
                os.flush();
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

            if (os != null) {
                try {
                    os.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        try {
            is = new FileInputStream(outputFilePath);
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
        return super.findClass(name);
    }
}
