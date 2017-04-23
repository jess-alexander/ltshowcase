/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltshowcase;

import ltshowcase.model.Photo;
import ltshowcase.model.Album;
//import ltshowcase.RemoveDuplicates;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.net.URL;
import java.nio.charset.Charset;

import com.google.gson.*;

/**
 *
 * @author Jess
 */
public class ltshowcase {
    
    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static String readJsonFromUrl(String url) throws IOException {
    InputStream is = new URL(url).openStream();
    try  {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      return jsonText;
    } finally {
      is.close();
    }
  }

    public static void main(String[] args) throws IOException {
        
        // THIS CODE IS FOR IMPORTING JSON DATA INTO A LARGE STRING FOR FORMATTING
        String json = "";
        try{
            json = readJsonFromUrl("https://jsonplaceholder.typicode.com/photos");
        }catch (IOException ex) {
            System.out.println("something bad happened WITH I/O...");
            // ISSUE: INSERT BETTER ERROR HANDLING
        } finally{
            if (json.equals(""))
                json = "error";
        }
        Gson gson = new Gson();
        
        Photo[] photoArray  = gson.fromJson(json, Photo[].class);
        Album[] photoAlbum = photoArray;
        
        Object[] displayAlbumId = RemoveDuplicates.removeDuplicates(photoAlbum);        
        
        System.out.println(displayAlbumId.length);               
        
    }
    
}