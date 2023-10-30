package my.adhd.MyADHD.api.services;

import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.api.repositories.ChildRepository;
import my.adhd.MyADHD.api.repositories.GameRepository;
import my.adhd.MyADHD.models.Child;
import my.adhd.MyADHD.models.GameResults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class GameService {

    private final ChildRepository childRepository;
    private final GameRepository gameRepository;
    //Find child by code for the Game
    public Child findByCode(String childCode){
        return  childRepository.findChildByCode(childCode).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public GameResults save(GameResults gameResults, Integer childId){
        Child child = childRepository.findById(childId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        gameResults.setChildId(child);
        return gameRepository.save(gameResults);
    }
}
