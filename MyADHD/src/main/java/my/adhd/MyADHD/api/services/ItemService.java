package my.adhd.MyADHD.api.services;

import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.api.repositories.ItemRepository;
import my.adhd.MyADHD.models.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll(){
        List<Item> itemList =  itemRepository.findAll();

        return itemList;
    }
    public void createItemsDB() throws IOException, ParseException {

        JSONParser parser  = new JSONParser();
        try {
            //JSONArray a = (JSONArray) parser.parse(new FileReader("src/main/resources/Items.json"));
            JSONArray a = (JSONArray) parser.parse(new InputStreamReader(ClassLoader.getSystemResourceAsStream("Items.json")));
            for (Object o : a)
            {
                JSONObject data = (JSONObject) o;
                Item item = new Item();
                item.setDescription((String) data.get("description"));
                item.setName((String) data.get("name"));
                itemRepository.save(item);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
