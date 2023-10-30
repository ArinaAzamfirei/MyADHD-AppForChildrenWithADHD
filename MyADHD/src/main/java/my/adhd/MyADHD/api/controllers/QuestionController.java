package my.adhd.MyADHD.api.controllers;


import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.models.Question;
import my.adhd.MyADHD.api.services.QuestionService;
import my.adhd.MyADHD.models.dtos.QuestionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/questions")
public class QuestionController {

    @Autowired
    private ModelMapper modelMapper;
    public final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getQuestions(@RequestParam String first, @RequestParam String last){
        List<Question> questionsList = questionService.findAllPaged(Integer.parseInt(first),Integer.parseInt(last));
        List<QuestionDTO> response = new ArrayList<>();
        for (Question q: questionsList) {
            QuestionDTO questionDto = modelMapper.map(q, QuestionDTO.class);
            response.add(questionDto);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id){
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

}
