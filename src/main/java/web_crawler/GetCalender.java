package web_crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by liuyufei on 8/28/16.
 */
public class GetCalender {

//    https://login.wintec.ac.nz/
//    https://login.wintec.ac.nz/auth.php

    public static void main(String[] args) throws IOException {

//        javascript:document.location=redirectURI
//        https://sts.wintec.ac.nz/adfs/ls/?wa=wsignin1.0&wtrealm=urn:federation:MicrosoftOnline&wctx=wa%3Dwsignin1%252E0%26rpsnv%3D2%26ct%3D1375219339%26rver%3D6%252E1%252E6206%252E0%26wp%3DMBI%26wreply%3Dhttps%3a%2f%2fstudentwintecac.sharepoint.com%2f%26LoginOptions%3D1

//        https://apm.wintec.ac.nz/my.policy
        Connection.Response mylearn = Jsoup.connect("https://sts.wintec.ac.nz/")
                .method(Connection.Method.GET)
                .execute();


        Connection.Response policy = Jsoup.connect("https://apm.wintec.ac.nz/my.policy")
                .method(Connection.Method.GET)
                .execute();


        Document document = Jsoup.connect("https://login.wintec.ac.nz/auth.php")
                .data("cookieexists", "false")
                .data("username", "yufliu21")
                .data("password", "Wintec123")
                .cookies(mylearn.cookies())
                .post();



//
//        Document doc = Jsoup.connect("https://learning.wintec.ac.nz/course/view.php?id=6518").get();
//        String title = doc.title();

//        System.out.println(mylearn.cookies());
        System.out.println(policy.body());
        System.out.println(document);



    }



}
