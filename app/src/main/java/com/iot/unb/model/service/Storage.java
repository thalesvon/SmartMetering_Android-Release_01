package com.iot.unb.model.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iot.unb.SmartMetering.MainActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by aclopesjr on 5/11/17.
 */

public class Storage {

    public static <T> boolean persist(T objectToStore, String fileName) {

        try {
            //Deserializes the object on json to save
            String json = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(objectToStore);
            //Creates output stream to save file.
            FileOutputStream outputStream;
            //Creates the file and open it to write
            outputStream = MainActivity.getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            //Saves the content on file
            outputStream.write((json.getBytes()));
            //Closes the file
            outputStream.close();

            return true;
        } catch (FileNotFoundException e) {
            Log.d("Smart Metering", e.getMessage());
        } catch (IOException e) {
            Log.d("Smart Metering", e.getMessage());
        }
        return false;
    }

    public static <T> T load(String fileName, Class<T> classType) {

        try {
            InputStream inputStream = MainActivity.getContext().openFileInput(fileName);

            if (null != inputStream) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();

                T result = new Gson()
                        .fromJson(stringBuilder.toString(), classType);

                return result;
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return null;
    }

}
