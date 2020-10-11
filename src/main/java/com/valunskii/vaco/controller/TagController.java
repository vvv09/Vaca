package com.valunskii.vaco.controller;

import com.valunskii.vaco.domain.Color;
import com.valunskii.vaco.domain.Tag;
import com.valunskii.vaco.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/tags")
public class TagController {

    private TagService tagService;

    @Autowired
    public void setClassroomRepo(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public String retrieveAll(Model model) {
        List<Tag> tags = tagService.getAll();
        model.addAttribute("tags", tags);
        model.addAttribute("amount", tagService.count());
        model.addAttribute("route", "words");
        return "tag-list";
    }

    @GetMapping("/new")
    public String getNew(Model model) {
        model.addAttribute("tag", new Tag());
        model.addAttribute("colors", Color.values());
        model.addAttribute("update", false);
        model.addAttribute("route", "words");
        return "tag-form";
    }

    @PostMapping
    public String create(@Valid Tag tag, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ControllerUtils.getValidationErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("tag", tag);
            model.addAttribute("colors", Color.values());
            model.addAttribute("update", false);
            model.addAttribute("route", "words");
            return "tag-form";
        } else {
            tagService.create(tag);
            return "redirect:/tags";
        }
    }

    @PostMapping("{id}")
    public String update(@ModelAttribute("tag") Tag tag,
                         @PathVariable("id") Long id,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ControllerUtils.getValidationErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("tag", tag);
            model.addAttribute("update", true);
            model.addAttribute("colors", Color.values());
            model.addAttribute("route", "words");
            return "tag-form";
        } else {
            tagService.update(tag, id);
            model.addAttribute("route", "words");
            return "redirect:/tags";
        }
    }

    @GetMapping("/{tag}")
    public String getOne(@PathVariable Tag tag, Model model) {
        model.addAttribute("tag", tag);
        model.addAttribute("colors", Color.values());
        model.addAttribute("update", true);
        model.addAttribute("route", "words");
        return "tag-form";
    }

    @GetMapping("/delete/{tag}")
    public String delete(@PathVariable Tag tag, Model model) {
        tagService.delete(tag);
        model.addAttribute("route", "words");
        return "redirect:/tags";
    }
}
