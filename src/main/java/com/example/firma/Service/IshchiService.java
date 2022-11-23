package com.example.firma.Service;

import com.example.firma.DTO.APIResponse;
import com.example.firma.DTO.IshchiDTO;
import com.example.firma.Entite.Ishchi;
import com.example.firma.Entite.Manzil;
import com.example.firma.Repozitary.IshchiRepozitary;
import com.example.firma.Repozitary.ManzilRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IshchiService {
    @Autowired
    ManzilRepozitary manzilRepozitary;
    @Autowired
    IshchiRepozitary ishchiRepozitary;

    public APIResponse postIshchi(IshchiDTO ishchiDTO){
        Manzil manzil=new Manzil();
        manzil.setKocha(ishchiDTO.getKocha());
        manzil.setViloyat(ishchiDTO.getViloyat());
        manzil.setTuman(ishchiDTO.getTuman());
        Manzil save = manzilRepozitary.save(manzil);
        Ishchi ishchi=new Ishchi();
        ishchi.setIsmi(ishchiDTO.getIsmi());
        ishchi.setFam(ishchiDTO.getFam());
        ishchi.setLavozim(ishchiDTO.getLavozim());
        ishchi.setTelRaqam(ishchiDTO.getTelRaqam());
        ishchi.setManzil(save);
        ishchiRepozitary.save(ishchi);
        return  new APIResponse("saqlandi",true);
    }

    public APIResponse taxrirlash(Integer id, IshchiDTO ishchiDTO){
        Optional<Ishchi> byId = ishchiRepozitary.findById(id);
        if(byId.isPresent()) {
            Manzil manzil=byId.get().getManzil();
            manzil.setViloyat(ishchiDTO.getViloyat());
            manzil.setKocha(ishchiDTO.getKocha());
            manzil.setTuman(ishchiDTO.getTuman());
            Manzil save = manzilRepozitary.save(manzil);
            Ishchi ishchi=new Ishchi();
            ishchi.setIsmi(ishchiDTO.getIsmi());
            ishchi.setFam(ishchiDTO.getFam());
            ishchi.setLavozim(ishchiDTO.getLavozim());
            ishchi.setTelRaqam(ishchiDTO.getTelRaqam());
            ishchi.setManzil(save);
            ishchiRepozitary.save(ishchi);
            return new APIResponse("MAlumot taxrirlandi",true);
        }
        return null;
    }

    public APIResponse oqish(IshchiDTO ishchiDTO) {
        List<Ishchi> All=ishchiRepozitary.findAll();
        return new APIResponse(All.toString(),true);
    }


    public APIResponse oqishID(Integer id) {
        Optional<Ishchi> all=ishchiRepozitary.findById(id);
        if(all.isPresent()) return new APIResponse(all.toString(),true);
        return new APIResponse("Malumot mavjud emas",false);
    }


    public APIResponse deleteid(Integer id) {
        Optional<Ishchi> delete = ishchiRepozitary.findById(id);
        if (delete.isPresent()) {
            ishchiRepozitary.deleteById(id);
            manzilRepozitary.deleteById(id);
            return new APIResponse("malumot ochirildi", true);
        }
        return new APIResponse("malumot mavjudmas", false);
    }


}
