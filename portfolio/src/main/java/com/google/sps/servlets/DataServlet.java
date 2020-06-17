// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns a random greeting. TODO: modify this file to handle comments data */
@WebServlet("/data")
public final class DataServlet extends HttpServlet {
    //private ArrayList<String> comments = new ArrayList<String>();
    
    private ArrayList<String> arr;
    @Override
    public void init(){
        arr = new ArrayList<>();
        arr.add("Nice Picss");
        arr.add("Love the fun facts");
        arr.add("Woo Go Travis!");
        
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // Convert the server stats to JSON
        //ServerStats commentStats = new CommentStats();
        String json = convertToJson(arr);
        response.setContentType("application/json;");
        System.out.println(json);
        response.getWriter().println(json);
    }

    /**
    * Converts a ServerStats instance into a JSON string using manual String concatentation.
    */
    private String convertToJson(ArrayList<String> arr) {
        String json = "{";
        json += "\"comment1\": ";
        json += "\"" + arr.get(0) + "\"";
        json += ", ";
        json += "\"comment2\": ";
        json += "\"" + arr.get(1) + "\"" + ", ";
        json += "\"comment3\": ";
        json += "\"" + arr.get(2) + "\"";
        json += "}";
        return json;
    }


    /**
    * Converts a ServerStats instance into a JSON string using the Gson library. Note: We first added
    * the Gson library dependency to pom.xml.
    */
    // private String convertToJsonUsingGson(CommentStats commentStats) {
    //     Gson gson = new Gson();
    //     String json = gson.toJson(commentStats);
    //     return json;
    // }
}

