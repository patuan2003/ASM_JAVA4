package com.example.tuanpaph26902_assignment.service.impl;

import com.example.tuanpaph26902_assignment.entity.ChiTietSanPham;
import com.example.tuanpaph26902_assignment.repository.ChiTietSanPhamRepository;
import com.example.tuanpaph26902_assignment.request.ChiTietSanPhamRequest;
import com.example.tuanpaph26902_assignment.service.ServiceRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ChiTietSanPhamServiceImpl implements ServiceRequest<ChiTietSanPham, ChiTietSanPhamRequest> {

    private ChiTietSanPhamRepository chiTietSPRepository = new ChiTietSanPhamRepository();
    private SanPhamServiceImpl sanPhamService = new SanPhamServiceImpl();
    private MauSacServiceImpl mauSacService = new MauSacServiceImpl();
    private DongSanPhamServiceImpl dongSPService = new DongSanPhamServiceImpl();
    private NhaSanXuatServiceImpl nsxService = new NhaSanXuatServiceImpl();

    @Override
    public List<ChiTietSanPham> getAll() {
        return chiTietSPRepository.getAll();
    }

    @Override
    public ChiTietSanPham getOne(UUID id) {
        return chiTietSPRepository.getOne(id);
    }

    @Override
    public Boolean save(ChiTietSanPhamRequest request) {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        chiTietSanPham.setGiaBan(new BigDecimal(request.getGiaBan()));
        chiTietSanPham.setGiaNhap(new BigDecimal(request.getGiaNhap()));
        chiTietSanPham.setMoTa(request.getMoTa());
        chiTietSanPham.setSoLuongTon(Integer.valueOf(request.getSoLuongTon()));
        chiTietSanPham.setNamBH(Integer.valueOf(request.getNamBH()));
        chiTietSanPham.setSanPham(sanPhamService.getOne(UUID.fromString(request.getIdSanPham())));
        chiTietSanPham.setMauSac(mauSacService.getOne(UUID.fromString(request.getIdMauSac())));
        chiTietSanPham.setDongSanPham(dongSPService.getOne(UUID.fromString(request.getIdDongSanPham())));
        chiTietSanPham.setNhaSanXuat(nsxService.getOne(UUID.fromString(request.getIdNhaSanXuat())));
        return chiTietSPRepository.add(chiTietSanPham);
    }

    @Override
    public Boolean update(ChiTietSanPhamRequest request) {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        chiTietSanPham.setId(UUID.fromString(request.getId()));
        chiTietSanPham.setGiaBan(new BigDecimal(request.getGiaBan()));
        chiTietSanPham.setGiaNhap(new BigDecimal(request.getGiaNhap()));
        chiTietSanPham.setMoTa(request.getMoTa());
        chiTietSanPham.setSoLuongTon(Integer.valueOf(request.getSoLuongTon()));
        chiTietSanPham.setNamBH(Integer.valueOf(request.getNamBH()));
        chiTietSanPham.setSanPham(sanPhamService.getOne(UUID.fromString(request.getIdSanPham())));
        chiTietSanPham.setMauSac(mauSacService.getOne(UUID.fromString(request.getIdMauSac())));
        chiTietSanPham.setDongSanPham(dongSPService.getOne(UUID.fromString(request.getIdDongSanPham())));
        chiTietSanPham.setNhaSanXuat(nsxService.getOne(UUID.fromString(request.getIdNhaSanXuat())));
        return chiTietSPRepository.update(chiTietSanPham);
    }

    @Override
    public Boolean delete(ChiTietSanPham chiTietSanPham) {
        return chiTietSPRepository.delete(chiTietSanPham);
    }

    @Override
    public ChiTietSanPham getByMa(String ma) {
        return null;
    }

    @Override
    public String validate(ChiTietSanPhamRequest request) {
        String error = null;
        if (request.getGiaBan().trim().length() == 0 ||
                request.getGiaNhap().trim().length() == 0 ||
                request.getGiaNhap().trim().length() == 0 ||
                request.getNamBH().trim().length() == 0 ||
                request.getSoLuongTon().trim().length() == 0 ||
                request.getMoTa().trim().length() == 0) {
            error = "Không được để trống";
        } else {
            if (Double.valueOf(request.getGiaNhap()) >= Double.valueOf(request.getGiaBan())) {
                error = "Giá bán phải lớn hơn giá nhập";
            }
        }
        return error;
    }

}
