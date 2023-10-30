package my.adhd.MyADHD.api.controllers;

import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.api.services.GameService;
import my.adhd.MyADHD.models.Child;
import my.adhd.MyADHD.models.GameResults;
import my.adhd.MyADHD.models.dtos.ChildDTOGame;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/game")
public class GameController {
    @Autowired
    private ModelMapper modelMapper;
    private final GameService gameService;

    //Find child by Code for the Android GAME
    @GetMapping("/{childCode}")
    public ResponseEntity<ChildDTOGame> getChildByCode(@PathVariable String childCode) {
        Child child = gameService.findByCode(childCode);
        ChildDTOGame childDTO = modelMapper.map(child, ChildDTOGame.class);
        return ResponseEntity.ok(childDTO);
    }
    @PostMapping("/save/{childId}")
    public ResponseEntity<GameResults> saveGame(@RequestBody GameResults gameResults, @PathVariable Integer childId) {
        return ResponseEntity.ok(gameService.save(gameResults, childId));

    }
}