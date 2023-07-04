package com.example.tuanpaph26902_assignment.service.impl;

import com.example.tuanpaph26902_assignment.entity.KhachHang;
import com.example.tuanpaph26902_assignment.repository.KhachHangRepository;
import com.example.tuanpaph26902_assignment.request.KhanhHangRequest;
import com.example.tuanpaph26902_assignment.service.ServiceRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class KhachHangServiceImpl implements ServiceRequest<KhachHang, KhanhHangRequest> {

    private KhachHangRepository khachHangRepository = new KhachHangRepository();

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.getAll();
    }

    @Override
    public KhachHang getOne(UUID id) {
        return khachHangRepository.getOne(id);
    }

    @Override
    public Boolean save(KhanhHangRequest request) {
        KhachHang kh = new KhachHang();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        kh.setMa(request.getMa());
        kh.setHo(request.getHo());
        kh.setTenDem(request.getTenDem());
        kh.setTen(request.getTen());
        try {
            kh.setNgaySinh(sdf.parse(request.getNgaySinh()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        kh.setSdt(request.getSdt());
        kh.setDiaChi(request.getDiaChi());
        kh.setThanhPho(request.getThanhPho());
        kh.setQuocGia(request.getQuocGia());
        kh.setMatKhau(request.getMatKhau());
        return khachHangRepository.add(kh);
    }

    @Override
    public Boolean update(KhanhHangRequest request) {
        KhachHang kh = new KhachHang();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        kh.setId(UUID.fromString(request.getId()));
        kh.setMa(request.getMa());
        kh.setHo(request.getHo());
        kh.setTenDem(request.getTenDem());
        kh.setTen(request.getTen());
        try {
            kh.setNgaySinh(sdf.parse(request.getNgaySinh()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        kh.setSdt(request.getSdt());
        kh.setDiaChi(request.getDiaChi());
        kh.setThanhPho(request.getThanhPho());
        kh.setQuocGia(request.getQuocGia());
        kh.setMatKhau(request.getMatKhau());
        return khachHangRepository.update(kh);
    }

    @Override
    public Boolean delete(KhachHang khachHang) {
        return khachHangRepository.delete(khachHang);
    }

    @Override
    public KhachHang getByMa(String ma) {
        return khachHangRepository.getByMa(ma);
    }

    @Override
    public String validate(KhanhHangRequest khanhHangRequest) {
        String error = null;
        if (khanhHangRequest.getDiaChi().trim().length() == 0
                || khanhHangRequest.getHo().trim().length() == 0
                || khanhHangRequest.getMatKhau().trim().length() == 0
                || khanhHangRequest.getNgaySinh().trim().length() == 0
                || khanhHangRequest.getTenDem().trim().length() == 0
                || khanhHangRequest.getTen().trim().length() == 0
                || khanhHangRequest.getThanhPho().trim().length() == 0) {
            error = "Không được để trống";
        } else {
            if (!khanhHangRequest.getSdt().trim().matches("^0+[0-9]{9}")) {
                error = "sdt phải bắt đầu bằng số 0 và có đủ 10 số";
            }
        }
        KhachHang check = getByMa(khanhHangRequest.getMa());
        if (check != null) {
            error = "Mã đã tồn tại";
        }
        return error;
    }
}
