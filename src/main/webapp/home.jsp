<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 12-Dec-24
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .delete-confirm-checkbox {
        display: none;
    }

    .delete-confirm {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 9999;
        justify-content: center;
        align-items: center;
    }

    .confirm-box {
        background: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        text-align: center;
        width: 300px;
    }

    .delete-confirm-checkbox:checked + .delete-confirm {
        display: flex;
    }

    .confirm-box .btn {
        margin: 10px;
        text-decoration: none;
        padding: 10px 20px;
        border-radius: 5px;
        color: white;
    }

    .confirm-box .btn-danger {
        background-color: #dc3545;
    }

    .confirm-box .btn-secondary {
        background-color: #6c757d;
    }


</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h4>Management Product</h4>
        <a href="${pageContext.request.contextPath}/products?path=add"><button class="btn btn-dark"><i class="fas fa-plus"></i> Add Product</button></a>
    </div>
    <form method="get" action="/products?path=search">
    <div class="card p-3 mb-3">

        <h5><i class="fas fa-search"></i> Find Product</h5>
        <div class="row g-2">
            <div class="col-md-3">
                <input hidden="hidden" name="path" value="search">
                <input type="text" class="form-control" placeholder="Enter Product Name" name="keyword">
            </div>
            <div class="col-md-2">
                <input hidden="hidden" name="path" value="search">
                <input type="text" class="form-control" placeholder="Enter Price" name="price">
            </div>
            <div class="col-md-2">
                <input type="text" class="form-control" placeholder="Enter Category" name="keyword">
            </div>
            <div class="col-md-2">
                <input type="text" class="form-control" placeholder="Enter Color" name="keyword">
            </div>
            <div class="col-md-1 d-flex align-items-center">
                <button class="btn btn-outline-secondary"><i class="fas fa-sync-alt"></i></button>
            </div>
            <div class="col-md-2">
                <button class="btn btn-primary w-100"><i class="fas fa-search"></i> Search</button>
            </div>
        </div>
    </div>
    </form>
    <p class="text-danger">${text}</p>
    <form method="post" action="${pageContext.request.contextPath}/products">
    <table class="table table-bordered">
        <thead class="table-primary">
        <tr>
            <th>STT</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Color</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${list}" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.quantity}</td>
            <td>${item.color}</td>
            <td>${item.categoryId.name}</td>
            <td>
                <a href="#" class="text-primary"><i class="fas fa-edit"></i> Edit</a> |
                <label for="delete-${item.id}" class="text-danger" style="cursor: pointer;">
                    <i class="fas fa-trash"></i> Delete
                </label>
                <input type="checkbox" id="delete-${item.id}" class="delete-confirm-checkbox">
                <div class="delete-confirm">
                    <div class="confirm-box">
                        <p>Bạn có chắc chắn muốn xóa sản phẩm này không?</p>
                        <a href="${pageContext.request.contextPath}/products?path=delete&id=${item.id}" class="btn btn-danger">Xóa</a>
                        <label for="delete-${item.id}" class="btn btn-secondary">Hủy</label>
                    </div>
                </div>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybBogGzOgQpeP8pgi04B3p7r6L2e8zBOHp+0m9Qd8Pp6L6gD6" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-pZjw8f+ua7Kw1TIq5tvbLrQ+0WfGe1Ff5Lg5ZC2oqvN8Kp1U+6E5z0I5qD0XGf6z" crossorigin="anonymous"></script>
</body>
</html>
