package com.valunskii.vaco.controller;

import com.valunskii.vaco.domain.Word;
import com.valunskii.vaco.service.TagService;
import com.valunskii.vaco.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/words")
public class WordController {

    private WordService wordService;
    private TagService tagService;

    public final String SUCCESS = "success";
    public final String WARN = "warning";
    public final String ERROR = "danger";

    public final String CREATE_SUCCESS = "New word has been successfully added to the dictionary!\n" +
            " Now you can <a href=\"/words\" class=\"alert-link\">return to words list</a>\n" +
            " or <a href=\"/words/new\" class=\"alert-link\">create another one word</a>.";

    public final String UPDATE_SUCCESS = "Word has been successfully updated!\n";

    @Autowired
    public WordController(WordService wordService, TagService tagService) {
        this.wordService = wordService;
        this.tagService = tagService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<Word> books = wordService.getAll();
        model.addAttribute("words", books);
        model.addAttribute("amount", books.size());
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("route", "words");
        return "word-list";
    }

    @GetMapping("/{word}")
    public String getOne(@RequestParam(name = "status", required = false) String status,
                         @PathVariable Word word,
                         Model model) {
        model.addAttribute("word", word);
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("update", true);
        addMessageIfExists(status, model);
        model.addAttribute("route", "words");
        return "word-form";
    }

    @GetMapping("/new")
    public String getNew(Model model) {
        model.addAttribute("word", new Word());
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("update", false);
        model.addAttribute("success", false);
        model.addAttribute("route", "words");
        return "word-form";
    }

    @PostMapping
    public String create(@RequestParam Map<String, String> form,
                         @Valid Word word,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ControllerUtils.getValidationErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("word", word);
            model.addAttribute("tags", tagService.getAll());
            model.addAttribute("update", false);
            model.addAttribute("route", "words");
            return "word-form";
        } else {
            refreshWordTagBinding(form, word);
            wordService.create(word);
            return "redirect:/words/" + word.getId() + "?status=created";
        }
    }

    @PostMapping("{id}")
    public String update(@RequestParam Map<String, String> form,
                         @Valid Word word,
                         @PathVariable("id") Long id,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ControllerUtils.getValidationErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("word", word);
            model.addAttribute("tags", tagService.getAll());
            model.addAttribute("update", true);
            model.addAttribute("route", "words");
            return "word-form";
        } else {
            refreshWordTagBinding(form, word);
            wordService.update(word, id);
            return "redirect:/words/" + word.getId()+ "?status=updated";
        }
    }

    @GetMapping("/delete/{word}")
    public String delete(@PathVariable Word word, Model model) {
        wordService.delete(word);
        model.addAttribute("route", "words");
        return "redirect:/words";
    }

    private void refreshWordTagBinding(@RequestParam Map<String, String> form, @Valid Word word) {
        Set<String> allTagsNames = tagService.getAll().stream().map(tag -> tag.getName()).collect(Collectors.toSet());
        word.setTags(new HashSet<>());
        for (String key : form.keySet()) {
            if (allTagsNames.contains(key)) {
                word.getTags().add(tagService.getByName(key));
            }
        }
    }

    private void addMessageIfExists(String status, Model model) {
        if ("created".equals(status)) {
            model.addAttribute("message", CREATE_SUCCESS);
            model.addAttribute("messageType", SUCCESS);
        }
        if ("updated".equals(status)) {
            model.addAttribute("message", UPDATE_SUCCESS);
            model.addAttribute("messageType", SUCCESS);
        }
    }
}
