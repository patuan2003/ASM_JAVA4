<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/25/2023
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<section class="container w-50 mt-5">
    <h1 class="text-center text-secondary">UPDATE CỬA HÀNG</h1>
    <form action="/cua-hang/update" method="post">
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Mã</label>
                <input class="form-control" name="ma" value="${ch.ma}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Địa chỉ</label>
                <input class="form-control" name="diaChi" value="${ch.diaChi}"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Tên</label>
                <input class="form-control" name="ten" value="${ch.ten}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Thành phố</label>
                <input class="form-control" name="thanhPho" value="${ch.thanhPho}" />
            </div>
        </div>
        <div class="form-group mb-3">
            <label>Quốc gia</label>
            <select class="form-select" aria-label="Default select example" name="quocGia">
                <option value="VN" ${ch.quocGia == "VN" ? "selected":""}>Việt Nam</option>
                <option value="TL" ${ch.quocGia == "TL" ? "selected":""}>Thái Lan</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</section>
</body>
</html>
