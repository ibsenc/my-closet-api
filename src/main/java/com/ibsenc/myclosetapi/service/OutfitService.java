package com.ibsenc.myclosetapi.service;

import com.ibsenc.myclosetapi.model.Outfit;
import com.ibsenc.myclosetapi.repository.OutfitRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OutfitService {

  private final OutfitRepository outfitRepository;

  public OutfitService(OutfitRepository outfitRepository) {
    this.outfitRepository = outfitRepository;
  }

  public Outfit createOutfit(Outfit newOutfit) {
    newOutfit.setId(UUID.randomUUID().toString());

    return outfitRepository.save(newOutfit);
  }
}
