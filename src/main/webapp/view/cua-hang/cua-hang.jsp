<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/25/2023
  Time: 7:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets/fontawesome-free-6.3.0-web/css/all.min.css">
</head>
<body>
<section class="container w-50 mt-5">
    <h1 class="text-center text-secondary">CỬA HÀNG</h1>
    <form action="/cua-hang/add" method="post">
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
                <input class="form-control" name="thanhPho" value="${ch.thanhPho}"/>
            </div>
        </div>
        <div class="form-group mb-3">
            <label>Quốc gia</label>
            <select class="form-select" aria-label="Default select example" name="quocGia">
                <option value="VN" ${ch.quocGia == "VN" ? "selected":""}>Việt Nam</option>
                <option value="TL" ${ch.quocGia == "TL" ? "selected":""}>Thái Lan</option>
            </select>
        </div>
        <br>
        <span class="text text-danger" role="alert">${errorCH}</span>
        <br>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Mã</th>
            <th scope="col">Tên</th>
            <th scope="col">Địa chỉ</th>
            <th scope="col">Thành phố</th>
            <th scope="col">Quốc gia</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${chs}" var="x" varStatus="viTri">
            <tr>
                <th scope="row">${viTri.index}</th>
                <td>${x.ma}</td>
                <td>${x.ten}</td>
                <td>${x.diaChi}</td>
                <td>${x.thanhPho}</td>
                <td>${x.quocGia == "VN" ? "Việt Nam" : "Thái Lan"}</td>
                <td>
                    <a href="/cua-hang/detail?id=${x.id}" class="text-warning"><i
                            class="fa-solid fa-circle-info"></i></a>
                    <a href="/cua-hang/view-update?id=${x.id}" class="text-success"><i
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
    let btnXoa = (id) =>{
        let option = confirm("Bạn có muốn xóa?");
        if (option == true){
            window.location.href = '/cua-hang/delete?id='+id
            alert("Xóa thành công");
        }
    }
</script>
</body>
</html>
