package com.example.serialport.send;

import com.example.serialport.portdata.AllData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClientExample {


    public static String send(AllData params) {
        try {
            URL url = new URL("http://baidu.com/");
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setReadTimeout(5000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setDoOutput(true);

                ObjectMapper objectMapper = new ObjectMapper();
                String requestBodyJson = objectMapper.writeValueAsString(params);

                OutputStream os = urlConnection.getOutputStream();
                os.write(requestBodyJson.getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == 200) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    return readStream(in);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param in
     * @return
     * @throws IOException
     */
    private static String readStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        System.out.println(sb.toString());
        br.close();
        return sb.toString();
    }
}

