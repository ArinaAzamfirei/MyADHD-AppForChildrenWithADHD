package my.adhd.MyADHD.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adhd.MyADHD.api.services.ItemService;
import my.adhd.MyADHD.api.services.QuestionService;
import my.adhd.MyADHD.config.ConfigurationProprieties;
import my.adhd.MyADHD.models.Item;
import my.adhd.MyADHD.models.Question;
import my.adhd.MyADHD.models.dtos.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuestionDBHandler implements CommandLineRunner {

    private final QuestionService questionService;
    private final ItemService itemService;

    public void run(String... args) throws Exception {

        List<QuestionDTO> questionsList = questionService.findAll();
        if(questionsList.isEmpty()){
            questionService.createQuestionsDB();
        }

        List<Item> itemList = itemService.findAll();
        if(itemList.isEmpty()){
            itemService.createItemsDB();
        }
    }
}
