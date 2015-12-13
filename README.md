# Yauheniya-Yarmaliuk
    mvn clean install
    mvn site
    mvn site:stage

Start rest app: cd rest-app mvn jetty:run

All URL is used prefix **localhost:8090/rest**
* /employees
* /vouchers
* /cost
* see more in the package com.zhenia.project.home.rest

Start web app: cd web-app mvn jetty:run

All URL is used prefix **localhost:8080**
* /voucherList
* /employeeList
* /costList
* see more in the package com.zhenia.project.home.web

You can use tomcat but only **localhost:8090**
application context **/rest** in rest-app
and **/** in web-app
