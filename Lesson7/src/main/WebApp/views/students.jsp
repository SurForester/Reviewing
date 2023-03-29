<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
</head>

<body>
<h1>Students page</h1>
<a href="/students/add">Добавить студента</a>
<br>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Mark</th>
        <th>---</th>
    </tr>
    <c:forEach items="${students}" var="students">
        <tr>
            <td>${students.id}</td>
            <td>${students.name}</td>
            <td>${students.mark}</td>
            <td><a href="${pageContext.request.contextPath}/students/delete/${students.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>