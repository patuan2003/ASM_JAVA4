<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/25/2023
  Time: 7:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body>
<section class="container w-50 mt-5">
    <form action="/ctsp/update" method="post">
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Năm bảo hành</label>
                <input class="form-control" name="namBH" value="${sp.namBH}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Số lượng tồn</label>
                <input class="form-control" name="soLuongTon" value="${sp.soLuongTon}"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Giá nhập</label>
                <input class="form-control" name="giaNhap" value="${sp.giaNhap}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Giá bán</label>
                <input class="form-control" name="giaBan" value="${sp.giaBan}"/>
            </div>
        </div>
        <div class="form-group mb-3">
            <label>Mô tả</label>
            <input class="form-control" name="moTa" value="${sp.moTa}"/>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Sản phẩm</label>
                <select class="form-select" aria-label="Default select example" name="idSanPham">
                    <c:forEach items="${sanPhams}" var="x">
                        <option value="${x.id}" ${x.id == sp.sanPham.id ? "selected":""}>${x.ten}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label>Màu sắc</label>
                <select class="form-select" aria-label="Default select example" name="idMauSac">
                    <c:forEach items="${mauSacs}" var="x">
                        <option value="${x.id}" ${x.id == sp.mauSac.id ? "selected":""}>${x.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row mb-3">
        <div class="form-group col-md-6">
            <label>Nhà sản xuất</label>
            <select class="form-select" aria-label="Default select example" name="idNhaSanXuat">
                <c:forEach items="${nhaSanXuats}" var="x">
                    <option value="${x.id}" ${x.id == sp.nhaSanXuat.id ? "selected": ""} >${x.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-md-6">
            <label>Dòng sản phẩm</label>
            <select class="form-select" aria-label="Default select example" name="idDongSanPham">
                <c:forEach items="${dongSanPhams}" var="x">
                    <option value="${x.id}" ${x.id == sp.dongSanPham.id ? "selected": ""}>${x.ten}</option>
                </c:forEach>
            </select>
        </div>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</section>
</body>
</html>
