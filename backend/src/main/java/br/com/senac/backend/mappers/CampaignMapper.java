package br.com.senac.backend.mappers;

import br.com.senac.backend.dto.CampaignDTO;
import br.com.senac.backend.dto.CampaignSavedDTO;
import br.com.senac.backend.entities.Campaign;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {

    public CampaignDTO mapToDTO(Campaign campaign) {
        return CampaignDTO.builder()
                .id(campaign.getId())
                .name(campaign.getName())
                .targetValue(campaign.getTargetValue())
                .beneficiary(campaign.getBeneficiary())
                .bankName(campaign.getBankName())
                .accountAgency(campaign.getAccountAgency())
                .accountIdentification(campaign.getAccountIdentification())
                .campaignDescription(campaign.getCampaignDescription())
                .urlImage(campaign.getUrlImage())
                .user(campaign.getUser().getName())
                .build();
    }

    public CampaignSavedDTO mapToSavedDTO(Campaign campaign) {
        return CampaignSavedDTO.builder()
                .id(campaign.getId())
                .name(campaign.getName())
                .targetValue(campaign.getTargetValue())
                .beneficiary(campaign.getBeneficiary())
                .bankName(campaign.getBankName())
                .accountAgency(campaign.getAccountAgency())
                .accountIdentification(campaign.getAccountIdentification())
                .campaignDescription(campaign.getCampaignDescription())
                .urlImage(campaign.getUrlImage())
                .build();
    }

}
