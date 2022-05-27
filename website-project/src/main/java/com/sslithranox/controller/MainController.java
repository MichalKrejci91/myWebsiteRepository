package com.sslithranox.controller;

import com.sslithranox.entity.Artwork;
import com.sslithranox.service.ArtworkService;
import com.sslithranox.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ArtworkService service;

    @RequestMapping("/")
    public String viewMainPage(Model model){
        List<Artwork> listArtwork = service.listAll();
        model.addAttribute("listArtwork", listArtwork);

        return "index";
    }

    @RequestMapping("/new")
    public String showNewArtworkPage(Model model){
        Artwork artwork = new Artwork();
        model.addAttribute("artwork", artwork);

        return "add_artwork";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveArtwork(@ModelAttribute("artwork") Artwork artwork, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        artwork.setPhotos(fileName);

        service.save(artwork);

        String uploadDir = "artwork-photos/" + artwork.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        System.out.println(fileName);


        return "redirect:/";
    }

    /* This doesn't work with Multipart file yet. Next step - edit Artwork, keep / change image
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditArtworkPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_artwork");
        Artwork artwork = service.get(id);
        mav.addObject("artwork", artwork);

        return mav;
    }*/

    @RequestMapping("/delete/{id}")
    public String deleteArtwork(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/view/{id}")
    public ModelAndView showSingleProductPage(@PathVariable(name = "id") int id){
        ModelAndView mav = new ModelAndView("view_artwork");
        Artwork artwork= service.get(id);
        mav.addObject("artwork", artwork);

        return mav;
    }

    //static pages
    @RequestMapping("/about_me")
    public String viewAboutMePage(){
        return "about_me";
    }

    @RequestMapping("/portfolio")
    public String viewPortfolioPage(){
        return "portfolio";
    }

    @RequestMapping("/playground")
    public String viewPlaygroundPage(){
        return "playground";
    }
}
