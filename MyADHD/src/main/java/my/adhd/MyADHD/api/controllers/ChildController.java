package my.adhd.MyADHD.api.controllers;

import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.models.Child;
import my.adhd.MyADHD.api.services.ChildService;
import my.adhd.MyADHD.models.dtos.ChildDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/children")
public class ChildController {


    @Autowired
    private ModelMapper modelMapper;
    private final ChildService childService;


    @GetMapping
    public ResponseEntity<List<Child>> getChildren(){return ResponseEntity.ok(childService.findAll());}



    @GetMapping("/{userId}")
    public ResponseEntity<List<ChildDTO>> getChildByUserID(@PathVariable Integer userId){
        List<Child> childList = childService.getChildByUserId(userId);
        List<ChildDTO> response = new ArrayList<>();
        for (Child c:childList) {
           ChildDTO childDto = modelMapper.map(c, ChildDTO.class);
           response.add(childDto);
        }

        return ResponseEntity.ok(response);
    }
    @PostMapping("/{userId}")
    public ResponseEntity<Child> createChild(@RequestBody Child child, @PathVariable Integer userId)
    {
        return ResponseEntity.ok(childService.create(child, userId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Child> updateChild(@PathVariable Integer id, @RequestBody Child child){
        return ResponseEntity.ok(childService.update(id, child));
    }

    @DeleteMapping("/{id}")
    public void deleteChild(@PathVariable Integer id) {
        childService.delete(id);
    }

}
