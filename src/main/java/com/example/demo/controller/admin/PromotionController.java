package com.example.demo.controller.admin;

import com.example.demo.entity.film.Seo;
import com.example.demo.entity.user.Promotion;
import com.example.demo.service.interfaces.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("admin/promotion")
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public String promotions(Model model) {
        model.addAttribute("promotionList", promotionService.findAll());
        return "admin/promotion/adminPromotions";
    }

    @GetMapping("/create")
    public String createPromotion() {
        return "admin/promotion/createPromotion";
    }

    @GetMapping(value = "/edit", params = {"promotionId"})
    public String editPromotion(Model model, @RequestParam Long promotionId) {
        Optional<Promotion> optionalPromotion = promotionService.findWithImagesById(promotionId);
        if (optionalPromotion.isPresent()) {
            model.addAttribute("promotion", optionalPromotion.get());
            return "admin/promotion/editPromotion";
        }
        return "redirect:/admin/promotion";
    }

    @GetMapping(value = "/delete", params = {"promotionId"})
    public String deletePromotion(@RequestParam Long promotionId) {
        promotionService.deleteById(promotionId);
        return "redirect:/admin/promotion";
    }

    @PostMapping("/update")
    public String updatePromotion(@RequestParam Long promotionId,
                                  @RequestParam String promotionName,
                                  @RequestParam String promotionDescription,
                                  @RequestParam String promotionVideoLink,
                                  @RequestParam Boolean promotionStatus,
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate promotionPublishDate,
                                  @RequestParam(required = false) MultipartFile promotionMainImage,
                                  @RequestParam(required = false) MultipartFile[] promotionImages,
                                  @RequestParam String seoURL,
                                  @RequestParam String seoTitle,
                                  @RequestParam String seoKeyWords,
                                  @RequestParam String seoDescription) {
        promotionService.updatePromotion(promotionId, promotionName, promotionDescription, promotionVideoLink, promotionStatus, promotionPublishDate,
                promotionMainImage, promotionImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/promotion";
    }

    @PostMapping("/save")
    public String savePromotion(@RequestParam String promotionName,
                                @RequestParam String promotionDescription,
                                @RequestParam String promotionVideoLink,
                                @RequestParam Boolean promotionStatus,
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate promotionPublishDate,
                                @RequestParam(required = false) MultipartFile promotionMainImage,
                                @RequestParam(required = false) MultipartFile[] promotionImages,
                                @RequestParam String seoURL,
                                @RequestParam String seoTitle,
                                @RequestParam String seoKeyWords,
                                @RequestParam String seoDescription) {

        promotionService.createPromotion(promotionName, promotionDescription, promotionVideoLink, promotionStatus, promotionPublishDate,
                promotionMainImage, promotionImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/promotion";
    }
}
