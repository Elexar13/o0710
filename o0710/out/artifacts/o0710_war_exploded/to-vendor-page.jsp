<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Vendors</title>
</head>
<body>
    <h2>Производители: </h2>
    <ul>
        <jsp:useBean id="vendorBean" scope="request" type="beans.VendorBean"/>
        <c:forEach items="${vendorBean.vendors}" var="v">
            <li>${v.name}</li>
        </c:forEach>
    </ul>

</body>
</html>