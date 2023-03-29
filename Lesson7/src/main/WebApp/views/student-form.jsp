<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<body>
<%--@elvariable id="student" type="java"--%>
<form:form action="processForm" modelAttribute="student">
    Name: <form:input path="name" />
    <br>
    Mark: <form:input path="mark" />
    <input type="submit" value="Submit" />
</form:form>
</body>
</html>