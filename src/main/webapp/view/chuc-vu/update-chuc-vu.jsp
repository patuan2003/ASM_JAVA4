<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/21/2023
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>

<body>

<section class="container w-50 border border-success p-5 mt-5">
    <form action="/chuc-vu/update" method="post">
        <div class="form-group row mb-3">
            <label class="col-sm-2 col-form-label">Mã</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="ma" value="${cv.ma}">
            </div>
        </div>
        <div class="form-group row mb-3">
            <label class="col-sm-2 col-form-label">Tên</label>
            <div class="col-sm-10">
                <input class="form-control" name="ten" value="${cv.ten}">
            </div>
        </div>
        <button type="submit" class="btn btn-success ">Update</button>
    </form>
</section>
</body>
</html>
