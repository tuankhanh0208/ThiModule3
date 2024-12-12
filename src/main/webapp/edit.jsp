<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 12-Dec-24
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h2>Add new product</h2>
    <form action="${pageContext.request.contextPath}/products?path=add" method="post">
        <div class="mb-3">
            <label for="productName" class="form-label">Name</label>
            <input type="text" class="form-control" id="productName" name="name">
        </div>
        <div class="mb-3">
            <label for="productPrice" class="form-label">Price</label>
            <input type="text" class="form-control" id="productPrice" name="price">
        </div>
        <div class="mb-3">
            <label for="productQuantity" class="form-label">Quantity</label>
            <input type="text" class="form-control" id="productQuantity" name="quantity">
        </div>
        <div class="mb-3">
            <label for="productColor" class="form-label">Color</label>
            <input type="text" class="form-control" id="productColor" name="color">
        </div>
        <div class="mb-3">
            <label for="productDescription" class="form-label">Description</label>
            <textarea class="form-control" id="productDescription" rows="3" name="description"></textarea>
        </div>
        <div class="mb-3">
            <label for="productCategory" class="form-label">Category</label>
            <select class="form-select" id="productCategory" name="categoryMethod">
                <c:forEach items="${categoriesList}" var="category">
                    <option value="${category.cid}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Create</button>
        <button type="button" class="btn btn-secondary">Back</button>
    </form>
</div>
</body>
</html>

