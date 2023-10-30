package my.adhd.MyADHD.api.controllers;


import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.api.services.TestsService;
import my.adhd.MyADHD.models.Tests;
import my.adhd.MyADHD.models.dtos.TestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/tests")
public class TestsController {

    @Autowired
    ModelMapper modelMapper;
    private final TestsService testsService;

    @GetMapping("/{childId}")
    public ResponseEntity<List<TestDTO>> getTestsByChildID(@PathVariable Integer childId){
        List<Tests> testsList = testsService.getTestsByChildId(childId);
        List<TestDTO> response = new ArrayList<>();
        for (Tests t: testsList) {
            TestDTO testDto = modelMapper.map(t, TestDTO.class);
            response.add(testDto);
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{childId}/{childCode}")
    public ResponseEntity<TestDTO> saveDiagnostic(@RequestBody List<Integer> answerList,
                                                  @PathVariable Integer childId, @PathVariable String childCode)
    {
        Tests test = testsService.diagnostic(answerList, childId, childCode);
        TestDTO testDTO = modelMapper.map(test, TestDTO.class);
        return ResponseEntity.ok(testDTO);
    }

}
