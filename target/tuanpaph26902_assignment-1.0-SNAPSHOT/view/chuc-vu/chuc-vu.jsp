<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/21/2023
  Time: 10:54 AM
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

<section class="container w-75">
    <h1 class="text-center text-secondary mt-3 mb-3">QUẢN LÝ CHỨC VỤ</h1>
    <form action="/chuc-vu/add" method="post">
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
        <br>
        <span class="text text-danger" role="alert">${errorCV}</span>
        <br>
        <button type="submit" class="btn btn-success">Add</button>
    </form>


    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Mã</th>
            <th scope="col">Tên</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${chucVus}" var="x" varStatus="viTri">
            <tr>
                <th scope="row">${viTri.index+1}</th>
                <td>${x.ma}</td>
                <td>${x.ten}</td>
                <td>
                    <a href="/chuc-vu/detail?id=${x.id}" class="text-warning"><i
                            class="fa-solid fa-circle-info"></i></a>
                    <a href="/chuc-vu/view-update?id=${x.id}" class="text-success"><i
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
     let btnXoa = (id) => {
         let option = confirm("Bạn có muốn xóa");
         if (option == true){
             window.location.href = '/chuc-vu/delete?id=' + id;
             alert("Xóa thành công")
         }
     }
</script>
</body>
</html>
