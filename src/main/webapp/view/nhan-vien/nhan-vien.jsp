<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/25/2023
  Time: 9:54 AM
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
    <h1 class="text-center text-secondary">NHÂN VIÊN</h1>
    <form action="/nhan-vien/add" method="post">
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
                <input type="password" class="form-control" name="matKhau"/>
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
                           checked ${nv.gioiTinh == "Nam" ? "checked":""}/>
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
        <br>
        <span class="text text-danger" role="alert">${errorNV}</span>
        <br>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Mã</th>
            <th scope="col">Họ tên</th>
            <th scope="col">Ngày sinh</th>
            <th scope="col">Giới tính</th>
            <th scope="col">Địa chỉ</th>
            <th scope="col">Sdt</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Chức vụ</th>
            <th scope="col">Của hàng</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${nvs}" var="x" varStatus="viTri">
            <tr>
                <th scope="row">${viTri.index+1}</th>
                <td>${x.ma}</td>
                <td>${x.ho} ${x.tenDem} ${x.ten} </td>
<%--                <td>${sdf.format(x.ngaySinh)}</td>--%>
                <td>${x.gioiTinh}</td>
                <td>${x.diaChi}</td>
                <td>${x.sdt}</td>
                <td>${x.trangThai == 1 ? "Hoạt động" : "Không hoạt động"}</td>
                <td>${x.chucVu.ten}</td>
                <td>${x.cuaHang.ten}</td>
                <td>
                    <a href="/nhan-vien/detail?id=${x.id}" class="text-warning"><i
                            class="fa-solid fa-circle-info"></i></a>
                    <a href="/nhan-vien/view-update?id=${x.id}" class="text-success"><i
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
            window.location.href = '/nhan-vien/delete?id=' + id
            alert("Xóa thành công");
        }
    }
</script>
</body>
</html>
