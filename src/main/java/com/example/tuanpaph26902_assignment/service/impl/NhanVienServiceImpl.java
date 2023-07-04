package com.example.tuanpaph26902_assignment.service.impl;

import com.example.tuanpaph26902_assignment.entity.NhanVien;
import com.example.tuanpaph26902_assignment.repository.NhanVienRepository;
import com.example.tuanpaph26902_assignment.request.NhanVienRequest;
import com.example.tuanpaph26902_assignment.service.ServiceRequest;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class NhanVienServiceImpl implements ServiceRequest<NhanVien, NhanVienRequest> {

    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private CuaHangServiceImpl cuaHangService = new CuaHangServiceImpl();
    private ChucVuServiceImpl chucVuService = new ChucVuServiceImpl();

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.getAll();
    }

    @Override
    public NhanVien getOne(UUID id) {
        return nhanVienRepository.getOne(id);
    }

    @Override
    public Boolean save(NhanVienRequest request) {
        NhanVien nhanVien = new NhanVien();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        nhanVien.setMa(request.getMa());
        nhanVien.setHo(request.getHo());
        nhanVien.setTenDem(request.getTenDem());
        nhanVien.setTen(request.getTen());
        nhanVien.setDiaChi(request.getDiaChi());
        nhanVien.setGioiTinh(request.getGioiTinh());
        nhanVien.setSdt(request.getSdt());
        nhanVien.setMatKhau(request.getMatKhau());
        nhanVien.setTrangThai(request.getTrangThai());
        try {
            nhanVien.setNgaySinh(sdf.parse(request.getNgaySinh()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        nhanVien.setChucVu(chucVuService.getOne(UUID.fromString(request.getIdChucVu())));
        nhanVien.setCuaHang(cuaHangService.getOne(UUID.fromString(request.getIdCuaHang())));
        return nhanVienRepository.add(nhanVien);
    }

    @Override
    public Boolean update(NhanVienRequest request) {
        NhanVien nhanVien = new NhanVien();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        nhanVien.setId(UUID.fromString(request.getId()));
        nhanVien.setMa(request.getMa());
        nhanVien.setHo(request.getHo());
        nhanVien.setTenDem(request.getTenDem());
        nhanVien.setTen(request.getTen());
        nhanVien.setDiaChi(request.getDiaChi());
        nhanVien.setGioiTinh(request.getGioiTinh());
        nhanVien.setSdt(request.getSdt());
        nhanVien.setMatKhau(request.getMatKhau());
        nhanVien.setTrangThai(request.getTrangThai());
        nhanVien.setChucVu(chucVuService.getOne(UUID.fromString(request.getIdChucVu())));
        nhanVien.setCuaHang(cuaHangService.getOne(UUID.fromString(request.getIdCuaHang())));
        try {
            nhanVien.setNgaySinh(sdf.parse(request.getNgaySinh()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVienRepository.update(nhanVien);
    }

    @Override
    public Boolean delete(NhanVien nhanVien) {
        return nhanVienRepository.delete(nhanVien);
    }

    @Override
    public NhanVien getByMa(String ma) {
        return nhanVienRepository.getByMa(ma);
    }

    @Override
    public String validate(NhanVienRequest request) {
        String error = null;
        if (request.getDiaChi().trim().length() == 0
                || request.getHo().trim().length() == 0
                || request.getMa().trim().length() == 0
                || request.getSdt().trim().length() == 0
                || request.getTenDem().trim().length() == 0
                || request.getTen().trim().length() == 0) {
            error = "Không được để trống";
        } else {
            if (!request.getSdt().trim().matches("^0+[0-9]{9}")) {
                error = "sdt phải bắt đầu bằng số 0 và có đủ 10 số";
            }
        }
        NhanVien check = getByMa(request.getMa());
        if (check != null) {
            error = "Mã đã tồn tại";
        }
        return error;
    }
}
