package springmongodbdatarest.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springmongodbdatarest.entity.Feed;
import springmongodbdatarest.service.DataService;
import springmongodbdatarest.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/verifyDataApp")
public class DataController {

    @Autowired
    DataService dataService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);


    /*@RequestMapping(value = "/passData", method = RequestMethod.POST)
    public String process(HttpServletRequest request,
            @RequestHeader(value = "email", required = true) String email,
             @RequestBody Product product){
        System.out.println("***********************************************************");
        System.out.println(product.toString() + ":::" + email);
        Product productVal = dataService.createProduct(product);
        System.out.println(productVal.toString());
        return "success";
    }*/

    @RequestMapping(value = "/saveFeed", method = RequestMethod.POST)
    public String saveFeed(HttpServletRequest request,
                           @RequestBody Feed feed) {
        dataService.createNewFeed(feed);
        return "success";
    }

    @RequestMapping(value = "/getFeedFromPublishDate", method = RequestMethod.GET)
    public List<Feed> getFeedFromPublishDate(HttpServletRequest request,
                                             @RequestParam(value = "fromDate", required = true) String fromDate,
                                             @RequestParam(value = "toDate", required = true) String toDate
    ) throws ParseException {
        return dataService.getFeedBetweenPublishDates(fromDate, toDate);

    }


    @RequestMapping(value = "/getFeedBySource", method = RequestMethod.GET)
    public List<Feed> getFeedFromPublishDate(HttpServletRequest request,
                                             @RequestParam(value = "source", required = true) String source
    ) {
        return dataService.getFeedBySource(source);
    }


    @RequestMapping(value = "/getFeedFromCreationDate", method = RequestMethod.GET)
    public List<Feed> getFeedFromCreationDate(HttpServletRequest request,
                                              @RequestParam(value = "fromDate", required = true) String fromDate,
                                              @RequestParam(value = "toDate", required = true) String toDate) throws ParseException {
        return dataService.getFeedFromCreationDate(fromDate, toDate);

    }

    @RequestMapping(value = "/getFeedFromTags", method = RequestMethod.GET)
    public List<Feed> getFeedFromTags(HttpServletRequest request,
                                      @RequestParam(value = "tags", required = true) String tags) throws ParseException {
        List<String> list = new ArrayList<String>(Arrays.asList(tags.split(",")));
        return dataService.getFeedBytags(list);

    }

    @RequestMapping(value = "/getFeedFromTagsAndSource", method = RequestMethod.GET)
    public List<Feed> getFeedFromTagsAndSource(HttpServletRequest request,
                                               @RequestParam(value = "tags", required = true) String tags,
                                               @RequestParam(value = "source", required = true) String source) throws ParseException {
        List<String> list = new ArrayList<String>(Arrays.asList(tags.split(",")));
        return dataService.getFeedBytagsAndSource(list, source);

    }


}
