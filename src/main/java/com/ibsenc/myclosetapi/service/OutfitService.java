package com.ibsenc.myclosetapi.service;

import com.ibsenc.myclosetapi.exceptions.OutfitNotFoundException;
import com.ibsenc.myclosetapi.exceptions.ResourceNotFoundException;
import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.model.Outfit;
import com.ibsenc.myclosetapi.repository.ImageRepository;
import com.ibsenc.myclosetapi.repository.OutfitRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class OutfitService {

  private final OutfitRepository outfitRepository;
  private final ArticleService articleService;
  private final ImageRepository imageRepository;

  public OutfitService(OutfitRepository outfitRepository, ArticleService articleService,
      ImageRepository imageRepository) {
    this.outfitRepository = outfitRepository;
    this.articleService = articleService;
    this.imageRepository = imageRepository;
  }

  public Outfit createOutfit(Outfit newOutfit) {
    newOutfit.setId(UUID.randomUUID().toString());
    // Prevents setting of images in create endpoint
    newOutfit.setImageFileNames(new ArrayList<>());
    // Prevents setting of articles in create endpoint
    newOutfit.setArticleIds(new ArrayList<>());

    return outfitRepository.save(newOutfit);
  }

  public Outfit getOutfit(String id) throws OutfitNotFoundException {
    return outfitRepository.findById(id)
        .orElseThrow(() -> new OutfitNotFoundException(id));
  }

  public void deleteOutfit(String id) {
    // Delete all images associated with the outfit
    try {
      getOutfit(id).getImageFileNames().stream()
          .forEach(imageFile -> imageRepository.deleteImage(imageFile));
    } catch (OutfitNotFoundException e) {
      log.error(e.getMessage());
    }
    outfitRepository.deleteById(id);
  }

  public Outfit updateOutfit(Outfit outfit) throws ResourceNotFoundException, IOException {
    final Outfit existingOutfit = getOutfit(outfit.getId());

    if (outfit.getName() != null) {
      existingOutfit.setName(outfit.getName());
    }

    if (outfit.getDescription() != null) {
      existingOutfit.setDescription(outfit.getDescription());
    }

    if (outfit.getImageFileNames() != null) {
      for (String imageFileName : outfit.getImageFileNames()) {
        imageRepository.getImage(imageFileName);
      }
      existingOutfit.setImageFileNames(outfit.getImageFileNames());
    }

    // Adds existing article(s) to outfit
    if (outfit.getArticleIds() != null) {
      for (String articleId : outfit.getArticleIds()) {
          articleService.getArticle(articleId);
      }
      existingOutfit.setArticleIds(outfit.getArticleIds());
    }

    return outfitRepository.save(existingOutfit);
  }

  public List<Outfit> getAllOutfits(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    Page<Outfit> pagedResult = outfitRepository.findAll(paging);

    return pagedResult.hasContent() ? pagedResult.getContent() : new ArrayList<>();
  }

  @SneakyThrows
  public Outfit addImageToOutfit(String outfitId, MultipartFile file) {
    final Outfit existingOutfit = this.getOutfit(outfitId);

    final String imageFileName = imageRepository.uploadImage(file);

    final List<String> outfitImageNames = existingOutfit.getImageFileNames();
    outfitImageNames.add(imageFileName);
    existingOutfit.setImageFileNames(outfitImageNames);

    return this.updateOutfit(existingOutfit);
  }

  public void deleteImageFromOutfit(String outfitId, String fileName)
      throws OutfitNotFoundException {
    // Remove image from the outfit's list of imageFileNames
    final Outfit outfit = this.getOutfit(outfitId);

    List<String> filteredFileNames = outfit.getImageFileNames()
        .stream()
        .filter(f -> !fileName.equals(f))
        .collect(Collectors.toList());

    outfit.setImageFileNames(filteredFileNames);
    outfitRepository.save(outfit);

    // Delete image from S3
    imageRepository.deleteImage(fileName);
  }

  public void removeArticleFromOutfit(String outfitId, String articleId)
      throws OutfitNotFoundException {
    // Remove article with articleId from the outfit's list of articleIds
    final Outfit outfit = this.getOutfit(outfitId);

    List<String> filteredArticleIds = outfit.getArticleIds()
        .stream()
        .filter(id -> !articleId.equals(id))
        .collect(Collectors.toList());

    outfit.setArticleIds(filteredArticleIds);
    outfitRepository.save(outfit);
  }
}
