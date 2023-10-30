package my.adhd.MyADHD.api.services;


import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.models.Question;
import my.adhd.MyADHD.api.repositories.QuestionRepository;
import my.adhd.MyADHD.models.dtos.QuestionDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionService {

    @Autowired
    ModelMapper modelMapper;
    private final QuestionRepository questionRepository;

    public List<Question> findAllPaged(Integer first, Integer last){return questionRepository.findAll(PageRequest.of(first,last)).toList();}

    public List<QuestionDTO> findAll(){
        List<Question> questionsList =  questionRepository.findAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question q: questionsList){
            QuestionDTO questionDTO = modelMapper.map(q, QuestionDTO.class);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }


    public Question getQuestionById(Integer id){
        return questionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public void createQuestionsDB() throws IOException, ParseException {

        JSONParser parser  = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("src/main/resources/Questions.json"));
            for (Object o : a)
            {
                JSONObject data = (JSONObject) o;
                Question question = new Question();
                question.setNumber((Long) data.get("number"));
                question.setText((String) data.get("text"));
                question.setQTypeId((String) data.get("qtype"));
                questionRepository.save(question);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

    }


}
