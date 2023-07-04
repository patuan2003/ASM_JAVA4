<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/25/2023
  Time: 7:18 AM
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
<section class="container w-75 mt-5">
    <h1 class="text-secondary text-center">KHÁCH HÀNG</h1>
    <form action="/khach-hang/add" method="post">
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Mã</label>
                <input class="form-control" name="ma" value="${kh.ma}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Sdt</label>
                <input type="number" class="form-control" name="sdt" value="${kh.sdt}"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Họ</label>
                <input class="form-control" name="ho"value="${kh.ho}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Địa chỉ</label>
                <input class="form-control" name="diaChi" value="${kh.diaChi}"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Tên đệm</label>
                <input class="form-control" name="tenDem" value="${kh.tenDem}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Thành phố</label>
                <input class="form-control" name="thanhPho" value="${kh.thanhPho}"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Tên</label>
                <input class="form-control" name="ten" value="${kh.ten}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Quốc gia</label>
                <select class="form-select" aria-label="Default select example" name="quocGia">
                    <option value="VN" ${kh.quocGia == "VN" ? "selected":""}>Việt Nam</option>
                    <option value="TL" ${kh.quocGia == "TL" ? "selected":""}>Thái Lan</option>
                </select></div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Ngày sinh</label>
                <input type="date" class="form-control" name="ngaySinh" value="${ngaySinh}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Mật khẩu</label>
                <input class="form-control" type="password" name="matKhau"/>
            </div>
        </div>
        <br/>
        <span class="text text-danger">${errorKH}</span>
        <br/>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>

    <table class="table container">
        <thead>
        <tr>
            <td>Ma</td>
            <td>Ho Ten</td>
            <td>Ngay Sinh</td>
            <td>Sdt</td>
            <td>Dia Chi</td>
            <td>Thanh Pho</td>
            <td>Quoc Gia</td>
             <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${khs}" var="x">
            <tr>
                 <td>${x.ma}</td>
                <td>${x.ho} ${x.tenDem} ${x.ten}</td>
                <td>${sdf.format(x.ngaySinh)}</td>
                <td>${x.sdt}</td>
                <td>${x.diaChi}</td>
                <td>${x.thanhPho}</td>
                <td>${x.quocGia == "VN" ? "Việt Nam" : "Thái Lan"}</td>
                 <td>
                     <a href="/khach-hang/detail?id=${x.id}" class="text-warning"><i
                             class="fa-solid fa-circle-info"></i></a>
                     <a href="/khach-hang/view-update?id=${x.id}" class="text-success"><i
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
        let option = confirm("Bạn có muốn xóa?");
        if (option == true) {
            window.location.href = '/khach-hang/delete?id=' + id
            alert("Xóa thành công");
        }
    }
</script>
</body>
</html>
