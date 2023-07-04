package com.example.tuanpaph26902_assignment.service.impl;

import com.example.tuanpaph26902_assignment.entity.MauSac;
import com.example.tuanpaph26902_assignment.repository.MauSacRepository;
import com.example.tuanpaph26902_assignment.service.Service;

import java.util.List;
import java.util.UUID;

public class MauSacServiceImpl implements Service<MauSac> {

    private MauSacRepository mauSacRepository;

    public MauSacServiceImpl() {
        mauSacRepository = new MauSacRepository();
    }

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.getAll();
    }

    @Override
    public MauSac getOne(UUID id) {
        return mauSacRepository.getOne(id);
    }

    @Override
    public Boolean add(MauSac mauSac) {
        return mauSacRepository.add(mauSac);
    }

    @Override
    public Boolean update(MauSac mauSac) {
        return mauSacRepository.update(mauSac);
    }

    @Override
    public Boolean delete(MauSac mauSac) {
        return mauSacRepository.delete(mauSac);
    }

    @Override
    public MauSac getByMa(String ma) {
        return mauSacRepository.getById(ma);
    }

    @Override
    public String validate(MauSac mauSac) {
        String error = null;
        if (mauSac.getMa().trim().length() == 0
                || mauSac.getTen().trim().length() == 0) {
            error = "Không được để trống";
        }
        MauSac check = getByMa(mauSac.getMa());
        if (check != null) {
            error = "Mã đã tồn tại";
        }
        return error;
    }
}
