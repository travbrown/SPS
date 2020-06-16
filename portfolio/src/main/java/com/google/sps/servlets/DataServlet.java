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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns a random greeting. TODO: modify this file to handle comments data */
@WebServlet("/greeting")
public final class DataServlet extends HttpServlet {
    private List<String> greetings;

    @Override
    public void init(){
        greetings = new ArrayList<>();
        greetings.add("Hello");
        greetings.add("Weh yah seh mi general?");
        greetings.add("Hey");
        greetings.add("Wah Gwan mi G?");
        greetings.add("Shalom");
        greetings.add("Hola");
        greetings.add("Mawning big boss");
        greetings.add("Yah dealid brogad?");
        System.out.println(greetings);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String greeting = greetings.get((int) (Math.random() * greetings.size()));

        response.setContentType("text/html;");
        response.getWriter().println(greeting);
    }
}

