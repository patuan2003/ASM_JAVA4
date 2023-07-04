<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/25/2023
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body>
<section class="container w-75 mt-5">
    <h1 class="text-center text-secondary">UPDATE NHÂN VIÊN</h1>
    <form action="/nhan-vien/update" method="post">
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Mã</label>
                <input class="form-control" name="ma" value="${nv.ma}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Sdt</label>
                <input class="form-control" name="sdt" value="${nv.sdt}"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Họ</label>
                <input class="form-control" name="ho" value="${nv.ho}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Địa chỉ</label>
                <input class="form-control" name="diaChi" value="${nv.diaChi}"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Tên đệm</label>
                <input class="form-control" name="tenDem" value="${nv.tenDem}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Mật khẩu</label>
                <input type="password" class="form-control" name="matKhau" value="${nv.matKhau}"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Tên</label>
                <input class="form-control" name="ten" value="${nv.ten}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Giới tính</label>
                <br/>
                <div class="form-check form-check-inline mt-3">
                    <input class="form-check-input" type="radio" name="gioiTinh" value="Nam"
                    ${nv.gioiTinh == "Nam" ? "checked":""}/>
                    <label class="form-check-label">Nam</label>
                </div>
                <div class="form-check form-check-inline mt-3">
                    <input class="form-check-input" type="radio" name="gioiTinh"
                           value="Nữ" ${nv.gioiTinh == "Nữ" ? "checked":""}/>
                    <label class="form-check-label">Nữ</label>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Ngày sinh</label>
                <input type="date" class="form-control" name="ngaySinh" value="${ngaySinh}"/>
            </div>
            <div class="form-group col-md-6">
                <label>Trạng thái</label>
                <select class="form-select" aria-label="Default select example" name="trangThai">
                    <option value="1" ${nv.trangThai == 1 ?"selected":""}>Hoạt động</option>
                    <option value="2" ${nv.trangThai == 2 ? "selected" : ""}>Không hoạt động</option>
                </select>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label>Nhà sản xuất</label>
                <select class="form-select" aria-label="Default select example" name="idChucVu">
                    <c:forEach items="${chucVus}" var="x">
                        <option value="${x.id}" ${x.id == nv.chucVu.id ? "selected": ""} >${x.ten}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label>Dòng sản phẩm</label>
                <select class="form-select" aria-label="Default select example" name="idCuaHang">
                    <c:forEach items="${cuaHangs}" var="x">
                        <option value="${x.id}" ${x.id == nv.cuaHang.id ? "selected": ""}>${x.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</section>
</body>
</html>
