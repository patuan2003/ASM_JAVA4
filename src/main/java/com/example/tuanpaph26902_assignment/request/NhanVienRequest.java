package com.example.tuanpaph26902_assignment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienRequest {

    private String id;
    private String ma;
    private String ho;
    private String tenDem;
    private String ten;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;
    private String sdt;
    private String matKhau;
    private String idCuaHang;
    private String idChucVu;
    private Integer trangThai;

}
