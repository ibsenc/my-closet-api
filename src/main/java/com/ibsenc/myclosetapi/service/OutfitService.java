package com.ibsenc.myclosetapi.service;

import com.ibsenc.myclosetapi.repository.OutfitRepository;
import org.springframework.stereotype.Service;

@Service
public class OutfitService {

  private final OutfitRepository outfitRepository;

  public OutfitService(OutfitRepository outfitRepository) {
    this.outfitRepository = outfitRepository;
  }
}
