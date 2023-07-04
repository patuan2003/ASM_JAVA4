package com.example.tuanpaph26902_assignment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietSanPhamRequest {

    private String id;
    private String namBH;
    private String soLuongTon;
    private String giaNhap;
    private String giaBan;
    private String moTa;
    private String idSanPham;
    private String idMauSac;
    private String idDongSanPham;
    private String idNhaSanXuat;

}
