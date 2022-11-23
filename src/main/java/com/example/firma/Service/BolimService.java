package com.example.firma.Service;

import com.example.firma.DTO.APIResponse;
import com.example.firma.DTO.BolimDTO;
import com.example.firma.Entite.Bolim;
import com.example.firma.Entite.Ishchi;
import com.example.firma.Repozitary.BolimRepozitary;
import com.example.firma.Repozitary.FirmaRepozitary;
import com.example.firma.Repozitary.IshchiRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BolimService {
    @Autowired
    BolimRepozitary bolimRepozitary;
    @Autowired
    IshchiRepozitary ishchiRepozitary;
    @Autowired
    FirmaRepozitary firmaRepozitary;

    public APIResponse postbolim(BolimDTO bolimDTO) {
        Bolim bolim=new Bolim();
        bolim.setNomi(bolimDTO.getNomi());
        bolim.setFirma(firmaRepozitary.findById(bolimDTO.getFirmaId()).get());
        bolimRepozitary.save(bolim);
        return new APIResponse("Malumot saqlandi",true);
    }

    public APIResponse taxrirlash(Integer id, BolimDTO bolimDTO) {
        Optional<Bolim> byId = bolimRepozitary.findById(id);
        if(byId.isPresent()) {
            Bolim bolim=byId.get();
            bolim.setNomi(bolimDTO.getNomi());
            bolim.setFirma(firmaRepozitary.findById(bolimDTO.getFirmaId()).get());
            bolimRepozitary.save(bolim);
            return new APIResponse("Malumot salandi",true);
        }
        return null;
    }

    public APIResponse oqish(BolimDTO bolimDTO) {
        List<Bolim> All=bolimRepozitary.findAll();
        return new APIResponse(All.toString(),true);
    }

    public APIResponse oqishID(Integer id) {
        Optional<Bolim> all=bolimRepozitary.findById(id);
        if(all.isPresent()) return new APIResponse(all.toString(),true);
        return new APIResponse("Malumot mavjud emas",false);
    }

    public APIResponse deleteid(Integer id) {
        Optional<Ishchi> delete = ishchiRepozitary.findById(id);
        if (delete.isPresent()) {
            firmaRepozitary.deleteById(id);
            return new APIResponse("malumot ochirildi", true);
        }
        return new APIResponse("malumot mavjudmas", false);
    }

}
