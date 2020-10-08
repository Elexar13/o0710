<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: netno
  Date: 07.10.2020
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Countries</title>
    <link rel="stylesheet" type="text/css" href="resources/styles.css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Short</th>
        <th>Link to vendor</th>
    </tr>
    </thead>
    <tbody>

    <jsp:useBean id="countryBean" scope="request" type="beans.CountryBean"/>
    <c:forEach items="${countryBean.countries}" var="c">
<tr>
    <td>${c.id}</td><td>${c.name}</td><td>${c.shortName}</td>
    <td><a href="/vendor-servlet?id=${c.id}">${c.shortName} vendor </a></td>
</tr>

    </c:forEach>
    </tbody>
</table>

</body>
</html>
