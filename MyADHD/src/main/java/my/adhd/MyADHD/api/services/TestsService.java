package my.adhd.MyADHD.api.services;


import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.config.ConfigurationProprieties;
import my.adhd.MyADHD.api.repositories.ChildRepository;
import my.adhd.MyADHD.api.repositories.TestsRepository;
import my.adhd.MyADHD.models.Child;
import my.adhd.MyADHD.models.Tests;
import my.adhd.MyADHD.models.dtos.QuestionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestsService {

    private final TestsRepository testsRepository;

    private final ChildRepository childRepository;

    private final QuestionService questionService;

    private final ConfigurationProprieties configurationProperties;

    @Autowired
    ModelMapper modelMapper;

    public List<Tests> getTestsByChildId(Integer childId){
        Child child  = childRepository.findById(childId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return testsRepository.findAllByChildId(child);
    }
    public void deleteTestByChildId(Integer childId){
        List<Tests> testsList = getTestsByChildId(childId);
        testsRepository.deleteAll(testsList);
    }
    public Tests diagnostic(List<Integer> answersList, Integer childId, String childCode){
        Tests test = new Tests();
        Child child = childRepository.findById(childId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<QuestionDTO> questionList = questionService.findAll();

        List<Integer> score = new ArrayList<>();
        Integer currentQ = 0;
        for(QuestionDTO q : questionList){
           score.add(Integer.parseInt(String.valueOf(q.getQTypeId().charAt(answersList.get(currentQ)))));
           if(currentQ < 107){
               currentQ ++;
           }

        }

        int A=0;
        int B=0;

        boolean invalid = false;
        // Calculam scorurile pentru determinarea indicelui de inconsistenta
        A += diff(score.get(43), score.get(66)) + diff(score.get(11), score.get(22)) + diff(score.get(35), score.get(59))
        + diff(score.get(13), score.get(80))+ diff(score.get(18), score.get(97))+ diff(score.get(44), score.get(98))
        + diff(score.get(93), score.get(101))+ diff(score.get(74), score.get(78))+ diff(score.get(12), score.get(91))
        + diff(score.get(38), score.get(83));

        B += addB(diff(score.get(43), score.get(66))) + addB(diff(score.get(11), score.get(22))) + addB(diff(score.get(35), score.get(59)))
        + addB(diff(score.get(13), score.get(80))) + addB(diff(score.get(18), score.get(97))) + addB(diff(score.get(44), score.get(98)))
        + addB(diff(score.get(93), score.get(101))) + addB(diff(score.get(74), score.get(78))) + addB(diff(score.get(12), score.get(91)))
        + addB(diff(score.get(38), score.get(83)));


        test.setDiagnostic("Sansele de a avea ADHD sunt foarte mici! ");
        // Daca se indeplineste conditia inseamna ca testul este invalid si trebuie refacut
        if(A >= 7 && B >= 2){
            invalid = true;
        }

        // Daca se indeplineste conditia inseamna ca testul este invalid si trebuie refacut

        if(invalid){
            test.setDiagnostic("Test invalid! Trebuie să refaceți testul. Încercați să răspundeți cât mai corect posibil!");
        }
        else{
            // Calculam scorurile pentru tipurile de ADHD posibile
            int scoreN = 0;
            int scoreHI = 0;
            scoreN = calculateScoreN(score);
            if(adhdNScore(score.get(67)) && adhdNScore(score.get(78))){
                scoreN ++;
            }

            scoreHI = calculateScoreHI(score);
            if(adhdHIScore(score.get(68)) || adhdHIScore(score.get(98))){
                scoreHI++;
            }

            if(adhdHIScore(score.get(53)) || adhdHIScore(score.get(44))){
                scoreHI++;
            }

            if(scoreN >= 6 && scoreHI >=6){
                test.setDiagnostic("Cel mai probabil copilul are ADHD de tip combinat.Se recomandă o evaluare ulterioară!");
            }
            else if(scoreN >=6){
                test.setDiagnostic("Cel mai probabil copilul are ADHD de tip Neatent. Se recomandă o evaluare ulterioară!");
            }
            else if(scoreHI >= 6){
                test.setDiagnostic("Cel mai probabil copilul are ADHD de tip Hiperactiv-Impulsiv. Se recomandă o evaluare ulterioară!");
            }

            // Calculam probabilitatea (veridicitatea) clasificarilor ADHD
            int sumProb = 0;
            sumProb += checkProb1(score.get(18)) + checkProb2(score.get(34)) + checkProb1(score.get(46))
                    + checkProb1(score.get(66)) + checkProb1(score.get(83)) + checkProb2(score.get(87))
                    + checkProb2(score.get(97)) + checkProb1(score.get(100)) + checkProb1(score.get(103));

            test.setPercentage(configurationProperties.getPercentage().get(sumProb));

        }

        test.setChildId(child);
        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        LocalTime localTime = LocalTime.of(timeNow.getHour(), timeNow.getMinute());
        test.setTimestamp(dateNow + " " + localTime);
        test.setChildCode(childCode);
        return testsRepository.save(test);
    }

    Integer diff(Integer a, Integer b){
        if(a > b)
            return a-b;
        else
            return b-a;
    }

    Integer addB(Integer diff){
        if(diff == 2 || diff ==3)
            return 1;
        return 0;
    }

    boolean adhdNScore(Integer asw){
        if(asw == 2 || asw == 3){
            return true;
        }
        return false;
    }

    boolean adhdHIScore(Integer asw){
        if(asw == 1 || asw == 2 || asw == 3){
            return true;
        }
        return false;
    }

    Integer calculateScoreN(List<Integer> score){
        List<Integer> index = List.of(46, 94, 34, 83, 27, 96, 100, 1);
        Integer scoreN = 0;
        for(int i = 0 ; i< index.size(); i++){
            if(adhdNScore(score.get(index.get(i)))){
                scoreN ++;
            }
        }
        return scoreN;
    }

    Integer calculateScoreHI(List<Integer> score){
        List<Integer> index = List.of(97, 92, 2, 70, 42, 60, 103);
        Integer scoreHI = 0;
        for(int i = 0 ; i< index.size(); i++){
            if(adhdHIScore(score.get(index.get(i)))){
                scoreHI ++;
            }
        }
        return scoreHI;
    }

    Integer checkProb1(Integer a){
        Integer val = 0;
        if(a == 0 || a == 1){
            val += 0;
        }
        else if(a == 2){
            val +=  1;
        }
        else if(a == 3){
            val +=  2;
        }
        return val;
    }

    Integer checkProb2(Integer a){
        Integer val = 0;
        if(a == 0 || a == 1){
            val += 0;
        }
        else if(a == 2 || a == 3){
            val +=  2;
        }
        return val;
    }
}

