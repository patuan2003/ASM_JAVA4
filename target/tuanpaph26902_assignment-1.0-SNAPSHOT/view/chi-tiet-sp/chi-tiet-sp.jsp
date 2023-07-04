<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/25/2023
  Time: 7:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets/fontawesome-free-6.3.0-web/css/all.min.css">
</head>
<body>
<section class="container w-75 mt-5">
    <h1 class="text-center text-secondary">CHI TIẾT SẢN PHẨM</h1>
    <form action="/ctsp/add" method="post">
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Năm bảo hành</label>
                <input type="text" class="form-control" name="namBH" value="${sp.namBH}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Số lượng tồn</label>
                <input type="number" class="form-control" name="soLuongTon" value="${sp.soLuongTon}"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Giá nhập</label>
                <input type="number" class="form-control" name="giaNhap" value="${sp.giaNhap}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Giá bán</label>
                <input type="number" class="form-control" name="giaBan" value="${sp.giaBan}"/>
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
                        <option value="${x.id}" ${x.id == sp.sanPham.id ? "selected": ""} >${x.ten}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label>Màu sắc</label>
                <select class="form-select" aria-label="Default select example" name="idMauSac">
                    <c:forEach items="${mauSacs}" var="x">
                        <option value="${x.id}"  ${x.id == sp.mauSac.id ? "selected": ""} >${x.ten}</option>
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
        <br>
        <span class="text text-danger" role="alert">${errorCTSP}</span>
        <br>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Năm Bảo hành</th>
            <th scope="col">Số lượng tồn</th>
            <th scope="col">Giá nhập</th>
            <th scope="col">Giá bán</th>
            <th scope="col">Mô tả</th>
            <th scope="col">Sản phẩm</th>
            <th scope="col">Màu sắc</th>
            <th scope="col">Nhà sản xuất</th>
            <th scope="col">Dòng sản phẩm</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ctsps}" var="x" varStatus="viTri">
            <tr>
                <th scope="row">${viTri.index+1}</th>
                <td>${x.namBH}</td>
                <td>${x.soLuongTon}</td>
                <td>${x.giaNhap}</td>
                <td>${x.giaBan}</td>
                <td>${x.moTa}</td>
                <td>${x.sanPham.ten}</td>
                <td>${x.mauSac.ten}</td>
                <td>${x.nhaSanXuat.ten}</td>
                <td>${x.dongSanPham.ten}</td>
                <td>
                    <a href="/ctsp/detail?id=${x.id}" class="text-warning"><i
                            class="fa-solid fa-circle-info"></i></a>
                    <a href="/ctsp/view-update?id=${x.id}" class="text-success"><i
                            class="fa-regular fa-pen-to-square"></i></a>
                    <a href="" onclick="btnXoa('${x.id}')" class="text-danger"><i
                            class="fa-solid fa-trash"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
<script>
    let btnXoa=(id)=>{
        let option =confirm("Bạn có muốn xóa?");
        if (option == true){
            window.location.href = '/ctsp/delete?id='+id;
            alert("Xóa thành công");
        }
    }
</script>
</body>
</html>
