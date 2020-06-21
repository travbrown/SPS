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
import com.google.sps.data.Comment;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns a random greeting. TODO: modify this file to handle comments data */
@WebServlet("/comments")
public final class DataServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // String thoughts = getParameter(request, "comment_box", "");
        // comments.add(thoughts);

        String name = getParameter(request, "username", "Anonymous");
        String commentText = getParameter(request, "comment_box", "");
        long timestamp = System.currentTimeMillis();

        Entity commentEntity = new Entity("Comment");
        commentEntity.setProperty("name", name);
        commentEntity.setProperty("thought", commentText);
        commentEntity.setProperty("timestamp", timestamp);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(commentEntity);
        
        // Redirect back to the HTML page.
        response.sendRedirect("/index.html");
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery results = datastore.prepare(query);

        List<Comment> comments = new ArrayList<>();
        for (Entity entity : results.asIterable()) {
            long id = entity.getKey().getId();
            String username = (String) entity.getProperty("name");
            String thought = (String) entity.getProperty("thought");
            long timestamp = (long) entity.getProperty("timestamp");

            Comment comment = new Comment(id, username, thought, timestamp);
            comments.add(comment);
        }

        Gson gson = new Gson();

        response.setContentType("application/json;");
        String json = convertToJson(comments);
        response.getWriter().println(json);

        // String json = convertToJsonUsingGson(comments);
        // response.setContentType("application/json;");
        // response.getWriter().println(json);
    }

    /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }

    /**
    * Converts a ServerStats instance into a JSON string using the Gson library. Note: We first added
    * the Gson library dependency to pom.xml.
    */
    private String convertToJson(List<Comment> comments) {
        Gson gson = new Gson();
        String json = gson.toJson(comments);
        return json;
    }
}

