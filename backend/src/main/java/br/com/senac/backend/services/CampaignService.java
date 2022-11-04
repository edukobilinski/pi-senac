package br.com.senac.backend.services;

import br.com.senac.backend.entities.Campaign;
import br.com.senac.backend.exceptions.DatabaseException;
import br.com.senac.backend.exceptions.ResourceNotFoundException;
import br.com.senac.backend.repositories.CampaignRepository;
import br.com.senac.backend.utils.ContextLog;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static br.com.senac.backend.utils.MessageLogsEnum.*;

@Log
@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public List<Campaign> findCampaignByUserId(Long id) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        var list = campaignRepository.findCampaignByUser_Id(id);

        log.info(SRV_0001D.logContext(context.getClasse(), context.getMetodo()));

        return list;
    }

    public List<Campaign> findAll() {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        var list = campaignRepository.findAll();

        log.info(SRV_0001D.logContext(context.getClasse(), context.getMetodo()));

        return list;
    }

    public Campaign findById(Long id) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        var obj = campaignRepository.findById(id);

        if (!obj.isEmpty()) {
            log.info(SRV_0001D.logContext(context.getClasse(), context.getMetodo()));
        } else {
            log.severe(SRV_0001E.getObjDescription(id.toString()));
        }
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Campaign insert(Campaign obj) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();

        var objSaved = campaignRepository.save(obj);
        if (objSaved != null) {
            log.info(SRV_0001D.logContext(context.getClasse(), context.getMetodo()));
        }
        return objSaved;
    }

    public void delete(Long id) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        try {
            campaignRepository.deleteById(id);
            log.info(SRV_0001D.logContext(context.getClasse(), context.getMetodo()));
        } catch (EmptyResultDataAccessException e) {
            log.severe(SRV_0001E.getObjDescription(id.toString()));
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            log.severe(SRV_0002E.getDescriptionAndException(e.getMessage()));
            throw new DatabaseException(e.getMessage());
        }
    }

    public Campaign update(Long id, Campaign obj) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        try {
            Campaign entity = campaignRepository.getReferenceById(id);

            updateData(entity, obj);

            var objSaved = campaignRepository.save(entity);

            if (objSaved != null) {
                log.info(SRV_0001D.logContext(context.getClasse(), context.getMetodo()));
            }
            return objSaved;
        } catch (EntityNotFoundException e) {
            log.severe(SRV_0001E.getObjDescription(id.toString()));
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Campaign entity, Campaign obj) {
        entity.setName(obj.getName());
        entity.setTargetValue(obj.getTargetValue());
        entity.setBeneficiary(obj.getBeneficiary());
        entity.setBankName(obj.getBankName());
        entity.setAccountAgency(obj.getAccountAgency());
        entity.setAccountIdentification(obj.getAccountIdentification());
        entity.setCampaignDescription(obj.getCampaignDescription());
    }

}
