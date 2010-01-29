package org.apache.commons.httpclient.demo;

import java.io.File;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import java.io.*;


@SuppressWarnings("deprecation")
public class httpclientfileupload {
    private static String targetURL ="http://90.0.12.20:8088/NationWideAdmin/test/ProcessFileUpload.jsp";
    public static void main(String[] args) {

        MultipartPostMethod filePost = new MultipartPostMethod(targetURL);

        File targetFilePath = new File("htmlparser.zip");

        try {
            filePost.addParameter("fileName", targetFilePath);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        HttpClient client = new HttpClient();

       //����Ҫ�ϴ����ļ����ܱȽϴ�,����ڴ������������ӳ�ʱʱ��

        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

//        try {
//            int status = client.executeMethod(filePost);
//        } catch (IOException ex1) {
//            ex1.printStackTrace();
//        }

    }
}
